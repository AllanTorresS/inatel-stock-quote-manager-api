package br.com.grupo.acert.dao;

import br.com.grupo.acert.model.entitys.ConverterHistory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ConverterHistoryRepository extends JpaRepository<ConverterHistory,Integer> {
}
