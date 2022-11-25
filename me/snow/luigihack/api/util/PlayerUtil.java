//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\L4J\Desktop\deobf workspace\1.12 stable mappings"!

//Decompiled by Procyon!

package me.snow.luigihack.api.util;

import net.minecraft.entity.*;
import net.minecraft.network.play.client.*;
import net.minecraft.network.*;
import java.net.*;
import java.nio.charset.*;
import javax.net.ssl.*;
import net.minecraft.advancements.*;
import net.minecraft.item.*;
import net.minecraft.block.*;
import com.google.common.collect.*;
import com.google.gson.*;
import net.minecraft.entity.player.*;
import me.snow.luigihack.*;
import net.minecraft.util.math.*;
import net.minecraft.client.entity.*;
import net.minecraft.client.*;
import org.apache.commons.io.*;
import org.json.simple.*;
import java.io.*;
import org.json.simple.parser.*;
import net.minecraft.client.network.*;
import java.util.*;
import me.snow.luigihack.impl.command.*;
import com.mojang.util.*;

public class PlayerUtil implements Util
{
    private static /* synthetic */ JsonParser PARSER;
    public static /* synthetic */ Timer timer;
    
    private static JsonElement getResources(final URL url, final String s) throws Exception {
        return getResources(url, s, null);
    }
    
    public static double[] directionSpeed(final double n) {
        float moveForward = PlayerUtil.mc.player.movementInput.moveForward;
        float moveStrafe = PlayerUtil.mc.player.movementInput.moveStrafe;
        float n2 = PlayerUtil.mc.player.prevRotationYaw + (PlayerUtil.mc.player.rotationYaw - PlayerUtil.mc.player.prevRotationYaw) * PlayerUtil.mc.getRenderPartialTicks();
        if (moveForward != 0.0f) {
            if (moveStrafe > 0.0f) {
                n2 += ((moveForward > 0.0f) ? -45 : 45);
            }
            else if (moveStrafe < 0.0f) {
                n2 += ((moveForward > 0.0f) ? 45 : -45);
            }
            moveStrafe = 0.0f;
            if (moveForward > 0.0f) {
                moveForward = 1.0f;
            }
            else if (moveForward < 0.0f) {
                moveForward = -1.0f;
            }
        }
        final double sin = Math.sin(Math.toRadians(n2 + 90.0f));
        final double cos = Math.cos(Math.toRadians(n2 + 90.0f));
        return new double[] { moveForward * n * cos + moveStrafe * n * sin, moveForward * n * sin - moveStrafe * n * cos };
    }
    
    public static boolean isInHole(final Entity entity) {
        final BlockPos blockPos = new BlockPos(Math.floor(entity.getPositionVector().x), Math.floor(entity.getPositionVector().y + 0.2), Math.floor(entity.getPositionVector().z));
        return PlayerUtil.mc.world.getBlockState(blockPos.down()).getBlock().blockResistance >= 1200.0f && PlayerUtil.mc.world.getBlockState(blockPos.east()).getBlock().blockResistance >= 1200.0f && PlayerUtil.mc.world.getBlockState(blockPos.west()).getBlock().blockResistance >= 1200.0f && PlayerUtil.mc.world.getBlockState(blockPos.north()).getBlock().blockResistance >= 1200.0f && PlayerUtil.mc.world.getBlockState(blockPos.south()).getBlock().blockResistance >= 1200.0f;
    }
    
    public static UUID getUUIDFromName(final String s) {
        try {
            final lookUpUUID target = new lookUpUUID(s);
            final Thread thread = new Thread(target);
            thread.start();
            thread.join();
            return target.getUUID();
        }
        catch (Exception ex) {
            return null;
        }
    }
    
    public static boolean IsEating() {
        return PlayerUtil.mc.player != null && PlayerUtil.mc.player.getHeldItemMainhand().getItem() instanceof ItemFood && PlayerUtil.mc.player.isHandActive();
    }
    
    public static void faceVector(final Vec3d vec3d, final boolean b) {
        final float[] legitRotations = EntityUtil.getLegitRotations(vec3d);
        Util.mc.player.connection.sendPacket((Packet)new CPacketPlayer.Rotation(legitRotations[0], b ? ((float)MathHelper.normalizeAngle((int)legitRotations[1], 360)) : legitRotations[1], Util.mc.player.onGround));
    }
    
