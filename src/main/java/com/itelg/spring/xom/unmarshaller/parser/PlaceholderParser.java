package com.itelg.spring.xom.unmarshaller.parser;

import com.itelg.spring.xom.unmarshaller.XomUnmarshaller;

import nu.xom.Element;

/**
 * Placeholder to prevent empty bean list (should be fixed in spring 4.3)
 */
public class PlaceholderParser implements Parser<XomUnmarshaller>
{
    @Override
    public XomUnmarshaller parse(Element rootElement)
    {
        return null;
    }
}