package tk.bridgersilk.pyblock.expressions.entity;

import java.util.UUID;

import org.bukkit.Bukkit;
import org.bukkit.entity.Entity;
import org.bukkit.entity.LivingEntity;
import org.bukkit.inventory.ItemStack;

import tk.bridgersilk.pyblock.PyBlock;

public class expActiveItem {
    public static ItemStack getActiveItem(UUID uuid) {
        Entity e = Bukkit.getEntity(uuid);
        if (e != null && e instanceof LivingEntity) {
            return ((LivingEntity) e).getItemInUse();
        } else {
            String warn = "No LivingEntity with the UUID " + uuid + " was found! (call: getActiveItem)";
            PyBlock.getInstance().getLogger().warning(warn);
            return null;
        }
    }
}
