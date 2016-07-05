package com.itelg.spring.xom.unmarshaller.configuration;

import java.util.Collections;

import org.junit.Assert;
import org.junit.Test;

import com.itelg.spring.xom.unmarshaller.XomUnmarshaller;
import com.itelg.spring.xom.unmarshaller.parser.PlaceholderParser;

public class SpringXomUnmarshallerConfigurationTest
{
    private SpringXomUnmarshallerConfiguration configuration = new SpringXomUnmarshallerConfiguration();
    
    @Test
    public void testXomUnmarshaller()
    {
        PlaceholderParser parser = new PlaceholderParser();
        XomUnmarshaller xomUnmarshaller = configuration.xomUnmarshaller(Collections.singletonList(parser));
        Assert.assertTrue(xomUnmarshaller.supports(XomUnmarshaller.class));
    }
    
    @Test
    public void testPlaceholderParser()
    {
        Assert.assertEquals(new PlaceholderParser().getClass(), configuration.placeholderParser().getClass());
    }
}