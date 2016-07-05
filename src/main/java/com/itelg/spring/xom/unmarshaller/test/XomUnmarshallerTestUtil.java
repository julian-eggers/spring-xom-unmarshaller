package com.itelg.spring.xom.unmarshaller.test;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.util.Collections;

import com.itelg.spring.xom.unmarshaller.parser.Parser;
import com.itelg.spring.xom.unmarshaller.parser.ParserAnalyzer;
import com.itelg.spring.xom.unmarshaller.parser.ParserHolder;
import com.itelg.spring.xom.unmarshaller.parser.resolver.ParserResolver;
import com.itelg.xpath.helper.DocumentHelper;

import nu.xom.Element;

public class XomUnmarshallerTestUtil
{
    private XomUnmarshallerTestUtil()
    {
    }

    public static boolean resolves(Parser<?> parser, String xml)
    {
        try (InputStream inputStream = new ByteArrayInputStream(xml.getBytes()))
        {
            Element rootElement = DocumentHelper.getRootElement(inputStream);
            return resolves(parser, rootElement);
        }
        catch (Exception e)
        {
            return false;
        }
    }

    public static boolean resolves(Parser<?> parser, Element rootElement)
    {
        ParserHolder holder = ParserAnalyzer.analyze(parser);

        try
        {
            ParserResolver.resolve(Collections.singletonList(holder), rootElement);
            return true;
        }
        catch (Exception e)
        {
            return false;
        }
    }
}