package screenplays;

import static common.screenplay.GivenWhenThen.givenThat;
import static common.screenplay.GivenWhenThen.when;
import static screenplays.consequences.Consequences.see;
import static screenplays.pages.AnyPage.logoutMenuAction;
import static screenplays.tasks.Tasks.login;
import static screenplays.tasks.Tasks.openTheApplication;
import static screenplays.tasks.Tasks.register;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import common.User;
import common.screenplay.Actor;
import common.screenplay.abilities.BrowseTheWeb;
import common.screenplay.abilities.Login;


class RegisterUserStory extends BaseStory {

    private Actor joe = Actor.named("Joe").can(Login.with(User.JOE));
    private Actor bob = Actor.named("Bob").can(Login.with(User.NEW_USER));

    @BeforeEach
    void userCanBrowseTheWeb() {
        bob.can(BrowseTheWeb.with(theBrowser));
        joe.can(BrowseTheWeb.with(theBrowser));
    }

    @Test
    @DisplayName("registering allows the user to log into the shop")
    void registerNewUserAndLogin() {
        givenThat(bob).wasAbleTo(openTheApplication());
        when(bob) //
            .attemptsTo(register()) //
            .and() //
            .attemptsTo(login()) //
            .then(bob).should(see(logoutMenuAction()));
    }

    @Test
    @Disabled("should only be used to create user if application was reset")
    void createUserNamedJoe() {
        givenThat(joe).wasAbleTo(openTheApplication());
        when(joe) //
            .attemptsTo(register(), login()) //
            .then(joe).should(see(logoutMenuAction()));
    }

}
