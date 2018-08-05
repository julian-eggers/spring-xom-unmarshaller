package com.itelg.spring.xom.unmarshaller.test.parser;

import com.itelg.spring.xom.unmarshaller.parser.annotation.XPathExpressionMatcher;
import com.itelg.spring.xom.unmarshaller.test.domain.Order;
import com.itelg.xpath.helper.XPathHelper;

import nu.xom.Element;

@XPathExpressionMatcher(value = "//response/@type", expressionValue = "order")
public class OverwrittenXPathExpressionValueOrderParser extends XPathExpressionValueOrderParser
{
    @Override
    public Order parse(Element rootElement)
    {
        Order order = new Order();
        order.setId(XPathHelper.getPLong("//response/data/id", rootElement));
        return order;
    }
}
