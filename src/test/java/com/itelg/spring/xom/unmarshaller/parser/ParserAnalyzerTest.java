package com.itelg.spring.xom.unmarshaller.parser;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;

import org.junit.jupiter.api.Test;

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

class ParserAnalyzerTest
{
    @Test
    void testAnalyzeWithRootTagByType()
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
    void testAnalyzeExtendedClassWithRootTagByType()
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
    void testAnalyzeOverwrittenClassWithRootTagByType()
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
    void testAnalyzeWithRootTagByAnnotation()
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
    void testAnalyzeExtendedClassWithRootTagByAnnotation()
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
    void testAnalyzeOverwrittenClassWithRootTagByAnnotation()
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
    void testAnalyzeWithDisabledRootTagByType()
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
    void testAnalyzeExtendedClassWithDisabledRootTagByType()
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
    void testAnalyzeOverwrittenClassWithDisabledRootTagByType()
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
    void testAnalyzeWithXPathExpression()
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
    void testAnalyzeExtendedClassWithXPathExpression()
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
    void testAnalyzeOverwrittenClassWithXPathExpression()
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
    void testAnalyzeWithXPathExpressionValue()
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
    void testAnalyzeExtendedClassWithXPathExpressionValue()
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
    void testAnalyzeOverwrittenClassWithXPathExpressionValue()
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
    void testAnalyzeWithInvalidParser()
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
    void testAnalyzeExtendedClassWithInvalidParser()
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
    void testAnalyzeOverwrittenClassWithInvalidParser()
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
