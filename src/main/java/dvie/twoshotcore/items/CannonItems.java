package dvie.twoshotcore.items;

import de.tr7zw.changeme.nbtapi.NBTItem;
import dvie.twoshotcore.TwoShotCore;
import dvie.twoshotcore.util.Util;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

public class CannonItems {


    public static void stackRemover(Player player) {
        ItemStack itemStack = TwoShotCore.featuresConfig.bone;
        NBTItem nbtItem = new NBTItem(itemStack);
        nbtItem.setString("cannonitem_identifier", "bone");
        itemStack = nbtItem.getItem();

        Util.giveItemsToPlayer(player, itemStack, 1);
    }

    public static void sandMagicSand(Player player) {
        ItemStack itemStack = TwoShotCore.featuresConfig.magicSand;
        NBTItem nbtItem = new NBTItem(itemStack);
        nbtItem.setString("cannonitem_identifier", "magic_sand");
        itemStack = nbtItem.getItem();

        Util.giveItemsToPlayer(player, itemStack, 1);
    }

    public static void redSandMagicSand(Player player) {
        ItemStack itemStack = TwoShotCore.featuresConfig.magicSandRed;
        NBTItem nbtItem = new NBTItem(itemStack);
        nbtItem.setString("cannonitem_identifier", "red_magic_sand");
        itemStack = nbtItem.getItem();

        Util.giveItemsToPlayer(player, itemStack, 1);
    }

    public static void b36MagicSand(Player player) {
        ItemStack itemStack = TwoShotCore.featuresConfig.magicSandb36;
        NBTItem nbtItem = new NBTItem(itemStack);
        nbtItem.setString("cannonitem_identifier", "36_magic_sand");
        itemStack = nbtItem.getItem();

        Util.giveItemsToPlayer(player, itemStack, 1);
    }

    public static void gravelMagicSand(Player player) {
        ItemStack itemStack = TwoShotCore.featuresConfig.magicSandGravel;
        NBTItem nbtItem = new NBTItem(itemStack);
        nbtItem.setString("cannonitem_identifier", "gravel_magic_sand");
        itemStack = nbtItem.getItem();

        Util.giveItemsToPlayer(player, itemStack, 1);
    }
}
