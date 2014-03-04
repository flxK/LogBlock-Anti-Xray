package net.felixkraus.LogBlockAntiXray;

import de.diddiz.LogBlock.Consumer;
import de.diddiz.LogBlock.LogBlock;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

/**
 * Created by felix on 04.03.14.
 */
public class AntiXray extends JavaPlugin {

    private Consumer lbconsumer = null;
    @Override
    public void onEnable(){
        final PluginManager pm = getServer().getPluginManager();
        final Plugin plugin = pm.getPlugin("LogBlock");
        if (plugin != null) lbconsumer = ((LogBlock) plugin).getConsumer();
    }


}
