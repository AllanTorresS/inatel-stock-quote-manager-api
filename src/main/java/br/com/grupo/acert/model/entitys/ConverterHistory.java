package br.com.grupo.acert.model.entitys;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.Getter;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "historico_conversao")
@Data
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class ConverterHistory {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "historico_gerador_sequence")
    @SequenceGenerator(name = "historico_gerador_sequence",sequenceName = "historico_id_seq")
    @EqualsAndHashCode.Include
    private Integer id;

    private String dataConversao;

    private String ipSolicitante;

    private Boolean isFahrenheitParaCelsius;

    private Double FahrenheitValor;

    private Double celsiusValor;

    private Double resultadoConversao;
}
