package dvie.twoshotcore.commands.conditions;

import dev.splityosis.commandsystem.SYSCondition;
import dvie.twoshotcore.TwoShotCore;
import dvie.twoshotcore.util.CannonPlayer;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.Arrays;
import java.util.List;

public class PlotEditCondition extends SYSCondition {

    @Override
    public boolean isValid(CommandSender commandSender) {
        if(!(commandSender instanceof Player)) return false;
        CannonPlayer cannonPlayer = new CannonPlayer((Player) commandSender);
        return true;
    }

    @Override
    public List<String> getConditionNotMetMessage(CommandSender commandSender) {
        return Arrays.asList(TwoShotCore.featuresConfig.plotEditNoPermission);
    }
}
