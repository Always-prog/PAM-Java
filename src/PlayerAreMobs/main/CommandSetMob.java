package PlayerAreMobs.main;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.sql.SQLException;

import static PlayerAreMobs.main.PlayersAreMobs.MobsDB;
import static PlayerAreMobs.main.PlayersAreMobs.MobsWorker;

public class CommandSetMob implements CommandExecutor{
    private PlayersAreMobs plugin;
    public CommandSetMob(PlayersAreMobs plugin){
        this.plugin = plugin;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if (args.length <= 0 ){return false;}
        if (!MobsWorker.exists(args[0])){
            sender.sendMessage("We can't find that class of the mob. Exists classes: "+MobsWorker.getClassesString());
            return true;
        }

        try {
            if (MobsDB.GetPlayerMob(sender.getName()) == null){
                MobsDB.NewPlayer(sender.getName(), args[0]);
            }else{
                MobsDB.UpdatePlayer(sender.getName(), args[0]);
            }
            sender.sendMessage( "You changed class of the mob to "+ChatColor.DARK_GREEN + args[0]);
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
        return true;
    }
}
