package com.aetherteam.aether_genesis.capability;

import com.aetherteam.aether_genesis.network.packet.ZephyrColorSyncPacket;
import com.aetherteam.nitrogen.attachment.INBTSynchable;
import com.aetherteam.nitrogen.network.BasePacket;
import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import org.apache.commons.lang3.tuple.Triple;

import java.util.Map;
import java.util.function.Consumer;
import java.util.function.Supplier;

public class ZephyrColorAttachment implements INBTSynchable {
    private boolean isTan = false;

    private final Map<String, Triple<Type, Consumer<Object>, Supplier<Object>>> synchableFunctions = Map.ofEntries(
            Map.entry("setTan", Triple.of(Type.BOOLEAN, (object) -> this.setTan((boolean) object), this::isTan))
    );

    public static final Codec<ZephyrColorAttachment> CODEC = RecordCodecBuilder.create(instance -> instance.group(
            Codec.BOOL.fieldOf("is_tan").forGetter(ZephyrColorAttachment::isTan)
    ).apply(instance, ZephyrColorAttachment::new));

    public ZephyrColorAttachment() {

    }

    private ZephyrColorAttachment(boolean isTan) {
        this.isTan = isTan;
    }

    @Override
    public Map<String, Triple<Type, Consumer<Object>, Supplier<Object>>> getSynchableFunctions() {
        return this.synchableFunctions;
    }

    public void setTan(boolean tan) {
        this.isTan = tan;
    }

    public boolean isTan() {
        return this.isTan;
    }

    public BasePacket getSyncPacket(int entityID, String key, Type type, Object value) {
        return new ZephyrColorSyncPacket(entityID, key, type, value);
    }
}
