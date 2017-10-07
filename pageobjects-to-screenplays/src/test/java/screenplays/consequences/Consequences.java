package screenplays.consequences;

public class Consequences {

    public static ItemsInShoppingBasket haveItemsInShoppingBasket(String... itemNames) {
        return new ItemsInShoppingBasket(itemNames);
    }
}
