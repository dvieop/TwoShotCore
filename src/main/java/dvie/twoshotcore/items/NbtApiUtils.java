package dvie.twoshotcore.items;
import de.tr7zw.changeme.nbtapi.NBTBlock;
import de.tr7zw.changeme.nbtapi.NBTItem;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.inventory.ItemStack;

import java.util.ArrayList;
import java.util.List;

public class NbtApiUtils {

    public static ItemStack applyNBTString(ItemStack itemStack, String key, String value){
        if (itemStack!= null && itemStack.getType()!= Material.AIR){
            NBTItem nbtItem = new NBTItem(itemStack);
            nbtItem.setString(key, value);
            return nbtItem.getItem();
        }
        return null;
    }

    public static String getNBTString(ItemStack itemStack, String key){
        if (itemStack!= null && itemStack.getType()!= Material.AIR){
            NBTItem nbtItem = new NBTItem(itemStack);
            if (nbtItem.hasTag(key)){
                String string = nbtItem.getString(key);
                return string;
            }
        }
        return "";
    }

    public static List<String> getNBTKeys(ItemStack itemStack){
        if (itemStack!= null && itemStack.getType()!= Material.AIR){
            NBTItem nbtItem = new NBTItem(itemStack);
            return new ArrayList<>(nbtItem.getKeys());
        }
        return null;
    }

    public static void removeAllData(Block block){
        NBTBlock nbtBlock = new NBTBlock(block);
        List<String> keys = new ArrayList<>(nbtBlock.getData().getKeys());
        for (String key : keys) {
            nbtBlock.getData().removeKey(key);
        }
    }

    public static boolean hasKeys(Block block){
        NBTBlock nbtBlock = new NBTBlock(block);
        if (nbtBlock.getData().getKeys().size() >= 1){
            return true;
        }
        return false;
    }

    public static List<String> getBlockNBTKeys(Block block){
        NBTBlock nbtBlock = new NBTBlock(block);
        return new ArrayList<>(nbtBlock.getData().getKeys());
    }

}
