package tk.bridgersilk.pyblock.listener;

import java.util.HashMap;
import java.util.Map;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerMoveEvent;

import tk.bridgersilk.pyblock.scripts.ScriptManager;

public class PlayerMoveListener implements Listener {
    @EventHandler
    public void onPlayerMove(PlayerMoveEvent event) {
        String playerName = event.getPlayer().getName();
        String eventWorld = event.getPlayer().getWorld().getName();
        Map<String, Object> pastLocation = new HashMap<>();
        pastLocation.put("world", event.getFrom().getWorld().getName());
        pastLocation.put("x", event.getFrom().getX());
        pastLocation.put("y", event.getFrom().getY());
        pastLocation.put("z", event.getFrom().getZ());
        pastLocation.put("pitch", event.getFrom().getPitch());
        pastLocation.put("yaw", event.getFrom().getYaw());
        Map<String, Object> futureLocation = new HashMap<>();
        futureLocation.put("world", event.getFrom().getWorld().getName());
        futureLocation.put("x", event.getFrom().getX());
        futureLocation.put("y", event.getFrom().getY());
        futureLocation.put("z", event.getFrom().getZ());
        futureLocation.put("pitch", event.getFrom().getPitch());
        futureLocation.put("yaw", event.getFrom().getYaw());

        Map<String, Object> context = new HashMap<>();
        context.put("event", event);
        context.put("player", playerName);
        context.put("world", eventWorld);
        context.put("past_location", pastLocation);
        context.put("future_location", futureLocation);

        ScriptManager.callEventFunction("event_player_move", context);
    }
}
