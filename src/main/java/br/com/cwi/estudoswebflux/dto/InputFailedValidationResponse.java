package br.com.cwi.estudoswebflux.dto;

import lombok.Data;

@Data
public class InputFailedValidationResponse {

    private int errorCode;
    private int input;
    private String message;
}
