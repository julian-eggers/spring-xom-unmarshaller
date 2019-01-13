package com.itelg.spring.xom.unmarshaller.utils;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;

import javax.xml.transform.stream.StreamSource;

import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageProperties;
import org.springframework.amqp.support.converter.MessageConversionException;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.lang.NonNull;
import org.springframework.oxm.Marshaller;
import org.springframework.oxm.Unmarshaller;

import com.itelg.spring.xom.unmarshaller.XomUnmarshaller;

/**
 *
 * AMQP-{@link org.springframework.amqp.support.converter.MessageConverter} which only supports unmarshalling
 * <p>
 * Useful in a scenario where it is only possible to provide an {@link Unmarshaller} and not a {@link Marshaller}
 *
 */
public class XomUnmarshallingAmqpMessageConverter implements MessageConverter
{
    private XomUnmarshaller xomUnmarshaller;

    public XomUnmarshallingAmqpMessageConverter(@NonNull XomUnmarshaller xomUnmarshaller)
    {
        this.xomUnmarshaller = xomUnmarshaller;
    }

    @Override
    public Message toMessage(Object object, MessageProperties messageProperties)
    {
        throw new IllegalArgumentException("Marshalling is not supported!");
    }

    @Override
    public Object fromMessage(Message message)
    {
        try (InputStream inputStream = new ByteArrayInputStream(message.getBody()))
        {
            return xomUnmarshaller.unmarshal(new StreamSource(inputStream));
        }
        catch (IOException ex)
        {
            throw new MessageConversionException("Could not access message content: " + message, ex);
        }
    }
}
