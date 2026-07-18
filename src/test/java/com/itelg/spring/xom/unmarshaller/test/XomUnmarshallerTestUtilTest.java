package com.itelg.spring.xom.unmarshaller.test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.Charset;

import org.apache.commons.io.IOUtils;
import org.junit.jupiter.api.Test;
import org.springframework.core.io.ClassPathResource;

import com.itelg.spring.xom.unmarshaller.parser.Parser;
import com.itelg.spring.xom.unmarshaller.test.parser.DisabledRootTagByTypeParser;
import com.itelg.spring.xom.unmarshaller.test.parser.RootTagByAnnotationParser;
import com.itelg.xpath.helper.DocumentHelper;

import nu.xom.Element;

class XomUnmarshallerTestUtilTest
{
    @Test
    void testResolvesXml() throws IOException
    {
        String xml = IOUtils.toString(new ClassPathResource("float.xml").getInputStream(), Charset.defaultCharset());
        Parser<?> parser = new DisabledRootTagByTypeParser();
        assertTrue(XomUnmarshallerTestUtil.resolves(parser, xml));
    }

    @Test
    void testResolvesXmlNot() throws IOException
    {
        String xml = IOUtils.toString(new ClassPathResource("float.xml").getInputStream(), Charset.defaultCharset());
        Parser<?> parser = new RootTagByAnnotationParser();
        assertFalse(XomUnmarshallerTestUtil.resolves(parser, xml));
    }

    @Test
    void testResolvesXmlInvalid()
    {
        Parser<?> parser = new DisabledRootTagByTypeParser();
        assertFalse(XomUnmarshallerTestUtil.resolves(parser, "invalid xml"));
    }

    @Test
    void testResolvesElement() throws Exception
    {
        try (InputStream inputStream = new ClassPathResource("float.xml").getInputStream())
        {
            Element rootElement = DocumentHelper.getRootElement(inputStream);
            Parser<?> parser = new DisabledRootTagByTypeParser();
            assertTrue(XomUnmarshallerTestUtil.resolves(parser, rootElement));
        }
    }

    @Test
    void testResolvesElementNot() throws Exception
    {
        try (InputStream inputStream = new ClassPathResource("float.xml").getInputStream())
        {
            Element rootElement = DocumentHelper.getRootElement(inputStream);
            Parser<?> parser = new RootTagByAnnotationParser();
            assertFalse(XomUnmarshallerTestUtil.resolves(parser, rootElement));
        }
    }
}
