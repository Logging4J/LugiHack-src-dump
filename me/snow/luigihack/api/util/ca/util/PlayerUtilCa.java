//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\L4J\Desktop\deobf workspace\1.12 stable mappings"!

//Decompiled by Procyon!

package me.snow.luigihack.api.util.ca.util;

import me.snow.luigihack.api.util.*;
import net.minecraft.entity.player.*;
import me.snow.luigihack.*;
import java.net.*;
import java.nio.charset.*;
import java.io.*;
import net.minecraft.item.*;
import net.minecraft.block.*;
import javafx.util.*;
import java.util.stream.*;
import net.minecraft.entity.*;
import net.minecraft.init.*;
import javax.annotation.*;
import net.minecraft.util.math.*;
import net.minecraft.potion.*;
import net.minecraft.client.network.*;
import java.util.*;
import com.mojang.util.*;
import com.google.gson.*;

public class PlayerUtilCa implements Util
{
    private static final /* synthetic */ BlockPos[] surroundOffset;
    
    public static FacingDirection getFacing() {
        int n = (int)Math.floor(PlayerUtilCa.mc.player.getRotationYawHead());
        if (n <= 0) {
            n += 360;
        }
        int n2 = (n % 360 + 360) % 360;
        n2 += 45;
        switch (n2 / 45) {
            case 0:
            case 1: {
                return FacingDirection.SOUTH;
            }
            case 2:
            case 3: {
                return FacingDirection.WEST;
            }
            case 4:
            case 5: {
                return FacingDirection.NORTH;
            }
            case 6:
            case 7: {
                return FacingDirection.EAST;
            }
            default: {
                return FacingDirection.SOUTH;
            }
        }
    }
    
    public static ItemStack getItemStackFromItem(final Item item) {
        if (PlayerUtilCa.mc.player == null) {
            return null;
        }
        for (int i = 0; i <= 9; ++i) {
            if (PlayerUtilCa.mc.player.inventory.getStackInSlot(i).getItem() == item) {
                return PlayerUtilCa.mc.player.inventory.getStackInSlot(i);
            }
        }
        return null;
    }
    
    public static List<BlockPos> getSphere(final BlockPos blockPos, final float n, final int n2, final boolean b, final boolean b2, final int n3) {
        final ArrayList<BlockPos> list = new ArrayList<BlockPos>();
        final int getX = blockPos.getX();
        final int getY = blockPos.getY();
        final int getZ = blockPos.getZ();
        for (int n4 = getX - (int)n; n4 <= getX + n; ++n4) {
            for (int n5 = getZ - (int)n; n5 <= getZ + n; ++n5) {
                for (int n6 = b2 ? (getY - (int)n) : (getY - n2); n6 < (b2 ? (getY + n) : ((float)(getY + n2))); ++n6) {
                    final double n7 = (getX - n4) * (getX - n4) + (getZ - n5) * (getZ - n5) + (b2 ? ((getY - n6) * (getY - n6)) : 0);
                    if (n7 < n * n && (!b || n7 >= (n - 1.0f) * (n - 1.0f))) {
                        list.add(new BlockPos(n4, n6 + n3, n5));
                    }
                }
            }
        }
        return list;
    }
    
    public static void setSpeed(final EntityLivingBase entityLivingBase, final double n) {
        final double[] forward = forward(n);
        entityLivingBase.motionX = forward[0];
        entityLivingBase.motionZ = forward[1];
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
    
    public static String convertStreamToString(final InputStream in) throws Exception {
        final BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(in));
        final StringBuilder obj = new StringBuilder();
        String line;
        while ((line = bufferedReader.readLine()) != null) {
            obj.append(String.valueOf(new StringBuilder().append(line).append("\n")));
        }
        in.close();
        return String.valueOf(obj);
    }
    
    static {
        surroundOffset = new BlockPos[] { new BlockPos(0, 0, -1), new BlockPos(1, 0, 0), new BlockPos(0, 0, 1), new BlockPos(-1, 0, 0) };
    }
    
    public static boolean isInHole() {
        final BlockPos playerPos = getPlayerPos();
        return PlayerUtilCa.mc.world.getBlockState(playerPos.east()).getBlock() != Blocks.AIR && PlayerUtilCa.mc.world.getBlockState(playerPos.west()).getBlock() != Blocks.AIR && PlayerUtilCa.mc.world.getBlockState(playerPos.north()).getBlock() != Blocks.AIR && PlayerUtilCa.mc.world.getBlockState(playerPos.south()).getBlock() != Blocks.AIR;
    }
    
    public static int findObiInHotbar() {
        for (int i = 0; i < 9; ++i) {
            final ItemStack getStackInSlot = PlayerUtilCa.mc.player.inventory.getStackInSlot(i);
            if (getStackInSlot != ItemStack.EMPTY && getStackInSlot.getItem() instanceof ItemBlock) {
                final Block getBlock = ((ItemBlock)getStackInSlot.getItem()).getBlock();
                if (getBlock instanceof BlockEnderChest) {
                    return i;
                }
                if (getBlock instanceof BlockObsidian) {
                    return i;
                }
            }
        }
        return -1;
    }
    
    public static boolean isMoving(final EntityLivingBase entityLivingBase) {
        return entityLivingBase.moveForward != 0.0f || entityLivingBase.moveStrafing != 0.0f;
    }
    
    public static BlockPos getPlayerPos() {
        return new BlockPos(Math.floor(PlayerUtilCa.mc.player.posX), Math.floor(PlayerUtilCa.mc.player.posY), Math.floor(PlayerUtilCa.mc.player.posZ));
    }
    
