package tk.bridgersilk.pyblock;

import tk.bridgersilk.pyblock.command.PyBlockCommand;
import tk.bridgersilk.pyblock.config.ConfigManager;
import tk.bridgersilk.pyblock.listener.EventManager;
import tk.bridgersilk.pyblock.scripts.ScriptManager;
import tk.bridgersilk.pyblock.utils.MaterialStubGenerator;

import org.bukkit.plugin.java.JavaPlugin;

public class PyBlock extends JavaPlugin
{
    private static PyBlock instance;

    @Override
	public void onEnable() {
		instance = this;
		getLogger().info("Starting PyBlock...");

		// load config
        getLogger().info("Loading config...");
		ConfigManager.init(this);

		// reg cmds
        getLogger().info("Registering commands...");
		getCommand("pyblock").setExecutor(new PyBlockCommand());

		// reg events
        getLogger().info("Registering events...");
		EventManager.registerEvents(this);

        // generate stubs
        MaterialStubGenerator.ensureMaterialStub(this);
        
		// load scripts
        getLogger().info("Loading scripts...");
		ScriptManager.loadAllScripts();
        getLogger().info("Successfully started plugin!");
	}

    @Override
	public void onDisable() {
		getLogger().info("Shutting down PyBlock...");
	}

	public static PyBlock getInstance() {
		return instance;
	}
}
