package dvie.twoshotcore.commands;

import dev.splityosis.commandsystem.SYSCommand;
import dvie.twoshotcore.TwoShotCore;
import lombok.Getter;

@Getter
public abstract class TwoShotCoreCommand {

    private final String name;
    @Getter
    private final String[] aliases;


    public TwoShotCoreCommand(String name, String... aliases) {
        this.name = name;
        this.aliases = aliases;
    }

    public abstract SYSCommand command();

    public void registerCommand() {
        command().setPermission(TwoShotCore.BASE_COMMAND_PERMISSION + command().getName());
        command().registerCommand(TwoShotCore.getCore());
    }

}
