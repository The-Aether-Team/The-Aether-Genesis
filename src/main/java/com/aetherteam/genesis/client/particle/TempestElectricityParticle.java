package com.aetherteam.genesis.client.particle;

import net.minecraft.client.multiplayer.ClientLevel;
import net.minecraft.client.particle.*;
import net.minecraft.client.renderer.LightTexture;
import net.minecraft.core.particles.SimpleParticleType;
import net.minecraft.util.Mth;
import net.minecraft.world.phys.Vec3;
import org.joml.Vector3f;

public class TempestElectricityParticle extends TextureSheetParticle {
    private final SpriteSet sprite;

    protected TempestElectricityParticle(ClientLevel level, double x, double y, double z, double xSpeed, double ySpeed, double zSpeed, SpriteSet sprite) {
        super(level, x, y, z, xSpeed, ySpeed, zSpeed);
        this.friction = 0.96F;
        this.speedUpWhenYMotionIsBlocked = true;
        this.sprite = sprite;
        this.xd *= 0.1F;
        this.yd *= 0.1F;
        this.zd *= 0.1F;
        float f = this.random.nextFloat() * 0.4F + 0.6F;
        Vector3f color = Vec3.fromRGB24(14283007).toVector3f();
        this.rCol = this.randomizeColor(color.x(), f);
        this.gCol = this.randomizeColor(color.y(), f);
        this.bCol = this.randomizeColor(color.z(), f);
        this.quadSize *= 0.75F * 2.0;
        int i = (int) (8.0 / (this.random.nextDouble() * 0.8 + 0.2));
        this.lifetime = (int) Math.max(i * 2.0, 1.0F);
        this.setSpriteFromAge(sprite);
    }

    @Override
    public ParticleRenderType getRenderType() {
        return ParticleRenderType.PARTICLE_SHEET_TRANSLUCENT;
    }

    @Override
    public float getQuadSize(float scaleFactor) {
        return this.quadSize * Mth.clamp(((float) this.age + scaleFactor) / (float) this.lifetime * 32.0F, 0.0F, 1.0F);
    }

    @Override
    public void tick() {
        super.tick();
        this.setSpriteFromAge(this.sprite);
    }

    @Override
    public int getLightColor(float partialTick) {
        return LightTexture.FULL_BRIGHT;
    }

    protected float randomizeColor(float coordMultiplier, float multiplier) {
        return (0.2F + 0.8F) * coordMultiplier * multiplier;
    }

    public static class Factory implements ParticleProvider<SimpleParticleType> {
        private final SpriteSet sprite;

        public Factory(SpriteSet sprite) {
            this.sprite = sprite;
        }

        @Override
        public Particle createParticle(SimpleParticleType type, ClientLevel level, double x, double y, double z, double xSpeed, double ySpeed, double zSpeed) {
            TempestElectricityParticle particle = new TempestElectricityParticle(level, x, y, z, xSpeed, ySpeed, zSpeed, this.sprite);
            particle.pickSprite(this.sprite);
            return particle;
        }
    }
}
