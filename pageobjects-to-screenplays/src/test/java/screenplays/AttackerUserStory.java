package screenplays;

import common.User;
import common.screenplay.Actor;
import common.screenplay.abilities.BrowseTheWeb;
import common.screenplay.abilities.Login;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static common.screenplay.GivenWhenThen.givenThat;
import static common.screenplay.GivenWhenThen.when;
import static screenplays.consequences.Consequences.notSee;
import static screenplays.pages.AnyPage.logoutMenuAction;
import static screenplays.tasks.Tasks.*;


class AttackerUserStory extends BaseStory {

    private Actor attacker = Actor.named("Attacker").can(Login.with(User.ATTACKER));

    @BeforeEach
    void userCanBrowseTheWeb() {
        attacker.can(BrowseTheWeb.with(theBrowser));
    }

    @Test
    @DisplayName("login into the shop as administrator using sql injection")
    void attackToLoginAsAdministrator() {
        givenThat(attacker).wasAbleTo(openTheApplication());
        when(attacker) //
            .attemptsTo(login()) //
            .then(attacker).should(notSee(logoutMenuAction()));
    }

}
