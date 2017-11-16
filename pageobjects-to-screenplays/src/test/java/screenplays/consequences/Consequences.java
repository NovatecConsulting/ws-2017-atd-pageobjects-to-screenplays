package screenplays.consequences;

import common.elements.WebElementWrapper;
import screenplays.base.abilities.Locator;

import java.util.Optional;
import java.util.function.Function;

public class Consequences {

    /**
     * Checks for given items in shopping basket.
     *
     * @param itemNames list of item names
     * @return the shopping basket consequence
     */
    public static ItemsInShoppingBasket haveItemsInShoppingBasket(String... itemNames) {
        return new ItemsInShoppingBasket(itemNames);
    }

    /**
     * Checks that the actor can see a web element (is visible)
     *
     * @param function function object for evaluation
     * @return the visible consequence
     */
    public static <T extends WebElementWrapper> VisibleElement see(Function<Locator, Optional<T>>function) {
        return new VisibleElement<>(function);
    }

    /**
     * Checks that the actor cannot see a web element (is not visible)
     *
     * @param function function object for evaluation
     * @return the invisible consequence
     */
    public static <T extends WebElementWrapper> NotVisibleElement notSee(Function<Locator, Optional<T>>function) {
        return new NotVisibleElement<>(function);
    }

}
