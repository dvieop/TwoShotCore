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
}
