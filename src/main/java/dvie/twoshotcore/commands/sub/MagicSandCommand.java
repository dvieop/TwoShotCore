package dvie.twoshotcore.commands.sub;

import dev.splityosis.commandsystem.SYSCommand;
import dvie.twoshotcore.commands.TwoShotCoreCommand;
import dvie.twoshotcore.menus.MagicSandMenu;

public class MagicSandCommand extends TwoShotCoreCommand {

    public MagicSandCommand() {
        super("magicsand", "ms");
    }

    @Override
    public SYSCommand command() {
        return new SYSCommand(getAliases())
                .executesPlayer((player, strings) -> {
                    new MagicSandMenu().open(player);
                });
    }
}
