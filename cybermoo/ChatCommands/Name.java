package cybermoo.ChatCommands;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;


@Retention(RetentionPolicy.RUNTIME)
public @interface Name {
    String value();
    String[] aliases() default {};
}
