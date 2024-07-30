package dvie.twoshotcore;

import com.plotsquared.core.PlotAPI;
import dvie.twoshotcore.commands.TwoShotCoreCommands;
import dvie.twoshotcore.files.FeaturesConfig;
import dvie.twoshotcore.util.Util;
import lombok.Getter;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;
import org.reflections.Reflections;

public final class TwoShotCore extends JavaPlugin {

    public static String BASE_COMMAND_PERMISSION = "twoshotcore.command";
    @Getter
    public static TwoShotCore core;
    @Getter
    public static Reflections reflection = new Reflections("dvie.twoshotcore");
    @Getter
    public static PlotAPI plotAPI;
    public static FeaturesConfig featuresConfig;

    @Override
    public void onEnable() {
        core = this;
        new TwoShotCoreCommands();
        reloadConfigs();
    }

    @Override
    public void onDisable() {

    }

    public void reloadConfigs(){
        featuresConfig = new FeaturesConfig(getDataFolder(), "features.yml");
        featuresConfig.initialize();
    }
}
