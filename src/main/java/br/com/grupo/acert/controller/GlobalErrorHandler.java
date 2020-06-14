package br.com.grupo.acert.controller;

import br.com.grupo.acert.model.error.AcertErrorResponse;
import br.com.grupo.acert.util.FormataData;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@ControllerAdvice
public class GlobalErrorHandler extends ResponseEntityExceptionHandler {

    private final String preFixo = "Parâmentro ";

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {

        List<String> errors = ex.getBindingResult()
                .getFieldErrors()
                .stream()
                .map(x -> this.preFixo + x.getField() + ", " + x.getDefaultMessage())
                .collect(Collectors.toList());

        AcertErrorResponse acertErrorResponse = this.montarErrorResponse(errors, status,"campos com erros");

        return new ResponseEntity(acertErrorResponse, headers, status);
    }

    @Override
    protected ResponseEntity<Object> handleHttpMessageNotReadable(HttpMessageNotReadableException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {

        List<String> erros = Arrays.asList("Erro durante conversão do json");
        String debugMessage = ex.getLocalizedMessage();

        AcertErrorResponse acertErrorResponse = this.montarErrorResponse(erros,status,debugMessage);

        return new ResponseEntity(acertErrorResponse, headers, status);
    }

    private AcertErrorResponse montarErrorResponse(List<String> errors, HttpStatus status, String debugMessage) {
        String pattern = "dd/MM/yyyy HH:mm:ss";
        LocalDateTime hoje = LocalDateTime.now();

        String dataFormatada = FormataData.formatarData(pattern,hoje);

        AcertErrorResponse acertErrorResponse = new AcertErrorResponse();

        acertErrorResponse.setHttpStatusCodigo(status.value());
        acertErrorResponse.setHttpStatusCodigoDescricao(status.toString());
        acertErrorResponse.setErrorDiaHora(dataFormatada);
        acertErrorResponse.setListaDeErrors(errors);
        acertErrorResponse.setDebugMessage(debugMessage);

        return acertErrorResponse;
    }
}
