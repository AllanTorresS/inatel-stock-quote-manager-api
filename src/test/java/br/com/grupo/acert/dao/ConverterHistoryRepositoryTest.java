package br.com.grupo.acert.dao;


import br.com.grupo.acert.model.entitys.ConverterHistory;
import br.com.grupo.acert.model.error.AcertNotFoundExeception;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.util.Assert;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@DataJpaTest
@AutoConfigureTestDatabase(replace= AutoConfigureTestDatabase.Replace.NONE)
public class ConverterHistoryRepositoryTest {

    @Autowired
    private ConverterHistoryRepository repository;

    @Test
    public void salva_um_historico_de_conversao_de_fahrenheit_para_celsius(){
        ConverterHistory converterHistory = new ConverterHistory();

        converterHistory.setFahrenheitValor(Double.parseDouble("45"));
        converterHistory.setResultadoConversao(Double.parseDouble("7.22"));
        converterHistory.setIpSolicitante("172.168.8.9");
        converterHistory.setDataConversao(LocalDateTime.now().toString());
        converterHistory.setIsFahrenheitParaCelsius(true);

        ConverterHistory bd = this.repository.save(converterHistory);

        Assert.notNull(bd.getId(),"não foi possivel salvar o registro");

    }
    @Test
    public void salva_um_historico_de_conversao_de_celsius_para_fahrenheit(){
        ConverterHistory converterHistory = new ConverterHistory();

        converterHistory.setCelsiusValor(Double.parseDouble("22"));
        converterHistory.setResultadoConversao(Double.parseDouble("71.6"));
        converterHistory.setIpSolicitante("172.168.8.9");
        converterHistory.setDataConversao(LocalDateTime.now().toString());
        converterHistory.setIsFahrenheitParaCelsius(false);

        ConverterHistory bd = this.repository.save(converterHistory);

        Assert.notNull(bd.getId(),"não foi possivel salvar o registro");

    }


    @Test
    public void deve_retornar_todos_os_historicos_de_conversao_salvos(){

        List<ConverterHistory> bd = this.repository.findAll();

        int quantidade = bd.size();

        Assertions.assertEquals(quantidade,6);

    }

    @Test
    public void deve_retornar_um_historico_de_conversao_pelo_id(){

        Optional<ConverterHistory> bd = this.repository.findById(Integer.parseInt("1"));

        bd.orElseThrow(()-> new AcertNotFoundExeception("Não encontrado"));
    }

    @Test
    public void deve_retornar_todos_os_historicos_de_convsersao_de_celsius_para_fahrenheit (){

        List<ConverterHistory> bd = this.repository.listaTodosCelsiusParaFahrenheit();
        int numerosRegistros = bd.size();
        Assertions.assertEquals(numerosRegistros,3);
    }

    @Test
    public void deve_retornar_todos_os_historicos_de_convsersao_de_fahrenheit_para_celsius (){

        List<ConverterHistory> bd = this.repository.listarTodosFahrenheitParaCelsius();
        int numerosRegistros = bd.size();
        Assertions.assertEquals(numerosRegistros,3);
    }
}
