package com.nota.playerlist;

import org.bukkit.Bukkit;
import org.bukkit.OfflinePlayer;
import org.bukkit.command.BlockCommandSender;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.Set;
import java.util.logging.Logger;
import java.util.stream.Collectors;

/*
 * PlayerList Java Plugin
 */
public class Plugin extends JavaPlugin {
  private static final Logger LOGGER = Logger.getLogger("playerlist");

  @Override
  public void onEnable() {
    LOGGER.info("PlayerList enabled");
  }

  @Override
  public void onDisable() {
    LOGGER.info("PlayerList disabled");
  }

  @SuppressWarnings("deprecation")
  @Override
  public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
    if (cmd.getName().equalsIgnoreCase("helloworld")) {
      sender.sendMessage("Hello, world!");
      return true;
    }

    if (cmd.getName().equalsIgnoreCase("whitelistlist")) {
      Set<OfflinePlayer> whitelistedPlayers = Bukkit.getServer().getWhitelistedPlayers();

      if (whitelistedPlayers.isEmpty()) {
        sender.sendMessage("The whitelist is empty.");
        return true;
      }

      // Convert the list of OfflinePlayer objects into a readable string
      String playerNames = whitelistedPlayers.stream()
          .map(OfflinePlayer::getName)
          .filter(name -> name != null) // Some offline players might have null names
          .collect(Collectors.joining(", "));

      if (sender instanceof Player) {
        sender.sendMessage("Whitelisted Players: " + playerNames);
      } else if (sender instanceof BlockCommandSender) {
        // Command block: Broadcast to all players nearby
        Bukkit.broadcastMessage("[Command Block] Whitelisted Players: " + playerNames);
      } else {
        sender.sendMessage("Whitelisted Players: " + playerNames);
      }

      return true;
    }

    return false;
  }
}
