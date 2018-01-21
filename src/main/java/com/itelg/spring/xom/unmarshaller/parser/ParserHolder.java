package com.itelg.spring.xom.unmarshaller.parser;

import java.util.ArrayList;
import java.util.List;

public class ParserHolder
{
    private Parser<?> parser;
    private Class<?> returnType;
    private List<String> supportedRootTags = new ArrayList<>();
    private String xpathExpression;
    private String xpathExpressionValue;

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

    public String getXPathExpression()
    {
        return xpathExpression;
    }

    public void setXPathExpression(String xpathExpression)
    {
        this.xpathExpression = xpathExpression;
    }

    public String getXpathExpressionValue()
    {
        return xpathExpressionValue;
    }

    public void setXpathExpressionValue(String xpathExpressionValue)
    {
        this.xpathExpressionValue = xpathExpressionValue;
    }
}