package dvie.twoshotcore.files;

import dev.splityosis.configsystem.configsystem.AnnotatedConfig;
import dev.splityosis.configsystem.configsystem.ConfigField;
import dvie.twoshotcore.util.Util;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;

import java.io.File;
import java.util.Arrays;
import java.util.List;

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

    @ConfigField(path = "bone.item")
    public ItemStack bone = Util.createItemStack(Material.BONE, 1, "&6Sand Remover &7(Right-Click Sand)", Arrays.asList("&7Use any bone to remove sand stacks", "&7by right-clicking them"));

    @ConfigField(path = "bone.receive")
    public String boneReceive = "&3(!) Gave you sand stack remover bone.";

    @ConfigField(path = "bone.range")
    public int boneRange = 50;

    @ConfigField(path = "bone.allowedItems")
    public List<Material> boneItems = Arrays.asList(Material.SAND, Material.GRAVEL, Material.ANVIL);

    @ConfigField(path = "bone.removedMessage")
    public String boneRemovedMessage = "&6(!) Removed &f%sand% &6at:";

}
