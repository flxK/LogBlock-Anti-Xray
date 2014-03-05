package net.felixkraus.LogBlockAntiXray;

import de.diddiz.LogBlock.BlockChange;
import de.diddiz.LogBlock.LogBlock;
import de.diddiz.LogBlock.QueryParams;
import de.diddiz.util.Block;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import static java.util.Arrays.asList;

/**
 * Created by felix on 04.03.14.
 */
public class AntiXray extends JavaPlugin {

    private LogBlock lb = null;

    public List<Block> types = new ArrayList<Block>();

    @Override
    public void onEnable(){
        final PluginManager pm = getServer().getPluginManager();
        final Plugin plugin = pm.getPlugin("LogBlock");
        if (plugin != null) lb = ((LogBlock) plugin);
    }

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args){
        if(label.equalsIgnoreCase("xlu")){
            if(args.length!=2) return false;
            if(!isInteger(args[1])){
                sender.sendMessage("Please give a valid integer for the time span.");
                return false;
            }
            else {
                List<Integer> ids = asList(1, 14, 15, 16, 21, 56, 73, 74, 129);
                for(int i = 0;ids.size()>i;i++) types.add(new Block(ids.get(i), -1));

                QueryParams params = new QueryParams(lb);
                params.setPlayer(args[0]);
                params.bct = QueryParams.BlockChangeType.DESTROYED;
                params.limit = -1;
                params.needId = true;
                params.needPlayer = true;
                params.needType = true;
                params.world = getServer().getWorlds().get(0);
                params.types = new ArrayList<Block>(types);
                try {
                    for (BlockChange bc : lb.getBlockChanges(params)) {
                        System.out.println(bc.type);
                    }
                } catch (SQLException e) {
                }
                return true;
            }
        }


        return false;
    }



    private static boolean isInteger(String str) {
        if (str == null) {
            return false;
        }
        int length = str.length();
        if (length == 0) {
            return false;
        }
        int i = 0;
        if (str.charAt(0) == '-') {
            if (length == 1) {
                return false;
            }
            i = 1;
        }
        for (; i < length; i++) {
            char c = str.charAt(i);
            if (c <= '/' || c >= ':') {
                return false;
            }
        }
        return true;
    }

}
