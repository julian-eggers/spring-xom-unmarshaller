package com.itelg.spring.xom.unmarshaller.configuration;

import java.util.List;

import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.itelg.spring.xom.unmarshaller.XomUnmarshaller;
import com.itelg.spring.xom.unmarshaller.parser.Parser;
import com.itelg.spring.xom.unmarshaller.parser.PreParseInterceptor;

@Configuration
public class SpringXomUnmarshallerConfiguration
{
    @Bean
    @ConditionalOnBean
    public PreParseInterceptor preParseInterceptor()
    {
        return xml -> xml;
    }

    @Bean
    @ConditionalOnMissingBean
    @ConditionalOnBean(Parser.class)
    public XomUnmarshaller xomUnmarshaller(List<Parser<?>> parsers, PreParseInterceptor preParseInterceptor)
    {
        return new XomUnmarshaller(parsers, preParseInterceptor);
    }
}
