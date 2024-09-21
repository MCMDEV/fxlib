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

import de.mcmdev.fxlib.audience.FxAudience;
import de.mcmdev.fxlib.serializer.ComponentSerializer;
import de.mcmdev.fxlib.serializer.FxPartSerializer;
import net.kyori.adventure.text.Component;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.spongepowered.configurate.ConfigurationNode;
import org.spongepowered.configurate.serialize.SerializationException;

public class FxMessagePart extends FxPart {

    private final Component message;

    public FxMessagePart(FxAudience audience, Component message) {
        super(audience);
        this.message = message;
    }

    @Override
    protected void play(Player target, Location location) {
        target.sendMessage(message);
    }

    public static class Serializer extends FxPartSerializer<FxMessagePart> {

        @Override
        public FxMessagePart deserialize(ConfigurationNode node, FxAudience audience) throws SerializationException {
            Component component = ComponentSerializer.INSTANCE.deserialize(node.node("message"));
            return new FxMessagePart(audience, component);
        }
    }
}
