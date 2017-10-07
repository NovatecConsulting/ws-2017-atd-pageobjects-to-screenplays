package common.screenplay;

public interface Performer {

    <T extends Actor> void performAs(T actor);
}
