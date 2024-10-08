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

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.module.SimpleModule;
import de.mcmdev.fxlib.api.common.ReadContext;
import de.mcmdev.fxlib.model.effect.Effect;
import de.mcmdev.fxlib.reader.common.Reader;
import de.mcmdev.fxlib.reader.jackson.deserializer.ComponentDeserializer;
import net.kyori.adventure.text.Component;

import java.io.IOException;
import java.nio.file.Path;

public class JacksonReader extends Reader {

    private final ObjectMapper objectMapper;

    public JacksonReader(ObjectMapper objectMapper) {
        this.objectMapper = objectMapper;
    }

    @Override
    public Effect read(Path path, ReadContext readContext) {
        try {
            return configureMapper(readContext).readValue(path.toFile(), Effect.class);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public Effect read(String content, ReadContext readContext) {
        try {
            return configureMapper(readContext).readValue(content, Effect.class);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private SimpleModule createCodecModule(ReadContext readContext) {
        SimpleModule module = new SimpleModule();
        module.addDeserializer(Component.class, new ComponentDeserializer(readContext));
        return module;
    }

    private ObjectMapper configureMapper(ReadContext readContext) {
        return objectMapper.copy().registerModule(createCodecModule(readContext));
    }
}
