package app.httpclient;

import java.net.URI;
import java.util.List;

import javax.inject.Singleton;

import io.micronaut.core.type.Argument;
import io.micronaut.http.HttpRequest;
import io.micronaut.http.client.RxHttpClient;
import io.micronaut.http.client.annotation.Client;
import io.micronaut.http.uri.UriBuilder;
import io.reactivex.Flowable;
import io.reactivex.Maybe;

@Singleton
public class BintrayLowLevelClient {
	private final RxHttpClient httpClient;
	private final URI uri;

	public BintrayLowLevelClient(@Client(BintrayConfiguration.BINTRAY_API_URL) RxHttpClient httpClient,
			BintrayConfiguration configuration) {
		this.httpClient = httpClient;
		this.uri = UriBuilder.of("/api").path(configuration.getApiversion()).path("repos")
				.path(configuration.getOrganization()).path(configuration.getRepository()).path("packages").build();

	}

	Maybe<List<BintrayPackage>> fetchPackage() {
		HttpRequest<?> req = HttpRequest.GET(uri);
		Flowable flowable = httpClient.retrieve(req, Argument.listOf(BintrayPackage.class));
		return (Maybe<List<BintrayPackage>>) flowable.firstElement();
	}
}
