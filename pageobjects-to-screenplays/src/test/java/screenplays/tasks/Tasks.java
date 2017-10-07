package screenplays.tasks;

public class Tasks {

    public static OpenApplication openTheApplication() {
        return new OpenApplication();
    }

    public static NavigateToLogin forceNavigationToLoginPage() {
        return new NavigateToLogin();
    }

    public static NavigateToRegistration forceNavigationToRegistrationPage() {
        return new NavigateToRegistration();
    }

    public static NavigateToShoppingBasket navigateToShoppingBasket() {
        return new NavigateToShoppingBasket();
    }

    public static PerformLogin login() {
        return new PerformLogin();
    }

    public static PerformRegistration register() {
        return new PerformRegistration();
    }

    public static PerformSearch searchFor(String query) {
        return new PerformSearch(query);
    }

    public static PerformAddItemsToBasket addItemsToBasket(String... itemNames) {
        return new PerformAddItemsToBasket(itemNames);
    }

}
