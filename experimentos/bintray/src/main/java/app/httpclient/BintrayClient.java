package app.httpclient;

import io.micronaut.http.annotation.Get;
import io.micronaut.http.client.annotation.Client;
import io.reactivex.Flowable;

@Client(BintrayConfiguration.BINTRAY_API_URL)
public interface BintrayClient {
	@Get("/api/${bintray.apiversion}/repos/${bintray.organization}/${bintray.repository}/packages")
	Flowable<BintrayPackage> fetchPackage();
}
