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

public class ParticlePart extends Part {

    private final String particle;
    private final double offsetX;
    private final double offsetY;
    private final double offsetZ;
    private final double deltaX;
    private final double deltaY;
    private final double deltaZ;
    private final int count;
    private final double extra;

    @JsonCreator
    public ParticlePart(@JsonProperty("settings") Settings settings, @JsonProperty("particle") String particle, @JsonProperty("offsetX") double offsetX, @JsonProperty("offsetY") double offsetY, @JsonProperty("offsetZ") double offsetZ, @JsonProperty("deltaX") double deltaX, @JsonProperty("deltaY") double deltaY, @JsonProperty("deltaZ") double deltaZ, @JsonProperty("count") int count, @JsonProperty("extra") double extra) {
        super(settings);
        this.particle = particle;
        this.offsetX = offsetX;
        this.offsetY = offsetY;
        this.offsetZ = offsetZ;
        this.deltaX = deltaX;
        this.deltaY = deltaY;
        this.deltaZ = deltaZ;
        this.count = count;
        this.extra = extra;
    }

    public String getParticle() {
        return particle;
    }

    public double getOffsetX() {
        return offsetX;
    }

    public double getOffsetY() {
        return offsetY;
    }

    public double getOffsetZ() {
        return offsetZ;
    }

    public double getDeltaX() {
        return deltaX;
    }

    public double getDeltaY() {
        return deltaY;
    }

    public double getDeltaZ() {
        return deltaZ;
    }

    public int getCount() {
        return count;
    }

    public double getExtra() {
        return extra;
    }

    @Override
    public String toString() {
        return "ParticlePart{" +
                "particle='" + particle + '\'' +
                ", offsetX=" + offsetX +
                ", offsetY=" + offsetY +
                ", offsetZ=" + offsetZ +
                ", deltaX=" + deltaX +
                ", deltaY=" + deltaY +
                ", deltaZ=" + deltaZ +
                ", count=" + count +
                ", extra=" + extra +
                '}';
    }
}
