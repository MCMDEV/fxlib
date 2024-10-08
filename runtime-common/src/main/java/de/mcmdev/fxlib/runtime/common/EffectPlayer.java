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

package de.mcmdev.fxlib.runtime.common;

import de.mcmdev.fxlib.api.common.RuntimeContext;
import de.mcmdev.fxlib.model.effect.Effect;
import de.mcmdev.fxlib.model.parts.Part;
import de.mcmdev.fxlib.model.settings.Settings;

public class EffectPlayer<Player, Context extends RuntimeContext<Player>> {

    private final MasterDisplayer<Player, Context> masterDisplayer;

    public EffectPlayer(MasterDisplayer<Player, Context> masterDisplayer) {
        this.masterDisplayer = masterDisplayer;
    }

    public void play(Context runtimeContext, Effect effectModel) {
        Settings settings = effectModel.getSettings();
        if(settings != null)    {
            settings = Settings.DEFAULT;
        }
        for (Part part : effectModel.getParts()) {
            Settings partSettings = part.getSettings();
            if(partSettings != null)    {
                partSettings = settings;
            }
            masterDisplayer.display(runtimeContext, part, partSettings);
        }
    }

}
