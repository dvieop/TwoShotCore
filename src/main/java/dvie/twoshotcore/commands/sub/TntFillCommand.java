package dvie.twoshotcore.commands.sub;

import dev.splityosis.commandsystem.SYSCommand;
import dev.splityosis.commandsystem.arguments.IntegerArgument;
import dvie.twoshotcore.TwoShotCore;
import dvie.twoshotcore.commands.TwoShotCoreCommand;
import dvie.twoshotcore.commands.conditions.PlotEditCondition;
import dvie.twoshotcore.util.CannonPlayer;
import dvie.twoshotcore.util.Util;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;

public class TntFillCommand extends TwoShotCoreCommand {

    public TntFillCommand() {
        super("tntfill", "tf");
    }

    @Override
    public SYSCommand command() {
        return new SYSCommand(getAliases())
                .setArguments(new IntegerArgument())
                .setConditions(new PlotEditCondition())
                .executesPlayer((player, strings) -> {
                    CannonPlayer player1 = CannonPlayer.getCannonPlayer(player);
                    int MAX_RADIUS = TwoShotCore.featuresConfig.maxTntFillRadius;
                    int radius = Integer.parseInt(strings[0]);
                    if (radius > MAX_RADIUS) {
                        radius = MAX_RADIUS;
                    }
                    player1.fillRadius(new ItemStack(Material.TNT, 64));
                    int dispensers = player1.countDispensers(radius);
                    Util.sendMessage(player, TwoShotCore.featuresConfig.tntFillMessage.replace("%count%", String.valueOf(dispensers).replace("%radius%", String.valueOf(radius))));
                });
    }
}
