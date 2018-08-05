package com.itelg.spring.xom.unmarshaller.test.parser;

import com.itelg.xpath.helper.XPathHelper;

import nu.xom.Element;

public class OverwrittenRootTagByTypeParser extends RootTagByTypeParser
{
    @Override
    public String parse(Element rootElement)
    {
        return XPathHelper.getString("data/@value", rootElement);
    }
}