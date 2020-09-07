package net.baxmc.api.commons.entitites;


import java.net.InetSocketAddress;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.UUID;

import net.baxmc.api.BaxAPI;
import net.minecraft.server.v1_8_R3.IChatBaseComponent;
import net.minecraft.server.v1_8_R3.Packet;
import net.minecraft.server.v1_8_R3.PacketPlayOutChat;
import net.minecraft.server.v1_8_R3.PacketPlayOutTitle;
import net.minecraft.server.v1_8_R3.PlayerConnection;
import org.bukkit.Achievement;
import org.bukkit.Bukkit;
import org.bukkit.Effect;
import org.bukkit.EntityEffect;
import org.bukkit.GameMode;
import org.bukkit.Instrument;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.Note;
import org.bukkit.Server;
import org.bukkit.Sound;
import org.bukkit.Statistic;
import org.bukkit.WeatherType;
import org.bukkit.World;
import org.bukkit.block.Block;
import org.bukkit.conversations.Conversation;
import org.bukkit.conversations.ConversationAbandonedEvent;
import org.bukkit.craftbukkit.v1_8_R3.entity.CraftPlayer;
import org.bukkit.entity.Arrow;
import org.bukkit.entity.Egg;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.entity.Snowball;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.player.PlayerTeleportEvent;
import org.bukkit.inventory.EntityEquipment;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.InventoryView;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.PlayerInventory;
import org.bukkit.map.MapView;
import org.bukkit.metadata.MetadataValue;
import org.bukkit.permissions.Permission;
import org.bukkit.permissions.PermissionAttachment;
import org.bukkit.permissions.PermissionAttachmentInfo;
import org.bukkit.plugin.Plugin;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.scoreboard.Scoreboard;
import org.bukkit.util.Vector;

public class AdvancedPlayer {
    public UUID uuid;

    public AdvancedPlayer(Player p) {
        this.uuid = p.getUniqueId();
    }

    public AdvancedPlayer(String username) {
        this.uuid = Bukkit.getOfflinePlayer(username).getUniqueId();
    }

    public AdvancedPlayer(UUID uuid) {
        this.uuid = uuid;
    }

    public UUID getUUID() {
        return this.uuid;
    }

    public Player getPlayer() {
        if (isOnline())
            return Bukkit.getPlayer(this.uuid);
        return null;
    }

    public boolean isOnline() {
        return Bukkit.getOfflinePlayer(this.uuid).isOnline();
    }

    public void sendMessageNOPREFIX(String message) {
        if (isOnline())
            getPlayer().sendMessage(message);
    }

    public void sendDefaultMessage(String message){
        if(isOnline())
            getPlayer().sendMessage(BaxAPI.getInstance().getPrefix() + message);
    }

    public void sendMessageWarning(String message) {
        if (isOnline())
            getPlayer().sendMessage(BaxAPI.getInstance().getWarningPrefix() + message);
    }

    public void sendMessageError(String message) {
        if (isOnline())
            getPlayer().sendMessage(BaxAPI.getInstance().getErrorPrefix() + message);
    }

    public void sendMessageInfo(String message) {
        if (isOnline())
            getPlayer().sendMessage(BaxAPI.getInstance().getInfoPrefix() + message);
    }

    public void sendTitle(String title, String subtitle, int fadeIn, int fadeOut, int display) {
        if (isOnline()) {
            PacketPlayOutTitle titlePacket = new PacketPlayOutTitle(PacketPlayOutTitle.EnumTitleAction.TITLE, IChatBaseComponent.ChatSerializer.a(title), fadeIn, display, fadeOut);
            PacketPlayOutTitle subtitlePacket = new PacketPlayOutTitle(PacketPlayOutTitle.EnumTitleAction.TITLE, IChatBaseComponent.ChatSerializer.a(subtitle), fadeIn, display, fadeOut);
            PlayerConnection playerConnection = (((CraftPlayer)getPlayer()).getHandle()).playerConnection;
            playerConnection.sendPacket((Packet)titlePacket);
            playerConnection.sendPacket((Packet)subtitlePacket);
        }
    }

    public void sendActionBar(String message) {
        if (isOnline()) {
            IChatBaseComponent icbc = IChatBaseComponent.ChatSerializer.a("{\"text\": \"" + message + "\"}");
            PacketPlayOutChat bar = new PacketPlayOutChat(icbc, (byte)2);
            (((CraftPlayer)getPlayer()).getHandle()).playerConnection.sendPacket((Packet)bar);
        }
    }

