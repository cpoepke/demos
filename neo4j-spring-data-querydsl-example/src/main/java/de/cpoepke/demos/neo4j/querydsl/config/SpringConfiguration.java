/*
 * Project       neo4j-spring-data-querydsl-example
 *
 * The MIT License (MIT)
 *
 * Copyright (c) 2013 Conrad Poepke
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy of
 * this software and associated documentation files (the "Software"), to deal in
 * the Software without restriction, including without limitation the rights to
 * use, copy, modify, merge, publish, distribute, sublicense, and/or sell copies of
 * the Software, and to permit persons to whom the Software is furnished to do so,
 * subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY, FITNESS
 * FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR
 * COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY, WHETHER
 * IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM, OUT OF OR IN
 * CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE SOFTWARE.
 *
 *
 * $Last modified: Mi, 28 Aug 2013 22:16:17 +0200 $
 * $Author: cpoepke $
 */

package de.cpoepke.demos.neo4j.querydsl.config;

import de.cpoepke.demos.neo4j.querydsl.converter.DateMidnightToLongConverter;
import de.cpoepke.demos.neo4j.querydsl.converter.DateMidnightToStringConverter;
import de.cpoepke.demos.neo4j.querydsl.converter.LongToDateMidnightConverter;
import de.cpoepke.demos.neo4j.querydsl.converter.StringToDateMidnightConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.core.convert.converter.Converter;
import org.springframework.data.convert.JodaTimeConverters;
import org.springframework.format.datetime.joda.JodaTimeFormatterRegistrar;
import org.springframework.format.number.NumberFormatAnnotationFormatterFactory;
import org.springframework.format.support.DefaultFormattingConversionService;

/**
 * User: Conrad Pöpke
 * Date: 22.08.13
 * Time: 02:07
 */
@Configuration
@Import(Neo4JConfiguration.class)
public class SpringConfiguration {

    @Bean
    public DefaultFormattingConversionService conversionService() {

        // Use the DefaultFormattingConversionService but do not register defaults
        DefaultFormattingConversionService conversionService = new DefaultFormattingConversionService(false);

        // Ensure @NumberFormat is still supported
        conversionService.addFormatterForFieldAnnotation(new NumberFormatAnnotationFormatterFactory());

        // Joda Time Converter
        for (Converter<?, ?> converter : JodaTimeConverters.getConvertersToRegister()) {
            conversionService.addConverter(converter);
        }
        conversionService.addConverter(new DateMidnightToStringConverter());
        conversionService.addConverter(new StringToDateMidnightConverter());
        conversionService.addConverter(new DateMidnightToLongConverter());
        conversionService.addConverter(new LongToDateMidnightConverter());

        // Register date conversion with a specific global format
        JodaTimeFormatterRegistrar registrar = new JodaTimeFormatterRegistrar();
        registrar.setUseIsoFormat(true);
        registrar.registerFormatters(conversionService);

        return conversionService;
    }
}
