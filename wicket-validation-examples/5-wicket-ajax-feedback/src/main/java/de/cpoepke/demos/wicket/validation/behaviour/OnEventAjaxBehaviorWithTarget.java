package de.cpoepke.demos.wicket.validation.behaviour;

import org.apache.wicket.Component;
import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.ajax.form.AjaxFormComponentUpdatingBehavior;

public class OnEventAjaxBehaviorWithTarget extends AjaxFormComponentUpdatingBehavior {

    private Component[] targets;

    public OnEventAjaxBehaviorWithTarget(String event, Component... targets) {
        super(event);
        this.targets = targets;
    }

    @Override
    protected void onUpdate(AjaxRequestTarget target) {
        for (Component component : this.targets) {
            target.add(component);
        }
    }

    @Override
    protected void onError(AjaxRequestTarget target, RuntimeException e) {
        for (Component component : this.targets) {
            target.add(component);
        }
    }
}
