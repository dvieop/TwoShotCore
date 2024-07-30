package dvie.twoshotcore.commands.sub;

import dev.splityosis.commandsystem.SYSCommand;
import dvie.twoshotcore.commands.TwoShotCoreCommand;
import dvie.twoshotcore.items.CannonItems;

public class BoneCommand extends TwoShotCoreCommand {

    public BoneCommand() {
        super("bone", "b");
    }

    @Override
    public SYSCommand command() {
        return new SYSCommand(getAliases())
                .executesPlayer((player, strings) -> {
                    CannonItems.stackRemover(player);
                });
    }
}
