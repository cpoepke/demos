package de.cpoepke.demos.wicket.validation.validation;

import javax.validation.ConstraintViolation;

import org.apache.wicket.validation.ValidationError;
import org.wicketstuff.jsr303.util.Assert;

abstract class MyViolationErrorBuilder<T>
{
    static class Property<T> extends MyViolationErrorBuilder<T>
    {
        public Property(final ConstraintViolation<T> violation)
        {
            super(violation);
        }

        @Override
        protected String render()
        {
            return JSR303Validation.getViolationMessageRenderer()
                    .renderPropertyViolation(violation);
        }
    }
    static class Bean<T> extends MyViolationErrorBuilder<T>
    {

        public Bean(final ConstraintViolation<T> violation)
        {
            super(violation);
        }

        @Override
        protected String render()
        {
            return JSR303Validation.getViolationMessageRenderer().renderBeanViolation(violation);
        }
    }

    protected final ConstraintViolation<T> violation;

    MyViolationErrorBuilder(final ConstraintViolation<T> violation)
    {
        Assert.parameterNotNull(violation, "violation");
        this.violation = violation;
    }

    ValidationError createError()
    {
        final ValidationError ve = new ValidationError();
        // Hack code start
        //ve.setMessage(render());
        // Hack code end
        final String messageTemplate = violation.getMessageTemplate();
        final String key = extractKey(messageTemplate);
        if (key != null)
        {
            ve.addKey(key);
        }
        return ve;
    }

    protected abstract String render();

    private static String extractKey(final String messageTemplate)
    {
        Assert.parameterNotNull(messageTemplate, "messageTemplate");
        final String key = messageTemplate.trim();
        if (key.startsWith("{") && key.endsWith("}"))
        {
            return key.substring(1, key.length() - 1);
        }
        else
        {
            return null;
        }
    }
}
