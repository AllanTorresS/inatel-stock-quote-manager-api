package br.com.grupo.inatel.model.error;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

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
