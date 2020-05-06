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
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;


@Component
public class ElasticSearch {
	
	@Value("${elasticsearch.username.credential}")
	private String usernameCredential;
	
	@Value("${elasticsearch.password.credential}")
	private String passwordCredential;
	
	@Value("${elasticsearch.hostnameCloud}")
	private String hostnameCloud;

	public RestHighLevelClient createClient() {

		final CredentialsProvider credentialProvider = new BasicCredentialsProvider();
		credentialProvider.setCredentials(AuthScope.ANY, new UsernamePasswordCredentials(
				usernameCredential, passwordCredential));

		RestClientBuilder clientBuilder = RestClient.builder(new HttpHost(hostnameCloud, 443, "https"))
				.setHttpClientConfigCallback(new RestClientBuilder.HttpClientConfigCallback() {

					public HttpAsyncClientBuilder customizeHttpClient(HttpAsyncClientBuilder httpClientBuilder) {
						return httpClientBuilder.setDefaultCredentialsProvider(credentialProvider);
					}
				});

		RestHighLevelClient client = new RestHighLevelClient(clientBuilder);
		return client;
	}
}
