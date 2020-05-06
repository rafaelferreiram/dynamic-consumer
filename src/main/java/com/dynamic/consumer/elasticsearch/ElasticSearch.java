package com.dynamic.consumer.elasticsearch;

import org.apache.http.HttpHost;
import org.apache.http.auth.AuthScope;
import org.apache.http.auth.UsernamePasswordCredentials;
import org.apache.http.client.CredentialsProvider;
import org.apache.http.impl.client.BasicCredentialsProvider;
import org.apache.http.impl.nio.client.HttpAsyncClientBuilder;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestClientBuilder;
import org.elasticsearch.client.RestHighLevelClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class ElasticSearch {

	static Logger logger = LoggerFactory.getLogger(ElasticSearch.class.getName());

	@Value("${elasticsearch.username.credential}")
	private String usernameCredential;

	@Value("${elasticsearch.password.credential}")
	private String passwordCredential;

	@Value("${elasticsearch.hostnameCloud}")
	private String hostnameCloud;

	public RestHighLevelClient createClient() {
		RestHighLevelClient client = null;
		try {
			final CredentialsProvider credentialProvider = new BasicCredentialsProvider();
			credentialProvider.setCredentials(AuthScope.ANY,
					new UsernamePasswordCredentials(usernameCredential, passwordCredential));

			RestClientBuilder clientBuilder = RestClient.builder(new HttpHost(hostnameCloud, 443, "https"))
					.setHttpClientConfigCallback(new RestClientBuilder.HttpClientConfigCallback() {

						public HttpAsyncClientBuilder customizeHttpClient(HttpAsyncClientBuilder httpClientBuilder) {
							return httpClientBuilder.setDefaultCredentialsProvider(credentialProvider);
						}
					});

			client = new RestHighLevelClient(clientBuilder);
			logger.info("ElasticSearch client created");
		} catch (Exception e) {
			logger.error("Error creating ElasticSearch client.", e.getMessage());
		}

		return client;
	}
}
