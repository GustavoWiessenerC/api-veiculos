package api.veiculos.utils;

import org.springframework.stereotype.Component;

@Component
public class ValidateEmptyIsNull {

    public static boolean isEmptyOrNotNull(String value) {
        return value.isEmpty() || value.isBlank();
    }
}
