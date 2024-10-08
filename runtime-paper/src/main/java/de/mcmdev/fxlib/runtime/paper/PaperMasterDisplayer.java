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

import de.mcmdev.fxlib.api.paper.PaperRuntimeContext;
import de.mcmdev.fxlib.model.parts.*;
import de.mcmdev.fxlib.model.settings.Settings;
import de.mcmdev.fxlib.runtime.common.MasterDisplayer;
import org.bukkit.entity.Player;

public class PaperMasterDisplayer extends MasterDisplayer<Player, PaperRuntimeContext> {

    @Override
    public void display(PaperRuntimeContext runtimeContext, Part part, Settings settings) {
        switch (part) {
            case ActionbarPart actionbarPart -> new ActionbarDisplayer().display(runtimeContext, actionbarPart, settings);
            case MessagePart messagePart -> new MessageDisplayer().display(runtimeContext, messagePart, settings);
            case ParticlePart particlePart -> new ParticleDisplayer().display(runtimeContext, particlePart, settings);
            case SoundPart soundPart -> new SoundDisplayer().display(runtimeContext, soundPart, settings);
            case TitlePart titlePart -> new TitleDisplayer().display(runtimeContext, titlePart, settings);
            default -> throw new IllegalStateException("Unexpected value: " + part);
        }
    }
}
