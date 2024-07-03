package org.xiaohua.testplugin.command;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.xiaohua.testplugin.io.IOController;
import org.xiaohua.testplugin.utils.PlayerMessageSender;

import java.util.Map;

public class AddWhitelistCommand implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (sender instanceof Player) {
            try {
                Player player = (Player) sender;
                Map<String, Integer> adminList = IOController.getAdminList();
                if (adminList.get(player.getName()) == null) {
                    player.sendMessage(PlayerMessageSender.tag + "你没有执行此命令的权限");
                    return false;
                }
                PlayerMessageSender.sendPrivateMessageToPlayer(player, "已信任玩家 " + args[0]);
                Thread.sleep(1000);
                return IOController.addWhitelistedPlayer(args[0]);
            } catch (Exception e) {
                Bukkit.getConsoleSender().sendMessage("§4Error: " + e.getMessage());
                return false;
            }
        } else {
            sender.sendMessage("§4Do Not Execute This Command In The Console");
            return false;
        }
    }
}