    public int getPing() {
        if (isOnline())
            try {
                return ((Integer)getPlayer().getClass().getMethod("getHandle", new Class[0]).invoke(getPlayer(), new Object[0]).getClass()
                        .getField("ping").get(getPlayer().getClass().getMethod("getHandle", new Class[0]).invoke(getPlayer(), new Object[0]))).intValue();
            } catch (Exception e) {
                return 0;
            }
        return 0;
    }

    public String getName() {
        return Bukkit.getOfflinePlayer(getUUID()).getName();
    }

    public String getDisplayName() {
        return getPlayer().getDisplayName();
    }

    public void setDisplayName(String s) {
        getPlayer().setDisplayName(s);
    }

    public String getPlayerListName() {
        return getPlayer().getPlayerListName();
    }

    public void setPlayerListName(String s) {
        getPlayer().setPlayerListName(s);
    }

    public void setCompassTarget(Location location) {
        getPlayer().setCompassTarget(location);
    }

    public Location getCompassTarget() {
        return getPlayer().getCompassTarget();
    }

    public InetSocketAddress getAddress() {
        return getPlayer().getAddress();
    }

    public void sendRawMessage(String s) {
        getPlayer().sendRawMessage(s);
    }

    public void kickPlayer(String s) {
        getPlayer().kickPlayer(s);
    }

    public void chat(String s) {
        getPlayer().chat(s);
    }

    public boolean performCommand(String s) {
        return getPlayer().performCommand(s);
    }

    public boolean isSneaking() {
        return getPlayer().isSneaking();
    }

    public void setSneaking(boolean b) {
        getPlayer().setSneaking(b);
    }

    public boolean isSprinting() {
        return getPlayer().isSprinting();
    }

    public void setSprinting(boolean b) {
        getPlayer().setSprinting(b);
    }

    public void saveData() {
        getPlayer().saveData();
    }

    public void loadData() {
        getPlayer().loadData();
    }

    public void setSleepingIgnored(boolean b) {
        getPlayer().setSleepingIgnored(b);
    }

    public boolean isSleepingIgnored() {
        return getPlayer().isSleepingIgnored();
    }

    @Deprecated
    public void playNote(Location location, byte b, byte b1) {
        getPlayer().playNote(location, b, b1);
    }

    public void playNote(Location location, Instrument instrument, Note note) {
        getPlayer().playNote(location, instrument, note);
    }

    public void playSound(Location location, Sound sound, float v, float v1) {
        getPlayer().playSound(location, sound, v, v1);
    }

    public void playSound(Location location, String s, float v, float v1) {
        getPlayer().playSound(location, s, v, v1);
    }

    @Deprecated
    public void playEffect(Location location, Effect effect, int i) {
        getPlayer().playEffect(location, effect, i);
    }

    public <T> void playEffect(Location location, Effect effect, T t) {
        getPlayer().playEffect(location, effect, t);
    }

    @Deprecated
    public void sendBlockChange(Location location, Material material, byte b) {
        getPlayer().sendBlockChange(location, material, b);
    }

    @Deprecated
    public boolean sendChunkChange(Location location, int i, int i1, int i2, byte[] bytes) {
        return getPlayer().sendChunkChange(location, i, i1, i2, bytes);
    }

    @Deprecated
    public void sendBlockChange(Location location, int i, byte b) {
        getPlayer().sendBlockChange(location, i, b);
    }

    public void sendSignChange(Location location, String[] strings) throws IllegalArgumentException {
        getPlayer().sendSignChange(location, strings);
    }

    public void sendMap(MapView mapView) {
        getPlayer().sendMap(mapView);
    }

    public void updateInventory() {
        getPlayer().updateInventory();
    }

    public void awardAchievement(Achievement achievement) {
        getPlayer().awardAchievement(achievement);
    }

    public void removeAchievement(Achievement achievement) {
        getPlayer().removeAchievement(achievement);
    }

    public boolean hasAchievement(Achievement achievement) {
        return getPlayer().hasAchievement(achievement);
    }

    public void incrementStatistic(Statistic statistic) throws IllegalArgumentException {
        getPlayer().incrementStatistic(statistic);
    }

