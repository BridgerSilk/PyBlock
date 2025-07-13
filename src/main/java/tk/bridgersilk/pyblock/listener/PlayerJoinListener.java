package tk.bridgersilk.pyblock.listener;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

import tk.bridgersilk.pyblock.scripts.ScriptManager;

public class PlayerJoinListener implements Listener {

    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent event) {
        String playerName = event.getPlayer().getName();
        ScriptManager.callEventFunction("event_player_join", event, playerName);
    }
}
