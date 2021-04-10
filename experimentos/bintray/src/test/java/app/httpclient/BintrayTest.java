package app.httpclient;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Arrays;
import java.util.List;
import java.util.stream.StreamSupport;

import javax.inject.Inject;

import org.junit.jupiter.api.Test;

import io.micronaut.core.type.Argument;
import io.micronaut.http.HttpRequest;
import io.micronaut.http.HttpResponse;
import io.micronaut.http.HttpStatus;
import io.micronaut.http.client.RxStreamingHttpClient;
import io.micronaut.http.client.annotation.Client;
import io.micronaut.test.extensions.junit5.annotation.MicronautTest;
import io.reactivex.Flowable;

@MicronautTest
public class BintrayTest {

	private static final List<String> PROFILES = Arrays.asList("base", "federation", "function", "function-aws",
			"service");
	@Inject
	@Client("/")
	private RxStreamingHttpClient client;

	@Test
	void lowLevelApiTest() {
		// when
		HttpRequest request = HttpRequest.GET("/bintray/packages-lowlevel");
		HttpResponse<List<BintrayPackage>> response = client.toBlocking().exchange(request,
				Argument.listOf(BintrayPackage.class));

		assertEquals(HttpStatus.OK, response.getStatus());
		assertNotNull(response.body());

		List<BintrayPackage> packages = response.body();

		assertTrue(packages.stream().map(BintrayPackage::getName).anyMatch(PROFILES::contains));
	}

	@Test
	void highLevelApiTest() {
		HttpRequest request = HttpRequest.GET("/bintray/packages");
		Flowable<BintrayPackage> packagesStream = client.jsonStream(request, BintrayPackage.class);
		Iterable<BintrayPackage> packages = packagesStream.blockingIterable();
		
		 assertTrue(StreamSupport.stream(packages.spliterator(), false)
                 .map(BintrayPackage::getName)
                 .anyMatch(PROFILES::contains));
	}
}
