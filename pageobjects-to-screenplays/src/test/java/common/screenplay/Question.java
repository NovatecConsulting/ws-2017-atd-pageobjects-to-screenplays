package common.screenplay;

/**
 * A question.
 * @param <A> type of answer for the question
 */
public interface Question<A> {
    A answeredBy(Actor actor);
}
