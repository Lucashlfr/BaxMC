package net.baxmc.api.utils;

import org.bukkit.Color;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.LeatherArmorMeta;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

public class ItemBuilder {

    Material material;
    String displayName;
    String[] lore;
    byte data;
    ArrayList<ItemFlag> itemFlags = new ArrayList<ItemFlag>();
    HashMap<Enchantment, Integer> enchantments = new HashMap<Enchantment, Integer>();
    boolean unbreakbale = false;
    boolean hideFlags = false;
    int amount = 1;
    Color color = Color.NAVY;

    public static ItemBuilder generateNew(Material material) {
        return new ItemBuilder(material);
    }

    public ItemBuilder(Material material) {
        this.material = material;
    }

    public ItemBuilder setDisplayName(String name) {
        this.displayName = name;
        return this;
    }

    public ItemBuilder setData(byte data) {
        this.data = data;
        return this;
    }

    public ItemBuilder setItemFlags(ArrayList<ItemFlag> itemFlags) {
        this.itemFlags = itemFlags;
        return this;
    }

    public ItemBuilder addItemFlag(ItemFlag flag) {
        this.itemFlags.add(flag);
        return this;
    }

    public ItemBuilder setEnchantments(HashMap<Enchantment, Integer> enchantments) {
        this.enchantments = enchantments;
        return this;
    }

    public ItemBuilder addEnchantment(Enchantment enchantment, int level) {
        this.enchantments.put(enchantment, level);
        return this;
    }

    public ItemBuilder setLore(String lore) {
        this.lore = lore.split("\n");
        return this;
    }

    public ItemBuilder setLore(String... lore) {
        this.lore = lore;
        return this;
    }

    public ItemBuilder setLore(ArrayList<String> lore) {
        this.lore = lore.toArray(new String[lore.size()]);
        return this;
    }

    public ItemBuilder hideFlags(){
        this.hideFlags = true;
        return this;
    }

    public ItemBuilder setUnbreakbale(boolean unbreakbale) {
        this.unbreakbale = unbreakbale;
        return this;
    }

    public ItemBuilder setAmount(int amount) {
        this.amount = amount;
        return this;
    }

    public Material getMaterial() {
        return material;
    }

    public String getDisplayName() {
        return displayName;
    }

    public String[] getLore() {
        return lore;
    }

    public byte getData() {
        return data;
    }

    public ArrayList<ItemFlag> getItemFlags() {
        return itemFlags;
    }

    public HashMap<Enchantment, Integer> getEnchantments() {
        return enchantments;
    }

    public boolean isUnbreakbale() {
        return unbreakbale;
    }

    public int getAmount() {
        return amount;
    }

    public boolean isHideFlags() {
        return hideFlags;
    }

    public ItemBuilder setColor(final Color color){
        this.color = color;
        return this;
    }

    public ItemStack build() {
        ItemStack is = new ItemStack(getMaterial(), getAmount(), getData());
        if (getMaterial() != Material.AIR) {
            ItemMeta im = is.getItemMeta();
            if (getDisplayName() != null && !getDisplayName().equals("")) {
                im.setDisplayName(getDisplayName());
            }
            if (getLore() != null && getLore().length != 0) {
                im.setLore(new ArrayList<String>(Arrays.asList(getLore())));
            }
            im.spigot().setUnbreakable(isUnbreakbale());
            if (getItemFlags().size() != 0 && getItemFlags() != null) {
                getItemFlags().forEach(im::addItemFlags);
            }
            if (getEnchantments().size() != 0 && getEnchantments() != null) {
                getEnchantments().forEach((enchantment, level) -> im.addEnchant(enchantment, level, true));
            }
            if(isHideFlags()){
                ItemFlag itemFlag2[] = ItemFlag.values();
                for (int i = 0; i < itemFlag2.length; i++) {
                    im.addItemFlags(itemFlag2[i]);
                }
            }

            if(material.toString().startsWith("LEATHER_")){
                final LeatherArmorMeta leatherArmorMeta = (LeatherArmorMeta)im;
                leatherArmorMeta.setColor(color);
            }

            is.setItemMeta(im);
        }
        return is;
    }

}

