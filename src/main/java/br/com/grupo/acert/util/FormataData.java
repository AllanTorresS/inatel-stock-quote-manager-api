package br.com.grupo.acert.util;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class FormataData {

    public  static String formatarData(String pattern, LocalDateTime data){
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(pattern);

        return data.format(formatter);
    }
}
