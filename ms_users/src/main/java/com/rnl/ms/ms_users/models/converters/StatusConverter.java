package com.rnl.ms.ms_users.models.converters;

import java.util.stream.Stream;
import javax.persistence.AttributeConverter;
import javax.persistence.Converter;
import com.rnl.ms.ms_users.enums.StatusUser;

/**
 * @author RNL
 * @link https://www.baeldung.com/jpa-persisting-enums-in-jpa
 */
@Converter(autoApply = true)
public class StatusConverter implements AttributeConverter<StatusUser, String> {

    @Override
    public String convertToDatabaseColumn(StatusUser status) {
        if(status == null) {
            return null;
        }
        return status.getCode();
    }

    @Override
    public StatusUser convertToEntityAttribute(String code) {
        if(code == null) {
            return null;
        }
        return Stream.of(StatusUser.values())     // Para a sequencia de elementos do enum StatusUser
          .filter(c -> c.getCode().equals(code))  // Filtra os elementos que possuem o mesmo codigo que o passado
          .findFirst()                            // Retorna o primeiro elemento encontrado
          .orElseThrow(IllegalArgumentException::new); // Caso nenhum elemento seja encontrado, lança uma excecao
    } 
}
