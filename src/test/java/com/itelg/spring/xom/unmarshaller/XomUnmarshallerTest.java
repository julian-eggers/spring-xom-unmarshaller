package com.itelg.spring.xom.unmarshaller;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.io.IOException;
import java.io.InputStream;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import javax.xml.transform.stream.StreamSource;

import org.junit.Before;
import org.junit.Test;
import org.springframework.core.io.ClassPathResource;
import org.springframework.oxm.Unmarshaller;
import org.springframework.oxm.UnmarshallingFailureException;

import com.itelg.spring.xom.unmarshaller.parser.Parser;
import com.itelg.spring.xom.unmarshaller.test.domain.Customer;
import com.itelg.spring.xom.unmarshaller.test.domain.Order;
import com.itelg.spring.xom.unmarshaller.test.parser.DisabledRootTagByTypeParser;
import com.itelg.spring.xom.unmarshaller.test.parser.RootTagByAnnotationParser;
import com.itelg.spring.xom.unmarshaller.test.parser.RootTagByTypeParser;
import com.itelg.spring.xom.unmarshaller.test.parser.XPathExpressionCustomerParser;
import com.itelg.spring.xom.unmarshaller.test.parser.XPathExpressionOrderParser;
import com.itelg.spring.xom.unmarshaller.test.parser.XPathExpressionValueCustomerParser;
import com.itelg.spring.xom.unmarshaller.test.parser.XPathExpressionValueOrderParser;

public class XomUnmarshallerTest
{
    private Unmarshaller unmarshaller;

    @Before
    public void init()
    {
        List<Parser<?>> parsers = new ArrayList<>();
        parsers.add(new RootTagByTypeParser());
        parsers.add(new RootTagByAnnotationParser());
        parsers.add(new DisabledRootTagByTypeParser());
        parsers.add(new XPathExpressionCustomerParser());
        parsers.add(new XPathExpressionOrderParser());
        parsers.add(new XPathExpressionValueCustomerParser());
        parsers.add(new XPathExpressionValueOrderParser());
        unmarshaller = new XomUnmarshaller(parsers);
    }

    @Test
    public void testSupports()
    {
        assertTrue(unmarshaller.supports(String.class));
        assertTrue(unmarshaller.supports(Long.class));
        assertTrue(unmarshaller.supports(Double.class));
        assertTrue(unmarshaller.supports(Customer.class));
        assertTrue(unmarshaller.supports(Order.class));
        assertFalse(unmarshaller.supports(BigDecimal.class));
    }

    @Test
    public void testUnmarshallWithUnknownParser() throws IOException
    {
        try (InputStream inputStream = new ClassPathResource("double.xml").getInputStream())
        {
            unmarshaller.unmarshal(new StreamSource(inputStream));
            fail("Exception expected");
        }
        catch (Exception e)
        {
            assertEquals(UnmarshallingFailureException.class, e.getClass());
            assertEquals("No parser applied (Root-Tag: double)", e.getMessage());
        }
    }

    @Test
    public void testUnmarshallWithUnknownException() throws IOException
    {
        try (InputStream inputStream = new ClassPathResource("invalid.xml").getInputStream())
        {
            unmarshaller.unmarshal(new StreamSource(inputStream));
            fail("Exception expected");
        }
        catch (Exception e)
        {
            assertEquals(UnmarshallingFailureException.class, e.getClass());
            assertEquals("XML document structures must start and end within the same entity.; nested exception is nu.xom.ParsingException: XML document structures must start and end within the same entity. at line 1, column 7", e.getMessage());
        }
    }

    @Test
    public void testUnmarshallWithRootTagByType() throws IOException
    {
        try (InputStream inputStream = new ClassPathResource("string.xml").getInputStream())
        {
            String value = (String) unmarshaller.unmarshal(new StreamSource(inputStream));
            assertEquals("test", value);
        }
    }

    @Test
    public void testUnmarshallWithRootTagByAnnotationAndInteger() throws IOException
    {
        try (InputStream inputStream = new ClassPathResource("integer.xml").getInputStream())
        {
            Long value = (Long) unmarshaller.unmarshal(new StreamSource(inputStream));
            assertEquals(Long.valueOf(456), value);
        }
    }

    @Test
    public void testUnmarshallWithRootTagByAnnotationAndLong() throws IOException
    {
        try (InputStream inputStream = new ClassPathResource("long.xml").getInputStream())
        {
            Long value = (Long) unmarshaller.unmarshal(new StreamSource(inputStream));
            assertEquals(Long.valueOf(123), value);
        }
    }

    @Test
    public void testUnmarshallWithDisableRootTagByType() throws IOException
    {
        try (InputStream inputStream = new ClassPathResource("float.xml").getInputStream())
        {
            Double value = (Double) unmarshaller.unmarshal(new StreamSource(inputStream));
            assertEquals(Double.valueOf(12.23), value);
        }
    }

    @Test
    public void testUnmarshallWithXPathExpressionAndCustomer() throws IOException
    {
        try (InputStream inputStream = new ClassPathResource("response1-customer.xml").getInputStream())
        {
            Customer value = (Customer) unmarshaller.unmarshal(new StreamSource(inputStream));
            assertEquals(123, value.getId());
        }
    }

    @Test
    public void testUnmarshallWithXPathExpressionAndOrder() throws IOException
    {
        try (InputStream inputStream = new ClassPathResource("response1-order.xml").getInputStream())
        {
            Order value = (Order) unmarshaller.unmarshal(new StreamSource(inputStream));
            assertEquals(456, value.getId());
        }
    }

    @Test
    public void testUnmarshallWithXPathExpressionValueAndCustomer() throws IOException
    {
        try (InputStream inputStream = new ClassPathResource("response2-customer.xml").getInputStream())
        {
            Customer value = (Customer) unmarshaller.unmarshal(new StreamSource(inputStream));
            assertEquals(123, value.getId());
        }
    }

    @Test
    public void testUnmarshallWithXPathExpressionValueAndOrder() throws IOException
    {
        try (InputStream inputStream = new ClassPathResource("response2-order.xml").getInputStream())
        {
            Order value = (Order) unmarshaller.unmarshal(new StreamSource(inputStream));
            assertEquals(456, value.getId());
        }
    }
}