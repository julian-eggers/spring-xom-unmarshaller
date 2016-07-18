package com.itelg.spring.xom.unmarshaller.parser;

import org.junit.Assert;
import org.junit.Test;

public class ParserAnalyzerTest
{
    @Test
    public void testAnalyzeRootTagMatcherParser()
    {
        Parser<?> parser = new RootTagMatcherParser();
        ParserHolder holder = ParserAnalyzer.analyze(parser);

        Assert.assertEquals(parser, holder.getParser());
        Assert.assertEquals(String.class, holder.getReturnType());
        Assert.assertEquals(3, holder.getSupportedRootTags().size());
        Assert.assertTrue(holder.getSupportedRootTags().contains("String"));
        Assert.assertTrue(holder.getSupportedRootTags().contains("Double"));
        Assert.assertTrue(holder.getSupportedRootTags().contains("Integer"));
    }

    @Test
    public void testAnalyzeRootTagTypeMatchingDisabledParser()
    {
        Parser<?> parser = new RootTagTypeMatchingDisableParser();
        ParserHolder holder = ParserAnalyzer.analyze(parser);

        Assert.assertEquals(parser, holder.getParser());
        Assert.assertEquals(Integer.class, holder.getReturnType());
        Assert.assertEquals(1, holder.getSupportedRootTags().size());
        Assert.assertTrue(holder.getSupportedRootTags().contains("Number"));
    }

    @Test(expected = RuntimeException.class)
    public void testAnalazyInvalidParser()
    {
        Parser<?> parser = new InvalidParser();
        ParserAnalyzer.analyze(parser);
    }
}