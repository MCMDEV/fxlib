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

package de.mcmdev.fxlib.runtime.paper;

import com.destroystokyo.paper.ParticleBuilder;
import de.mcmdev.fxlib.api.paper.PaperRuntimeContext;
import de.mcmdev.fxlib.model.parts.ParticlePart;
import de.mcmdev.fxlib.model.settings.Settings;
import de.mcmdev.fxlib.runtime.common.Displayer;
import org.bukkit.Particle;
import org.bukkit.entity.Player;

import java.util.Collection;

public class ParticleDisplayer extends Displayer<Player, PaperRuntimeContext, ParticlePart> {
    @Override
    public void display(PaperRuntimeContext runtimeContext, ParticlePart part, Settings settings) {
        Collection<Player> players = SettingsFilter.filterBySettings(runtimeContext, settings);
        new ParticleBuilder(KeyedEnumUtil.fromKey(Particle.class, part.getParticle()))
                .location(runtimeContext.getLocation().clone().add(part.getOffsetX(), part.getOffsetY(), part.getOffsetZ()))
                .count(part.getCount())
                .offset(part.getDeltaX(), part.getDeltaY(), part.getDeltaZ())
                .extra(part.getExtra())
                .receivers(players)
                .spawn();
    }
}
