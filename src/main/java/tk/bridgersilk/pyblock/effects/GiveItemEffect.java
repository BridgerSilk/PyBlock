package tk.bridgersilk.pyblock.effects;

import java.util.UUID;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import tk.bridgersilk.pyblock.PyBlock;

public class GiveItemEffect {
    public static void giveItem(UUID playeruuid, ItemStack item) {
        Player player = Bukkit.getPlayer(playeruuid);
        if (player != null) {
            player.getInventory().addItem(item);
        } else {
            String warn = "No Player with the UUID " + playeruuid + " was found! (call: giveItem)";
            PyBlock.getInstance().getLogger().warning(warn);
        }
    }
}
