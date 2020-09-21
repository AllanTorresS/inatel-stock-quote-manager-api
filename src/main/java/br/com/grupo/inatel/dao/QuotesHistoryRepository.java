package br.com.grupo.inatel.dao;

import br.com.grupo.inatel.model.entitys.QuotesHistory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface QuotesHistoryRepository extends JpaRepository<QuotesHistory, String> {

    @Query(value = "SELECT distinct h FROM QuotesHistory h join fetch h.quotes")
    List<QuotesHistory> listAllQuotesHistory();

    @Override
    @Query(value = "SELECT distinct h FROM QuotesHistory h join fetch h.quotes where h.id = ?1")
    Optional<QuotesHistory> findById(String id);
}
