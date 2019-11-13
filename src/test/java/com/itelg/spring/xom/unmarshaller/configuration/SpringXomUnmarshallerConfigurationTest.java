package com.itelg.spring.xom.unmarshaller.configuration;

import static org.junit.Assert.assertTrue;

import java.util.Collections;

import org.junit.Test;

import com.itelg.spring.xom.unmarshaller.XomUnmarshaller;
import com.itelg.spring.xom.unmarshaller.test.parser.RootTagByAnnotationParser;

public class SpringXomUnmarshallerConfigurationTest
{
    private SpringXomUnmarshallerConfiguration configuration = new SpringXomUnmarshallerConfiguration();

    @Test
    public void testXomUnmarshaller()
    {
        XomUnmarshaller xomUnmarshaller = configuration.xomUnmarshaller(Collections.singletonList(new RootTagByAnnotationParser()), xml -> xml);
        assertTrue(xomUnmarshaller.supports(Long.class));
    }
}
