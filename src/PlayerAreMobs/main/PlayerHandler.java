package PlayerAreMobs.main;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Item;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.player.*;
import org.bukkit.event.block.Action;
import org.bukkit.inventory.ItemStack;

import java.sql.SQLException;
import java.util.Objects;

import static PlayerAreMobs.main.PlayersAreMobs.MobsDB;
import static org.bukkit.Bukkit.getServer;

public class PlayerHandler implements Listener {

    @EventHandler
    public void interact(PlayerInteractEvent e) throws SQLException, ClassNotFoundException {
        Player p = e.getPlayer();
        ItemStack item = p.getItemInHand();
        Material material = item.getType();
        if (MobsDB.GetPlayerMob(p.getDisplayName()).toString().equals("zombie")){
            if (material == Material.BOW || material == Material.CROSSBOW){
                p.sendMessage(ChatColor.DARK_RED + "You can't use that! You are "+ChatColor.DARK_GREEN + "zombie!");
                e.setCancelled(true);
            };
        }





    }

    @EventHandler
    public void entityDamageByEntityEvent(EntityDamageByEntityEvent event) throws SQLException, ClassNotFoundException {
        if (!(event.getDamager() instanceof Player)){
            return;
        }
        Player p = getServer().getPlayer(event.getDamager().getName());
        ItemStack item = p.getItemInHand();
        Material material = item.getType();
        if (Objects.equals(MobsDB.GetPlayerMob(p.getDisplayName()), "skeleton")) {

            if (material == Material.WOODEN_SWORD || // Swords
                    material == Material.STONE_SWORD ||
                    material == Material.IRON_SWORD ||
                    material == Material.DIAMOND_SWORD ||
                    material == Material.NETHERITE_SWORD ||
                    material == Material.WOODEN_AXE || // axes
                    material == Material.STONE_AXE ||
                    material == Material.IRON_AXE ||
                    material == Material.DIAMOND_AXE ||
                    material == Material.NETHERITE_AXE
            )
            {
                p.sendMessage(ChatColor.DARK_RED + "You can't use that! You are "+ChatColor.DARK_GREEN + "skeleton!");
                event.setCancelled(true);
            };
        }
    }

}