    public void decrementStatistic(Statistic statistic) throws IllegalArgumentException {
        getPlayer().decrementStatistic(statistic);
    }

    public void incrementStatistic(Statistic statistic, int i) throws IllegalArgumentException {
        getPlayer().incrementStatistic(statistic, i);
    }

    public void decrementStatistic(Statistic statistic, int i) throws IllegalArgumentException {
        getPlayer().decrementStatistic(statistic, i);
    }

    public void setStatistic(Statistic statistic, int i) throws IllegalArgumentException {
        getPlayer().setStatistic(statistic, i);
    }

    public int getStatistic(Statistic statistic) throws IllegalArgumentException {
        return getPlayer().getStatistic(statistic);
    }

    public void incrementStatistic(Statistic statistic, Material material) throws IllegalArgumentException {
        getPlayer().incrementStatistic(statistic, material);
    }

    public void decrementStatistic(Statistic statistic, Material material) throws IllegalArgumentException {
        getPlayer().decrementStatistic(statistic, material);
    }

    public int getStatistic(Statistic statistic, Material material) throws IllegalArgumentException {
        return getPlayer().getStatistic(statistic, material);
    }

    public void incrementStatistic(Statistic statistic, Material material, int i) throws IllegalArgumentException {
        getPlayer().incrementStatistic(statistic, material, i);
    }

    public void decrementStatistic(Statistic statistic, Material material, int i) throws IllegalArgumentException {
        getPlayer().decrementStatistic(statistic, material, i);
    }

    public void setStatistic(Statistic statistic, Material material, int i) throws IllegalArgumentException {
        getPlayer().setStatistic(statistic, material, i);
    }

    public void incrementStatistic(Statistic statistic, EntityType entityType) throws IllegalArgumentException {
        getPlayer().incrementStatistic(statistic, entityType);
    }

    public void decrementStatistic(Statistic statistic, EntityType entityType) throws IllegalArgumentException {
        getPlayer().decrementStatistic(statistic, entityType);
    }

    public int getStatistic(Statistic statistic, EntityType entityType) throws IllegalArgumentException {
        return getPlayer().getStatistic(statistic, entityType);
    }

    public void incrementStatistic(Statistic statistic, EntityType entityType, int i) throws IllegalArgumentException {
        getPlayer().incrementStatistic(statistic, entityType, i);
    }

    public void decrementStatistic(Statistic statistic, EntityType entityType, int i) {
        getPlayer().decrementStatistic(statistic, entityType, i);
    }

    public void setStatistic(Statistic statistic, EntityType entityType, int i) {
        getPlayer().setStatistic(statistic, entityType, i);
    }

    public void setPlayerTime(long l, boolean b) {
        getPlayer().setPlayerTime(l, b);
    }

    public long getPlayerTime() {
        return getPlayer().getPlayerTime();
    }

    public long getPlayerTimeOffset() {
        return getPlayer().getPlayerTimeOffset();
    }

    public boolean isPlayerTimeRelative() {
        return getPlayer().isPlayerTimeRelative();
    }

    public void resetPlayerTime() {
        getPlayer().resetPlayerTime();
    }

    public void setPlayerWeather(WeatherType weatherType) {
        getPlayer().setPlayerWeather(weatherType);
    }

    public WeatherType getPlayerWeather() {
        return getPlayer().getPlayerWeather();
    }

    public void resetPlayerWeather() {
        getPlayer().resetPlayerWeather();
    }

    public void giveExp(int i) {
        getPlayer().giveExp(i);
    }

    public void giveExpLevels(int i) {
        getPlayer().giveExpLevels(i);
    }

    public float getExp() {
        return getPlayer().getExp();
    }

    public void setExp(float v) {
        getPlayer().setExp(v);
    }

    public int getLevel() {
        return getPlayer().getLevel();
    }

    public void setLevel(int i) {
        getPlayer().setLevel(i);
    }

    public int getTotalExperience() {
        return getPlayer().getTotalExperience();
    }

    public void setTotalExperience(int i) {
        getPlayer().setTotalExperience(i);
    }

    public float getExhaustion() {
        return getPlayer().getExhaustion();
    }

    public void setExhaustion(float v) {
        getPlayer().setExhaustion(v);
    }

