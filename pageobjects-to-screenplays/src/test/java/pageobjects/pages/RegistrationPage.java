package pageobjects.pages;

import static org.assertj.core.api.Assertions.assertThat;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import common.User;
import common.elements.Button;
import common.elements.PasswordField;
import common.elements.SelectionField;
import common.elements.TextField;


/**
 * This models the Juice Shop's registration page and all interactions possible
 * there.
 */
public class RegistrationPage extends JuiceShopPage<RegistrationPage> {

    public RegistrationPage(WebDriver webDriver) {
        super(webDriver);
    }

    @Override
    protected void assertThatCorrectPageIsDisplayed() {
        WebElement headline = findByCss("Registration Headline", "h3[translate=TITLE_REGISTRATION]");
        assertThat(headline.isDisplayed()).isTrue();
    }

    /**
     * Executes the registration for the given {@link User}.
     * <p>
     * This method expects the user NOT to exist in the Juice Shop's user directory.
     * Invoking this method for an existing user will result in an exception
     * since the resulting page will not be the expected {@link LoginPage}.
     */
    public LoginPage registerUser(User user) {
        findEmailField().setText(user.email);
        findPasswordField().setText(user.password);
        findPasswordRepeatField().setText(user.password);
        findSecurityQuestionSelectionField().selectIndex(user.questionIndex);
        findSecurityQuestionAnswerField().setText(user.questionAnswer);
        findRegisterButton().click();
        return create(LoginPage.class);
    }

    private TextField findEmailField() {
        return new TextField(findByCss("E-Mail Field", "#userEmail"));
    }

    private PasswordField findPasswordField() {
        return new PasswordField(findByCss("Password Field", "#userPassword"));
    }

    private PasswordField findPasswordRepeatField() {
        return new PasswordField(findByCss("Password Repeat Field", "#userPasswordRepeat"));
    }

    private SelectionField findSecurityQuestionSelectionField() {
        return new SelectionField(findByCss("Security Question Select", "#securityQuestion"));
    }

    private TextField findSecurityQuestionAnswerField() {
        return new TextField(findByCss("Answer Field", "#securityAnswer"));
    }

    private Button findRegisterButton() {
        return new Button(findByCss("Register Button", "#registerButton"));
    }

}
