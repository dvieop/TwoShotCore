package dvie.twoshotcore.commands.sub;

import dev.splityosis.commandsystem.SYSCommand;
import dvie.twoshotcore.commands.TwoShotCoreCommand;
import dvie.twoshotcore.items.CannonItems;

public class Block36Command extends TwoShotCoreCommand {

    public Block36Command() {
        super("block36", "b36");
    }

    @Override
    public SYSCommand command() {
        return new SYSCommand(getAliases())
                .executesPlayer((player, strings) -> {
                    CannonItems.b36MagicSand(player);
                });
    }
}
