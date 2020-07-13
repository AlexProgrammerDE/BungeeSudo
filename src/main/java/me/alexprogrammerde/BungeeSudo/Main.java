package me.alexprogrammerde.BungeeSudo;

import net.md_5.bungee.api.plugin.Plugin;
import net.md_5.bungee.config.Configuration;

import java.util.logging.Logger;

public class Main extends Plugin {
    static Main plugin;
    public static Configuration config;

    public void onEnable() {
        plugin = this;
        Logger logger = this.getLogger();

        logger.info("§9Registering command");
        getProxy().getPluginManager().registerCommand(this, new MainCommand("bungeesudo", "bungeesudo.use"));

        logger.info("§9Enabling Metrics");
        int pluginId = 8178; // <-- Replace with the id of your plugin!
        Metrics metrics = new Metrics(this, pluginId);

        logger.info("§9Checking for updates");
        new UpdateChecker(this, 81430).getVersion(version -> {
            if (this.getDescription().getVersion().equalsIgnoreCase(version)) {
                logger.info("§9There is not a new update available.");
            } else {
                logger.info("§cThere is a new update available. Its: " + version);
            }
        });
    }

    @Override
    public void onDisable() {

    }
}
