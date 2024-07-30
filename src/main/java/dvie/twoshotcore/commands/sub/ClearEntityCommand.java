package dvie.twoshotcore.commands.sub;

import dev.splityosis.commandsystem.SYSCommand;
import dvie.twoshotcore.TwoShotCore;
import dvie.twoshotcore.commands.TwoShotCoreCommand;
import dvie.twoshotcore.util.Util;
import org.bukkit.entity.Entity;

public class ClearEntityCommand extends TwoShotCoreCommand {

    public ClearEntityCommand() {
        super("clearentity", "ce");
    }

    @Override
    public SYSCommand command() {
        return new SYSCommand(getAliases())
                .executesPlayer((player, strings) -> {
                    int radius = TwoShotCore.featuresConfig.clearEntityRadius;
                    int count = 0;
                    for (Entity entity : player.getNearbyEntities(radius, radius, radius)){
                        if (entity.getType().isAlive()) {
                            entity.remove();
                            count++;
                            Util.sendMessage(player, TwoShotCore.featuresConfig.clearEntityMessage.replace("%count%", String.valueOf(count)));
                        }
                    }
                });
    }
}
