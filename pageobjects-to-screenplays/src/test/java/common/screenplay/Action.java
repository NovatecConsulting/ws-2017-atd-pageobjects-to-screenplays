
package common.screenplay;

import java.util.function.Function;

/**
 * Simple action to be performed by an actor.
 */
public interface Action<R> extends Function<Actor,R> {
}
