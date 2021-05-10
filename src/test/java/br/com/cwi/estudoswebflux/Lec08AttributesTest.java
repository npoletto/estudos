package br.com.cwi.estudoswebflux;

import br.com.cwi.estudoswebflux.dto.MultiplyRequest;
import br.com.cwi.estudoswebflux.dto.Response;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

public class Lec08AttributesTest extends BaseTest {

    @Autowired
    private WebClient webClient;

    @Test
    public void headersTest() {
        Mono<Response> responseMono = this.webClient
                .post()
                .uri("reactive-math/multiply")
                .bodyValue(buildRequest( 5, 2))
                .attribute("auth", "oath")
                .retrieve()
                .bodyToMono(Response.class)
                .doOnNext(System.out::println);

        StepVerifier.create(responseMono)
                .expectNextCount(1)
                .verifyComplete();

    }

    private MultiplyRequest buildRequest(int a, int b) {
        MultiplyRequest multiplyRequest = new MultiplyRequest();
        multiplyRequest.setFirst(a);
        multiplyRequest.setSecond(b);
        return multiplyRequest;
    }
}
