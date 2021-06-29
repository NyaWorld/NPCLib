/*
 * Copyright (c) 2018 Jitse Boonstra
 */

package net.jitse.npclib.nms.v1_17_R1.packets;

import com.comphenix.tinyprotocol.Reflection;
import net.minecraft.network.protocol.game.PacketPlayOutNamedEntitySpawn;
import net.minecraft.network.syncher.DataWatcher;
import net.minecraft.network.syncher.DataWatcherObject;
import net.minecraft.network.syncher.DataWatcherRegistry;
import org.bukkit.Location;

import java.util.UUID;

/**
 * @author Jitse Boonstra
 */
public class PacketPlayOutNamedEntitySpawnWrapper {

    public PacketPlayOutNamedEntitySpawn create(UUID uuid, Location location, int entityId) {
        PacketPlayOutNamedEntitySpawn packetPlayOutNamedEntitySpawn = new PacketPlayOutNamedEntitySpawn();

        Reflection.getField(packetPlayOutNamedEntitySpawn.getClass(), "a", int.class)
                .set(packetPlayOutNamedEntitySpawn, entityId);
        Reflection.getField(packetPlayOutNamedEntitySpawn.getClass(), "b", UUID.class)
                .set(packetPlayOutNamedEntitySpawn, uuid);
        Reflection.getField(packetPlayOutNamedEntitySpawn.getClass(), "c", double.class)
                .set(packetPlayOutNamedEntitySpawn, location.getX());
        Reflection.getField(packetPlayOutNamedEntitySpawn.getClass(), "d", double.class)
                .set(packetPlayOutNamedEntitySpawn, location.getY());
        Reflection.getField(packetPlayOutNamedEntitySpawn.getClass(), "e", double.class)
                .set(packetPlayOutNamedEntitySpawn, location.getZ());
        Reflection.getField(packetPlayOutNamedEntitySpawn.getClass(), "f", byte.class)
                .set(packetPlayOutNamedEntitySpawn, (byte) ((int) (location.getYaw() * 256.0F / 360.0F)));
        Reflection.getField(packetPlayOutNamedEntitySpawn.getClass(), "g", byte.class)
                .set(packetPlayOutNamedEntitySpawn, (byte) ((int) (location.getPitch() * 256.0F / 360.0F)));

        DataWatcher dataWatcher = new DataWatcher(null);
        dataWatcher.register(new DataWatcherObject<>(16, DataWatcherRegistry.a), (byte) 127);

        return packetPlayOutNamedEntitySpawn;
    }
}
