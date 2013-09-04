package de.cpoepke.demos.wicket.validation.validation;

import org.apache.wicket.behavior.Behavior;
import org.apache.wicket.markup.html.form.FormComponent;
import org.apache.wicket.model.AbstractPropertyModel;
import org.apache.wicket.validation.INullAcceptingValidator;
import org.apache.wicket.validation.IValidatable;
import org.apache.wicket.validation.ValidationError;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.wicketstuff.jsr303.util.Assert;

import javax.validation.ConstraintViolation;
import java.io.Serializable;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class MyPropertyValidator<T> implements INullAcceptingValidator<T>, Serializable
{
	public static class Exclude extends Behavior
	{
		private static final long serialVersionUID = 1L;
	}

	public void validate(final IValidatable<T> validatable)
	{
		// skip, if propertyExpression is empty
		if (propertyExpression == null || propertyExpression.trim().length() == 0)
			return;

		// skip, if marked as excluded
		if (hasExclusionBehaviour())
			return;

		final T value = validatable.getValue();

		final Set<?> violations = JSR303Validation.getValidator().validateValue(beanClass,
			propertyExpression, value);
		for (final Object v : violations)
		{
            // Hack code start
            ConstraintViolation<?> violation = (ConstraintViolation<?>) v;
            ValidationError error = wrap(violation).createError();
            for (Map.Entry<String, Object> entry : violation.getConstraintDescriptor().getAttributes().entrySet()) {
                error.setVariable(entry.getKey(), entry.getValue());
            }
			validatable.error(error);
            // Hack code end
		}
	}

	private <V> MyViolationErrorBuilder.Property<V> wrap(ConstraintViolation<V> violation)
	{
		return new MyViolationErrorBuilder.Property<V>(violation);
	}

	private static final long serialVersionUID = 1L;

	private final Class<?> beanClass;
	private final String propertyExpression;

	private final FormComponent<T> fc;

	public MyPropertyValidator(final AbstractPropertyModel<?> apm, FormComponent<T> componentToApplyTo)
	{
		this.fc = componentToApplyTo;
		Assert.parameterNotNull(apm, "apm");
		this.beanClass = apm.getInnermostModelOrObject().getClass();
		this.propertyExpression = apm.getPropertyExpression();
	}

	private boolean hasExclusionBehaviour()
	{
		List<? extends Behavior> behaviors = fc.getBehaviors();
		for (Behavior iBehavior : behaviors)
		{
			if (iBehavior instanceof MyPropertyValidator.Exclude)
			{
				return true;
			}
		}

		return false;
	}

	private static transient volatile Logger _transient_logger = LoggerFactory.getLogger(MyPropertyValidator.class);

	public static final Logger log()
	{
		if (_transient_logger == null)
		{
			_transient_logger = LoggerFactory.getLogger(MyPropertyValidator.class);
		}
		return _transient_logger;
	}
}
