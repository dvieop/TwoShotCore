package dvie.twoshotcore.listeners;

import de.tr7zw.changeme.nbtapi.NBTItem;
import dvie.twoshotcore.TwoShotCore;
import dvie.twoshotcore.util.SkullUtil;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.ArmorStand;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.player.PlayerArmorStandManipulateEvent;
import org.bukkit.event.player.PlayerKickEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.inventory.ItemStack;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class Block36Listener implements Listener {

    public Block36Listener(TwoShotCore plugin) {
        plugin.getServer().getPluginManager().registerEvents(this, plugin);
    }

    public static Map<Player, Location> block36s = new HashMap();

    @EventHandler
    public void blockPlace(BlockPlaceEvent e) {
        Player player = e.getPlayer();
        ItemStack hand = player.getInventory().getItemInHand();
        NBTItem nbtItem = new NBTItem(hand);
        Block block = e.getBlock();

        if (!nbtItem.hasKey("cannonitem_identifier") || !nbtItem.getString("cannonitem_identifier").equals("36_magic_sand")) return;
        e.setCancelled(true);

        block.setType(Material.PISTON_MOVING_PIECE);
        block36s.put(player, block.getLocation());
        Location location = block.getLocation();
        ArmorStand armorStand = location.getWorld().spawn(location.clone().add(0.5, -1.2, 0.5), ArmorStand.class);

        String texture = "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvYTk2ODQwZDJhNTkwMDNjMzc3ZjllMmMzYTRmYmRkM2FhZDMwMWViODNlYmVlM2ExZTg5ZjJhODNkNWU4NmUwMyJ9fX0=";
        ItemStack head = SkullUtil.skullItem(texture);

        armorStand.setVisible(false);
        armorStand.setHelmet(head);
    }

    @EventHandler
    public void onArmorClick(PlayerArmorStandManipulateEvent e) {
        Location location = e.getRightClicked().getLocation();
        ArmorStand armorStand = e.getRightClicked();
            if (location.equals(block36s.get(e.getPlayer()))) {
                e.setCancelled(true);
                armorStand.setHelmet(null);
            }
    }

    public static void removeBlock36s(Player player) {
        if (block36s.containsKey(player)) {
            Location location = block36s.get(player);
            Block block = location.getBlock();

            location.getWorld().getNearbyEntities(location.clone().add(0.5, -1.2, 0.5), 0.5, 0.5, 0.5).forEach(entity -> {
                if (entity instanceof ArmorStand) {
                    entity.remove();
                }
            });

            block.setType(Material.AIR);
            block36s.remove(player);
        }
    }

    @EventHandler
    public void onPlayerQuit(PlayerQuitEvent e) {
        removeBlock36s(e.getPlayer());
    }

    @EventHandler
    public void onPlayerKick(PlayerKickEvent e) {
        removeBlock36s(e.getPlayer());
    }
}

