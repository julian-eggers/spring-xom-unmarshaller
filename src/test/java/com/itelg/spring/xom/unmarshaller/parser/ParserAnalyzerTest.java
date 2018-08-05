package com.itelg.spring.xom.unmarshaller.parser;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import org.junit.Test;

import com.itelg.spring.xom.unmarshaller.test.domain.Customer;
import com.itelg.spring.xom.unmarshaller.test.parser.DisabledRootTagByTypeParser;
import com.itelg.spring.xom.unmarshaller.test.parser.ExtendedDisabledRootTagByTypeParser;
import com.itelg.spring.xom.unmarshaller.test.parser.ExtendedInvalidParser;
import com.itelg.spring.xom.unmarshaller.test.parser.ExtendedRootTagByAnnotationParser;
import com.itelg.spring.xom.unmarshaller.test.parser.ExtendedRootTagByTypeParser;
import com.itelg.spring.xom.unmarshaller.test.parser.ExtendedXPathExpressionCustomerParser;
import com.itelg.spring.xom.unmarshaller.test.parser.ExtendedXPathExpressionValueCustomerParser;
import com.itelg.spring.xom.unmarshaller.test.parser.InvalidParser;
import com.itelg.spring.xom.unmarshaller.test.parser.OverwrittenDisabledRootTagByTypeParser;
import com.itelg.spring.xom.unmarshaller.test.parser.OverwrittenInvalidParser;
import com.itelg.spring.xom.unmarshaller.test.parser.OverwrittenRootTagByAnnotationParser;
import com.itelg.spring.xom.unmarshaller.test.parser.OverwrittenRootTagByTypeParser;
import com.itelg.spring.xom.unmarshaller.test.parser.OverwrittenXPathExpressionCustomerParser;
import com.itelg.spring.xom.unmarshaller.test.parser.OverwrittenXPathExpressionValueCustomerParser;
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
    public void testAnalyzeExtendedClassWithRootTagByType()
    {
        Parser<?> parser = new ExtendedRootTagByTypeParser();
        ParserHolder holder = ParserAnalyzer.analyze(parser);

        assertEquals(parser, holder.getParser());
        assertEquals(String.class, holder.getReturnType());
        assertEquals(1, holder.getSupportedRootTags().size());
        assertTrue(holder.getSupportedRootTags().contains("String"));
        assertNull(holder.getXPathExpression());
        assertNull(holder.getXpathExpressionValue());
    }

    @Test
    public void testAnalyzeOverwrittenClassWithRootTagByType()
    {
        Parser<?> parser = new OverwrittenRootTagByTypeParser();
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
    public void testAnalyzeExtendedClassWithRootTagByAnnotation()
    {
        Parser<?> parser = new ExtendedRootTagByAnnotationParser();
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
    public void testAnalyzeOverwrittenClassWithRootTagByAnnotation()
    {
        Parser<?> parser = new OverwrittenRootTagByAnnotationParser();
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
    public void testAnalyzeExtendedClassWithDisabledRootTagByType()
    {
        Parser<?> parser = new ExtendedDisabledRootTagByTypeParser();
        ParserHolder holder = ParserAnalyzer.analyze(parser);

        assertEquals(parser, holder.getParser());
        assertEquals(Double.class, holder.getReturnType());
        assertEquals(1, holder.getSupportedRootTags().size());
        assertTrue(holder.getSupportedRootTags().contains("Float"));
        assertNull(holder.getXPathExpression());
        assertNull(holder.getXpathExpressionValue());
    }

    @Test
    public void testAnalyzeOverwrittenClassWithDisabledRootTagByType()
    {
        Parser<?> parser = new OverwrittenDisabledRootTagByTypeParser();
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
    public void testAnalyzeExtendedClassWithXPathExpression()
    {
        Parser<?> parser = new ExtendedXPathExpressionCustomerParser();
        ParserHolder holder = ParserAnalyzer.analyze(parser);

        assertEquals(parser, holder.getParser());
        assertEquals(Customer.class, holder.getReturnType());
        assertEquals(1, holder.getSupportedRootTags().size());
        assertTrue(holder.getSupportedRootTags().contains("Customer"));
        assertEquals("//response/customer", holder.getXPathExpression());
        assertNull(holder.getXpathExpressionValue());
    }

    @Test
    public void testAnalyzeOverwrittenClassWithXPathExpression()
    {
        Parser<?> parser = new OverwrittenXPathExpressionCustomerParser();
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
    public void testAnalyzeExtendedClassWithXPathExpressionValue()
    {
        Parser<?> parser = new ExtendedXPathExpressionValueCustomerParser();
        ParserHolder holder = ParserAnalyzer.analyze(parser);

        assertEquals(parser, holder.getParser());
        assertEquals(Customer.class, holder.getReturnType());
        assertEquals(1, holder.getSupportedRootTags().size());
        assertTrue(holder.getSupportedRootTags().contains("Customer"));
        assertEquals("//response/@type", holder.getXPathExpression());
        assertEquals("customer", holder.getXpathExpressionValue());
    }

    @Test
    public void testAnalyzeOverwrittenClassWithXPathExpressionValue()
    {
        Parser<?> parser = new OverwrittenXPathExpressionValueCustomerParser();
        ParserHolder holder = ParserAnalyzer.analyze(parser);

        assertEquals(parser, holder.getParser());
        assertEquals(Customer.class, holder.getReturnType());
        assertEquals(1, holder.getSupportedRootTags().size());
        assertTrue(holder.getSupportedRootTags().contains("Customer"));
        assertEquals("//response/@type", holder.getXPathExpression());
        assertEquals("customer", holder.getXpathExpressionValue());
    }

    @Test
    public void testAnalyzeWithInvalidParser()
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

    @Test
    public void testAnalyzeExtendedClassWithInvalidParser()
    {
        try
        {
            ParserAnalyzer.analyze(new ExtendedInvalidParser());
            fail("Exception expected");
        }
        catch (Exception e)
        {
            assertEquals("Invalid parser-implementation!", e.getMessage());
        }
    }

    @Test
    public void testAnalyzeOverwrittenClassWithInvalidParser()
    {
        try
        {
            ParserAnalyzer.analyze(new OverwrittenInvalidParser());
            fail("Exception expected");
        }
        catch (Exception e)
        {
            assertEquals("Invalid parser-implementation!", e.getMessage());
        }
    }
}