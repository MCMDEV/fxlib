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

package de.mcmdev.fxlib;

import de.mcmdev.fxlib.audience.FxAudience;
import de.mcmdev.fxlib.part.FxPart;
import de.mcmdev.fxlib.registry.FxRegistry;
import de.mcmdev.fxlib.serializer.FxAudienceSerializer;
import de.mcmdev.fxlib.serializer.FxPartSerializer;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.spongepowered.configurate.ConfigurationNode;
import org.spongepowered.configurate.serialize.SerializationException;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class FxEffect {

    public static FxEffect EMPTY = new FxEffect(new FxAudience(false, 0), Collections.emptyList());

    public static FxEffect deserialize(ConfigurationNode node) throws SerializationException {
        List<FxPart> parts = new ArrayList<>();
        List<ConfigurationNode> childNodes = node.node("parts").getList(ConfigurationNode.class);
        if(childNodes == null) {
            return FxEffect.EMPTY;
        }

        FxAudience defaultAudience = FxAudienceSerializer.INSTANCE.deserialize(node.node("audience"));
        for (ConfigurationNode childNode : childNodes) {
            parts.add(deserializePart(childNode, defaultAudience));
        }

        return new FxEffect(defaultAudience, parts);
    }

    private static FxPart deserializePart(ConfigurationNode childNode, FxAudience defaultAudience) throws SerializationException {
        String string = childNode.node("type").getString();
        FxPartSerializer<?> serializer = FxRegistry.getSerializer(string);
        if (serializer == null) {
            throw new SerializationException("Unknown effect part type: " + string);
        }
        return serializer.deserializeWithDefault(childNode, defaultAudience);
    }

    private final FxAudience audience;
    private final List<FxPart> parts;

    public FxEffect(FxAudience audience, List<FxPart> parts) {
        this.audience = audience;
        this.parts = parts;
    }

    public void play(Player primaryTarget, Location location)   {
        for (FxPart part : parts) {
            part.playAudience(primaryTarget, location);
        }
    }

    public FxAudience getAudience() {
        return audience;
    }

    public List<FxPart> getParts() {
        return parts;
    }
}
