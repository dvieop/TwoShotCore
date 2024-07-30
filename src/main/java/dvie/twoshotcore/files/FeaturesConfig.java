package dvie.twoshotcore.files;

import dev.splityosis.configsystem.configsystem.AnnotatedConfig;
import dev.splityosis.configsystem.configsystem.ConfigField;

import java.io.File;

public class FeaturesConfig extends AnnotatedConfig {

    public FeaturesConfig(File parentDirectory, String name) {
        super(parentDirectory, name);
    }

    @ConfigField(path = "plotedit.nopermission")
    public String plotEditNoPermission = "&cYou do not have permission to edit this plot.";

    @ConfigField(path = "clearentity.radius")
    public int clearEntityRadius = 50;

    @ConfigField(path = "clearentity.message")
    public String clearEntityMessage = "(i) Removed %count% total entities from your plot.";

    @ConfigField(path = "tntfill.radius")
    public int maxTntFillRadius = 75;

    @ConfigField(path = "tntfill.message")
    public String tntFillMessage = "&aSuccessfully filled %count% dispensers in a radius of %radius% with 576 (⁓64 x 9) TNT";

}
