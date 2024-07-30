package dvie.twoshotcore.listeners;

import de.tr7zw.changeme.nbtapi.NBTItem;
import dvie.twoshotcore.TwoShotCore;
import dvie.twoshotcore.util.Util;
import org.bukkit.Location;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;

public class StackRemoverListener implements Listener {

    public StackRemoverListener(TwoShotCore plugin) {
        plugin.getServer().getPluginManager().registerEvents(this, plugin);
    }

    @EventHandler
    public void onPlayerInteract(PlayerInteractEvent e) {
        Player player = e.getPlayer();
        ItemStack hand = player.getInventory().getItemInHand();
        NBTItem nbtItem = new NBTItem(hand);
        Block block = targetBlock(player, 50);
        if (hand == null) return;
        if (!nbtItem.hasKey("cannonitem_identifier") || !nbtItem.getString("cannonitem_identifier").equals("bone")) return;
        if (block == null) return;
        int count = 0;
        Location location = block.getLocation().clone();
        for (int i = 0; i < 256; i++) {
            Block iter = location.add(0,1,0).getBlock();
            if (TwoShotCore.featuresConfig.boneItems.contains(iter.getType())) continue;
            Util.clearSand(iter.getLocation());
            break;
        }
        if (count > 0) {
            Util.sendMessage(player, TwoShotCore.featuresConfig.boneRemovedMessage.replace("%sand%", String.valueOf(count) + Util.locationToString(location)));
        }
    }

    private Block targetBlock(Player player, int range) {
        Location location = player.getEyeLocation();
        int distance = 0;
        while (distance <= range) {
            location = location.add(location.getDirection());
            Block block = location.getBlock();
            if (TwoShotCore.featuresConfig.boneItems.contains(block.getType())) {
                return block;
            }
            distance++;
        }
        return null;
    }
}
