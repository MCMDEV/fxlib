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

package de.mcmdev.fxlib.model.parts;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import de.mcmdev.fxlib.model.settings.Settings;
import net.kyori.adventure.key.Key;
import net.kyori.adventure.sound.Sound;

public class SoundPart extends Part {

    private final String sound;
    private final String source;
    private final float volume;
    private final float pitch;

    @JsonCreator
    public SoundPart(@JsonProperty("settings") Settings settings, @JsonProperty("sound") String sound, @JsonProperty("source") String source, @JsonProperty("volume") float volume, @JsonProperty("pitch") float pitch) {
        super(settings);
        this.sound = sound;
        this.source = source;
        this.volume = volume;
        this.pitch = pitch;
    }

    public Sound buildSound() {
        return Sound.sound().type(Key.key(sound)).source(Sound.Source.NAMES.value(source)).pitch(pitch).volume(volume).build();
    }

    public String getSound() {
        return sound;
    }

    public String getSource() {
        return source;
    }

    public float getVolume() {
        return volume;
    }

    public float getPitch() {
        return pitch;
    }

    @Override
    public String toString() {
        return "SoundPart{" +
                "sound='" + sound + '\'' +
                ", source='" + source + '\'' +
                ", volume=" + volume +
                ", pitch=" + pitch +
                '}';
    }
}
