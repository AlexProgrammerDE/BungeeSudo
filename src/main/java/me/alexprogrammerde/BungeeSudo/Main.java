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

        logger.info("§aRegistering command");
        getProxy().getPluginManager().registerCommand(this, new MainCommand("bungeesudo", "bungeesudo.use"));
    }

    @Override
    public void onDisable() {

    }
}
