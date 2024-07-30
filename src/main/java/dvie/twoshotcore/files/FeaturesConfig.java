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

    @ConfigField(path = "magicSand.item.b36")
    public ItemStack magicSandb36 = Util.createItemStack(Material.PISTON_BASE, 1, "&3&lBlock 36 &8(&fPlace&8)", Arrays.asList("&7Use this block to place the unobtainable", "&7BLOCK 36", "", "&f&lVISIBLE: &7TRUE", "&7(Punch with block to toggle visibility)"), true);

    @ConfigField(path = "magicSand.item.sand")
    public ItemStack magicSand = Util.createItemStack(Material.SAND, 1, "&3&lMagic Sand &8(&fSand&8)", Arrays.asList("&7Use this magic block to automatically", "&7place sand under its placed location"), true);

    @ConfigField(path = "magicSand.item.redSand")
    public ItemStack magicSandRed = Util.createItemStack(Material.RED_SANDSTONE, 1, "&3&lMagic Sand &8(&fRed Sand&8)", Arrays.asList("&7Use this magic block to automatically", "&7place red sand under its placed location"), true);

    @ConfigField(path = "magicSand.item.gravel")
    public ItemStack magicSandGravel = Util.createItemStack(Material.GRAVEL, 1, "&3&lMagic Sand &8(&fGravel&8)", Arrays.asList("&7Use this magic block to automatically", "&7place gravel under its placed location"), true);
}
