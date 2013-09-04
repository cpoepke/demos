package de.cpoepke.demos.wicket.validation.component;

import org.apache.wicket.AttributeModifier;
import org.apache.wicket.feedback.IFeedback;
import org.apache.wicket.markup.html.WebMarkupContainer;
import org.apache.wicket.markup.html.form.FormComponent;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.Model;

public class CSSFeedbackContainer extends WebMarkupContainer implements IFeedback {

    private static final long serialVersionUID = 1L;

    private final FormComponent component;

    public CSSFeedbackContainer(String id, FormComponent component)
    {
        super(id);

        this.component = component;

        final IModel<String> replacementModel = new Model<String>() {
            private static final long serialVersionUID = 1L;

            @Override
            public String getObject() {
                if (CSSFeedbackContainer.this.component.isValid()) {
                    return AttributeModifier.VALUELESS_ATTRIBUTE_REMOVE;
                }
                return CSSFeedbackContainer.this.component.getFeedbackMessages().first().getLevelAsString().toLowerCase();
            }
        };
        add(new AttributeModifier("class", replacementModel));
    }
}
