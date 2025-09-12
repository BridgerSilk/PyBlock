package tk.bridgersilk.pyblock.expressions.global;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import org.bukkit.Bukkit;
import org.bukkit.entity.Entity;

import tk.bridgersilk.pyblock.PyBlock;

public class expLocation {
    public static Map<String, Object> getLocation(UUID uuid) {
        Entity e = Bukkit.getEntity(uuid);
        if (e != null) {
            Map<String, Object> location = new HashMap<>();
            location.put("world", e.getLocation().getWorld().getName());
            location.put("x", e.getLocation().getX());
            location.put("y", e.getLocation().getY());
            location.put("z", e.getLocation().getZ());
            location.put("pitch", e.getLocation().getPitch());
            location.put("yaw", e.getLocation().getYaw());
            location.put("location", e.getLocation());
            return location;
        } else {
            String warn = "No Entity with the UUID " + uuid + " was found! (call: getLocation)";
            PyBlock.getInstance().getLogger().warning(warn);
            return new HashMap<>();
        }
    }
}
