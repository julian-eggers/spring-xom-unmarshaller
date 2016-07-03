package com.itelg.spring.xom.unmarshaller.configuration;

import java.util.List;

import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.itelg.spring.xom.unmarshaller.XomUnmarshaller;
import com.itelg.spring.xom.unmarshaller.parser.Parser;
import com.itelg.spring.xom.unmarshaller.parser.PlaceholderParser;

@Configuration
public class SpringXomUnmarshallerConfiguration
{
    @ConditionalOnMissingBean
    @Bean
    public XomUnmarshaller xomUnmarshaller(List<Parser<?>> parsers)
    {
        return new XomUnmarshaller(parsers);
    }

    @Bean
    public Parser<XomUnmarshaller> placeholderParser()
    {
        return new PlaceholderParser();
    }
}