    public float getSaturation() {
        return getPlayer().getSaturation();
    }

    public void setSaturation(float v) {
        getPlayer().setSaturation(v);
    }

    public int getFoodLevel() {
        return getPlayer().getFoodLevel();
    }

    public void setFoodLevel(int i) {
        getPlayer().setFoodLevel(i);
    }

    public Location getBedSpawnLocation() {
        return getPlayer().getBedSpawnLocation();
    }

    public void setBedSpawnLocation(Location location) {
        getPlayer().setBedSpawnLocation(location);
    }

    public void setBedSpawnLocation(Location location, boolean b) {
        getPlayer().setBedSpawnLocation(location, b);
    }

    public boolean getAllowFlight() {
        return getPlayer().getAllowFlight();
    }

    public void setAllowFlight(boolean b) {
        getPlayer().setAllowFlight(b);
    }

    public void hidePlayer(Player player) {
        getPlayer().hidePlayer(player);
    }

    public void showPlayer(Player player) {
        getPlayer().showPlayer(player);
    }

    public boolean canSee(Player player) {
        return getPlayer().canSee(player);
    }

    @Deprecated
    public boolean isOnGround() {
        return getPlayer().isOnGround();
    }

    public boolean isFlying() {
        return getPlayer().isFlying();
    }

    public void setFlying(boolean b) {
        getPlayer().setFlying(b);
    }

    public void setFlySpeed(float v) throws IllegalArgumentException {
        getPlayer().setFlySpeed(v);
    }

    public void setWalkSpeed(float v) throws IllegalArgumentException {
        getPlayer().setWalkSpeed(v);
    }

    public float getFlySpeed() {
        return getPlayer().getFlySpeed();
    }

    public float getWalkSpeed() {
        return getPlayer().getWalkSpeed();
    }

    @Deprecated
    public void setTexturePack(String s) {
        getPlayer().setTexturePack(s);
    }

    public void setResourcePack(String s) {
        getPlayer().setResourcePack(s);
    }

    public Scoreboard getScoreboard() {
        return getPlayer().getScoreboard();
    }

    public void setScoreboard(Scoreboard scoreboard) throws IllegalArgumentException, IllegalStateException {
        getPlayer().setScoreboard(scoreboard);
    }

    public boolean isHealthScaled() {
        return getPlayer().isHealthScaled();
    }

    public void setHealthScaled(boolean b) {
        getPlayer().setHealthScaled(b);
    }

    public void setHealthScale(double v) throws IllegalArgumentException {
        getPlayer().setHealthScale(v);
    }

    public double getHealthScale() {
        return getPlayer().getHealthScale();
    }

    public Entity getSpectatorTarget() {
        return getPlayer().getSpectatorTarget();
    }

    public void setSpectatorTarget(Entity entity) {
        getPlayer().setSpectatorTarget(entity);
    }

    @Deprecated
    public void sendTitle(String s, String s1) {
        getPlayer().sendTitle(s, s1);
    }

    @Deprecated
    public void resetTitle() {
        getPlayer().resetTitle();
    }

    public Player.Spigot spigot() {
        return getPlayer().spigot();
    }

    public PlayerInventory getInventory() {
        return getPlayer().getInventory();
    }

    public Inventory getEnderChest() {
        return getPlayer().getEnderChest();
    }

    public boolean setWindowProperty(InventoryView.Property property, int i) {
        return getPlayer().setWindowProperty(property, i);
    }

    public InventoryView getOpenInventory() {
        return getPlayer().getOpenInventory();
    }

    public InventoryView openInventory(Inventory inventory) {
        return getPlayer().openInventory(inventory);
    }

    public InventoryView openWorkbench(Location location, boolean b) {
        return getPlayer().openWorkbench(location, b);
    }

    public InventoryView openEnchanting(Location location, boolean b) {
        return getPlayer().openEnchanting(location, b);
    }

    public void openInventory(InventoryView inventoryView) {
        getPlayer().openInventory(inventoryView);
    }

    public void closeInventory() {
        getPlayer().closeInventory();
    }

    public ItemStack getItemInHand() {
        return getPlayer().getItemInHand();
    }

    public void setItemInHand(ItemStack itemStack) {
        getPlayer().setItemInHand(itemStack);
    }

    public ItemStack getItemOnCursor() {
        return getPlayer().getItemOnCursor();
    }

