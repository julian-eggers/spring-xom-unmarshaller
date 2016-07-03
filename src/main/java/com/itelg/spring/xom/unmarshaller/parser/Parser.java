package com.itelg.spring.xom.unmarshaller.parser;

import nu.xom.Element;

public interface Parser<T>
{
    T parse(Element rootElement);
}