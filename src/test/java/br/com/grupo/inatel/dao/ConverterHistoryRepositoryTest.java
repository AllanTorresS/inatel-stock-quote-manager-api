package br.com.grupo.inatel.dao;


import br.com.grupo.inatel.model.entitys.QuotesHistory;
import br.com.grupo.inatel.model.error.AcertNotFoundExeception;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.util.Assert;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@DataJpaTest
@AutoConfigureTestDatabase(replace= AutoConfigureTestDatabase.Replace.NONE)
public class ConverterHistoryRepositoryTest {

    @Autowired
    private QuotesHistoryRepository repository;

    @Test
    public void salva_um_quote_history(){
        QuotesHistory quotesHistory = new QuotesHistory();
        Map map = new HashMap<String, BigDecimal>();

        quotesHistory.setId("PVC047");
        map.put("2019-01-01" , BigDecimal.ONE);

        quotesHistory.setQuotes(map);

        QuotesHistory bd = this.repository.save(quotesHistory);

        Assert.notNull(bd.getId(),"não foi possivel salvar o registro");

    }

}
