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

package de.mcmdev.fxlib.reader.jackson;

import com.fasterxml.jackson.dataformat.yaml.YAMLMapper;
import de.mcmdev.fxlib.model.effect.Effect;
import de.mcmdev.fxlib.model.parts.*;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.NamedTextColor;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class JacksonReaderTest {

    private static final String TEXT_EFFECT = """
          # Settings define how the effect should be displayed.
          settings:
            # If global is true, the effect will be displayed to all players within range.
            # If false, the effect will only be displayed to the player who triggered it.
            global: true
            # The radius defines how far away the effect can be seen. Only used if global is true.
            radius: 20
          # Parts define the individual components of the effect.
          # There is no limit to the number of parts that can be included.
          parts:
            - type: actionbar
              # The message to display.
              message: '<red>Hello!'
            - type: message
              # The message to display.
              message: 'Hello!'
            - type: particle
              # The particle to display.
              particle: 'minecraft:flame'
              # Moves the origin of the particle in the x, y, and z directions. \s
              offsetX: 0.5
              offsetY: 0.5
              offsetZ: 0.5
              # Causes the particle to move in the x, y, and z directions.
              deltaX: 0.1
              deltaY: 0.1
              deltaZ: 0.1
              # The number of particles to display.
              count: 100
              # The extra numeric data value for the particle. Usually used for speed.
              extra: 0.1
            - type: sound
              # The sound to play.
              sound: 'minecraft:entity.experience_orb.pickup'
              # The origin of the sound. Can be 'master', 'music', 'record', 'weather', 'block', 'hostile', 'neutral', 'player', 'ambient', or 'voice'.
              source: 'master'
              # The volume of the sound. 1.0 is normal volume. Can be between 0.0 and 10.0.
              volume: 1.0
              # The pitch of the sound. 1.0 is normal pitch. Can be between 0.5 and 2.0.
              pitch: 1.2
            - type: title
              # The title to display.
              title: '<yellow>Title'
              # The subtitle to display.
              subtitle: '<red>Subtitle'
              # The number of seconds to fade in.
              fadeIn: 20
              # The number of seconds to stay.
              stay: 40
              # The number of seconds to fade out.
              fadeOut: 20
              # Global settings can be overridden for individual parts.
              settings:
                global: false
                radius: 10
            """;

    @Test
    public void testReader()    {
        JacksonReader reader = new JacksonReader(new YAMLMapper());
        Effect effect = reader.read(TEXT_EFFECT, new StubReadContext());

        // Global settings
        Assertions.assertTrue(effect.getSettings().isGlobal());
        Assertions.assertEquals(20, effect.getSettings().getRadius());

        // Actionbar
        Assertions.assertInstanceOf(ActionbarPart.class, effect.getParts().get(0));
        Assertions.assertEquals(Component.text("Hello!", NamedTextColor.RED), ((ActionbarPart) effect.getParts().get(0)).getMessage());

        // Message
        Assertions.assertInstanceOf(MessagePart.class, effect.getParts().get(1));
        Assertions.assertEquals(Component.text("Hello!"), ((MessagePart) effect.getParts().get(1)).getMessage());

        // Particle
        Assertions.assertInstanceOf(ParticlePart.class, effect.getParts().get(2));
        Assertions.assertEquals("minecraft:flame", ((ParticlePart) effect.getParts().get(2)).getParticle());
        Assertions.assertEquals(0.5, ((ParticlePart) effect.getParts().get(2)).getOffsetX());
        Assertions.assertEquals(0.5, ((ParticlePart) effect.getParts().get(2)).getOffsetY());
        Assertions.assertEquals(0.5, ((ParticlePart) effect.getParts().get(2)).getOffsetZ());
        Assertions.assertEquals(0.1, ((ParticlePart) effect.getParts().get(2)).getDeltaX());
        Assertions.assertEquals(0.1, ((ParticlePart) effect.getParts().get(2)).getDeltaY());
        Assertions.assertEquals(0.1, ((ParticlePart) effect.getParts().get(2)).getDeltaZ());
        Assertions.assertEquals(100, ((ParticlePart) effect.getParts().get(2)).getCount());
        Assertions.assertEquals(0.1, ((ParticlePart) effect.getParts().get(2)).getExtra());

        // Sound
        Assertions.assertInstanceOf(SoundPart.class, effect.getParts().get(3));
        Assertions.assertEquals("minecraft:entity.experience_orb.pickup", ((SoundPart) effect.getParts().get(3)).getSound());
        Assertions.assertEquals("master", ((SoundPart) effect.getParts().get(3)).getSource());
        Assertions.assertEquals(1.0, ((SoundPart) effect.getParts().get(3)).getVolume(), 0.01);
        Assertions.assertEquals(1.2, ((SoundPart) effect.getParts().get(3)).getPitch(), 0.01);

        // Title
        Assertions.assertInstanceOf(TitlePart.class, effect.getParts().get(4));
        Assertions.assertEquals(Component.text("Title", NamedTextColor.YELLOW), ((TitlePart) effect.getParts().get(4)).getTitle());
        Assertions.assertEquals(Component.text("Subtitle", NamedTextColor.RED), ((TitlePart) effect.getParts().get(4)).getSubtitle());
        Assertions.assertEquals(20, ((TitlePart) effect.getParts().get(4)).getFadeIn());
        Assertions.assertEquals(40, ((TitlePart) effect.getParts().get(4)).getStay());
        Assertions.assertEquals(20, ((TitlePart) effect.getParts().get(4)).getFadeOut());
        // Global settings can be overridden for individual parts.
        Assertions.assertFalse(effect.getParts().get(4).getSettings().isGlobal());
        Assertions.assertEquals(10, effect.getParts().get(4).getSettings().getRadius());
    }

}
