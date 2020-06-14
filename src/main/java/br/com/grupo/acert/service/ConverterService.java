package br.com.grupo.acert.service;

import br.com.grupo.acert.dao.ConverterHistoryRepository;
import br.com.grupo.acert.model.entitys.ConverterHistory;
import br.com.grupo.acert.model.error.AcertNotFoundExeception;
import br.com.grupo.acert.model.request.ConverterRequestCelsius;
import br.com.grupo.acert.model.request.ConverterRequestFahrenheit;
import br.com.grupo.acert.util.FormataData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpServletRequest;
import java.time.LocalDateTime;
import java.util.Locale;

@Service
public class ConverterService {

    @Autowired
    private ConverterHistoryRepository converterHistoryRepository;

    @Autowired
    private MessageSource messageSource;

    @Transactional
    public Double celsiusParaFahrenheit(ConverterRequestCelsius conversaoRequest, HttpServletRequest request){
        Double temperaturaCelsius = conversaoRequest.getConverterTemperatura();
        Double temperaturaConvertidaFahrenheit = temperaturaCelsius * 1.8 + 32;

        ConverterHistory  converterHistory = new ConverterHistory();
        converterHistory.setCelsiusValor(temperaturaCelsius);

        this.salvarHistoricoConversao(converterHistory,request,false,temperaturaConvertidaFahrenheit);

        return temperaturaConvertidaFahrenheit;
    }


    @Transactional
    public Double fahrenheitParaCelsius(ConverterRequestFahrenheit conversaoRequest,HttpServletRequest request){
        Double temperaturaFahrenheit = conversaoRequest.getConverterTemperatura();
        Double temperaturaConvertidaCelsius = (5.0 / 9.0) * (temperaturaFahrenheit - 32.0);

        ConverterHistory  converterHistory = new ConverterHistory();
        converterHistory.setFahrenheitValor(temperaturaFahrenheit);

        this.salvarHistoricoConversao(converterHistory,request,true,temperaturaConvertidaCelsius);

        return temperaturaConvertidaCelsius;
    }

    protected void salvarHistoricoConversao(ConverterHistory converterHistory, HttpServletRequest request, Boolean isFahrenheitParaCelsius, Double resultadoConversao){
        String pattern = "dd/MM/yyyy HH:mm:ss";
        LocalDateTime hoje = LocalDateTime.now();

        String dataFormatada = FormataData.formatarData(pattern,hoje);

        converterHistory.setDataConversao(dataFormatada);
        converterHistory.setIpSolicitante(request.getRemoteAddr());
        converterHistory.setIsFahrenheitParaCelsius(isFahrenheitParaCelsius);
        converterHistory.setResultadoConversao(resultadoConversao);

        String pt_br = this.messageSource.getMessage("not.found.execption", null, new Locale("pt_BR"));
        this.converterHistoryRepository.save(converterHistory);

        throw new AcertNotFoundExeception("{not.found.execption}");

    }
}