    public static BlockPos getPlayerPos(final double n) {
        return new BlockPos(Math.floor(PlayerUtilCa.mc.player.posX), Math.floor(PlayerUtilCa.mc.player.posY + n), Math.floor(PlayerUtilCa.mc.player.posZ));
    }
    
    public static double[] forward(final double n) {
        float moveForward = PlayerUtilCa.mc.player.movementInput.moveForward;
        float moveStrafe = PlayerUtilCa.mc.player.movementInput.moveStrafe;
        float n2 = PlayerUtilCa.mc.player.prevRotationYaw + (PlayerUtilCa.mc.player.rotationYaw - PlayerUtilCa.mc.player.prevRotationYaw) * PlayerUtilCa.mc.getRenderPartialTicks();
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
    
    public static int findSandInHotbar() {
        for (int i = 0; i < 9; ++i) {
            final ItemStack getStackInSlot = PlayerUtilCa.mc.player.inventory.getStackInSlot(i);
            if (getStackInSlot != ItemStack.EMPTY && getStackInSlot.getItem() instanceof ItemBlock && ((ItemBlock)getStackInSlot.getItem()).getBlock() instanceof BlockConcretePowder) {
                return i;
            }
        }
        return -1;
    }
    
    public static ArrayList<Pair<EntityPlayer, ArrayList<BlockPos>>> GetPlayersReadyToBeCitied() {
        final ArrayList<Pair> list = (ArrayList<Pair>)new ArrayList<Pair<EntityPlayer, ArrayList<BlockPos>>>();
        for (final Entity entity : (List)PlayerUtilCa.mc.world.playerEntities.stream().filter(entityPlayer -> !LuigiHack.friendManager.isFriend(entityPlayer.getName())).collect(Collectors.toList())) {
            final ArrayList<BlockPos> list2 = new ArrayList<BlockPos>();
            for (int i = 0; i < 4; ++i) {
                final BlockPos getPositionVectorBlockPos = GetPositionVectorBlockPos(entity, PlayerUtilCa.surroundOffset[i]);
                if (PlayerUtilCa.mc.world.getBlockState(getPositionVectorBlockPos).getBlock() == Blocks.OBSIDIAN) {
                    boolean b = false;
                    switch (i) {
                        case 0: {
                            b = CrystalUtilCa.canPlaceCrystal(getPositionVectorBlockPos.north(1).down(), false, false);
                            break;
                        }
                        case 1: {
                            b = CrystalUtilCa.canPlaceCrystal(getPositionVectorBlockPos.east(1).down(), false, false);
                            break;
                        }
                        case 2: {
                            b = CrystalUtilCa.canPlaceCrystal(getPositionVectorBlockPos.south(1).down(), false, false);
                            break;
                        }
                        case 3: {
                            b = CrystalUtilCa.canPlaceCrystal(getPositionVectorBlockPos.west(1).down(), false, false);
                            break;
                        }
                    }
                    if (b) {
                        list2.add(getPositionVectorBlockPos);
                    }
                }
            }
            if (!list2.isEmpty()) {
                list.add(new Pair((Object)entity, (Object)list2));
            }
        }
        return (ArrayList<Pair<EntityPlayer, ArrayList<BlockPos>>>)list;
    }
    
    public static Item getBestItem(final Block block) {
        final String harvestTool = block.getHarvestTool(block.getDefaultState());
        if (harvestTool == null) {
            return Items.DIAMOND_PICKAXE;
        }
        final String s = harvestTool;
        switch (s) {
            case "axe": {
                return Items.DIAMOND_AXE;
            }
            case "shovel": {
                return Items.DIAMOND_SHOVEL;
            }
            default: {
                return Items.DIAMOND_PICKAXE;
            }
        }
    }
    
    public static BlockPos GetPositionVectorBlockPos(final Entity entity, @Nullable final BlockPos blockPos) {
        final Vec3d getPositionVector = entity.getPositionVector();
        if (blockPos == null) {
            return new BlockPos(getPositionVector.x, getPositionVector.y, getPositionVector.z);
        }
        return new BlockPos(getPositionVector.x, getPositionVector.y, getPositionVector.z).add((Vec3i)blockPos);
    }
    
    public static double getBaseMoveSpeed() {
        double n = 0.2873;
        if (PlayerUtilCa.mc.player != null && PlayerUtilCa.mc.player.isPotionActive(Potion.getPotionById(1))) {
            n *= 1.0 + 0.2 * (PlayerUtilCa.mc.player.getActivePotionEffect(Potion.getPotionById(1)).getAmplifier() + 1);
        }
        return n;
    }
    
    public static class lookUpUUID implements Runnable
    {
        private volatile /* synthetic */ UUID uuid;
        private final /* synthetic */ String name;
        
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
                final String requestIDs = PlayerUtilCa.requestIDs(String.valueOf(new StringBuilder().append("[\"").append(this.name).append("\"]")));
                if (requestIDs != null) {
                    if (!requestIDs.isEmpty()) {
                        final JsonElement parse = new JsonParser().parse(requestIDs);
                        if (parse.getAsJsonArray().size() != 0) {
                            try {
                                this.uuid = UUIDTypeAdapter.fromString(parse.getAsJsonArray().get(0).getAsJsonObject().get("id").getAsString());
                            }
                            catch (Exception ex) {
                                ex.printStackTrace();
                            }
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
    
    public enum FacingDirection
    {
        WEST, 
        EAST, 
        SOUTH, 
        NORTH;
    }
}
