package common.screenplay;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * Actor (persona) performing some action.
 */
public class Actor {

    private String name;
    private Map<Class, Ability> abilities = new HashMap<>();

    public static Actor named(String name) {
        return new Actor(name);
    }

    private Actor(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public <T extends Ability> Actor can(T ability) {
        abilities.put(ability.getClass(), ability);
        return this;
    }

    @SuppressWarnings("unchecked")
    public <T extends Ability> T abilityTo(Class<? extends T> ability) {
        return (T) abilities.get(ability);
    }

    public <T extends Ability> boolean hasAbilityTo(Class<? extends T> ability) {
        return abilities.containsKey(ability);
    }

    public Actor and() {
        return this;
    }

    public Actor then(Actor actor) {
        return actor;
    }

    public final void wasAbleTo(Performer... todos) {
        attemptsTo(todos);
    }

    public final Actor attemptsTo(Performer... tasks) {
        Arrays.stream(tasks).forEach(
            t -> t.performAs(this)
        );
        return this;
    }

    public final <T> T can(Consequence<T> consequence) {
        return consequence.apply(this);
    }

    public final <T> Actor performs(Action<T> action) {
        action.apply(this);
        return this;
    }

    public final void should(Consequence... consequences) {
        for (Consequence consequence : consequences) {
            check(consequence);
        }
    }

    private <T> void check(Consequence<T> consequence) {
        consequence.apply(this);
    }
}
