package br.com.cwi.estudoswebflux.dto;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.time.LocalDateTime;

@Data
@ToString
@NoArgsConstructor
public class Response {
    private LocalDateTime time;
    private int output;

    public Response(int output) {
        this.output = output;
        time = LocalDateTime.now();
    }
}
