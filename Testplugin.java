import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.ShapedRecipe;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.Arrays;

public class Testplugin extends JavaPlugin {
    private static ItemStack item01;

    public static Testplugin plugin;

    static {
        item01 = new ItemStack(Material.DIAMOND_SWORD, (short) 1);
    }

    @Override
    public void onEnable() {
        plugin = this;

        ItemMeta test = item01.getItemMeta();
        test.setDisplayName(ChatColor.RED + "神劍");
        test.setLore(Arrays.asList((ChatColor.YELLOW + "無限大的力量"),
                (ChatColor.RED + "請小心使用")));
        item01.setItemMeta(test);

        ShapedRecipe recipe = new ShapedRecipe(new NamespacedKey(plugin, "plugin"), item01);
        recipe.shape(
                "EEE",
                "EBE",
                "EEE"
        );
        recipe.setIngredient('E', Material.DIAMOND);
        recipe.setIngredient('B', Material.DIAMOND_SWORD);

        Bukkit.addRecipe(recipe);
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        Player p = (Player) sender;
        if (label.equalsIgnoreCase("get")) {
            if (args.length == 0) {
                p.sendMessage(ChatColor.RED + "請輸入編號");
            } else if (args.length == 1) {
                String a = args[0];
                switch (a) {
                    case "01":
                        p.getInventory().addItem(item01);
                        p.updateInventory();
                        break;
                    default:
                        p.sendMessage("無此編號");
                }
            }
        }
        return false;
    }

    @Override
    public void onDisable() {
        getServer().clearRecipes();
    }
}