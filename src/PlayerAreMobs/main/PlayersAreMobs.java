package PlayerAreMobs.main;

import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

import java.sql.SQLException;


public class PlayersAreMobs extends JavaPlugin {
    public static Sqlite MobsDB = new Sqlite();

    public static Mobs MobsWorker = new Mobs();
    public void onEnable (){
        Bukkit.getPluginManager().registerEvents(new PlayerHandler(),this);
        MobsWorker.main();
        // Creating Database for PlayersAreMobs
        try {
            MobsDB.CreateDB();
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
        getCommand("set_mob").setExecutor(new CommandSetMob(this));
        getCommand("iam").setExecutor(new CommandIam(this));

        getLogger().info("Enabled!");
    }
    public void onDisable(){
        getLogger().info("Disabled!");
    }
}
