package tk.bridgersilk.pyblock.listener;

import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;

import tk.bridgersilk.pyblock.scripts.ScriptManager;

public class BlockBreakListener implements Listener {
    @EventHandler
    public void onBlockBreak(BlockBreakEvent event) {
        String playerName = event.getPlayer().getName();
        String blockType = event.getBlock().getType().name();
        Location eventLocation = event.getBlock().getLocation();
        World eventWorld = event.getBlock().getWorld();
        Boolean dropItems = event.isDropItems();

        ScriptManager.callEventFunction("event_block_break", event, playerName, blockType, eventLocation, eventWorld, dropItems);
    }
}
