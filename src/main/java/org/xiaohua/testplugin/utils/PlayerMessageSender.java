package org.xiaohua.testplugin.utils;

import org.bukkit.entity.Player;

public class PlayerMessageSender {
    public static final String tag = "§f[§d服务器§f]§a";

    public static boolean sendPrivateMessageToPlayer(Player player, String message) {
        player.sendMessage(tag + message);
        return true;
    }
}
