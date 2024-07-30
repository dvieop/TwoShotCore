package dvie.twoshotcore.commands;

import dvie.twoshotcore.TwoShotCore;
import dvie.twoshotcore.util.Util;
import org.bukkit.ChatColor;

import java.util.Set;

public class TwoShotCoreCommands {

    public TwoShotCoreCommands(){
        Set<Class<? extends TwoShotCoreCommand>> commandClasses = TwoShotCore.getReflection().getSubTypesOf(TwoShotCoreCommand.class);
        for (Class<?> commandClass : commandClasses) {
            try {
                TwoShotCoreCommand command = (TwoShotCoreCommand) commandClass.newInstance();
                command.registerCommand();
                Util.log("&bRegistered command: &e" + command.getName() + " &bwith aliases: &a" + String.join(", ", command.getAliases()));
            } catch (InstantiationException | IllegalAccessException e) {
                e.printStackTrace();
            }
        }
    }
}
