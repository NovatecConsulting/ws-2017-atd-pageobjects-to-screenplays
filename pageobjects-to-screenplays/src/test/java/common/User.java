package common;

import java.util.UUID;


public class User {

    public static final User JOE = new User("Joe", "joe@example.com", "password", 1, "test");
    public static final User UNKNOWN = new User("Unknown User", "noone@example.com", "unknown", 1, "unknown");
    public static final User ATTACKER = new User("Attacker", "admin@juice-sh.op' or 1=1;--", "123", 1, "test");

    public static final User NEW_USER = new User(//
        "New User",//
        UUID.randomUUID() + "@example.com",//
        "password",//
        1,//
        "unknown"//
    );

    public final String name;
    public final String email;
    public final String password;
    public final int questionIndex;
    public final String questionAnswer;

    public User(String name, String email, String password, int questionIndex, String questionAnswer) {
        this.name = name;
        this.email = email;
        this.password = password;
        this.questionIndex = questionIndex;
        this.questionAnswer = questionAnswer;
    }

}
