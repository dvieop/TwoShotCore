package dvie.twoshotcore.menus;

import dev.splityosis.menulib.Menu;
import dev.splityosis.menulib.MenuItem;
import dvie.twoshotcore.items.CannonItems;
import dvie.twoshotcore.util.Util;
import org.bukkit.Material;
import org.bukkit.entity.Player;

import java.util.Arrays;

public class MagicSandMenu extends Menu {

    public MagicSandMenu() {
        super(9);
        setTitle("&7TwoShot | Magic Sand");

        MenuItem sand = new MenuItem(Util.createItemStack(Material.SAND, 1, "&3&lMagic Sand &8(&fSand&8)", Arrays.asList("&7Use this magic block to automatically", "&7place sand under its placed location", "", "&b&lCLICK TO RECEIVE THIS BLOCK"), true)).executes((inventoryClickEvent, menu) -> {
            Player player = (Player) inventoryClickEvent.getWhoClicked();
            CannonItems.sandMagicSand(player);
        });

        MenuItem redSand = new MenuItem(Util.createItemStack(Material.RED_SANDSTONE, 1, "&3&lMagic Sand &8(&fRed Sand&8)", Arrays.asList("&7Use this magic block to automatically", "&7place red sand under its placed location", "", "&b&lCLICK TO RECEIVE THIS BLOCK"), true)).executes((inventoryClickEvent, menu) -> {
            Player player = (Player) inventoryClickEvent.getWhoClicked();
            CannonItems.redSandMagicSand(player);
        });

        MenuItem gravel = new MenuItem(Util.createItemStack(Material.SAND, 1, "&3&lMagic Sand &8(&fGravel&8)", Arrays.asList("&7Use this magic block to automatically", "&7place gravel under its placed location", "", "&b&lCLICK TO RECEIVE THIS BLOCK"), true)).executes((inventoryClickEvent, menu) -> {
            Player player = (Player) inventoryClickEvent.getWhoClicked();
            CannonItems.gravelMagicSand(player);
        });

        MenuItem b36 = new MenuItem(Util.createItemStack(Material.PISTON_BASE, 1, "&3&lBlock 36 &8(&fPlace&8)", Arrays.asList("&7Use this block to place the unobtainable", "&7BLOCK 36", "", "&f&lVISIBLE: &7TRUE", "&7(Punch with block to toggle visibility)", "", "&b&lCLICK TO RECEIVE THIS BLOCK"), true)).executes((inventoryClickEvent, menu) -> {
            Player player = (Player) inventoryClickEvent.getWhoClicked();
            CannonItems.b36MagicSand(player);
        });

        setItem(1, sand);
        setItem(3, redSand);
        setItem(5, gravel);
        setItem(7, b36);

    }
}
