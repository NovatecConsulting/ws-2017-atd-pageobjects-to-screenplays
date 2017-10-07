
package common.screenplay;

import java.util.function.Function;

/**
 * Consequence of a task action to be checked by an actor.
 */
public interface Consequence<R> extends Function<Actor,R> {
}
