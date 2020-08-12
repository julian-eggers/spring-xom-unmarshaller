package com.itelg.spring.xom.unmarshaller.configuration;

import java.util.List;

import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.itelg.spring.xom.unmarshaller.XomUnmarshaller;
import com.itelg.spring.xom.unmarshaller.parser.Parser;

@Configuration
public class SpringXomUnmarshallerConfiguration
{
    @Bean
    @ConditionalOnMissingBean
    @ConditionalOnBean(Parser.class)
    public XomUnmarshaller xomUnmarshaller(List<Parser<?>> parsers)
    {
        return new XomUnmarshaller(parsers);
    }
}
