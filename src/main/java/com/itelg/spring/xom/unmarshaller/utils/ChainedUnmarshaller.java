package com.itelg.spring.xom.unmarshaller.utils;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import javax.xml.transform.Source;
import javax.xml.transform.stream.StreamSource;

import org.apache.commons.io.IOUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.oxm.Unmarshaller;
import org.springframework.oxm.UnmarshallingFailureException;
import org.springframework.util.Assert;

public class ChainedUnmarshaller implements Unmarshaller
{
    private List<Unmarshaller> unmarshallers;

    public ChainedUnmarshaller(List<Unmarshaller> unmarshallers)
    {
        Assert.notEmpty(unmarshallers, "'unmarshallers' must not be empty");
        this.unmarshallers = unmarshallers;
    }

    @Override
    public boolean supports(Class<?> clazz)
    {
        for (Unmarshaller unmarshaller : unmarshallers)
        {
            if (unmarshaller.supports(clazz))
            {
                return true;
            }
        }

        return false;
    }

    @Override
    public Object unmarshal(Source source) throws IOException
    {
        List<String> errors = new ArrayList<>();
        byte[] byteSource = toByteArray(source);

        for (Unmarshaller unmarshaller : unmarshallers)
        {
            try (InputStream inputStream = new ByteArrayInputStream(byteSource))
            {
                return unmarshaller.unmarshal(new StreamSource(inputStream));
            }
            catch (RuntimeException e)
            {
                errors.add(e.getMessage());
            }
        }

        throw new UnmarshallingFailureException("Could not unmarshal (Errors: " + StringUtils.join(errors, ",") + ")");
    }

    private static byte[] toByteArray(Source source) throws IOException
    {
        StreamSource streamSource = (StreamSource) source;

        try (InputStream inputStream = streamSource.getInputStream();
                ByteArrayOutputStream outputStream = new ByteArrayOutputStream())
        {
            IOUtils.copy(inputStream, outputStream);
            return outputStream.toByteArray();
        }
    }
}