    public void setItemOnCursor(ItemStack itemStack) {
        getPlayer().setItemOnCursor(itemStack);
    }

    public boolean isSleeping() {
        return getPlayer().isSleeping();
    }

    public int getSleepTicks() {
        return getPlayer().getSleepTicks();
    }

    public GameMode getGameMode() {
        return getPlayer().getGameMode();
    }

    public void setGameMode(GameMode gameMode) {
        getPlayer().setGameMode(gameMode);
    }

    public boolean isBlocking() {
        return getPlayer().isBlocking();
    }

    public int getExpToLevel() {
        return getPlayer().getExpToLevel();
    }

    public double getEyeHeight() {
        return getPlayer().getEyeHeight();
    }

    public double getEyeHeight(boolean b) {
        return getPlayer().getEyeHeight(b);
    }

    public Location getEyeLocation() {
        return getPlayer().getEyeLocation();
    }

    @Deprecated
    public List<Block> getLineOfSight(HashSet<Byte> hashSet, int i) {
        return getPlayer().getLineOfSight(hashSet, i);
    }

    public List<Block> getLineOfSight(Set<Material> set, int i) {
        return getPlayer().getLineOfSight(set, i);
    }

    @Deprecated
    public Block getTargetBlock(HashSet<Byte> hashSet, int i) {
        return getPlayer().getTargetBlock(hashSet, i);
    }

    public Block getTargetBlock(Set<Material> set, int i) {
        return getPlayer().getTargetBlock(set, i);
    }

    @Deprecated
    public List<Block> getLastTwoTargetBlocks(HashSet<Byte> hashSet, int i) {
        return getPlayer().getLastTwoTargetBlocks(hashSet, i);
    }

    public List<Block> getLastTwoTargetBlocks(Set<Material> set, int i) {
        return getPlayer().getLastTwoTargetBlocks(set, i);
    }

    @Deprecated
    public Egg throwEgg() {
        return getPlayer().throwEgg();
    }

    @Deprecated
    public Snowball throwSnowball() {
        return getPlayer().throwSnowball();
    }

    @Deprecated
    public Arrow shootArrow() {
        return getPlayer().shootArrow();
    }

    public int getRemainingAir() {
        return getPlayer().getRemainingAir();
    }

    public void setRemainingAir(int i) {
        getPlayer().setRemainingAir(i);
    }

    public int getMaximumAir() {
        return getPlayer().getMaximumAir();
    }

    public void setMaximumAir(int i) {
        getPlayer().setMaximumAir(i);
    }

    public int getMaximumNoDamageTicks() {
        return getPlayer().getMaximumNoDamageTicks();
    }

    public void setMaximumNoDamageTicks(int i) {
        getPlayer().setMaximumNoDamageTicks(i);
    }

    public double getLastDamage() {
        return getPlayer().getLastDamage();
    }

    public void setLastDamage(double v) {
        getPlayer().setLastDamage(v);
    }

    public int getNoDamageTicks() {
        return getPlayer().getNoDamageTicks();
    }

    public void setNoDamageTicks(int i) {
        getPlayer().setNoDamageTicks(i);
    }

    public Player getKiller() {
        return getPlayer().getKiller();
    }

    public boolean addPotionEffect(PotionEffect potionEffect) {
        return getPlayer().addPotionEffect(potionEffect);
    }

    public boolean addPotionEffect(PotionEffect potionEffect, boolean b) {
        return getPlayer().addPotionEffect(potionEffect, b);
    }

    public boolean addPotionEffects(Collection<PotionEffect> collection) {
        return getPlayer().addPotionEffects(collection);
    }

    public boolean hasPotionEffect(PotionEffectType potionEffectType) {
        return getPlayer().hasPotionEffect(potionEffectType);
    }

    public void removePotionEffect(PotionEffectType potionEffectType) {
        getPlayer().removePotionEffect(potionEffectType);
    }

    public Collection<PotionEffect> getActivePotionEffects() {
        return getPlayer().getActivePotionEffects();
    }

    public boolean hasLineOfSight(Entity entity) {
        return getPlayer().hasLineOfSight(entity);
    }

    public boolean getRemoveWhenFarAway() {
        return getPlayer().getRemoveWhenFarAway();
    }

