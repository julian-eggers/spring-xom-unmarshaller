package com.itelg.spring.xom.unmarshaller.test.parser;

import com.itelg.spring.xom.unmarshaller.parser.annotation.XPathExpressionMatcher;
import com.itelg.spring.xom.unmarshaller.test.domain.Customer;
import com.itelg.xpath.helper.XPathHelper;

import nu.xom.Element;

@XPathExpressionMatcher(value = "//response/@type", expressionValue = "customer")
public class OverwrittenXPathExpressionValueCustomerParser extends XPathExpressionValueCustomerParser
{
    @Override
    public Customer parse(Element rootElement)
    {
        Customer customer = new Customer();
        customer.setId(XPathHelper.getPLong("//response/data/id", rootElement));
        return customer;
    }
}
