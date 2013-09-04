package de.cpoepke.demos.wicket.validation.component;

import org.apache.wicket.markup.html.form.TextField;
import org.apache.wicket.model.IModel;

public class TextInputPanel extends AbstractInputPanel {

    public TextInputPanel(String id, String labelKey, IModel<String> model) {
        super(id, new TextField<String>(getInputId(), model), labelKey);
    }
}
