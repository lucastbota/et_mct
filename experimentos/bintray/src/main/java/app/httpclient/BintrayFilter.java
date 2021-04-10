package app.httpclient;

import io.micronaut.context.annotation.Requires;
import io.micronaut.http.HttpResponse;
import io.micronaut.http.MutableHttpRequest;
import io.micronaut.http.annotation.Filter;
import io.micronaut.http.filter.ClientFilterChain;
import io.micronaut.http.filter.HttpClientFilter;
import org.reactivestreams.Publisher;
/*
@Filter("/api/${bintray.apiversion}/repos/**")
@Requires(property = BintrayConfiguration.PREFIX + ".username")
@Requires(property = BintrayConfiguration.PREFIX + ".token")
public class BintrayFilter implements HttpClientFilter {
    private final BintrayConfiguration configuration;

    public BintrayFilter(BintrayConfiguration configuration) {
        this.configuration = configuration;
    }

    @Override
    public Publisher<? extends HttpResponse<?>> doFilter(MutableHttpRequest<?> request, ClientFilterChain clientFilterChain) {
        return clientFilterChain.proceed(request.basicAuth(configuration.getUsername(), configuration.getToken()));
    }
}*/

public class BintrayFilter{}