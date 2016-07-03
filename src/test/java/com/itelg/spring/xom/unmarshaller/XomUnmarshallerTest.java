package com.itelg.spring.xom.unmarshaller;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import javax.xml.transform.stream.StreamSource;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.springframework.core.io.ClassPathResource;
import org.springframework.oxm.Unmarshaller;
import org.springframework.oxm.UnmarshallingFailureException;

import com.itelg.spring.xom.unmarshaller.parser.Parser;
import com.itelg.spring.xom.unmarshaller.parser.RootTagMatcherParser;
import com.itelg.spring.xom.unmarshaller.parser.RootTagTypeMatchingDisableParser;

public class XomUnmarshallerTest
{
    private Unmarshaller unmarshaller;

    @Before
    public void init()
    {
        List<Parser<?>> parsers = new ArrayList<>();
        parsers.add(new RootTagMatcherParser());
        parsers.add(new RootTagTypeMatchingDisableParser());
        unmarshaller = new XomUnmarshaller(parsers);
    }

    @Test
    public void testSupports()
    {
        Assert.assertTrue(unmarshaller.supports(String.class));
        Assert.assertTrue(unmarshaller.supports(Integer.class));
        Assert.assertFalse(unmarshaller.supports(Double.class));
    }

    @Test
    public void testUnmarshallString() throws IOException
    {
        try (InputStream inputStream = new ClassPathResource("string.xml").getInputStream())
        {
            String value = (String) unmarshaller.unmarshal(new StreamSource(inputStream));
            Assert.assertEquals("test", value);
        }
    }
    
    @Test
    public void testUnmarshallNumber() throws IOException
    {
        try (InputStream inputStream = new ClassPathResource("number.xml").getInputStream())
        {
            Integer value = (Integer) unmarshaller.unmarshal(new StreamSource(inputStream));
            Assert.assertEquals(Integer.valueOf(12), value);
        }
    }
    
    @Test(expected = UnmarshallingFailureException.class)
    public void testUnmarshallLong() throws IOException
    {
        try (InputStream inputStream = new ClassPathResource("long.xml").getInputStream())
        {
            unmarshaller.unmarshal(new StreamSource(inputStream));
            Assert.fail("unmarshaller should fail!");
        }
    }
}