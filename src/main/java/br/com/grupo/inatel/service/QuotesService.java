package br.com.grupo.inatel.service;

import br.com.grupo.inatel.dao.HistoryRepository;
import br.com.grupo.inatel.model.entitys.History;
import br.com.grupo.inatel.model.error.AcertNotFoundExeception;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class QuotesService {

    @Autowired
    private HistoryRepository quotesHistoryRepository;

    @Transactional
    public History save(History quotesHistory) {
        return this.quotesHistoryRepository.save(quotesHistory);
    }

    public History findById(String id) {
        return this.quotesHistoryRepository.findById(id).orElseThrow(()-> new AcertNotFoundExeception("not.found.execption"));
    }

    public List<History> findAll() {
        return this.quotesHistoryRepository.listAllQuotesHistory();
    }
}
