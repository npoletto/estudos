package br.com.cwi.estudoswebflux.config;

import br.com.cwi.estudoswebflux.dto.InputFailedValidationResponse;
import br.com.cwi.estudoswebflux.dto.MultiplyRequest;
import br.com.cwi.estudoswebflux.dto.Response;
import br.com.cwi.estudoswebflux.exception.InputValidationException;
import br.com.cwi.estudoswebflux.service.ReactiveMathService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class RequestHandler {

    @Autowired
    private ReactiveMathService mathService;

    public Mono<ServerResponse> squareHandler(ServerRequest serverRequest) {
        int input = Integer.parseInt(serverRequest.pathVariable("input"));
        Mono<Response> responseMono = this.mathService.findSquare(input);
        return ServerResponse.ok().body(responseMono, Response.class);
    }

    public Mono<ServerResponse> tableHandler(ServerRequest serverRequest) {
        int input = Integer.parseInt(serverRequest.pathVariable("input"));
        Flux<Response> responseFlux = this.mathService.multiplicationTable(input);
        return ServerResponse.ok().body(responseFlux, Response.class);
    }

    public Mono<ServerResponse> tableStreamHandler(ServerRequest serverRequest) {
        int input = Integer.parseInt(serverRequest.pathVariable("input"));
        Flux<Response> responseFlux = this.mathService.multiplicationTable(input);
        return ServerResponse.ok()
                .contentType(MediaType.TEXT_EVENT_STREAM)
                .body(responseFlux, Response.class);
    }

    public Mono<ServerResponse> multiplyHandler(ServerRequest serverRequest) {
        Mono<MultiplyRequest> requestMono = serverRequest.bodyToMono(MultiplyRequest.class);
        Mono<Response> responseMono = this.mathService.multiply(requestMono);
        return ServerResponse.ok().body(responseMono, Response.class);
    }

    public Mono<ServerResponse> squareHandlerWithValidation(ServerRequest serverRequest) {
        int input = Integer.parseInt(serverRequest.pathVariable("input"));
        if(input<10 || input>20) {
            return Mono.error(new InputValidationException(input));
        }
        Mono<Response> responseMono = this.mathService.findSquare(input);
        return ServerResponse.ok().body(responseMono, Response.class);
    }
}
