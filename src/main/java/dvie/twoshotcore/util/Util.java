package dvie.twoshotcore.util;

import com.sk89q.worldedit.EditSession;
import com.sk89q.worldedit.WorldEdit;
import com.sk89q.worldedit.bukkit.BukkitAdapter;
import com.sk89q.worldedit.function.mask.BlockTypeMask;
import com.sk89q.worldedit.function.mask.Mask;
import com.sk89q.worldedit.regions.CuboidRegion;
import com.sk89q.worldedit.regions.Region;
import com.sk89q.worldedit.world.block.BlockTypes;
import dev.splityosis.configsystem.configsystem.actionsystem.ActionData;
import dev.splityosis.configsystem.configsystem.actionsystem.Actions;
import dvie.twoshotcore.TwoShotCore;
import net.minecraft.server.v1_8_R3.IChatBaseComponent;
import net.minecraft.server.v1_8_R3.PacketPlayOutChat;
import org.bukkit.*;
import org.bukkit.block.Block;
import org.bukkit.block.Dispenser;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.craftbukkit.v1_8_R3.entity.CraftPlayer;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.plugin.Plugin;

import java.io.File;
import java.text.DecimalFormat;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public class Util {

    public static final String LOG_PREFIX = "&8[&eTwoShotCore&8]";

    public static void sendMessage(CommandSender to, String message) {
        to.sendMessage(colorize(message));
    }

    public static void sendMessage(CommandSender to, List<String> message) {
        message.forEach(s -> {
            sendMessage(to, s);
        });
    }

    public static void broadcast(String message) {
        for (Player onlinePlayer : Bukkit.getOnlinePlayers())
            sendMessage(onlinePlayer, message);
        log(message);
    }

    public static void broadcast(List<String> message) {
        for (Player onlinePlayer : Bukkit.getOnlinePlayers())
            sendMessage(onlinePlayer, message);
        log(message);
    }

    public static void log(String message) {
        sendMessage(Bukkit.getServer().getConsoleSender(), LOG_PREFIX + " " + message);
    }

    public static void log(List<String> message) {
        List<String> msg = new ArrayList<>(message);
        if (!msg.isEmpty())
            msg.set(0, LOG_PREFIX + " " + msg.get(0));
        sendMessage(Bukkit.getServer().getConsoleSender(), msg);
    }

    private static final Pattern HEX_PATTERN = Pattern.compile("&(#\\w{6})");

    public static String colorize(String str) {
        Matcher matcher = HEX_PATTERN.matcher(net.md_5.bungee.api.ChatColor.translateAlternateColorCodes('&', str));
        StringBuffer buffer = new StringBuffer();

        while (matcher.find())
            matcher.appendReplacement(buffer, net.md_5.bungee.api.ChatColor.valueOf(matcher.group(1)).toString());

        return ChatColor.translateAlternateColorCodes('&', matcher.appendTail(buffer).toString());
    }

    public static List<String> colorize(List<String> lst) {
        if (lst == null) return null;
        List<String> newList = new ArrayList<>();
        lst.forEach(s -> {
            newList.add(colorize(s));
        });
        return newList;
    }

    public static ItemStack createItemStack(Material material, int amount, String name, List<String> lore) {
        ItemStack itemStack = new ItemStack(material, amount);
        ItemMeta meta = itemStack.getItemMeta();
        if (name != null)
            meta.setDisplayName(colorize(name));
        meta.setLore(colorize(lore));
        itemStack.setItemMeta(meta);
        return itemStack;
    }


    public static ItemStack createItemStack(Material material, int amount, String name, String... lore) {
        return createItemStack(material, amount, name, Arrays.asList(lore));
    }

    public static ItemStack createItemStack(Material material, int amount) {
        return createItemStack(material, amount, null);
    }

    public static List<String> replaceList(List<String> lst, String... replacements) {
        if (lst == null) return null;
        if (replacements.length % 2 != 0) throw new IllegalArgumentException("Replacements should be in pairs.");

        List<String> newList = new ArrayList<>();
        for (String s : lst) {
            String result = s;
            for (int i = 0; i < replacements.length; i += 2) {
                result = result.replace(replacements[i], replacements[i + 1]);
            }
            newList.add(result);
        }
        return newList;
    }

    public static ItemStack getItemFromSection(ConfigurationSection itemSection) {
        if (itemSection == null) return null;
        String material = itemSection.getString("material");
        int amount = itemSection.getInt("amount");
        if (amount == 0) amount = 1;
        String customName = itemSection.getString("custom-name");
        List<String> lore = itemSection.getStringList("custom-lore");

        Map<Enchantment, Integer> enchants = new HashMap<>();
        ConfigurationSection enchantsSection = itemSection.getConfigurationSection("enchants");
        if (enchantsSection != null)
            for (String key : enchantsSection.getKeys(false))
                enchants.put(Enchantment.getByName(key), enchantsSection.getInt(key));

        ItemStack item = createItemStack(Material.getMaterial(Objects.requireNonNull(material)), amount, customName, lore);
        item.addUnsafeEnchantments(enchants);
        return item;
    }

    public static void setItemInConfig(ConfigurationSection section, String path, ItemStack item) {
        String name = null;
        List<String> lore = null;

        if (item.getItemMeta() != null) {
            if (item.getItemMeta().hasDisplayName())
                name = item.getItemMeta().getDisplayName();
            if (item.getItemMeta().hasLore())
                lore = item.getItemMeta().getLore();
        }

        section.set(path + ".material", item.getType().name());
        section.set(path + ".custom-name", name);
        section.set(path + ".custom-lore", lore);

        Map<Enchantment, Integer> enchs = item.getEnchantments();
        for (Enchantment enchantment : enchs.keySet()) {
            section.set(path + ".enchants." + enchantment.getName(), enchs.get(enchantment));
        }
    }

    public static String locationToString(Location location) {
        String world = location.getWorld().getName();
        String x = String.valueOf(location.getBlockX());
        String y = String.valueOf(location.getBlockY());
        String z = String.valueOf(location.getBlockZ());
        return world + "_" + x + "_" + y + "_" + z;
    }

    public static Location locationFromString(String str) {
        String[] arr = str.split("_");
        World world = Bukkit.getWorld(arr[0]);
        double x = Double.parseDouble(arr[1]);
        double y = Double.parseDouble(arr[2]);
        double z = Double.parseDouble(arr[3]);
        return new Location(world, x, y, z);
    }

    public static String getFormattedEntityName(EntityType entityType) {
        StringBuilder builder = new StringBuilder();
        for (String s : entityType.name().toLowerCase().split("_"))
            builder.append(s.substring(0, 1).toUpperCase()).append(s.substring(1)).append(" ");
        return builder.substring(0, builder.length() - 1);
    }


    public static void performActionsGeneraly(Actions actions, Map<String, String> placeholders) {
        actions.perform(null, placeholders);
    }

    public static String createBar(int completed, int total, String completedChar, String emptyChar, String maxedOutChar) {
        StringBuilder builder = new StringBuilder();
        if (completed == total) {
            for (int i = 0; i < total; i++)
                builder.append(maxedOutChar);
            return colorize(builder.toString());
        }

        for (int i = 0; i < completed; i++)
            builder.append(completedChar);

        for (int i = 0; i < total - completed; i++)
            builder.append(emptyChar);
        return colorize(builder.toString());
    }

    public static ItemStack replaceTextInItem(ItemStack itemStack, String from, String to) {
        ItemStack item = itemStack.clone();
        ItemMeta meta = item.getItemMeta();
        if (meta == null)
            return item;
        if (meta.hasDisplayName())
            meta.setDisplayName(meta.getDisplayName().replace(from, to));
        if (meta.hasLore())
            meta.setLore(replaceList(meta.getLore(), from, to));
        item.setItemMeta(meta);
        return item;
    }

    public static Actions getDefaultActions(Sound sound, String... msg) {
        List<ActionData> actionDataList = new ArrayList<>();
        if (msg != null)
            actionDataList.add(new ActionData("MESSAGE", Arrays.asList(msg)));
        if (sound != null)
            actionDataList.add(new ActionData("SOUND", Arrays.asList(sound.name())));
        return new Actions(actionDataList);
    }

    public static Actions getDefaultTitleActions(Sound sound, String title, String subtitle, String... msg) {
        List<ActionData> actionDataList = new ArrayList<>();
        if (msg != null)
            actionDataList.add(new ActionData("MESSAGE", Arrays.asList(msg)));
        if (sound != null)
            actionDataList.add(new ActionData("SOUND", Arrays.asList(sound.name())));
        if (title != null && subtitle != null)
            actionDataList.add(new ActionData("TITLE", Arrays.asList(title, subtitle, "20", "20", "20")));
        return new Actions(actionDataList);
    }

    public static String getItemName(ItemStack item) {
        String name;
        if (item.getItemMeta() != null && item.getItemMeta().hasDisplayName())
            name = item.getItemMeta().getDisplayName();
        else {
            name = item.getType().name().toLowerCase().replace("_", " ");
            if (!name.equals("TNT")) {
                name = name.toLowerCase();
                StringBuilder stringBuilder = new StringBuilder();
                for (String s : name.split(Pattern.quote(" "))) {
                    stringBuilder.append(Character.toUpperCase(s.charAt(0))).append(s.substring(1)).append(" ");
                }
                name = stringBuilder.substring(0, stringBuilder.length() - 1);
            }
        }
        return name;
    }

    public static List<String> getLore(ItemStack itemStack) {
        if (itemStack.getItemMeta().hasLore()) {
            return itemStack.getItemMeta().getLore();
        }
        return new ArrayList<>();
    }

    public static String formatDouble(double num) {
        if (num == Math.floor(num))
            return new DecimalFormat("###,###").format(num);
        String s = new DecimalFormat("###,###.##").format(num);
        if (s.split(Pattern.quote("."))[1].length() == 1)
            return s + "0";
        return s;
    }

    public static void sendTitle(Player player, String header, String footer) {
        player.sendTitle(colorize(header), colorize(footer));
    }

    public static String formatInt(int num) {
        return new DecimalFormat("###,###").format(num);
    }

    public static void giveItemsToPlayer(Player player, ItemStack itemStack, int amount) {
        int maxStackSize = itemStack.getMaxStackSize();
        int remainingAmount = amount;

        while (remainingAmount > 0) {
            int giveAmount = Math.min(remainingAmount, maxStackSize);
            ItemStack giveItem = new ItemStack(itemStack);
            giveItem.setAmount(giveAmount);

            HashMap<Integer, ItemStack> leftovers = player.getInventory().addItem(giveItem);
            if (!leftovers.isEmpty()) {
                // If there are leftovers, drop them on the floor
                World world = player.getWorld();
                Location playerLocation = player.getLocation();
                for (ItemStack leftover : leftovers.values()) {
                    world.dropItemNaturally(playerLocation, leftover);
                }
            }

            remainingAmount -= giveAmount;
        }
    }

    public static String formatTime(long milliseconds) {
        int totalSeconds = (int) (milliseconds / 1000);
        int minutes = totalSeconds / 60;
        int seconds = totalSeconds % 60;

        String m;
        if (minutes == 0) m = "00";
        else if (minutes < 10) m = "0" + minutes;
        else m = String.valueOf(minutes);

        String s;
        if (seconds == 0) s = "00";
        else if (seconds < 10) s = "0" + seconds;
        else s = String.valueOf(seconds);

        return m + ":" + s;
    }

    public static void sendActionBar(Player p, String msg) {
        IChatBaseComponent cbc = IChatBaseComponent.ChatSerializer.a("{\"text\": \"" + colorize(msg) + "\"}");
        PacketPlayOutChat ppoc = new PacketPlayOutChat(cbc, (byte) 2);
        ((CraftPlayer) p).getHandle().playerConnection.sendPacket(ppoc);
    }

    public static boolean isMaterial(ItemStack itemStack, Material material) {
        if (itemStack != null && itemStack.getType().equals(material)) {
            return true;
        }
        return false;
    }

    public static boolean isNotNullOrAir(ItemStack itemStack) {
        if (itemStack != null && !itemStack.getType().equals(Material.AIR)) {
            return true;
        }
        return false;
    }

    public static boolean isNullOrAir(ItemStack itemStack) {
        if (itemStack != null && itemStack.getType().equals(Material.AIR)) {
            return true;
        }
        if (itemStack == null) {
            return true;
        }
        return false;
    }

    public static int getRandomIntBetween(double min, double max) {
        if (min >= max) {
            throw new IllegalArgumentException("Min value must be less than max value.");
        }

        Random random = new Random();
        int range = (int) (max - min) + 1;
        return random.nextInt(range) + (int) min;
    }

    public static List<String> reverseColorize(List<String> lst) {
        if (lst == null) return null;
        List<String> newLst = new ArrayList<>();
        for (String s : lst)
            newLst.add(reverseColorize(s));
        return newLst;
    }

    private static final Pattern patternAll = Pattern.compile("§x(§[0-9a-fA-F]){6}");

    public static String reverseColorize(String input) {
        Matcher matcher = patternAll.matcher(input);

        StringBuffer sb = new StringBuffer();
        while (matcher.find()) {
            String colorCode = matcher.group().replaceAll("§", "");
            matcher.appendReplacement(sb, "&#" + colorCode.substring(1));
        }
        matcher.appendTail(sb);

        return sb.toString().replaceAll("§([0-9a-fklmnorx])", "&$1");
    }


    public FileConfiguration getConfigFromPath(String pluginName, String path) {
        Plugin plugin = Bukkit.getServer().getPluginManager().getPlugin(pluginName);
        File file = new File(plugin.getDataFolder(), path.replace("/", System.lineSeparator()));
        return YamlConfiguration.loadConfiguration(file);
    }

    public static void clearSand(Location start) {
        Region region = new CuboidRegion(BukkitAdapter.adapt(start.getWorld()), BukkitAdapter.asBlockVector(start), BukkitAdapter.asBlockVector(start.clone().add(0, - start.getBlockY(), 0)));
        replaceBlocks(region, (Set<Material>) TwoShotCore.featuresConfig.boneItems, Material.AIR);
    }

    public static void replaceBlocks(Region region, Set<Material> from, Material to) {
        try (EditSession session = WorldEdit.getInstance().newEditSession(region.getWorld())) {
            Mask mask = new BlockTypeMask(session, from.stream().map((material) -> BlockTypes.get(material.name().toLowerCase())).collect(Collectors.toSet()));
            session.replaceBlocks(region, mask, BlockTypes.get(to.name().toLowerCase()));
        };
    }

    public static void addEnchantToItem(ItemStack item, Enchantment enchantment, int level, boolean hideEnchants) {
        ItemMeta meta = item.getItemMeta();
        meta.addEnchant(enchantment, level, true);
        if (hideEnchants) {
            meta.addItemFlags(new ItemFlag[]{ItemFlag.HIDE_ENCHANTS});
        }
        item.setItemMeta(meta);
    }

    public static ItemStack createItemStack(Material material, int amount, String name, List<String> lore, boolean enchanted) {
        ItemStack itemStack = new ItemStack(material, amount);
        ItemMeta meta = itemStack.getItemMeta();

        if (name != null)
            meta.setDisplayName(colorize(name));
        if (lore != null)
            meta.setLore(colorize(lore));
        if (enchanted) {
            meta.addEnchant(Enchantment.ARROW_DAMAGE, 1, true);
            meta.addItemFlags(new ItemFlag[]{ItemFlag.HIDE_ENCHANTS});
        }

        itemStack.setItemMeta(meta);
        return itemStack;
    }
}
