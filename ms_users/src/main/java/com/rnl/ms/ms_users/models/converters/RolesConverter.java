package com.rnl.ms.ms_users.models.converters;

import java.util.stream.Stream;
import javax.persistence.AttributeConverter;
import javax.persistence.Converter;
import com.rnl.ms.ms_users.enums.UserRoles;

/**
 * @author RNL
 * @link https://www.baeldung.com/jpa-persisting-enums-in-jpa
 */
@Converter(autoApply = true)
public class RolesConverter implements AttributeConverter<UserRoles, String> {

    @Override
    public String convertToDatabaseColumn(UserRoles role) {
        if(role == null) {
            return null;
        }
        return role.getCode();
    }

    @Override
    public UserRoles convertToEntityAttribute(String code) {
        if(code == null) {
            return null;
        }
        return Stream.of(UserRoles.values())     // Para a sequencia de elementos do enum UserRoles
          .filter(c -> c.getCode().equals(code))  // Filtra os elementos que possuem o mesmo codigo que o passado
          .findFirst()                            // Retorna o primeiro elemento encontrado
          .orElseThrow(IllegalArgumentException::new); // Caso nenhum elemento seja encontrado, lança uma excecao
    }     
}
