package de.cpoepke.demos.wicket.validation.component;

import org.apache.wicket.AttributeModifier;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.form.FormComponent;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.Model;

public class CSSFeedbackLabel extends Label {

    private FormComponent component;

    public CSSFeedbackLabel(String id, FormComponent component) {
        super(id, Model.of(""));

        this.component = component;

        final IModel<String> replacementModel = new Model<String>() {
            private static final long serialVersionUID = 1L;

            @Override
            public String getObject() {
                if (CSSFeedbackLabel.this.component.isValid()) {
                    return AttributeModifier.VALUELESS_ATTRIBUTE_REMOVE;
                }
                return CSSFeedbackLabel.this.component.getFeedbackMessages().first().getLevelAsString().toLowerCase();
            }
        };
        add(new AttributeModifier("class", replacementModel));
    }

    @Override
    protected void onBeforeRender() {
        super.onBeforeRender();

        setDefaultModel(null);

        if (component.hasFeedbackMessage()) {
            setDefaultModel(Model.of(component.getFeedbackMessages().first().getMessage()));
        }
    }
}
