package com.itelg.spring.xom.unmarshaller.test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.Charset;

import org.apache.commons.io.IOUtils;
import org.junit.Test;
import org.springframework.core.io.ClassPathResource;

import com.itelg.spring.xom.unmarshaller.parser.Parser;
import com.itelg.spring.xom.unmarshaller.test.parser.DisabledRootTagByTypeParser;
import com.itelg.spring.xom.unmarshaller.test.parser.RootTagByAnnotationParser;
import com.itelg.xpath.helper.DocumentHelper;

import nu.xom.Element;

public class XomUnmarshallerTestUtilTest
{
    @Test
    public void testResolvesXml() throws IOException
    {
        String xml = IOUtils.toString(new ClassPathResource("float.xml").getInputStream(), Charset.defaultCharset());
        Parser<?> parser = new DisabledRootTagByTypeParser();
        assertTrue(XomUnmarshallerTestUtil.resolves(parser, xml));
    }

    @Test
    public void testResolvesXmlNot() throws IOException
    {
        String xml = IOUtils.toString(new ClassPathResource("float.xml").getInputStream(), Charset.defaultCharset());
        Parser<?> parser = new RootTagByAnnotationParser();
        assertFalse(XomUnmarshallerTestUtil.resolves(parser, xml));
    }

    @Test
    public void testResolvesXmlInvalid()
    {
        Parser<?> parser = new DisabledRootTagByTypeParser();
        assertFalse(XomUnmarshallerTestUtil.resolves(parser, "invalid xml"));
    }

    @Test
    public void testResolvesElement() throws Exception
    {
        try (InputStream inputStream = new ClassPathResource("float.xml").getInputStream())
        {
            Element rootElement = DocumentHelper.getRootElement(inputStream);
            Parser<?> parser = new DisabledRootTagByTypeParser();
            assertTrue(XomUnmarshallerTestUtil.resolves(parser, rootElement));
        }
    }

    @Test
    public void testResolvesElementNot() throws Exception
    {
        try (InputStream inputStream = new ClassPathResource("float.xml").getInputStream())
        {
            Element rootElement = DocumentHelper.getRootElement(inputStream);
            Parser<?> parser = new RootTagByAnnotationParser();
            assertFalse(XomUnmarshallerTestUtil.resolves(parser, rootElement));
        }
    }
}