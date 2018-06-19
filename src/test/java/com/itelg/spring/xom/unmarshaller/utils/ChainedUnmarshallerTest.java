package com.itelg.spring.xom.unmarshaller.utils;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.util.Arrays;
import java.util.Collections;

import javax.xml.transform.Source;
import javax.xml.transform.stream.StreamSource;

import org.junit.Test;
import org.springframework.oxm.Unmarshaller;
import org.springframework.oxm.UnmarshallingFailureException;
import org.springframework.oxm.XmlMappingException;

import com.itelg.spring.xom.unmarshaller.XomUnmarshaller;
import com.itelg.spring.xom.unmarshaller.parser.Parser;
import com.itelg.spring.xom.unmarshaller.utils.ChainedUnmarshaller;
import com.itelg.xpath.helper.XPathHelper;

import nu.xom.Element;

public class ChainedUnmarshallerTest
{
    private Unmarshaller stringIntegerUnmarshaller = new ChainedUnmarshaller(Arrays.asList(new StringUnmarshaller(), new IntegerUnmarshaller()));

    private Unmarshaller integerStringUnmarshaller = new ChainedUnmarshaller(Arrays.asList(new IntegerUnmarshaller(), new StringUnmarshaller()));

    private String stringXml = "<String><value>abc</value></String>";

    private String integerXml = "<Integer><value>123</value></Integer>";

    @Test
    public void testSupports()
    {
        assertFalse(stringIntegerUnmarshaller.supports(Double.class));
        assertTrue(stringIntegerUnmarshaller.supports(String.class));
        assertTrue(stringIntegerUnmarshaller.supports(Integer.class));

        assertFalse(integerStringUnmarshaller.supports(Double.class));
        assertTrue(integerStringUnmarshaller.supports(String.class));
        assertTrue(integerStringUnmarshaller.supports(Integer.class));
    }

    @Test
    public void testUnmarshal() throws XmlMappingException, IOException
    {
        assertEquals("abc", stringIntegerUnmarshaller.unmarshal(createSource(stringXml)));
        assertEquals("abc", integerStringUnmarshaller.unmarshal(createSource(stringXml)));
        assertEquals(Integer.valueOf(123), stringIntegerUnmarshaller.unmarshal(createSource(integerXml)));
        assertEquals(Integer.valueOf(123), integerStringUnmarshaller.unmarshal(createSource(integerXml)));
    }

    @Test
    public void testUnmarshalWithException()
    {
        try
        {
            stringIntegerUnmarshaller.unmarshal(createSource("123"));
            fail("exception expected");
        }
        catch (Exception e)
        {
            assertEquals(UnmarshallingFailureException.class, e.getClass());
            assertEquals("Could not unmarshal (Errors: Could not unmarshal; nested exception is nu.xom.ParsingException: Content is not allowed in prolog. at line 1, column 1,Could not unmarshal; nested exception is nu.xom.ParsingException: Content is not allowed in prolog. at line 1, column 1)", e.getMessage());
        }
    }

    private static Source createSource(String xml)
    {
        return new StreamSource(new ByteArrayInputStream(xml.getBytes()));
    }

    private static class StringUnmarshaller extends XomUnmarshaller
    {
        public StringUnmarshaller()
        {
            super(Collections.singletonList(new StringParser()));
        }
    }

    private static class StringParser implements Parser<String>
    {
        @Override
        public String parse(Element rootElement)
        {
            return XPathHelper.getString("value", rootElement);
        }
    }

    private static class IntegerUnmarshaller extends XomUnmarshaller
    {
        public IntegerUnmarshaller()
        {
            super(Collections.singletonList(new IntegerParser()));
        }
    }

    private static class IntegerParser implements Parser<Integer>
    {
        @Override
        public Integer parse(Element rootElement)
        {
            return XPathHelper.getInteger("value", rootElement);
        }
    }
}
