package com.itelg.spring.xom.unmarshaller.test.parser;

import com.itelg.spring.xom.unmarshaller.parser.Parser;
import com.itelg.xpath.helper.XPathHelper;

import nu.xom.Element;

public class RootTagByTypeParser implements Parser<String>
{
    @Override
    public String parse(Element rootElement)
    {
        return XPathHelper.getString("data/@value", rootElement);
    }
}