    public void setRemoveWhenFarAway(boolean b) {
        getPlayer().setRemoveWhenFarAway(b);
    }

    public EntityEquipment getEquipment() {
        return getPlayer().getEquipment();
    }

    public void setCanPickupItems(boolean b) {
        getPlayer().setCanPickupItems(b);
    }

    public boolean getCanPickupItems() {
        return getPlayer().getCanPickupItems();
    }

    public boolean isLeashed() {
        return getPlayer().isLeashed();
    }

    public Entity getLeashHolder() throws IllegalStateException {
        return getPlayer().getLeashHolder();
    }

    public boolean setLeashHolder(Entity entity) {
        return getPlayer().setLeashHolder(entity);
    }

    public Location getLocation() {
        return getPlayer().getLocation();
    }

    public Location getLocation(Location location) {
        return getPlayer().getLocation(location);
    }

    public void setVelocity(Vector vector) {
        getPlayer().setVelocity(vector);
    }

    public Vector getVelocity() {
        return getPlayer().getVelocity();
    }

    public World getWorld() {
        return getPlayer().getWorld();
    }

    public boolean teleport(Location location) {
        return getPlayer().teleport(location);
    }

    public boolean teleport(Location location, PlayerTeleportEvent.TeleportCause teleportCause) {
        return getPlayer().teleport(location, teleportCause);
    }

    public boolean teleport(Entity entity) {
        return getPlayer().teleport(entity);
    }

    public boolean teleport(Entity entity, PlayerTeleportEvent.TeleportCause teleportCause) {
        return getPlayer().teleport(entity, teleportCause);
    }

    public List<Entity> getNearbyEntities(double v, double v1, double v2) {
        return getPlayer().getNearbyEntities(v, v1, v2);
    }

    public int getEntityId() {
        return getPlayer().getEntityId();
    }

    public int getFireTicks() {
        return getPlayer().getFireTicks();
    }

    public int getMaxFireTicks() {
        return getPlayer().getMaxFireTicks();
    }

    public void setFireTicks(int i) {
        getPlayer().setFireTicks(i);
    }

    public void remove() {
        getPlayer().remove();
    }

    public boolean isDead() {
        return getPlayer().isDead();
    }

    public boolean isValid() {
        return getPlayer().isValid();
    }

    public Server getServer() {
        return getPlayer().getServer();
    }

    public Entity getPassenger() {
        return getPlayer().getPassenger();
    }

    public boolean setPassenger(Entity entity) {
        return getPlayer().setPassenger(entity);
    }

    public boolean isEmpty() {
        return getPlayer().isEmpty();
    }

    public boolean eject() {
        return getPlayer().eject();
    }

    public float getFallDistance() {
        return getPlayer().getFallDistance();
    }

    public void setFallDistance(float v) {
        getPlayer().setFallDistance(v);
    }

    public void setLastDamageCause(EntityDamageEvent entityDamageEvent) {
        getPlayer().setLastDamageCause(entityDamageEvent);
    }

    public EntityDamageEvent getLastDamageCause() {
        return getPlayer().getLastDamageCause();
    }

    public UUID getUniqueId() {
        return getPlayer().getUniqueId();
    }

    public int getTicksLived() {
        return getPlayer().getTicksLived();
    }

    public void setTicksLived(int i) {
        getPlayer().setTicksLived(i);
    }

    public void playEffect(EntityEffect entityEffect) {
        getPlayer().playEffect(entityEffect);
    }

    public EntityType getType() {
        return getPlayer().getType();
    }

    public boolean isInsideVehicle() {
        return getPlayer().isInsideVehicle();
    }

    public boolean leaveVehicle() {
        return getPlayer().leaveVehicle();
    }

    public Entity getVehicle() {
        return getPlayer().getVehicle();
    }

    public void setCustomName(String s) {
        getPlayer().setCustomName(s);
    }

    public String getCustomName() {
        return getPlayer().getCustomName();
    }

    public void setCustomNameVisible(boolean b) {
        getPlayer().setCustomNameVisible(b);
    }

    public boolean isCustomNameVisible() {
        return getPlayer().isCustomNameVisible();
    }

    public void setMetadata(String s, MetadataValue metadataValue) {
        getPlayer().setMetadata(s, metadataValue);
    }

    public List<MetadataValue> getMetadata(String s) {
        return getPlayer().getMetadata(s);
    }

