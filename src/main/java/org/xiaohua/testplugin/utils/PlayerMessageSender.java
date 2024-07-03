package org.xiaohua.testplugin.utils;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import java.util.Collection;

public class PlayerMessageSender {
    public static final String tag = "§f[§d服务器§f]§a";

    public static boolean sendPrivateMessageToPlayer(Player player, String message) {
        player.sendMessage(tag + message);
        return true;
    }

    public static boolean sendMessageToAllPlayer(String message) {
        Collection<? extends Player> onlinePlayers = Bukkit.getOnlinePlayers();
        for (Player onlinePlayer : onlinePlayers) {
            onlinePlayer.sendMessage(tag + message);
        }
        return true;
    }
}
