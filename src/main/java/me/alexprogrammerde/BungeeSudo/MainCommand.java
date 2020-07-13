package me.alexprogrammerde.BungeeSudo;

import net.md_5.bungee.api.ChatColor;
import net.md_5.bungee.api.CommandSender;
import net.md_5.bungee.api.chat.ClickEvent;
import net.md_5.bungee.api.chat.ComponentBuilder;
import net.md_5.bungee.api.chat.HoverEvent;
import net.md_5.bungee.api.chat.hover.content.Text;
import net.md_5.bungee.api.connection.ProxiedPlayer;
import net.md_5.bungee.api.plugin.Command;
import net.md_5.bungee.api.plugin.TabExecutor;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class MainCommand extends Command implements TabExecutor {

    String name;

    public MainCommand(String name, String permission, String... aliases) {
        super(name, permission, aliases);
        this.name = name;
    }

    @Override
    public void execute(CommandSender sender, String[] args) {
        if (sender.hasPermission("bungeesudo.use")) {
            if (args.length == 0) {
                sender.sendMessage(new ComponentBuilder("Please use: ").append("/" + name + " player message").event(new HoverEvent(HoverEvent.Action.SHOW_TEXT, new Text(new ComponentBuilder("Click me!").event(new ClickEvent(ClickEvent.Action.SUGGEST_COMMAND, "/" + name + " ")).color(ChatColor.GOLD).create()))).create());
            } else if (args.length == 1) {
                sender.sendMessage(new ComponentBuilder("Please use: ").append("/" + name + " " + args[0] + " message").event(new HoverEvent(HoverEvent.Action.SHOW_TEXT, new Text(new ComponentBuilder("Click me!").event(new ClickEvent(ClickEvent.Action.SUGGEST_COMMAND, "/" + name + " " + args[0] + " ")).color(ChatColor.GOLD).create()))).create());
            } else {
                ProxiedPlayer player = Main.plugin.getProxy().getPlayer(args[0]);
                if (player.isConnected()) {
                    if (player.hasPermission("bungeesudo.bypass") && !sender.hasPermission("bungeesudo.bypassbypass")) {
                        sender.sendMessage(new ComponentBuilder("Sorry this player has a bypass permission. You can't sudo him.").color(ChatColor.RED).create());
                    } else {
                        StringBuilder message = new StringBuilder();

                        for (int i = 1; i != args.length; i++) {
                            if (message.toString().equals("")) {
                                message.append(args[i]);
                            } else {
                                message.append(" ").append(args[i]);
                            }
                        }

                        player.chat(message.toString());
                    }
                } else {
                    sender.sendMessage(new ComponentBuilder("Sorry this player isn't connected to the proxy.").color(ChatColor.RED).create());
                }
            }
        }
    }

    @Override
    public Iterable<String> onTabComplete(CommandSender sender, String[] args) {
        List<String> completion = new ArrayList<>();

        if (sender.hasPermission("bungeesudo.use")) {
            List<String> players = new ArrayList<>();

            for (ProxiedPlayer player : Main.plugin.getProxy().getPlayers()) {
                players.add(player.getName());
            }

            if (args.length == 1) {
                for (String string : players)
                    if (string.toLowerCase().startsWith(args[0].toLowerCase())) completion.add(string);
            }

            Collections.sort(players);
        }

        return completion;
    }
}