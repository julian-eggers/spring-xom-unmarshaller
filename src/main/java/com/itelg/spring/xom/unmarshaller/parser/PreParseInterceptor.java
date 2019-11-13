package com.itelg.spring.xom.unmarshaller.parser;

public interface PreParseInterceptor
{
    byte[] intercept(byte[] xml);
}
