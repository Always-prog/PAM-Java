package PlayerAreMobs.main;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

import java.sql.SQLException;

import static PlayerAreMobs.main.PlayersAreMobs.MobsDB;

public class CommandIam implements CommandExecutor{
    private PlayersAreMobs plugin;
    public CommandIam(PlayersAreMobs plugin){
        this.plugin = plugin;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        String mob = "";
        try {
            mob = MobsDB.GetPlayerMob(sender.getName());
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
        sender.sendMessage( "You are "+ ChatColor.DARK_GREEN + mob);
        return true;
    }
}
