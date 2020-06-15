package br.com.grupo.acert.dao;

import br.com.grupo.acert.model.entitys.ConverterHistory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ConverterHistoryRepository extends JpaRepository<ConverterHistory, Integer> {


    @Query(value = "select ch from ConverterHistory ch where ch.isFahrenheitParaCelsius = true")
    public List<ConverterHistory> listarTodosFahrenheitParaCelsius();

    @Query(value = "select ch from ConverterHistory ch where ch.isFahrenheitParaCelsius = false")
    public List<ConverterHistory> listaTodosCelsiusParaFahrenheit();
}
