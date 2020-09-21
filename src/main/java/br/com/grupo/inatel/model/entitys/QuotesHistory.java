package br.com.grupo.inatel.model.entitys;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;
import java.util.Map;

@Entity
@Table(name = "quotes_history")
@Data
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class QuotesHistory {

    @Id
    @EqualsAndHashCode.Include
    private String id;

    @ElementCollection
    private Map<String, String> quotes;

}
