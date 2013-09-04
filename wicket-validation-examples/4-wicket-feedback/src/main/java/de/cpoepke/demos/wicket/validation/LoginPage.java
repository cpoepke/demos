package de.cpoepke.demos.wicket.validation;

import de.cpoepke.demos.wicket.validation.component.CSSFeedbackLabel;
import de.cpoepke.demos.wicket.validation.model.User;
import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.markup.html.form.Button;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.form.PasswordTextField;
import org.apache.wicket.markup.html.form.TextField;
import org.apache.wicket.model.CompoundPropertyModel;
import org.wicketstuff.jsr303.PropertyValidation;

public class LoginPage extends WebPage {

	private static final long serialVersionUID = 1L;

	public LoginPage() {
		super(new CompoundPropertyModel<User>(new User()));

        Form form = new Form("form");

        TextField<String> field = new TextField<String>("username");
        field.setRequired(true);
        form.add(field);
        form.add(new CSSFeedbackLabel("username.feedback", field));

        field = new PasswordTextField("password");
        field.setRequired(false);
        form.add(field);
        form.add(new CSSFeedbackLabel("password.feedback", field));

        form.add(new Button("submit") {
            @Override
            public void onSubmit() {
                setResponsePage(LoggedInPage.class);
            }
        });

        form.add(new PropertyValidation());

        add(form);
    }
}
