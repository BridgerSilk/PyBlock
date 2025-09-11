package tk.bridgersilk.pyblock.expressions.entity;

import java.util.UUID;

import org.bukkit.Bukkit;
import org.bukkit.entity.Entity;
import org.bukkit.entity.LivingEntity;

import tk.bridgersilk.pyblock.PyBlock;

public class expHealth {
    public static double getHealth(UUID uuid) {
        Entity e = Bukkit.getEntity(uuid);
        if (e != null && e instanceof LivingEntity) {
            return ((LivingEntity) e).getHealth();
        } else {
            String warn = "No LivingEntity with the UUID " + uuid + " was found! (call: getHealth)";
            PyBlock.getInstance().getLogger().warning(warn);
            return -1;
        }
    }
}
