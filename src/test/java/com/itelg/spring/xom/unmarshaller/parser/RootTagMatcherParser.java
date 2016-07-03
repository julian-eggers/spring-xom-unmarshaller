package com.itelg.spring.xom.unmarshaller.parser;

import com.itelg.spring.xom.unmarshaller.parser.annotation.RootTagMatcher;
import com.itelg.xpath.helper.XPathHelper;

import nu.xom.Element;

@RootTagMatcher("Double")
@RootTagMatcher("Integer")
public class RootTagMatcherParser implements Parser<String>
{
    @Override
    public String parse(Element rootElement)
    {
        return XPathHelper.getString("data/@value", rootElement);
    }
}