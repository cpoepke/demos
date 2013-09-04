package de.cpoepke.demos.wicket.validation;

import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.markup.html.form.Button;
import org.apache.wicket.markup.html.form.Form;
import de.cpoepke.demos.wicket.validation.model.User;
import de.cpoepke.demos.wicket.validation.component.TextInputPanel;
import de.cpoepke.demos.wicket.validation.component.PasswordInputPanel;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.Model;
import org.apache.wicket.model.PropertyModel;
import org.wicketstuff.jsr303.PropertyValidation;

public class LoginPage extends WebPage {

	private static final long serialVersionUID = 1L;

	public LoginPage() {

        Form form = new Form("form");

        IModel<User> model = Model.of(new User());
        form.add(new TextInputPanel("username", "username", new PropertyModel<String>(model, "username")));
        form.add(new PasswordInputPanel("password", "password", new PropertyModel<String>(model, "password")));

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
