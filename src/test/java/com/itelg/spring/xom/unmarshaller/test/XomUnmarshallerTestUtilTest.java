package com.itelg.spring.xom.unmarshaller.test;

import java.io.IOException;
import java.io.InputStream;

import org.apache.commons.io.IOUtils;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.core.io.ClassPathResource;

import com.itelg.spring.xom.unmarshaller.parser.Parser;
import com.itelg.spring.xom.unmarshaller.parser.RootTagMatcherParser;
import com.itelg.spring.xom.unmarshaller.parser.RootTagTypeMatchingDisableParser;
import com.itelg.xpath.helper.DocumentHelper;

import nu.xom.Element;

public class XomUnmarshallerTestUtilTest
{
    @Test
    public void testResolvesXml() throws IOException
    {
        String xml = IOUtils.toString(new ClassPathResource("number.xml").getInputStream());
        Parser<?> parser = new RootTagTypeMatchingDisableParser();
        Assert.assertTrue(XomUnmarshallerTestUtil.resolves(parser, xml));
    }

    @Test
    public void testResolvesXmlNot() throws IOException
    {
        String xml = IOUtils.toString(new ClassPathResource("number.xml").getInputStream());
        Parser<?> parser = new RootTagMatcherParser();
        Assert.assertFalse(XomUnmarshallerTestUtil.resolves(parser, xml));
    }

    @Test
    public void testResolvesXmlInvalid()
    {
        Parser<?> parser = new RootTagTypeMatchingDisableParser();
        Assert.assertFalse(XomUnmarshallerTestUtil.resolves(parser, "invalid xml"));
    }

    @Test
    public void testResolvesElement() throws Exception
    {
        try (InputStream inputStream = new ClassPathResource("number.xml").getInputStream())
        {
            Element rootElement = DocumentHelper.getRootElement(inputStream);
            Parser<?> parser = new RootTagTypeMatchingDisableParser();
            Assert.assertTrue(XomUnmarshallerTestUtil.resolves(parser, rootElement));
        }
    }

    @Test
    public void testResolvesElementNot() throws Exception
    {
        try (InputStream inputStream = new ClassPathResource("number.xml").getInputStream())
        {
            Element rootElement = DocumentHelper.getRootElement(inputStream);
            Parser<?> parser = new RootTagMatcherParser();
            Assert.assertFalse(XomUnmarshallerTestUtil.resolves(parser, rootElement));
        }
    }
}