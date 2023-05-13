package gg.techquest.commands;

import gg.techquest.EliteKits;
import gg.techquest.profile.command.ProfileCommand;
import gg.techquest.commands.dev.DebugCommand;

public class CommandManager {

    private EliteKits plugin;

    public CommandManager(EliteKits plugin) {
        this.plugin = plugin;

        //profiles commands
        this.plugin.getCommand("profile").setExecutor(new ProfileCommand(plugin));
        this.plugin.getCommand("debug").setExecutor(new DebugCommand());

    }

}
