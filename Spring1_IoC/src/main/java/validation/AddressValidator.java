package validation;

import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

public class AddressValidator implements Validator {

    public boolean supports(Class<?> clazz) {
        return Address.class.equals(clazz);
    }

    public void validate(Object target, Errors errors) {

    }
}
