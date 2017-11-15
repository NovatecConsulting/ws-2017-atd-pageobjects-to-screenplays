package pageobjects;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import common.User;


class RegisterUiTest extends BaseUiTest {

    @Test
    @DisplayName("registering allows the user to log into the shop")
    void registerNewUserAndLogin() {
        openApplication() // starts on the product page
            .goToLogin() // login can be reached from any page, if user is not already logged in
            .goToRegistration() // registration is only reachable from the login page
            .registerUser(User.NEW_USER) // fill registration form and returning you to the login page
            .loginAs(User.NEW_USER); // login with newly created used
    }

}
