package com.itelg.spring.xom.unmarshaller.parser;

import com.itelg.spring.xom.unmarshaller.parser.annotation.DisableRootTagTypeMatcher;
import com.itelg.spring.xom.unmarshaller.parser.annotation.RootTagMatcher;
import com.itelg.xpath.helper.XPathHelper;

import nu.xom.Element;

@RootTagMatcher("Number")
@DisableRootTagTypeMatcher 
public class RootTagTypeMatchingDisableParser implements Parser<Integer>
{
    @Override
    public Integer parse(Element rootElement)
    {
        return XPathHelper.getInteger("data/@value", rootElement);
    }
}