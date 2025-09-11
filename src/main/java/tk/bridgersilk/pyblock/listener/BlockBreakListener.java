package tk.bridgersilk.pyblock.listener;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;

import tk.bridgersilk.pyblock.scripts.ScriptManager;

public class BlockBreakListener implements Listener {
    @EventHandler
    public void onBlockBreak(BlockBreakEvent event) {
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
        Boolean dropItems = event.isDropItems();
        int exp = event.getExpToDrop();

        Map<String, Object> context = new HashMap<>();
        context.put("event", event);
        context.put("player", playerName);
        context.put("world", eventWorld);
        context.put("block", blockType);
        context.put("location", eventLocation);
        context.put("drop_items", dropItems);
        context.put("exp", exp);
        context.put("player_uuid", playerUUID);

        ScriptManager.callEventFunction("event_block_break", context);
    }
}
