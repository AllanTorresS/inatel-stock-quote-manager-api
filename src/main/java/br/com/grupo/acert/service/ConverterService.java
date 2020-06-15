package br.com.grupo.acert.service;

import br.com.grupo.acert.dao.ConverterHistoryRepository;
import br.com.grupo.acert.model.entitys.ConverterHistory;
import br.com.grupo.acert.model.error.AcertInternalServerErrorExeception;
import br.com.grupo.acert.model.error.AcertNotFoundExeception;
import br.com.grupo.acert.model.request.ConverterRequestCelsius;
import br.com.grupo.acert.model.request.ConverterRequestFahrenheit;
import br.com.grupo.acert.util.FormataData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpServletRequest;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class ConverterService {

    @Autowired
    private ConverterHistoryRepository converterHistoryRepository;

    @Autowired
    private MessageSource messageSource;

    @Transactional
    public ConverterHistory celsiusParaFahrenheit(ConverterRequestCelsius conversaoRequest, HttpServletRequest request) {
        Double temperaturaCelsius = conversaoRequest.getConverterTemperatura();
        Double temperaturaConvertidaFahrenheit = temperaturaCelsius * 1.8 + 32;

        ConverterHistory converterHistory = new ConverterHistory();
        converterHistory.setCelsiusValor(temperaturaCelsius);

        return this.salvarHistoricoConversao(converterHistory, request, false, temperaturaConvertidaFahrenheit);

    }

    @Transactional
    public ConverterHistory fahrenheitParaCelsius(ConverterRequestFahrenheit conversaoRequest, HttpServletRequest request) {
        Double temperaturaFahrenheit = conversaoRequest.getConverterTemperatura();
        Double temperaturaConvertidaCelsius = (5.0 / 9.0) * (temperaturaFahrenheit - 32.0);

        ConverterHistory converterHistory = new ConverterHistory();
        converterHistory.setFahrenheitValor(temperaturaFahrenheit);

        return this.salvarHistoricoConversao(converterHistory, request, true, temperaturaConvertidaCelsius);
    }

    public List<ConverterHistory> listarTodos() {
        return this.converterHistoryRepository.findAll();
    }

    public List<ConverterHistory> listarTodosFahrenheitParaCelsius() {
        return this.converterHistoryRepository.listarTodosFahrenheitParaCelsius();
    }

    public List<ConverterHistory> listaTodosCelsiusParaFahrenheit() {
        return this.converterHistoryRepository.listaTodosCelsiusParaFahrenheit();
    }

    public ConverterHistory buscarPorId(Integer id) {
        return this.converterHistoryRepository.findById(id)
                .orElseThrow(() -> new AcertNotFoundExeception("not.found.execption"));
    }

    protected ConverterHistory salvarHistoricoConversao(ConverterHistory converterHistory, HttpServletRequest request, Boolean isFahrenheitParaCelsius, Double resultadoConversao) {
        String pattern = "dd/MM/yyyy HH:mm:ss";
        LocalDateTime hoje = LocalDateTime.now();

        String dataFormatada = FormataData.formatarData(pattern, hoje);
        String ip = request.getRemoteAddr();

        if (ip.equalsIgnoreCase("0:0:0:0:0:0:0:1")) {
            try {
                ip = InetAddress.getLocalHost().getHostAddress();

            } catch (UnknownHostException e) {
                throw new AcertInternalServerErrorExeception("internal.server.error.execption");
            }
        }

        converterHistory.setDataConversao(dataFormatada);
        converterHistory.setIpSolicitante(ip);
        converterHistory.setIsFahrenheitParaCelsius(isFahrenheitParaCelsius);
        converterHistory.setResultadoConversao(resultadoConversao);

        return this.converterHistoryRepository.save(converterHistory);

    }

}
