package tk.bridgersilk.pyblock.listener;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockPlaceEvent;

import tk.bridgersilk.pyblock.scripts.ScriptManager;

public class BlockPlaceListener implements Listener {
    @EventHandler
    public void onBlockBreak(BlockPlaceEvent event) {
        String playerName = event.getPlayer().getName();
        UUID playerUUID = event.getPlayer().getUniqueId();
        String blockType = event.getBlock().getType().name();
        Map<String, Object> eventLocation = new HashMap<>();
        eventLocation.put("world", event.getBlock().getLocation().getWorld().getName());
        eventLocation.put("x", event.getBlock().getLocation().getX());
        eventLocation.put("y", event.getBlock().getLocation().getY());
        eventLocation.put("z", event.getBlock().getLocation().getZ());
        eventLocation.put("pitch", event.getBlock().getLocation().getPitch());
        eventLocation.put("yaw", event.getBlock().getLocation().getYaw());
        String eventWorld = event.getPlayer().getWorld().getName();
        String blockAgainst = event.getBlockAgainst().getType().name();

        Map<String, Object> context = new HashMap<>();
        context.put("event", event);
        context.put("player", playerName);
        context.put("world", eventWorld);
        context.put("block", blockType);
        context.put("location", eventLocation);
        context.put("block_against", blockAgainst);
        context.put("player_uuid", playerUUID);

        ScriptManager.callEventFunction("event_block_place", context);
    }
}
