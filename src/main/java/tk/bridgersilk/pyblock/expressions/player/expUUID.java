package tk.bridgersilk.pyblock.expressions.player;

import java.util.UUID;

import org.bukkit.Bukkit;

import tk.bridgersilk.pyblock.PyBlock;

public class expUUID {
    public static UUID getUUID(String name) {
        UUID u = Bukkit.getPlayer(name).getUniqueId();
        if (u != null) {
            return u;
        } else {
            String warn = "No Player with the name " + name + " was found! (call: getUUID)";
            PyBlock.getInstance().getLogger().warning(warn);
            return null;
        }
    }
}
