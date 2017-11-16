package screenplays;

import common.User;
import screenplays.base.Actor;
import screenplays.base.abilities.BrowseTheWeb;
import screenplays.base.abilities.Login;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static screenplays.base.GivenWhenThen.givenThat;
import static screenplays.base.GivenWhenThen.when;
import static screenplays.consequences.Consequences.notSee;
import static screenplays.pages.AnyPage.logoutMenuAction;
import static screenplays.tasks.Tasks.*;


class AttackerUserStory extends BaseStory {

    private Actor theAttacker = Actor.named("Attacker").can(Login.with(User.ATTACKER));

    @BeforeEach
    void userCanBrowseTheWeb() {
        theAttacker.can(BrowseTheWeb.with(theBrowser));
    }

    @Test
    @DisplayName("login into the shop as administrator using sql injection")
    void attackToLoginAsAdministrator() {
        givenThat(theAttacker).wasAbleTo(openTheApplication());
        when(theAttacker) //
            .attemptsTo(loginByUsingSqlInjection()) //
            .then(theAttacker).should(notSee(logoutMenuAction()));
    }

}
