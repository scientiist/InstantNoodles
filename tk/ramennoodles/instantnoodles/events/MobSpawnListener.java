package tk.ramennoodles.instantnoodles.events;

import org.bukkit.entity.Phantom;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntitySpawnEvent;

public class MobSpawnListener implements Listener {
	
	@EventHandler
	public void onEntitySpawn(EntitySpawnEvent e) {
		if (e.getEntity() instanceof Phantom) {
			// fuck phantoms
			e.getEntity().remove();
			e.setCancelled(true);
		}
	}
}
