package br.com.grupo.acert.controller;

import br.com.grupo.acert.model.error.AcertErrorResponse;
import br.com.grupo.acert.model.error.AcertInternalServerErrorExeception;
import br.com.grupo.acert.model.error.AcertNotFoundExeception;
import br.com.grupo.acert.util.FormataData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.NoHandlerFoundException;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;
import java.util.Locale;
import java.util.stream.Collectors;

@ControllerAdvice
public class GlobalErrorHandler extends ResponseEntityExceptionHandler {

    @Autowired
    private MessageSource messageSource;

    private final String preFixo = "Parâmentro ";

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {

        List<String> errors = ex.getBindingResult()
                .getFieldErrors()
                .stream()
                .map(x -> this.preFixo + x.getField() + ", " + x.getDefaultMessage())
                .collect(Collectors.toList());

        AcertErrorResponse acertErrorResponse = this.montarErrorResponse(errors, status, "campos com erros");

        return new ResponseEntity(acertErrorResponse, headers, status);
    }

    @Override
    protected ResponseEntity<Object> handleHttpMessageNotReadable(HttpMessageNotReadableException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {

        List<String> erros = Arrays.asList(this.mensagemErroProperties("json.error", request));
        String debugMessage = ex.getLocalizedMessage();

        AcertErrorResponse acertErrorResponse = this.montarErrorResponse(erros, status, debugMessage);

        return new ResponseEntity(acertErrorResponse, headers, status);
    }

    @Override
    protected ResponseEntity<Object> handleHttpRequestMethodNotSupported(HttpRequestMethodNotSupportedException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {

        List<String> erros = Arrays.asList(this.mensagemErroProperties("end.point.parameters.unsupported", request));
        String debugMessage = ex.getLocalizedMessage();

        AcertErrorResponse acertErrorResponse = this.montarErrorResponse(erros, status, debugMessage);

        return new ResponseEntity(acertErrorResponse, headers, status);

    }

    @Override
    protected ResponseEntity<Object> handleNoHandlerFoundException(NoHandlerFoundException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
        List<String> erros = Arrays.asList(this.mensagemErroProperties("end.point.not.found", request));
        String debugMessage = ex.getLocalizedMessage();

        AcertErrorResponse acertErrorResponse = this.montarErrorResponse(erros, status, debugMessage);

        return new ResponseEntity(acertErrorResponse, headers, status);
    }

    @ExceptionHandler(AcertNotFoundExeception.class)
    public ResponseEntity<Object> handleAcertNotFoundException(AcertNotFoundExeception ex, WebRequest request) {

        List<String> erros = Arrays.asList(this.mensagemErroProperties("not.found.execption", request));
        String debugMessage = ex.getStackTrace().toString();

        AcertErrorResponse acertErrorResponse = this.montarErrorResponse(erros, HttpStatus.NOT_FOUND, debugMessage);

        return new ResponseEntity(acertErrorResponse, HttpStatus.NOT_FOUND);
    }


    @ExceptionHandler(AcertInternalServerErrorExeception.class)
    public ResponseEntity<Object> handleAcertInternalServerErrorExeception(AcertInternalServerErrorExeception ex, WebRequest request) {

        List<String> erros = Arrays.asList(this.mensagemErroProperties("internal.server.error.execption", request));
        String debugMessage = ex.getStackTrace().toString();

        AcertErrorResponse acertErrorResponse = this.montarErrorResponse(erros, HttpStatus.NOT_FOUND, debugMessage);

        return new ResponseEntity(acertErrorResponse, HttpStatus.NOT_FOUND);
    }

    private AcertErrorResponse montarErrorResponse(List<String> errors, HttpStatus status, String debugMessage) {
        String pattern = "dd/MM/yyyy HH:mm:ss";
        LocalDateTime hoje = LocalDateTime.now();
        String dataFormatada = FormataData.formatarData(pattern, hoje);

        AcertErrorResponse acertErrorResponse = new AcertErrorResponse();

        acertErrorResponse.setHttpStatusCodigo(status.value());
        acertErrorResponse.setHttpStatusCodigoDescricao(status.toString());
        acertErrorResponse.setErrorDiaHora(dataFormatada);
        acertErrorResponse.setListaDeErrors(errors);
        acertErrorResponse.setDebugMessage(debugMessage);

        return acertErrorResponse;
    }

    private String mensagemErroProperties(String mensagenChave, WebRequest request) {
        Locale locale = request.getLocale();
        return this.messageSource.getMessage(mensagenChave, null, locale);
    }
}
