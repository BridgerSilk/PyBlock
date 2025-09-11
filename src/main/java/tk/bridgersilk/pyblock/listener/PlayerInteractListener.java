package tk.bridgersilk.pyblock.listener;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import org.bukkit.Material;
import org.bukkit.block.BlockFace;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.EquipmentSlot;
import org.bukkit.inventory.ItemStack;
import org.bukkit.util.Vector;

import tk.bridgersilk.pyblock.scripts.ScriptManager;

public class PlayerInteractListener implements Listener {

    String clickedBlock;

    @EventHandler
    public void onPlayerInteract(PlayerInteractEvent event) {
        String playerName = event.getPlayer().getName();
        UUID playerUUID = event.getPlayer().getUniqueId();
        String eventWorld = event.getPlayer().getWorld().getName();
        BlockFace blockFace = event.getBlockFace();
        Action action = event.getAction();
        Map<String, Object> clickedBlockPosition = new HashMap<>();
        try {
            clickedBlock = event.getClickedBlock().getType().name();
            clickedBlockPosition.put("world", event.getClickedBlock().getLocation().getWorld().getName());
            clickedBlockPosition.put("x", event.getClickedBlock().getLocation().getX());
            clickedBlockPosition.put("y", event.getClickedBlock().getLocation().getY());
            clickedBlockPosition.put("z", event.getClickedBlock().getLocation().getZ());
            clickedBlockPosition.put("pitch", event.getClickedBlock().getLocation().getPitch());
            clickedBlockPosition.put("yaw", event.getClickedBlock().getLocation().getYaw());
        } catch (NullPointerException e) {
            clickedBlock = null;
            clickedBlockPosition.put("world", null);
            clickedBlockPosition.put("x", null);
            clickedBlockPosition.put("y", null);
            clickedBlockPosition.put("z", null);
            clickedBlockPosition.put("pitch", null);
            clickedBlockPosition.put("yaw", null);
        }
        Vector clickedPosition = event.getClickedPosition();
        EquipmentSlot hand = event.getHand();
        ItemStack item = event.getItem();
        Material material = event.getMaterial();

        Map<String, Object> context = new HashMap<>();
        context.put("event", event);
        context.put("player", playerName);
        context.put("world", eventWorld);
        context.put("block_face", blockFace);
        context.put("action", action);
        context.put("clicked_block", clickedBlock);
        context.put("clicked_block_position", clickedBlockPosition);
        context.put("clicked_position", clickedPosition);
        context.put("hand", hand);
        context.put("item", item);
        context.put("material", material);
        context.put("player_uuid", playerUUID);

        ScriptManager.callEventFunction("event_player_interact", context);
    }
}
