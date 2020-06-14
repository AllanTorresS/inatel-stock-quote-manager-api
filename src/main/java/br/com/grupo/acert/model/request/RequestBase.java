package br.com.grupo.acert.model.request;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;

@Getter
@Setter
public class RequestBase {

    @NotNull
    private Double converterTemperatura;

}
