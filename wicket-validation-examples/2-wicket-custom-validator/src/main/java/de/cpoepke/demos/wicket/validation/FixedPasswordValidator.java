package de.cpoepke.demos.wicket.validation;

import org.apache.wicket.validation.IValidatable;
import org.apache.wicket.validation.IValidator;
import org.apache.wicket.validation.ValidationError;

public class FixedPasswordValidator implements IValidator<String> {
    @Override
    public void validate(IValidatable<String> validatable) {

        if (!validatable.getValue().equals("password")) {
            validatable.error(new ValidationError()
                    .addKey("fixed-password-key")
                    .setVariable("variable", validatable.getValue()));
        }
    }
}
