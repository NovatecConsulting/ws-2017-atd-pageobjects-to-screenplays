
package screenplays.base.abilities;

import common.User;
import screenplays.base.Ability;
import screenplays.base.Actor;


/**
 * PerformLoginTask ability.
 */
public class Login implements Ability<User> {

    private User user;

    public static Login as(Actor actor) {
        if (actor.hasAbilityTo(Login.class)) {
            return actor.abilityTo(Login.class);
        } else {
            throw new IllegalStateException("The actor '" + actor.getName() + "' does not have the ability to log in");
        }
    }

    public static Login with(User user) {
        return new Login(user);
    }

    private Login(User user) {
        this.user = user;
    }

    @Override
    public User get() {
        return user;
    }
}
