package com.itelg.spring.xom.unmarshaller;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import javax.xml.transform.Source;
import javax.xml.transform.stream.StreamSource;

import org.apache.commons.io.IOUtils;
import org.springframework.oxm.Unmarshaller;
import org.springframework.oxm.UnmarshallingFailureException;
import org.springframework.util.Assert;

import com.itelg.spring.xom.unmarshaller.parser.Parser;
import com.itelg.spring.xom.unmarshaller.parser.ParserAnalyzer;
import com.itelg.spring.xom.unmarshaller.parser.ParserHolder;
import com.itelg.spring.xom.unmarshaller.parser.PreParseInterceptor;
import com.itelg.spring.xom.unmarshaller.parser.resolver.ParserResolver;
import com.itelg.xpath.helper.DocumentHelper;

public class XomUnmarshaller implements Unmarshaller
{
    private List<ParserHolder> parsers = new ArrayList<>();

    private PreParseInterceptor preParseInterceptor;

    public XomUnmarshaller(List<Parser<?>> parsers, PreParseInterceptor preParseInterceptor)
    {
        Assert.notEmpty(parsers, "'parsers' must not be empty");
        Assert.notNull(preParseInterceptor, "'preParseInterceptor' must not be null");

        parsers.forEach(parser -> this.parsers.add(ParserAnalyzer.analyze(parser)));
        this.preParseInterceptor = preParseInterceptor;
    }

    public XomUnmarshaller(List<Parser<?>> parsers)
    {
        this(parsers, xml -> xml);
    }

    @Override
    public boolean supports(Class<?> clazz)
    {
        for (var parser : parsers)
        {
            if (parser.getReturnType().isAssignableFrom(clazz))
            {
                return true;
            }
        }

        return false;
    }

    @Override
    public Object unmarshal(Source source) throws IOException
    {
        StreamSource streamSource = (StreamSource) source;

        try (InputStream inputStream = streamSource.getInputStream())
        {
            var originalXml = IOUtils.toByteArray(inputStream);
            var interceptedXml = preParseInterceptor.intercept(originalXml);

            try (InputStream interceptedInputStream = new ByteArrayInputStream(interceptedXml))
            {
                var rootElement = DocumentHelper.getRootElement(interceptedInputStream);
                var parser = ParserResolver.resolve(parsers, rootElement);
                return parser.parse(rootElement);
            }
        }
        catch (UnmarshallingFailureException e)
        {
            throw e;
        }
        catch (Exception e)
        {
            throw new UnmarshallingFailureException("Could not unmarshal", e);
        }
    }
}
