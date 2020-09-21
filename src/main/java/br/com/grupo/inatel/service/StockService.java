package br.com.grupo.inatel.service;

import br.com.grupo.inatel.model.error.AcertInternalServerErrorExeception;
import br.com.grupo.inatel.model.error.AcertNotFoundExeception;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

@Service
public class StockService {

    @Value("${url.stock.manager.api}")
    private  String urlStockManagerApi;

    @Autowired
    private  RestTemplate restTemplate;

    public Boolean validarChave(String chave) {
        try {
            ResponseEntity<String> response  = restTemplate.getForEntity(urlStockManagerApi + "/"+ chave, String.class);
            return response.getStatusCode().equals(HttpStatus.OK);
        }catch (HttpClientErrorException e ){
            if(e.getStatusCode().equals(HttpStatus.NOT_FOUND)){
                throw new AcertNotFoundExeception("not.found.execption");
            }
            throw new AcertInternalServerErrorExeception("error.consulta.api.externa");
        }
    }


}
