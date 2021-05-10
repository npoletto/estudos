package br.com.cwi.estudoswebflux.config;

import org.jetbrains.annotations.NotNull;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.client.*;
import reactor.core.publisher.Mono;

@Configuration
public class WebClientConfig {

    @Bean
    public WebClient webClient() {
        return WebClient.builder()
                .baseUrl("http://localhost:8080")
                .filter(this::sessionToken)
                .build();
    }

    /*
    private Mono<ClientResponse> sessionToken(ClientRequest request, ExchangeFunction ef) {
        System.out.println("generating session token");
        ClientRequest clientRequest = ClientRequest.from(request).headers( h-> h.setBearerAuth("some-lengthy-jwt"))
                .build();
        return ef.exchange(clientRequest);
    }
    */
    private @NotNull Mono<ClientResponse> sessionToken(ClientRequest request, ExchangeFunction ef) {
        ClientRequest clientRequest = request.attribute("auth")
                .map( v-> v.equals("basic") ? withBasicAuth(request) : withOAuth(request))
                .orElse(request);
        return ef.exchange(clientRequest);
    }

    private ClientRequest withBasicAuth(ClientRequest request) {
        return ClientRequest.from(request)
                .headers(h -> h.setBasicAuth("username", "password"))
                .build();
    }

    private ClientRequest withOAuth(ClientRequest request) {
        return ClientRequest.from(request)
                .headers(h -> h.setBearerAuth("some-token"))
                .build();
    }
}
