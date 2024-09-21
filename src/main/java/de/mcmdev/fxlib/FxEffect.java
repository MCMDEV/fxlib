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

import de.mcmdev.fxlib.context.FxDeserializationContext;
import de.mcmdev.fxlib.settings.FxSettings;
import de.mcmdev.fxlib.context.FxRuntimeContext;
import de.mcmdev.fxlib.part.FxPart;
import de.mcmdev.fxlib.registry.FxRegistry;
import de.mcmdev.fxlib.serializer.FxSettingsSerializer;
import de.mcmdev.fxlib.serializer.FxPartSerializer;
import org.spongepowered.configurate.ConfigurationNode;
import org.spongepowered.configurate.serialize.SerializationException;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class FxEffect {

    public static FxEffect EMPTY = new FxEffect(new FxSettings(false, 0), Collections.emptyList());

    public static FxEffect deserialize(ConfigurationNode node) throws SerializationException {
        return deserialize(node, FxDeserializationContext.EMPTY);
    }

    public static FxEffect deserialize(ConfigurationNode node, FxDeserializationContext deserializationContext) throws SerializationException {
        List<FxPart> parts = new ArrayList<>();
        List<ConfigurationNode> childNodes = node.node("parts").getList(ConfigurationNode.class);
        if(childNodes == null) {
            return FxEffect.EMPTY;
        }

        FxSettings defaultSettings = FxSettingsSerializer.INSTANCE.deserialize(node.node("settings"));
        for (ConfigurationNode childNode : childNodes) {
            parts.add(deserializePart(childNode, deserializationContext, defaultSettings));
        }

        return new FxEffect(defaultSettings, parts);
    }

    private static FxPart deserializePart(ConfigurationNode childNode, FxDeserializationContext deserializationContext, FxSettings defaultSettings) throws SerializationException {
        String string = childNode.node("type").getString();
        FxPartSerializer<?> serializer = FxRegistry.getSerializer(string);
        if (serializer == null) {
            throw new SerializationException("Unknown effect part type: " + string);
        }
        return serializer.deserializeWithDefault(childNode, deserializationContext, defaultSettings);
    }

    private final FxSettings settings;
    private final List<FxPart> parts;

    public FxEffect(FxSettings settings, List<FxPart> parts) {
        this.settings = settings;
        this.parts = parts;
    }

    public void play(FxRuntimeContext context)   {
        for (FxPart part : parts) {
            part.play(context);
        }
    }

    public FxSettings getSettings() {
        return settings;
    }

    public List<FxPart> getParts() {
        return parts;
    }
}