    public static String convertStreamToString(final InputStream source) {
        final Scanner useDelimiter = new Scanner(source).useDelimiter("\\A");
        return useDelimiter.hasNext() ? useDelimiter.next() : "/";
    }
    
    public static String requestIDs(final String s) {
        try {
            final HttpURLConnection httpURLConnection = (HttpURLConnection)new URL("https://api.mojang.com/profiles/minecraft").openConnection();
            httpURLConnection.setConnectTimeout(5000);
            httpURLConnection.setRequestProperty("Content-Type", "application/json; charset=UTF-8");
            httpURLConnection.setDoOutput(true);
            httpURLConnection.setDoInput(true);
            httpURLConnection.setRequestMethod("POST");
            final OutputStream outputStream = httpURLConnection.getOutputStream();
            outputStream.write(s.getBytes(StandardCharsets.UTF_8));
            outputStream.close();
            final BufferedInputStream bufferedInputStream = new BufferedInputStream(httpURLConnection.getInputStream());
            final String convertStreamToString = convertStreamToString(bufferedInputStream);
            bufferedInputStream.close();
            httpURLConnection.disconnect();
            return convertStreamToString;
        }
        catch (Exception ex) {
            return null;
        }
    }
    
    public static BlockPos getPlayerPosFloored() {
        return new BlockPos(Math.floor(PlayerUtil.mc.player.posX), Math.floor(PlayerUtil.mc.player.posY), Math.floor(PlayerUtil.mc.player.posZ));
    }
    
    private static JsonElement getResources(final URL url, final String requestMethod, final JsonElement jsonElement) throws Exception {
        HttpURLConnection httpURLConnection = null;
        try {
            httpURLConnection = (HttpsURLConnection)url.openConnection();
            httpURLConnection.setDoOutput(true);
            httpURLConnection.setRequestMethod(requestMethod);
            httpURLConnection.setRequestProperty("Content-Type", "application/json");
            if (jsonElement != null) {
                final DataOutputStream dataOutputStream = new DataOutputStream(httpURLConnection.getOutputStream());
                dataOutputStream.writeBytes(AdvancementManager.GSON.toJson(jsonElement));
                dataOutputStream.close();
            }
            final Scanner scanner = new Scanner(httpURLConnection.getInputStream());
            final StringBuilder obj = new StringBuilder();
            while (scanner.hasNextLine()) {
                obj.append(scanner.nextLine());
                obj.append('\n');
            }
            scanner.close();
            return PlayerUtil.PARSER.parse(String.valueOf(obj));
        }
        finally {
            if (httpURLConnection != null) {
                httpURLConnection.disconnect();
            }
        }
    }
    
    public static int findObiInHotbar() {
        for (int i = 0; i < 9; ++i) {
            final ItemStack getStackInSlot = PlayerUtil.mc.player.inventory.getStackInSlot(i);
            if (getStackInSlot != ItemStack.EMPTY) {
                if (getStackInSlot.getItem() instanceof ItemBlock) {
                    final Block getBlock = ((ItemBlock)getStackInSlot.getItem()).getBlock();
                    if (getBlock instanceof BlockEnderChest) {
                        return i;
                    }
                    if (getBlock instanceof BlockObsidian) {
                        return i;
                    }
                }
            }
        }
        return -1;
    }
    
    public static List<String> getHistoryOfNames(final UUID uuid) {
        try {
            final JsonArray asJsonArray = getResources(new URL(String.valueOf(new StringBuilder().append("https://api.mojang.com/user/profiles/").append(getIdNoHyphens(uuid)).append("/names"))), "GET").getAsJsonArray();
            final ArrayList arrayList = Lists.newArrayList();
            final Iterator iterator = asJsonArray.iterator();
            while (iterator.hasNext()) {
                final JsonObject asJsonObject = iterator.next().getAsJsonObject();
                arrayList.add(String.valueOf(new StringBuilder().append(asJsonObject.get("name").getAsString()).append("§8").append(new Date(asJsonObject.has("changedToAt") ? asJsonObject.get("changedToAt").getAsLong() : 0L))));
            }
            Collections.sort(arrayList);
            return (List<String>)arrayList;
        }
        catch (Exception ex) {
            return null;
        }
    }
    
