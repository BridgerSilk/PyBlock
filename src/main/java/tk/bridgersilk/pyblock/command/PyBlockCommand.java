package tk.bridgersilk.pyblock.command;

import tk.bridgersilk.pyblock.scripts.ScriptManager;
import tk.bridgersilk.pyblock.PyBlock;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

public class PyBlockCommand implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (args.length == 0) {
            sender.sendMessage("§6Use §e/pyblock help §6for available commands.");
            return true;
        }

        switch (args[0].toLowerCase()) {
            case "help":
                sender.sendMessage("§6--- §ePyBlock Help §6---");
                sender.sendMessage("§e/pyblock help §7- Show this message");
                sender.sendMessage("§e/pyblock version §7- Show plugin version");
                sender.sendMessage("§e/pyblock reload §7- Reload all scripts");
                sender.sendMessage("§e/pyblock reload <script.py> §7- Reload a specific script");
                break;

            case "version":
                sender.sendMessage("§6PyBlock version: §e" + PyBlock.getInstance().getDescription().getVersion());
                break;

            case "reload":
                if (args.length == 2) {
                    if (ScriptManager.reloadScript(args[1])) {
                        sender.sendMessage("§aReloaded script: §f" + args[1]);
                    } else {
                        sender.sendMessage("§cFailed to reload script or not found: " + args[1]);
                    }
                } else {
                    ScriptManager.loadAllScripts();
                    sender.sendMessage("§aReloaded all scripts.");
                }
                break;

            default:
                sender.sendMessage("§cUnknown subcommand. Use §e/pyblock help§c.");
        }

        return true;
    }
}