/*
 * MIT License
 *
 * Copyright (c) 2024 MCMDEV
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 *
 */

package de.mcmdev.fxlib.part;

import com.destroystokyo.paper.ParticleBuilder;
import de.mcmdev.fxlib.context.FxDeserializationContext;
import de.mcmdev.fxlib.context.FxRuntimeContext;
import de.mcmdev.fxlib.settings.FxSettings;
import de.mcmdev.fxlib.serializer.FxPartSerializer;
import de.mcmdev.fxlib.util.KeyedEnumUtil;
import org.bukkit.Particle;
import org.bukkit.entity.Player;
import org.spongepowered.configurate.ConfigurationNode;
import org.spongepowered.configurate.serialize.SerializationException;

public class FxParticlePart extends PreparedFxPart {

    private final Particle particle;
    private final double offsetX;
    private final double offsetY;
    private final double offsetZ;
    private final double deltaX;
    private final double deltaY;
    private final double deltaZ;
    private final int count;
    private final double extra;

    public FxParticlePart(FxSettings audience, Particle particle, double offsetX, double offsetY, double offsetZ, double deltaX, double deltaY, double deltaZ, int count, double extra) {
        super(audience);
        this.particle = particle;
        this.deltaX = deltaX;
        this.deltaY = deltaY;
        this.deltaZ = deltaZ;
        this.offsetX = offsetX;
        this.offsetY = offsetY;
        this.offsetZ = offsetZ;
        this.count = count;
        this.extra = extra;
    }

    @Override
    protected void play(Player target, FxRuntimeContext context, FxSettings settings) {
        new ParticleBuilder(particle)
                .count(count)
                .extra(extra)
                .offset(deltaX, deltaY, deltaZ)
                .location(context.getLocation().clone().add(offsetX, offsetY, offsetZ))
                .receivers(target)
                .spawn();
    }

    public static class Serializer extends FxPartSerializer<FxParticlePart> {

        @Override
        public FxParticlePart deserialize(ConfigurationNode node, FxDeserializationContext deserializationContext, FxSettings settings) throws SerializationException {
            Particle particle = KeyedEnumUtil.fromKey(Particle.class, node.node("key").getString(), Particle.HAPPY_VILLAGER);
            double offsetX = node.node("offsetX").getDouble(0);
            double offsetY = node.node("offsetY").getDouble(0);
            double offsetZ = node.node("offsetZ").getDouble(0);
            double deltaX = node.node("deltaX").getDouble(0);
            double deltaY = node.node("deltaY").getDouble(0);
            double deltaZ = node.node("deltaZ").getDouble(0);
            int count = node.node("count").getInt(1);
            double extra = node.node("extra").getDouble(0);
            return new FxParticlePart(settings, particle, offsetX, offsetY, offsetZ, deltaX, deltaY, deltaZ, count, extra);
        }
    }
}
