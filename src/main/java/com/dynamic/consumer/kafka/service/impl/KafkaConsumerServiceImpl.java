package com.dynamic.consumer.kafka.service.impl;

import java.io.IOException;
import java.time.Duration;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.elasticsearch.action.bulk.BulkRequest;
import org.elasticsearch.action.index.IndexRequest;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.common.xcontent.XContentType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.dynamic.consumer.elasticsearch.ElasticSearch;
import com.dynamic.consumer.kafka.consumer.KafkaConsumerConfig;
import com.dynamic.consumer.kafka.service.KafkaConsumerService;
import com.google.gson.JsonParser;

@Component
public class KafkaConsumerServiceImpl implements KafkaConsumerService {

	static Logger logger = LoggerFactory.getLogger(KafkaConsumerServiceImpl.class.getName());

	@Autowired
	private KafkaConsumerConfig consumerConfig;

	@Autowired
	private ElasticSearch elasticSearch;

	private Boolean ative;

	public void activateConsumer() throws IOException {
		RestHighLevelClient client = elasticSearch.createClient();

		KafkaConsumer<String, String> consumer = consumerConfig.createConsumer();

		if (consumer != null && isAtive()) {
			while (true) {
				ConsumerRecords<String, String> records = consumer.poll(Duration.ofMillis(100));

				int recordCounts = records.count();
				logger.info("Received " + recordCounts + " records ");
				BulkRequest bulkRequest = new BulkRequest();

				for (ConsumerRecord<String, String> record : records) {

					try {
						logger.info(record.value());
						String id = extractIdFromTweet(record.value());

						IndexRequest indexRequest = new IndexRequest().index("twitter").type("tweets").id(id)
								.source(record.value(), XContentType.JSON);

						bulkRequest.add(indexRequest);
					} catch (NullPointerException e) {
						logger.warn("Skipping bad data :" + record.value());
					}

				}

				if (recordCounts > 0) {
					client.bulk(bulkRequest, RequestOptions.DEFAULT);
					logger.info("Commiting offsets ...");
					consumer.commitSync();
					logger.info("Offsets Commited!");
					try {
						Thread.sleep(100);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			}
		} else {
			consumer.close();
			client.close();

		}

	}

	private String extractIdFromTweet(String tweetJson) {
		JsonParser jsonParser = new JsonParser();
		return jsonParser.parse(tweetJson).getAsJsonObject().get("id_str").getAsString();
	}

	public Boolean isAtive() {
		return ative;
	}

	public void setAtive(Boolean ative) {
		this.ative = ative;
	}

}
