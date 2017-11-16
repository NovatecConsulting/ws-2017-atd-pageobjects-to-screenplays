package screenplays;

import static screenplays.base.GivenWhenThen.givenThat;
import static screenplays.base.GivenWhenThen.then;
import static screenplays.base.GivenWhenThen.when;
import static screenplays.consequences.Consequences.haveItemsInShoppingBasket;
import static screenplays.tasks.Tasks.addItemsToBasket;
import static screenplays.tasks.Tasks.login;
import static screenplays.tasks.Tasks.openTheApplication;
import static screenplays.tasks.Tasks.searchFor;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import common.User;
import screenplays.base.Actor;
import screenplays.base.abilities.BrowseTheWeb;
import screenplays.base.abilities.Login;


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
