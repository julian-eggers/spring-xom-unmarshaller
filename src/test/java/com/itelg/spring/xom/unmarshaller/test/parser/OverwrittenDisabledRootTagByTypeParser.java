package com.itelg.spring.xom.unmarshaller.test.parser;

import com.itelg.spring.xom.unmarshaller.parser.annotation.DisableRootTagTypeMatcher;
import com.itelg.spring.xom.unmarshaller.parser.annotation.RootTagMatcher;
import com.itelg.xpath.helper.XPathHelper;

import nu.xom.Element;

@RootTagMatcher("Float")
@DisableRootTagTypeMatcher
public class OverwrittenDisabledRootTagByTypeParser extends DisabledRootTagByTypeParser
{
    @Override
    public Double parse(Element rootElement)
    {
        return XPathHelper.getDouble("data/@value", rootElement);
    }
}