package com.aetherteam.genesis.client.particle;

import net.minecraft.client.multiplayer.ClientLevel;
import net.minecraft.client.particle.*;
import net.minecraft.core.particles.SimpleParticleType;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.api.distmarker.OnlyIn;

public class NexSpiritResurrectionParticle extends SimpleAnimatedParticle {
    protected NexSpiritResurrectionParticle(ClientLevel level, double x, double y, double z, double xSpeed, double ySpeed, double zSpeed, SpriteSet sprites) {
        super(level, x, y, z, sprites, 1.25F);
        this.friction = 0.6F;
        this.xd = xSpeed;
        this.yd = ySpeed;
        this.zd = zSpeed;
        this.quadSize *= 0.75F;
        this.lifetime = 60 + this.random.nextInt(12);
        this.setSpriteFromAge(sprites);
        if (this.random.nextInt(4) == 0) {
            this.setColor((180 + this.random.nextInt(20)) / 255.0F, (180 + this.random.nextInt(20)) / 255.0F, (180 + this.random.nextInt(20)) / 255.0F);
        } else {
            this.setColor((80 + this.random.nextInt(30)) / 255.0F, 40 / 255.0F, 40 / 255.0F);
        }
    }

    @OnlyIn(Dist.CLIENT)
    public static class Factory implements ParticleProvider<SimpleParticleType> {
        private final SpriteSet sprites;

        public Factory(SpriteSet sprites) {
            this.sprites = sprites;
        }

        public Particle createParticle(SimpleParticleType type, ClientLevel level, double x, double y, double z, double xSpeed, double ySpeed, double zSpeed) {
            return new NexSpiritResurrectionParticle(level, x, y, z, xSpeed, ySpeed, zSpeed, this.sprites);
        }
    }
}
