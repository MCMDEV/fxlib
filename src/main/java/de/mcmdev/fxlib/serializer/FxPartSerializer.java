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

package de.mcmdev.fxlib.serializer;

import de.mcmdev.fxlib.audience.FxAudience;
import de.mcmdev.fxlib.part.FxPart;
import org.spongepowered.configurate.ConfigurationNode;
import org.spongepowered.configurate.serialize.SerializationException;

public abstract class FxPartSerializer<T extends FxPart> {

    public T deserializeWithDefault(ConfigurationNode node, FxAudience defaultAudience) throws SerializationException {
        ConfigurationNode audienceNode = node.node("audience");
        if(audienceNode.virtual()) {
            if(defaultAudience == null) {
                throw new SerializationException("No audience provided");
            }
            return deserialize(node, defaultAudience);
        }
        return deserialize(node, FxAudienceSerializer.INSTANCE.deserialize(audienceNode));
    }

    public abstract T deserialize(ConfigurationNode node, FxAudience audience) throws SerializationException;
}
