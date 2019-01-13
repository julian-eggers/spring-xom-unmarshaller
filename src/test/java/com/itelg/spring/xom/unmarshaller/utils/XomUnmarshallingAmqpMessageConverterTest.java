package com.itelg.spring.xom.unmarshaller.utils;

import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.Assert.assertEquals;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.io.IOUtils;
import org.junit.Before;
import org.junit.Test;
import org.springframework.amqp.core.MessageBuilder;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.core.io.ClassPathResource;

import com.itelg.spring.xom.unmarshaller.XomUnmarshaller;
import com.itelg.spring.xom.unmarshaller.parser.Parser;
import com.itelg.spring.xom.unmarshaller.test.parser.RootTagByTypeParser;

public class XomUnmarshallingAmqpMessageConverterTest
{
    private MessageConverter messageConverter;

    @Before
    public void init()
    {
        List<Parser<?>> parsers = new ArrayList<>();
        parsers.add(new RootTagByTypeParser());
        messageConverter = new XomUnmarshallingAmqpMessageConverter(new XomUnmarshaller(parsers));
    }

    @Test
    public void testToMessage()
    {
        assertThatThrownBy(() -> messageConverter.toMessage(null, null)).isInstanceOf(IllegalArgumentException.class)
                .hasMessage("Marshalling is not supported!");
    }

    @Test
    public void testFromMessage() throws IOException
    {
        MessageBuilder messageBuilder = MessageBuilder.withBody(IOUtils.toByteArray(new ClassPathResource("string.xml").getInputStream()));
        Object value = messageConverter.fromMessage(messageBuilder.build());
        assertEquals("test", value);
    }
}
