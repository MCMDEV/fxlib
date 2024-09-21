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
import de.mcmdev.fxlib.serializer.TitleTimesSerializer;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.title.Title;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.spongepowered.configurate.ConfigurationNode;
import org.spongepowered.configurate.serialize.SerializationException;

public class FxTitlePart extends FxPart {

    private final Component title;
    private final Component subtitle;
    private final Title.Times times;

    public FxTitlePart(FxAudience audience, Component title, Component subtitle, Title.Times times) {
        super(audience);
        this.title = title;
        this.subtitle = subtitle;
        this.times = times;
    }

    @Override
    public void play(Player player, Location location) {
        player.showTitle(Title.title(title, subtitle, times));
    }

    public static class Serializer extends FxPartSerializer<FxTitlePart> {

        @Override
        public FxTitlePart deserialize(ConfigurationNode node, FxAudience audience) throws SerializationException {
            Component component = ComponentSerializer.INSTANCE.deserialize(node.node("title"));
            Component subtitle = ComponentSerializer.INSTANCE.deserialize(node.node("subtitle"));
            Title.Times times = TitleTimesSerializer.INSTANCE.deserialize(node.node("times"));
            return new FxTitlePart(audience, component, subtitle, times);
        }
    }
}
