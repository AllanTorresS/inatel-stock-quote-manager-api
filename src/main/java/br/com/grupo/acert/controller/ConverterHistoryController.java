package br.com.grupo.acert.controller;

import br.com.grupo.acert.service.ConverterService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/converter-history")
public class ConverterHistoryController {

    @Autowired
    private ConverterService converterService;

    @GetMapping("/list-all")
    @ApiOperation(value = "Lista o histórico com todas as conversãoes que foram feitas")
    public ResponseEntity listarTodasConversoes() {
        return ResponseEntity.status(HttpStatus.OK).body(this.converterService.listarTodos());
    }

    @GetMapping("/celsius-fahrenheit")
    @ApiOperation(value = "Lista todas as conversões de temperatura que foram feitas de celsius para fahrenheit")
    public ResponseEntity celsiusParaFahrenheit() {
        return ResponseEntity.status(HttpStatus.OK).body(this.converterService.listaTodosCelsiusParaFahrenheit());
    }

    @GetMapping("/fahrenheit-celsius")
    @ApiOperation(value = "Lista todas as conversões de temperatura que foram feitas de fahrenheit para celsius")
    public ResponseEntity fahrenheitParaCelsius() {
        return ResponseEntity.status(HttpStatus.OK).body(this.converterService.listarTodosFahrenheitParaCelsius());
    }

    @GetMapping("/{id}")
    @ApiOperation(value = "Busca um único histórico de conversão apartir do seu id")
    public ResponseEntity fahrenheitParaCelsius(@PathVariable Integer id) {
        return ResponseEntity.status(HttpStatus.OK).body(this.converterService.buscarPorId(id));
    }
}
