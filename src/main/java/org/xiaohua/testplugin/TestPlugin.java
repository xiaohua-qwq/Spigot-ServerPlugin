package org.xiaohua.testplugin;

import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;
import org.xiaohua.testplugin.Listeners.Listener;
import org.xiaohua.testplugin.Listeners.WeatherManager;
import org.xiaohua.testplugin.Listeners.WelcomeMessage;
import org.xiaohua.testplugin.command.AddWhitelistCommand;
import org.xiaohua.testplugin.command.KffSendCommand;
import org.xiaohua.testplugin.command.KickAndBanCommand;

public final class TestPlugin extends JavaPlugin {

    @Override
    public void onEnable() {
        this.getLogger().info("init plugin...");
        Bukkit.getConsoleSender().sendMessage("§aplugin enabled");

        //注册事件监听器
        Listener listener = new Listener();
        WelcomeMessage welcomeMessage = new WelcomeMessage();
        Bukkit.getPluginManager().registerEvents(listener, this);
        Bukkit.getPluginManager().registerEvents(welcomeMessage, this);
        Bukkit.getPluginManager().registerEvents(new WeatherManager(), this);

        //注册指令
        Bukkit.getPluginCommand("kick").setExecutor(new KickAndBanCommand());
        Bukkit.getPluginCommand("aw").setExecutor(new AddWhitelistCommand());
        Bukkit.getPluginCommand("kff").setExecutor(new KffSendCommand());
    }

    @Override
    public void onDisable() {
        Bukkit.getConsoleSender().sendMessage("§4plugin disabled success");
    }
}
