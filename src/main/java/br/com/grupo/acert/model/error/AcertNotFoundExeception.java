package br.com.grupo.acert.model.error;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class AcertNotFoundExeception extends RuntimeException {

    public AcertNotFoundExeception(String mensagem) {
        super(mensagem);
    }
}
