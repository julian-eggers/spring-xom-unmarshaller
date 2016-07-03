package com.itelg.spring.xom.unmarshaller.parser.resolver;

import java.util.List;

import org.springframework.oxm.UnmarshallingFailureException;

import com.itelg.spring.xom.unmarshaller.parser.Parser;
import com.itelg.spring.xom.unmarshaller.parser.ParserHolder;

import nu.xom.Element;

public class ParserResolver
{
    public static Parser<?> resolve(List<ParserHolder> holders, Element rootElement)
    {
        String rootTag = rootElement.getLocalName().toLowerCase();
        
        for (ParserHolder holder : holders)
        {
            for (String supportedRootTag : holder.getSupportedRootTags())
            {
                if (rootTag.equalsIgnoreCase(supportedRootTag))
                {
                    return holder.getParser();
                }
            }
        }
        
        throw new UnmarshallingFailureException("No parser applied (Root-Tag: " + rootElement.getLocalName() + ")");
    }
}