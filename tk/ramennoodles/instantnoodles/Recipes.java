package tk.ramennoodles.instantnoodles;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.Sound;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Fireball;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.entity.SmallFireball;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.Recipe;
import org.bukkit.inventory.ShapedRecipe;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

public class Recipes implements Listener {
	
	static String staffName = (ChatColor.YELLOW+"Staff: Fireball");
	static String speedStaffName = (ChatColor.YELLOW+"Staff: Dash");

	
	public static void createRecipes() {
		
		ItemStack speedStaff = new ItemStack(Material.STICK, 1);
		ItemMeta speedStaffMeta = speedStaff.getItemMeta();
		speedStaffMeta.setDisplayName(speedStaffName);
		speedStaffMeta.addEnchant(Enchantment.DURABILITY, 0, true);
		
		speedStaffMeta.addItemFlags(ItemFlag.HIDE_ENCHANTS);
		speedStaff.setItemMeta(speedStaffMeta);
		
		ShapedRecipe speedStaffRecipe = new ShapedRecipe(new NamespacedKey(Main.pluginReference, "speedStaff"), speedStaff);
		
		speedStaffRecipe.shape(" f ", " d ", " s ");
		
		speedStaffRecipe.setIngredient('s', Material.STICK);
		speedStaffRecipe.setIngredient('d', Material.DIAMOND);
		speedStaffRecipe.setIngredient('f', Material.SUGAR);
		
		Bukkit.getServer().addRecipe(speedStaffRecipe);
		
		ItemStack staff = new ItemStack(Material.STICK, 1);
		ItemMeta staffMeta = staff.getItemMeta();
		staffMeta.setDisplayName(staffName);
		staffMeta.addEnchant(Enchantment.DURABILITY, 0, true);
		
		staffMeta.addItemFlags(ItemFlag.HIDE_ENCHANTS);
		staff.setItemMeta(staffMeta);
		
		ShapedRecipe testStaffRecipe = new ShapedRecipe(new NamespacedKey(Main.pluginReference, "teststaff"), staff);
		
		testStaffRecipe.shape(" f ", " d ", " s ");
		
		testStaffRecipe.setIngredient('s', Material.STICK);
		testStaffRecipe.setIngredient('d', Material.DIAMOND);
		testStaffRecipe.setIngredient('f', Material.GUNPOWDER);
		
		
		Bukkit.getServer().addRecipe(testStaffRecipe);
		
	}
	
	@EventHandler
	public void playerRightClick(PlayerInteractEvent event) {
		
		
		if (!(event.getAction() == Action.RIGHT_CLICK_AIR || event.getAction() == Action.RIGHT_CLICK_BLOCK)) { return; }
		
		Player player = event.getPlayer();
		
		ItemStack item = event.getItem();
		if (item == null) { return; }
		if (player.hasCooldown(item.getType())) { return; }
		
		
		if (item.getItemMeta().getDisplayName().equals(staffName)) {
			player.playSound(player.getLocation(), Sound.ENTITY_BLAZE_SHOOT, 1, 1);
			Location goalLocation = player.getEyeLocation();
			goalLocation.add(player.getLocation().getDirection().multiply(2));
			
			Fireball fireball = (Fireball) player.getWorld().spawnEntity(goalLocation, EntityType.FIREBALL);
			
			
			fireball.setDirection(player.getLocation().getDirection());
			fireball.setIsIncendiary(false);
			fireball.setYield(1);
			
			player.setCooldown(item.getType(), 60);
				
			player.updateInventory();
		}
		
		if (item.getItemMeta().getDisplayName().equals(speedStaffName)) {
			
			player.playSound(player.getLocation(), Sound.ENTITY_EVOKER_CAST_SPELL, 1, 1);
			player.addPotionEffect(new PotionEffect(PotionEffectType.SPEED, 10, 10));
			player.setCooldown(item.getType(), 300);
		}
		
	}
}
