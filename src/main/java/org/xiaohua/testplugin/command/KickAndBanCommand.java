package org.xiaohua.testplugin.command;

import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.xiaohua.testplugin.io.IOController;
import org.xiaohua.testplugin.utils.PlayerMessageSender;

import java.util.Map;

public class KickAndBanCommand implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender commandSender, org.bukkit.command.Command command, String s,
                             String[] strings) {

        try {
            Map<String, Integer> adminList = IOController.getAdminList();
            Player player = null; //执行者
            Player targetPlayer = commandSender.getServer().getPlayer(strings[0]); //目标玩家
            if (commandSender instanceof Player) {
                player = (Player) commandSender;
                if (adminList.get(player.getName()) == null) {
                    player.sendMessage(PlayerMessageSender.tag + "你没有执行此命令的权限");
                    return false;
                }
                player.sendMessage(PlayerMessageSender.tag + "已发送踢出请求...");
                Thread.sleep(1500);
                if (targetPlayer == null) {
                    player.sendMessage(PlayerMessageSender.tag + "§a目标无效");
                    return false;
                } else if (targetPlayer.getName().equals(commandSender.getName())) {
                    player.sendMessage(PlayerMessageSender.tag + "§4你不能踢出自己");
                    return false;
                }
                targetPlayer.kickPlayer("您已被管理员: " + commandSender.getName() + " 踢出服务器");
                player.sendMessage(PlayerMessageSender.tag + "§a已成功踢出: §4" + targetPlayer.getName());
                return true;
            } else {
                commandSender.sendMessage("§4Do Not Execute This Command In The Console");
                return false;
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
