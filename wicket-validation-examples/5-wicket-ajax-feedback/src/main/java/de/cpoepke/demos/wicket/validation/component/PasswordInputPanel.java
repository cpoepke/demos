package de.cpoepke.demos.wicket.validation.component;

import org.apache.wicket.markup.html.form.PasswordTextField;
import org.apache.wicket.model.IModel;

public class PasswordInputPanel extends AbstractInputPanel {

    public PasswordInputPanel(String id, String labelKey, IModel<String> model) {
        super(id, new PasswordTextField(getInputId(), model)
                .setResetPassword(false)
                .setRequired(false), labelKey);
    }
}
