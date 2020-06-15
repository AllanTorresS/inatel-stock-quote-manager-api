package br.com.grupo.acert.model.entitys;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;

@Entity
@Table(name = "historico_conversao")
@Data
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class ConverterHistory {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "historico_gerador_sequence")
    @SequenceGenerator(name = "historico_gerador_sequence", sequenceName = "historico_id_seq", allocationSize = 1)
    @EqualsAndHashCode.Include
    private Integer id;

    private String dataConversao;

    private String ipSolicitante;

    private Boolean isFahrenheitParaCelsius;

    private Double FahrenheitValor;

    private Double celsiusValor;

    private Double resultadoConversao;
}
