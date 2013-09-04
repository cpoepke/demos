package de.cpoepke.demos.wicket.validation.component;

import de.cpoepke.demos.wicket.validation.behaviour.OnEventAjaxBehaviorWithTarget;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.form.FormComponent;
import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.model.ResourceModel;

public abstract class AbstractInputPanel extends Panel {

    private static final String INPUT_ID = "input";

    public AbstractInputPanel(String id, FormComponent field, String labelKey) {
        this(id, id, field, labelKey);
    }

    public AbstractInputPanel(String id, String markupId, FormComponent field, String labelKey) {
        super(id);

        CSSFeedbackContainer container = new CSSFeedbackContainer("container", field);
        container.setOutputMarkupId(true);

        container.add(new Label("label", new ResourceModel(labelKey + "." + "container." + INPUT_ID))
                .setMarkupId(markupId + ".label"));

        Label feedback = new CSSFeedbackLabel("feedback", field);
        feedback.setMarkupId(markupId + ".feedback");
        feedback.setOutputMarkupId(true);
        add(feedback);

        field.setLabel(new ResourceModel(labelKey));
        field.setMarkupId(markupId + "." + INPUT_ID);
        field.add(new OnEventAjaxBehaviorWithTarget("blur", container, feedback));
        setMarkupId(markupId);

        container.add(field);
        add(container);
    }

    protected static String getInputId() {
        return INPUT_ID;
    }
}
