package pageobjects;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import common.User;


class RegisterUiTest extends BaseUiTest {

    @Test
    @DisplayName("registering allows the user to log into the shop")
    void registerNewUserAndLogin() {
        openApplication()//
            .goToLogin()//
            .goToRegistration()//
            .registerUser(User.NEW_USER)//
            .loginAs(User.NEW_USER);
    }

    @Test
    @Disabled("should only be used to create user if application was reset")
    void createUserNamedJoe() {
        openApplication()//
            .goToLogin()//
            .goToRegistration()//
            .registerUser(User.JOE)//
            .loginAs(User.JOE);
    }

}
