package org.xiaohua.testplugin.Listeners;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.player.AsyncPlayerChatEvent;
import org.xiaohua.testplugin.enus.ServerEnum;
import org.xiaohua.testplugin.io.IOController;
import org.xiaohua.testplugin.utils.PlayerMessageSender;

import java.util.List;
import java.util.Map;

public class Listener implements org.bukkit.event.Listener {

    @EventHandler(priority = EventPriority.HIGH, ignoreCancelled = true)
    public void onBlockPlace(BlockPlaceEvent blockPlaceEvent) {
        Player player = blockPlaceEvent.getPlayer();
        extracted(player);
        List<String> whitelistedPlayers = IOController.getWhitelistedPlayers();
        for (String whitelistedPlayerName : whitelistedPlayers) {
            if (player.getName().equals(whitelistedPlayerName)) {
                return;
            }
        }
        PlayerMessageSender.sendPrivateMessageToPlayer(player, "§f你没有§4放置方块§f的权限");
        blockPlaceEvent.setCancelled(true); //取消这个事件
    }

    @EventHandler(priority = EventPriority.HIGH, ignoreCancelled = true)
    public void onBlockBreak(BlockBreakEvent blockBreakEvent) {
        Player player = blockBreakEvent.getPlayer();
        extracted(player);
        List<String> whitelistedPlayers = IOController.getWhitelistedPlayers();
        for (String whitelistedPlayerName : whitelistedPlayers) {
            if (player.getName().equals(whitelistedPlayerName)) {
                return;
            }
        }
        PlayerMessageSender.sendPrivateMessageToPlayer(player, "§f你没有§4破坏方块§f的权限");
        blockBreakEvent.setCancelled(true); //取消这个事件
    }

    private static void extracted(Player player) {
        String message = "ThePlayerHasBeenBlocked " + player.getName() + " ActionsOnBlocks";
        Bukkit.getConsoleSender().sendMessage("§a" + message);
    }

    @EventHandler(priority = EventPriority.LOW, ignoreCancelled = false)
    public void addTitleWithPlayerChat(AsyncPlayerChatEvent playerChatEvent) {
        Player player = playerChatEvent.getPlayer(); //信息发送者
        Map<String, Integer> adminList = IOController.getAdminList();
        Integer access = adminList.get(player.getName());
        if (access == null) {
            return;
        }
        String message = playerChatEvent.getMessage();
        String newMessage;
        switch (access) {
            case 99:
                newMessage = ServerEnum.owner + message;
                playerChatEvent.setMessage(newMessage);
                return;
            case 10:
                newMessage = ServerEnum.operations + message;
                playerChatEvent.setMessage(newMessage);
                return;
        }
    }
}
