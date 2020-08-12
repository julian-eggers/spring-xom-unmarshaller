package com.itelg.spring.xom.unmarshaller.test.parser;

import com.itelg.spring.xom.unmarshaller.parser.Parser;

import nu.xom.Element;

public class InvalidParser implements Parser<Object>
{
    @Override
    public Object parse(Element rootElement)
    {
        return null;
    }
}
