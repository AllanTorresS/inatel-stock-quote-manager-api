package br.com.grupo.inatel.model.error;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR)
public class AcertInternalServerErrorExeception extends RuntimeException {

    public AcertInternalServerErrorExeception(String mensagem) {
        super(mensagem);
    }
}
