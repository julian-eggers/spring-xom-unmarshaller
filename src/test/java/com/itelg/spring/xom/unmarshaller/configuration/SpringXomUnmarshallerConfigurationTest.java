package com.itelg.spring.xom.unmarshaller.configuration;

import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Collections;

import org.junit.jupiter.api.Test;

import com.itelg.spring.xom.unmarshaller.XomUnmarshaller;
import com.itelg.spring.xom.unmarshaller.test.parser.RootTagByAnnotationParser;

class SpringXomUnmarshallerConfigurationTest
{
    private SpringXomUnmarshallerConfiguration configuration = new SpringXomUnmarshallerConfiguration();

    @Test
    void testXomUnmarshaller()
    {
        XomUnmarshaller xomUnmarshaller = configuration.xomUnmarshaller(Collections.singletonList(new RootTagByAnnotationParser()));
        assertTrue(xomUnmarshaller.supports(Long.class));
    }
}
