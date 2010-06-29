package cybermoo.util;

import cybermoo.ChatCommands.Name;
import cybermoo.ChatCommands.Command;

public class Annotations {
    public static String[] extractAliases(Class<? extends Command> command) {
        // skip classes that have no annotation attached
        if(!command.isAnnotationPresent(Name.class))
            return new String[0];
        
        // extract the annotation
        Name annotation = command.getAnnotation(Name.class);
        
        // ok.. extract the name and the aliases
        String name = annotation.value();
        String[] aliases = annotation.aliases();
        
        // name + aliases
        int size = aliases.length + 1;
        
        // create list of name + aliases
        String[] ret = new String[size];
        
        // store them
        ret[0]=name;
        if(size > 1)
            System.arraycopy(aliases, 0, ret, 1, aliases.length);
        
        return ret;
    }
}

        