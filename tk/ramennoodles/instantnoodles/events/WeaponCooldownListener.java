package tk.ramennoodles.instantnoodles.events;

import org.bukkit.Material;
import org.bukkit.attribute.Attribute;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.inventory.ItemStack;

import tk.ramennoodles.instantnoodles.Main;

public class WeaponCooldownListener implements Listener {
	
	
	@EventHandler
	public void playerJoin(PlayerJoinEvent e) {
		Player p = e.getPlayer();
		
		p.getAttribute(Attribute.GENERIC_ATTACK_SPEED).setBaseValue(1000);
	}
	
	@EventHandler
	public void playerAttack(EntityDamageByEntityEvent e) {
		if (!(e.getDamager() instanceof Player))
			return;
		
		Player attacker = (Player) e.getDamager();
		
		ItemStack weapon = attacker.getEquipment().getItemInMainHand();
		
		if (attacker.hasCooldown(weapon.getType())) {
			e.setCancelled(true);
			return;
		}
		
		Material m = weapon.getType();
		
		double speed = 1;
		
		if (Main.config.contains("weapons.cooldownspeeds." + m.toString())) {
			speed = Main.config.getDouble("weapons.cooldownspeeds."+m.toString());
		}
		
		attacker.setCooldown(weapon.getType(), (int)speed*20);
	}
}
