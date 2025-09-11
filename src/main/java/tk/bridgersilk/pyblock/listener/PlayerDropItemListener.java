package tk.bridgersilk.pyblock.listener;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import org.bukkit.Material;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerDropItemEvent;

import tk.bridgersilk.pyblock.scripts.ScriptManager;

public class PlayerDropItemListener implements Listener {
    @EventHandler
    public void onPlayerDropItem(PlayerDropItemEvent event) {
        String playerName = event.getPlayer().getName();
        UUID playerUUID = event.getPlayer().getUniqueId();
        String eventWorld = event.getPlayer().getWorld().getName();
        Material itemMaterial = event.getItemDrop().getItemStack().getType();
        UUID itemUUID = event.getItemDrop().getUniqueId();

        Map<String, Object> context = new HashMap<>();
        context.put("player", playerName);
        context.put("player_uuid", playerUUID);
        context.put("world", eventWorld);
        context.put("material", itemMaterial);
        context.put("item_uuid", itemUUID);

        ScriptManager.callEventFunction("event_item_drop", context);
    }
}
