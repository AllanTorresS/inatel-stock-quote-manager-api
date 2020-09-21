package br.com.grupo.inatel.service;

import br.com.grupo.inatel.dao.QuotesHistoryRepository;
import br.com.grupo.inatel.model.entitys.QuotesHistory;
import br.com.grupo.inatel.model.error.AcertNotFoundExeception;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class QuotesHistoryService {

    @Autowired
    private QuotesHistoryRepository quotesHistoryRepository;

    @Autowired
    private StockService stockService;

    @Transactional
    public QuotesHistory save(QuotesHistory quotesHistory) {
        String chave = quotesHistory.getId();

        if(stockService.validarChave(chave)){
            return this.quotesHistoryRepository.save(quotesHistory);
        }

        throw new AcertNotFoundExeception("chave.invalida");
    }

    public QuotesHistory findById(String id) {
        return this.quotesHistoryRepository.findById(id).orElseThrow(()-> new AcertNotFoundExeception("not.found.execption"));
    }

    public List<QuotesHistory> findAll() {
        return this.quotesHistoryRepository.listAllQuotesHistory();
    }
}
