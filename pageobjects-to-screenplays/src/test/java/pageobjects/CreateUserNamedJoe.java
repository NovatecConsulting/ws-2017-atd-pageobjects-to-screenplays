package pageobjects;

import org.junit.jupiter.api.Test;

import common.User;


class CreateUserNamedJoe extends BaseUiTest {

    @Test
    void createUserNamedJoe() {
        openApplication()//
            .goToLogin()//
            .goToRegistration()//
            .registerUser(User.JOE)//
            .loginAs(User.JOE);
    }

}
