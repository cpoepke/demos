package de.cpoepke.demos.wicket.validation;

import de.cpoepke.demos.wicket.validation.model.User;
import org.apache.wicket.markup.html.form.Button;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.form.PasswordTextField;
import org.apache.wicket.markup.html.form.TextField;
import org.apache.wicket.markup.html.panel.FeedbackPanel;
import org.apache.wicket.model.CompoundPropertyModel;
import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.validation.validator.PatternValidator;
import org.apache.wicket.validation.validator.StringValidator;

public class LoginPage extends WebPage {

	private static final long serialVersionUID = 1L;

	public LoginPage() {
		super(new CompoundPropertyModel<User>(new User()));

        add(new FeedbackPanel("feedback"));

        Form form = new Form("form");
        form.add(new TextField<String>("username")
                .setRequired(true)
                .add(StringValidator.lengthBetween(3, 10)));

        form.add(new PasswordTextField("password")
                .setRequired(true)
                .add(StringValidator.lengthBetween(3, 10))
                .add(new PatternValidator("((?=.*\\d)(?=.*[a-z])(?=.*[A-Z]).{3,10})")));

        form.add(new Button("submit") {
            @Override
            public void onSubmit() {
                setResponsePage(LoggedInPage.class);
            }
        });
        add(form);
    }
}
