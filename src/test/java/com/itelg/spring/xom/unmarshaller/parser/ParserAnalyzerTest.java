package com.itelg.spring.xom.unmarshaller.parser;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import org.junit.Test;

import com.itelg.spring.xom.unmarshaller.test.domain.Customer;
import com.itelg.spring.xom.unmarshaller.test.parser.DisabledRootTagByTypeParser;
import com.itelg.spring.xom.unmarshaller.test.parser.InvalidParser;
import com.itelg.spring.xom.unmarshaller.test.parser.RootTagByAnnotationParser;
import com.itelg.spring.xom.unmarshaller.test.parser.RootTagByTypeParser;
import com.itelg.spring.xom.unmarshaller.test.parser.XPathExpressionCustomerParser;
import com.itelg.spring.xom.unmarshaller.test.parser.XPathExpressionValueCustomerParser;

public class ParserAnalyzerTest
{
    @Test
    public void testAnalyzeWithRootTagByType()
    {
        Parser<?> parser = new RootTagByTypeParser();
        ParserHolder holder = ParserAnalyzer.analyze(parser);

        assertEquals(parser, holder.getParser());
        assertEquals(String.class, holder.getReturnType());
        assertEquals(1, holder.getSupportedRootTags().size());
        assertTrue(holder.getSupportedRootTags().contains("String"));
        assertNull(holder.getXPathExpression());
        assertNull(holder.getXpathExpressionValue());
    }

    @Test
    public void testAnalyzeWithRootTagByAnnotation()
    {
        Parser<?> parser = new RootTagByAnnotationParser();
        ParserHolder holder = ParserAnalyzer.analyze(parser);

        assertEquals(parser, holder.getParser());
        assertEquals(Long.class, holder.getReturnType());
        assertEquals(2, holder.getSupportedRootTags().size());
        assertTrue(holder.getSupportedRootTags().contains("Long"));
        assertTrue(holder.getSupportedRootTags().contains("Integer"));
        assertNull(holder.getXPathExpression());
        assertNull(holder.getXpathExpressionValue());
    }

    @Test
    public void testAnalyzeWithDisabledRootTagByType()
    {
        Parser<?> parser = new DisabledRootTagByTypeParser();
        ParserHolder holder = ParserAnalyzer.analyze(parser);

        assertEquals(parser, holder.getParser());
        assertEquals(Double.class, holder.getReturnType());
        assertEquals(1, holder.getSupportedRootTags().size());
        assertTrue(holder.getSupportedRootTags().contains("Float"));
        assertNull(holder.getXPathExpression());
        assertNull(holder.getXpathExpressionValue());
    }

    @Test
    public void testAnalyzeWithXPathExpression()
    {
        Parser<?> parser = new XPathExpressionCustomerParser();
        ParserHolder holder = ParserAnalyzer.analyze(parser);

        assertEquals(parser, holder.getParser());
        assertEquals(Customer.class, holder.getReturnType());
        assertEquals(1, holder.getSupportedRootTags().size());
        assertTrue(holder.getSupportedRootTags().contains("Customer"));
        assertEquals("//response/customer", holder.getXPathExpression());
        assertNull(holder.getXpathExpressionValue());
    }

    @Test
    public void testAnalyzeWithXPathExpressionValue()
    {
        Parser<?> parser = new XPathExpressionValueCustomerParser();
        ParserHolder holder = ParserAnalyzer.analyze(parser);

        assertEquals(parser, holder.getParser());
        assertEquals(Customer.class, holder.getReturnType());
        assertEquals(1, holder.getSupportedRootTags().size());
        assertTrue(holder.getSupportedRootTags().contains("Customer"));
        assertEquals("//response/@type", holder.getXPathExpression());
        assertEquals("customer", holder.getXpathExpressionValue());
    }

    @Test
    public void testAnalazyWithInvalidParser()
    {
        try
        {
            ParserAnalyzer.analyze(new InvalidParser());
            fail("Exception expected");
        }
        catch (Exception e)
        {
            assertEquals("Invalid parser-implementation!", e.getMessage());
        }
    }
}