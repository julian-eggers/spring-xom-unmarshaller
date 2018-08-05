package com.itelg.spring.xom.unmarshaller.test.parser;

import com.itelg.spring.xom.unmarshaller.parser.annotation.RootTagMatcher;
import com.itelg.xpath.helper.XPathHelper;

import nu.xom.Element;

@RootTagMatcher("Integer")
public class OverwrittenRootTagByAnnotationParser extends RootTagByAnnotationParser
{
    @Override
    public Long parse(Element rootElement)
    {
        return XPathHelper.getLong("data/@value", rootElement);
    }
}