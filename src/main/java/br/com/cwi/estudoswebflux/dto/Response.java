package br.com.cwi.estudoswebflux.dto;

import lombok.Data;
import java.time.LocalDateTime;

@Data
public class Response {
    private LocalDateTime time;
    private int output;

    public Response(int output) {
        this.output = output;
        time = LocalDateTime.now();
    }
}