    public static String getNameFromUUID(final UUID uuid) {
        try {
            final lookUpName target = new lookUpName(uuid);
            final Thread thread = new Thread(target);
            thread.start();
            thread.join();
            return target.getName();
        }
        catch (Exception ex) {
            return null;
        }
    }
    
    public static double getDistance(final Entity entity) {
        return PlayerUtil.mc.player.getDistance(entity);
    }
    
    public static String getNameFromUUID(final String s) {
        try {
            final lookUpName target = new lookUpName(s);
            final Thread thread = new Thread(target);
            thread.start();
            thread.join();
            return target.getName();
        }
        catch (Exception ex) {
            return null;
        }
    }
    
    public static boolean isPlayerMoving() {
        return PlayerUtil.mc.player.movementInput.moveStrafe != 0.0f || PlayerUtil.mc.player.movementInput.moveForward != 0.0f;
    }
    
    public static double getDistance(final Vec3d vec3d) {
        return PlayerUtil.mc.player.getDistance(vec3d.x, vec3d.y, vec3d.z);
    }
    
    public static double getDistance(final BlockPos blockPos) {
        return PlayerUtil.mc.player.getDistance((double)blockPos.getX(), (double)blockPos.getY(), (double)blockPos.getZ());
    }
    
    public static BlockPos getPlayerPos() {
        return new BlockPos(Math.floor(PlayerUtil.mc.player.posX), Math.floor(PlayerUtil.mc.player.posY), Math.floor(PlayerUtil.mc.player.posZ));
    }
    
    public static EntityPlayer findLookingPlayer(final double n) {
        final ArrayList<EntityPlayer> list = new ArrayList<EntityPlayer>();
        for (final EntityPlayer e : PlayerUtil.mc.world.playerEntities) {
            if (!e.getName().equals(PlayerUtil.mc.player.getName()) && !LuigiHack.friendManager.isFriend(e.getName())) {
                if (e.isDead) {
                    continue;
                }
                if (PlayerUtil.mc.player.getDistance((Entity)e) > n) {
                    continue;
                }
                list.add(e);
            }
        }
        EntityPlayer entityPlayer = null;
        final Vec3d getPositionEyes = PlayerUtil.mc.player.getPositionEyes(PlayerUtil.mc.getRenderPartialTicks());
        final Vec3d getLook = PlayerUtil.mc.player.getLook(PlayerUtil.mc.getRenderPartialTicks());
        final int n2 = 2;
        for (int i = 0; i < (int)n; ++i) {
            for (int j = n2; j > 0; --j) {
                for (final Entity entity : list) {
                    final AxisAlignedBB getEntityBoundingBox = entity.getEntityBoundingBox();
                    final double n3 = getPositionEyes.x + getLook.x * i + getLook.x / j;
                    final double n4 = getPositionEyes.y + getLook.y * i + getLook.y / j;
                    final double n5 = getPositionEyes.z + getLook.z * i + getLook.z / j;
                    if (getEntityBoundingBox.maxY >= n4 && getEntityBoundingBox.minY <= n4 && getEntityBoundingBox.maxX >= n3 && getEntityBoundingBox.minX <= n3 && getEntityBoundingBox.maxZ >= n5 && getEntityBoundingBox.minZ <= n5) {
                        entityPlayer = (EntityPlayer)entity;
                    }
                }
            }
        }
        return entityPlayer;
    }
    
    public static String getIdNoHyphens(final UUID uuid) {
        return uuid.toString().replaceAll("-", "");
    }
    
    static {
        PlayerUtil.timer = new Timer();
        PlayerUtil.PARSER = new JsonParser();
    }
    
