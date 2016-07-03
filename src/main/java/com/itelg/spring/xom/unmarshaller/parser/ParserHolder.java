package com.itelg.spring.xom.unmarshaller.parser;

import java.util.ArrayList;
import java.util.List;

public class ParserHolder
{
    private Parser<?> parser;
    private Class<?> returnType;
    private List<String> supportedRootTags = new ArrayList<>();;

    public Parser<?> getParser()
    {
        return parser;
    }

    public void setParser(Parser<?> parser)
    {
        this.parser = parser;
    }

    public Class<?> getReturnType()
    {
        return returnType;
    }

    public void setReturnType(Class<?> returnType)
    {
        this.returnType = returnType;
    }
    
    public List<String> getSupportedRootTags()
    {
        return supportedRootTags;
    }
    
    public void addSupportedRootTag(String rootTag)
    {
        supportedRootTags.add(rootTag);
    }
}