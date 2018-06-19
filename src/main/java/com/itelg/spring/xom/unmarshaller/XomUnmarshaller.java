package com.itelg.spring.xom.unmarshaller;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import javax.xml.transform.Source;
import javax.xml.transform.stream.StreamSource;

import org.springframework.oxm.Unmarshaller;
import org.springframework.oxm.UnmarshallingFailureException;
import org.springframework.oxm.XmlMappingException;

import com.itelg.spring.xom.unmarshaller.parser.Parser;
import com.itelg.spring.xom.unmarshaller.parser.ParserAnalyzer;
import com.itelg.spring.xom.unmarshaller.parser.ParserHolder;
import com.itelg.spring.xom.unmarshaller.parser.resolver.ParserResolver;
import com.itelg.xpath.helper.DocumentHelper;

import nu.xom.Element;

public class XomUnmarshaller implements Unmarshaller
{
    private List<ParserHolder> parsers = new ArrayList<>();

    public XomUnmarshaller(List<Parser<?>> parsers)
    {
        for (Parser<?> parser : parsers)
        {
            this.parsers.add(ParserAnalyzer.analyze(parser));
        }
    }

    @Override
    public boolean supports(Class<?> clazz)
    {
        for (ParserHolder parser : parsers)
        {
            if (parser.getReturnType().isAssignableFrom(clazz))
            {
                return true;
            }
        }

        return false;
    }

    @Override
    public Object unmarshal(Source source) throws IOException, XmlMappingException
    {
        StreamSource streamSource = (StreamSource) source;

        try (InputStream inputStream = streamSource.getInputStream())
        {
            Element rootElement = DocumentHelper.getRootElement(inputStream);
            Parser<?> parser = ParserResolver.resolve(parsers, rootElement);
            return parser.parse(rootElement);
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