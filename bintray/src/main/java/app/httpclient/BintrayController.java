package app.httpclient;

import java.util.List;

import io.micronaut.http.annotation.Controller;
import io.micronaut.http.annotation.Get;
import io.reactivex.Flowable;
import io.reactivex.Maybe;

@Controller("/bintray")
public class BintrayController {

	private final BintrayLowLevelClient bintrayLowLevelClient;

	private final BintrayClient bintrayClient;

	public BintrayController(BintrayLowLevelClient bintrayLowLevelClient, BintrayClient bintrayClient) {
		this.bintrayLowLevelClient = bintrayLowLevelClient;
		this.bintrayClient = bintrayClient;
	}

	@Get("/packages-lowlevel")
	public Maybe<List<BintrayPackage>> lowLevel() {
		return bintrayLowLevelClient.fetchPackage();
	}

	@Get("/packages")
	public Flowable<BintrayPackage> highLevel() {
		return bintrayClient.fetchPackage();
	}
}
