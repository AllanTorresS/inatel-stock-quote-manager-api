package br.com.grupo.inatel.dao;

import br.com.grupo.inatel.model.entitys.History;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface HistoryRepository extends JpaRepository<History, String> {

    @Query(value = "SELECT distinct h FROM History h join fetch h.quotes")
    List<History> listAllQuotesHistory();

    @Override
    @Query(value = "SELECT distinct h FROM History h join fetch h.quotes where h.id = ?1")
    Optional<History> findById(String id);
}
