package screenplays;

import static common.screenplay.GivenWhenThen.givenThat;
import static common.screenplay.GivenWhenThen.then;
import static common.screenplay.GivenWhenThen.when;
import static screenplays.consequences.Consequences.haveItemsInShoppingBasket;
import static screenplays.tasks.Tasks.addItemsToBasket;
import static screenplays.tasks.Tasks.login;
import static screenplays.tasks.Tasks.openTheApplication;
import static screenplays.tasks.Tasks.searchFor;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import common.User;
import common.screenplay.Actor;
import common.screenplay.abilities.BrowseTheWeb;
import common.screenplay.abilities.Login;


class ShoppingBasketUserStory extends BaseStory {

    private Actor joe = Actor.named("Joe").can(Login.with(User.JOE));

    @BeforeEach
    void userCanBrowseTheWeb() {
        joe.can(BrowseTheWeb.with(theBrowser));
    }

    @Test
    @DisplayName("adding items to basket")
    void addingItemsToBasket() {
        givenThat(joe).wasAbleTo(openTheApplication(), login(), searchFor("Apple"));
        when(joe).attemptsTo(addItemsToBasket("Apple Pomace", "Apple Juice (1000ml)"));
        then(joe).should(haveItemsInShoppingBasket("Apple Pomace", "Apple Juice (1000ml)"));
    }

}
