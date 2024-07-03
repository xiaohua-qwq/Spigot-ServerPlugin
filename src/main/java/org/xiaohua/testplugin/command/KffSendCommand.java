package org.xiaohua.testplugin.command;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.xiaohua.testplugin.io.IOController;
import org.xiaohua.testplugin.utils.PlayerMessageSender;

import java.util.Collection;
import java.util.Map;

public class KffSendCommand implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (sender instanceof Player) {
            Player player = (Player) sender;
            Map<String, Integer> adminList = IOController.getAdminList();
            if (adminList.get(player.getName()) == null) {
                player.sendMessage(PlayerMessageSender.tag + "你没有执行此命令的权限");
                return false;
            }

            String kffMessage = args[0];
            Collection<? extends Player> onlinePlayers = Bukkit.getOnlinePlayers();
            String tag = null;
            switch (adminList.get(player.getName())) {
                case 99:
                    tag = "§f<§4服主§f>§4 ";
                case 10:
                    tag = "§f<§a管理员§f>§a ";
            }
            for (Player targetPlayer : onlinePlayers) {
                PlayerMessageSender.sendPrivateMessageToPlayer(targetPlayer, targetPlayer.getName() + "激活了跨服发送");
                for (int i = 0; i < 9; i++) {
                    targetPlayer.sendMessage("§f[§9主世界§f] " + player.getName() + tag + kffMessage);
                }
            }
            return true;
        }
        return false;
    }
}
