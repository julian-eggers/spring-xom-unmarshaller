package com.itelg.spring.xom.unmarshaller.test.parser;

import com.itelg.spring.xom.unmarshaller.parser.annotation.XPathExpressionMatcher;
import com.itelg.spring.xom.unmarshaller.test.domain.Customer;
import com.itelg.xpath.helper.XPathHelper;

import nu.xom.Element;

@XPathExpressionMatcher("//response/customer")
public class OverwrittenXPathExpressionCustomerParser extends XPathExpressionCustomerParser
{
    @Override
    public Customer parse(Element rootElement)
    {
        Customer customer = new Customer();
        customer.setId(XPathHelper.getPLong("//response/customer/id", rootElement));
        return customer;
    }
}