    public boolean hasMetadata(String s) {
        return getPlayer().hasMetadata(s);
    }

    public void removeMetadata(String s, Plugin plugin) {
        getPlayer().removeMetadata(s, plugin);
    }

    public void sendMessage(String strings) {
        getPlayer().sendMessage(strings);
    }

    public boolean isPermissionSet(String s) {
        return getPlayer().isPermissionSet(s);
    }

    public boolean isPermissionSet(Permission permission) {
        return getPlayer().isPermissionSet(permission);
    }

    public boolean hasPermission(String s) {
        return getPlayer().hasPermission(s);
    }

    public boolean hasPermission(Permission permission) {
        return getPlayer().hasPermission(permission);
    }

    public PermissionAttachment addAttachment(Plugin plugin, String s, boolean b) {
        return getPlayer().addAttachment(plugin, s, b);
    }

    public PermissionAttachment addAttachment(Plugin plugin) {
        return getPlayer().addAttachment(plugin);
    }

    public PermissionAttachment addAttachment(Plugin plugin, String s, boolean b, int i) {
        return getPlayer().addAttachment(plugin, s, b, i);
    }

    public PermissionAttachment addAttachment(Plugin plugin, int i) {
        return getPlayer().addAttachment(plugin, i);
    }

    public void removeAttachment(PermissionAttachment permissionAttachment) {
        getPlayer().removeAttachment(permissionAttachment);
    }

    public void recalculatePermissions() {
        getPlayer().recalculatePermissions();
    }

    public Set<PermissionAttachmentInfo> getEffectivePermissions() {
        return getPlayer().getEffectivePermissions();
    }

    public boolean isOp() {
        return getPlayer().isOp();
    }

    public void setOp(boolean b) {
        getPlayer().setOp(b);
    }

    public void damage(double v) {
        getPlayer().damage(v);
    }

    public void damage(double v, Entity entity) {
        getPlayer().damage(v, entity);
    }

    public double getHealth() {
        return getPlayer().getHealth();
    }

    public void setHealth(double v) {
        getPlayer().setHealth(v);
    }

    public double getMaxHealth() {
        return getPlayer().getMaxHealth();
    }

    public void setMaxHealth(double v) {
        getPlayer().setMaxHealth(v);
    }

    public void resetMaxHealth() {
        getPlayer().resetMaxHealth();
    }

    public <T extends org.bukkit.entity.Projectile> T launchProjectile(Class<? extends T> aClass) {
        return (T)getPlayer().launchProjectile(aClass);
    }

    public <T extends org.bukkit.entity.Projectile> T launchProjectile(Class<? extends T> aClass, Vector vector) {
        return (T)getPlayer().launchProjectile(aClass, vector);
    }

    public boolean isConversing() {
        return getPlayer().isConversing();
    }

    public void acceptConversationInput(String s) {
        getPlayer().acceptConversationInput(s);
    }

    public boolean beginConversation(Conversation conversation) {
        return getPlayer().beginConversation(conversation);
    }

    public void abandonConversation(Conversation conversation) {
        getPlayer().abandonConversation(conversation);
    }

    public void abandonConversation(Conversation conversation, ConversationAbandonedEvent conversationAbandonedEvent) {
        getPlayer().abandonConversation(conversation, conversationAbandonedEvent);
    }

    public boolean isBanned() {
        return getPlayer().isBanned();
    }

    @Deprecated
    public void setBanned(boolean b) {
        getPlayer().setBanned(b);
    }

    public boolean isWhitelisted() {
        return getPlayer().isWhitelisted();
    }

    public void setWhitelisted(boolean b) {
        getPlayer().setWhitelisted(b);
    }

    public long getFirstPlayed() {
        return getPlayer().getFirstPlayed();
    }

    public long getLastPlayed() {
        return getPlayer().getLastPlayed();
    }

    public boolean hasPlayedBefore() {
        return getPlayer().hasPlayedBefore();
    }

    public Map<String, Object> serialize() {
        return getPlayer().serialize();
    }

    public void sendPluginMessage(Plugin plugin, String s, byte[] bytes) {
        getPlayer().sendPluginMessage(plugin, s, bytes);
    }

    public Set<String> getListeningPluginChannels() {
        return getPlayer().getListeningPluginChannels();
    }
}