package dvie.twoshotcore.util;

import com.plotsquared.core.player.PlotPlayer;
import com.plotsquared.core.plot.Plot;
import dvie.twoshotcore.TwoShotCore;
import lombok.Getter;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.block.Block;
import org.bukkit.block.Dispenser;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

import java.util.*;
import java.util.stream.Collectors;

@Getter
public class CannonPlayer {

    public static Map<UUID, CannonPlayer> cannonPlayerMap = new HashMap<>();

    private final Player player;
    private final PlotPlayer plotPlayer;
    private final UUID uuid;

    public CannonPlayer(Player player) {
        this.player = player;
        this.plotPlayer = PlotPlayer.from(player);
        this.uuid = player.getUniqueId();
    }

    public boolean isMember(){
        Plot plot = plotPlayer.getCurrentPlot();
        return plot.getMembers().contains(plotPlayer.getUUID());
    }

    public Plot getCurrentPlot(){
        return plotPlayer.getCurrentPlot();
    }

    public boolean isTrusted(){
        Plot plot = plotPlayer.getCurrentPlot();
        return plot.getOwner().equals(plotPlayer.getUUID()) || plot.getTrusted().contains(plotPlayer.getUUID());
    }

    public boolean isInPlot(){
        return plotPlayer.getCurrentPlot() != null;
    }

    public static CannonPlayer getCannonPlayer(Player player){
        return new CannonPlayer(player);
    }

    public List<Block> getNearbyBlocks(int radius) {
        World world = player.getLocation().getWorld();
        List<Block> blocks = new ArrayList<>();
        if (world == null) return blocks;

        int px = player.getLocation().getBlockX();
        int py = player.getLocation().getBlockY();
        int pz = player.getLocation().getBlockZ();

        for (int x = px - radius; x < radius + px; x++) {
            for (int y = py - radius; y < radius + py; y++) {
                for (int z = pz - radius; z < radius + pz; z++) {
                    blocks.add(world.getBlockAt(x, y, z));
                }
            }
        }
        return blocks;
    }

    public void fillRadius(ItemStack itemStack){
        List<Block> dispensers = getNearbyBlocks(TwoShotCore.featuresConfig.maxTntFillRadius)
                .stream()
                .filter(b -> b.getType().equals(Material.DISPENSER))
                .collect(Collectors.toList());

        for (Block block : dispensers) {
            Dispenser dispenser = (Dispenser) block.getState();
            Inventory inventory = dispenser.getInventory();
            for (int j = 0; j < 9; j++) {
                inventory.setItem(j, itemStack);
            }
        }
    }

    public int countDispensers(int radius) {
        return (int) getNearbyBlocks(radius).stream()
                .filter(block -> block.getType() == Material.DISPENSER)
                .count();
    }
}
