package com.itelg.spring.xom.unmarshaller.parser;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import lombok.Getter;
import lombok.Setter;

public class ParserHolder
{
    @Getter
    @Setter
    private Parser<?> parser;

    @Getter
    @Setter
    private Class<?> returnType;

    private Set<String> supportedRootTags = new HashSet<>();

    private String xpathExpression;

    @Getter
    @Setter
    private String xpathExpressionValue;

    /**
     * Hand-written because it hands out a copy: the field is a {@link Set}, the accessor a {@link List}.
     */
    public List<String> getSupportedRootTags()
    {
        return new ArrayList<>(supportedRootTags);
    }

    public void addSupportedRootTag(String rootTag)
    {
        supportedRootTags.add(rootTag);
    }

    /**
     * Hand-written because of the capital P: a generated accessor would be named getXpathExpression.
     */
    public String getXPathExpression()
    {
        return xpathExpression;
    }

    public void setXPathExpression(String xpathExpression)
    {
        this.xpathExpression = xpathExpression;
    }
}
