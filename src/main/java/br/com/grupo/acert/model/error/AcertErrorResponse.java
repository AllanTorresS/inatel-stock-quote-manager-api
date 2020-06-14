package br.com.grupo.acert.model.error;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class AcertErrorResponse {
    private String errorDiaHora;
    private List<String> listaDeErrors;
    private Integer httpStatusCodigo;
    private String httpStatusCodigoDescricao;
    private String debugMessage;
}