    public static double[] directionSpeed(final double n, final EntityPlayerSP entityPlayerSP) {
        final Minecraft getMinecraft = Minecraft.getMinecraft();
        float moveForward = entityPlayerSP.movementInput.moveForward;
        float moveStrafe = entityPlayerSP.movementInput.moveStrafe;
        float n2 = entityPlayerSP.prevRotationYaw + (entityPlayerSP.rotationYaw - entityPlayerSP.prevRotationYaw) * getMinecraft.getRenderPartialTicks();
        if (moveForward != 0.0f) {
            if (moveStrafe > 0.0f) {
                n2 += ((moveForward > 0.0f) ? -45 : 45);
            }
            else if (moveStrafe < 0.0f) {
                n2 += ((moveForward > 0.0f) ? 45 : -45);
            }
            moveStrafe = 0.0f;
            if (moveForward > 0.0f) {
                moveForward = 1.0f;
            }
            else if (moveForward < 0.0f) {
                moveForward = -1.0f;
            }
        }
        final double sin = Math.sin(Math.toRadians(n2 + 90.0f));
        final double cos = Math.cos(Math.toRadians(n2 + 90.0f));
        return new double[] { moveForward * n * cos + moveStrafe * n * sin, moveForward * n * sin - moveStrafe * n * cos };
    }
    
    public static class lookUpName implements Runnable
    {
        private final /* synthetic */ UUID uuidID;
        private volatile /* synthetic */ String name;
        private final /* synthetic */ String uuid;
        
        @Override
        public void run() {
            this.name = this.lookUpName();
        }
        
        public String getName() {
            return this.name;
        }
        
        public String lookUpName() {
            EntityPlayer getPlayerEntityByUUID = null;
            if (Util.mc.world != null) {
                getPlayerEntityByUUID = Util.mc.world.getPlayerEntityByUUID(this.uuidID);
            }
            if (getPlayerEntityByUUID == null) {
                final String value = String.valueOf(new StringBuilder().append("https://api.mojang.com/user/profiles/").append(this.uuid.replace("-", "")).append("/names"));
                try {
                    final JSONArray jsonArray = (JSONArray)JSONValue.parseWithException(IOUtils.toString(new URL(value)));
                    return ((JSONObject)JSONValue.parseWithException(jsonArray.get(jsonArray.size() - 1).toString())).get((Object)"name").toString();
                }
                catch (IOException | ParseException ex) {
                    final Throwable t;
                    t.printStackTrace();
                    return null;
                }
            }
            return getPlayerEntityByUUID.getName();
        }
        
        public lookUpName(final String s) {
            this.uuid = s;
            this.uuidID = UUID.fromString(s);
        }
        
        public lookUpName(final UUID uuidID) {
            this.uuidID = uuidID;
            this.uuid = uuidID.toString();
        }
    }
    
    public static class lookUpUUID implements Runnable
    {
        private final /* synthetic */ String name;
        private volatile /* synthetic */ UUID uuid;
        
        public lookUpUUID(final String name) {
            this.name = name;
        }
        
        @Override
        public void run() {
            NetworkPlayerInfo networkPlayerInfo2;
            try {
                networkPlayerInfo2 = new ArrayList<NetworkPlayerInfo>(Objects.requireNonNull(Util.mc.getConnection()).getPlayerInfoMap()).stream().filter(networkPlayerInfo -> networkPlayerInfo.getGameProfile().getName().equalsIgnoreCase(this.name)).findFirst().orElse(null);
                assert networkPlayerInfo2 != null;
                this.uuid = networkPlayerInfo2.getGameProfile().getId();
            }
            catch (Exception ex2) {
                networkPlayerInfo2 = null;
            }
            if (networkPlayerInfo2 == null) {
                Command.sendMessage("Player isn't online. Looking up UUID..");
                final String requestIDs = PlayerUtil.requestIDs(String.valueOf(new StringBuilder().append("[\"").append(this.name).append("\"]")));
                if (requestIDs == null || requestIDs.isEmpty()) {
                    Command.sendMessage("Couldn't find player ID. Are you connected to the internet? (0)");
                }
                else {
                    final JsonElement parse = new JsonParser().parse(requestIDs);
                    if (parse.getAsJsonArray().size() == 0) {
                        Command.sendMessage("Couldn't find player ID. (1)");
                    }
                    else {
                        try {
                            this.uuid = UUIDTypeAdapter.fromString(parse.getAsJsonArray().get(0).getAsJsonObject().get("id").getAsString());
                        }
                        catch (Exception ex) {
                            ex.printStackTrace();
                            Command.sendMessage("Couldn't find player ID. (2)");
                        }
                    }
                }
            }
        }
        
        public UUID getUUID() {
            return this.uuid;
        }
        
        public String getName() {
            return this.name;
        }
    }
}
