package de.cpoepke.demos.wicket.validation;

import de.cpoepke.demos.wicket.validation.model.User;
import de.cpoepke.demos.wicket.validation.validation.MyPropertyValidation;
import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.markup.html.form.Button;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.form.PasswordTextField;
import org.apache.wicket.markup.html.form.TextField;
import org.apache.wicket.markup.html.panel.FeedbackPanel;
import org.apache.wicket.model.CompoundPropertyModel;

public class LoginPage extends WebPage {

	private static final long serialVersionUID = 1L;

	public LoginPage() {
		super(new CompoundPropertyModel<User>(new User()));

        add(new FeedbackPanel("feedback"));

        Form form = new Form("form");
        form.add(new TextField<String>("username"));
        form.add(new PasswordTextField("password")
            .setRequired(false));

        form.add(new Button("submit") {
            @Override
            public void onSubmit() {
                setResponsePage(LoggedInPage.class);
            }
        });

        form.add(new MyPropertyValidation());

        add(form);
    }
}
