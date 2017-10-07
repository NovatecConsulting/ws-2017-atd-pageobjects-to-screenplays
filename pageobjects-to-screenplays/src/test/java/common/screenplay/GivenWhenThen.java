package common.screenplay;

public class GivenWhenThen {

    public static <T extends Actor> T givenThat(T actor) {
        return actor;
    }

    public static Actor andThat(Actor actor) {return actor; }
    public static Actor when(Actor actor) {  return actor; }
    public static Actor then(Actor actor) { return actor; }
    public static Actor and(Actor actor) { return actor; }

}
