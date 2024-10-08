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

package de.mcmdev.fxlib.plugin.paper;

import com.fasterxml.jackson.dataformat.yaml.YAMLMapper;
import de.mcmdev.fxlib.api.common.EffectReader;
import de.mcmdev.fxlib.api.paper.PaperContextFactory;
import de.mcmdev.fxlib.api.paper.PaperRuntimeContext;
import de.mcmdev.fxlib.runtime.paper.PaperContextFactoryImpl;
import de.mcmdev.fxlib.runtime.paper.PaperMasterDisplayer;
import de.mcmdev.fxlib.reader.jackson.JacksonReader;
import de.mcmdev.fxlib.runtime.common.EffectPlayer;
import de.mcmdev.fxlib.runtime.common.EffectReaderImpl;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.plugin.ServicePriority;
import org.bukkit.plugin.java.JavaPlugin;

public class FxLibPaperPlugin extends JavaPlugin {

    private PaperContextFactory paperContextFactory;
    private EffectReader<Player, PaperRuntimeContext> effectReader;

    @Override
    public void onEnable() {
        this.paperContextFactory = new PaperContextFactoryImpl();
        this.effectReader = new EffectReaderImpl<>(new JacksonReader(new YAMLMapper()), new EffectPlayer<>(new PaperMasterDisplayer()));

        Bukkit.getServicesManager().register(PaperContextFactory.class, paperContextFactory, this, ServicePriority.Normal);
        Bukkit.getServicesManager().register(EffectReader.class, effectReader, this, ServicePriority.Normal);
    }

    @Override
    public void onDisable() {
        Bukkit.getServicesManager().unregister(this.effectReader);
        Bukkit.getServicesManager().unregister(this.paperContextFactory);
    }
}
