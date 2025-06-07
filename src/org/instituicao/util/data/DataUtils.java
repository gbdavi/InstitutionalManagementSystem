package org.instituicao.util.data;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class DataUtils {

    /**
     * Converte e valida data de nascimento de String no formato dd/MM/yyyy para LocalDate
     * @param dataString String no formato dd/MM/yyyy.
     */
    public static LocalDate processarDataNascimento(String dataString) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        LocalDate data = LocalDate.parse(dataString, formatter);
        if (data.isAfter(LocalDate.now())) {
            System.out.println("A data não pode estar no futuro.");
            return null;
        }
        return data;
    }
}
