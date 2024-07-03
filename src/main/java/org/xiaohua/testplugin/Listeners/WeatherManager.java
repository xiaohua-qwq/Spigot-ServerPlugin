package org.xiaohua.testplugin.Listeners;

import org.bukkit.Bukkit;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.weather.WeatherChangeEvent;
import org.xiaohua.testplugin.utils.PlayerMessageSender;

public class WeatherManager implements Listener {
    @EventHandler(priority = EventPriority.HIGH, ignoreCancelled = true)
    public void onWeatherChange(WeatherChangeEvent event) {
        event.setCancelled(true);
        Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "weather clear");
        PlayerMessageSender.sendMessageToAllPlayer("已清除天气效果");
    }
}
