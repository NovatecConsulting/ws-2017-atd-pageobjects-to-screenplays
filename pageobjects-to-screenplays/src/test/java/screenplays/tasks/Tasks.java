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

    public static PerformLogin login() {
        return new PerformLogin();
    }

}
