package com.itelg.spring.xom.unmarshaller.test.parser;

import com.itelg.spring.xom.unmarshaller.parser.Parser;
import com.itelg.spring.xom.unmarshaller.parser.annotation.XPathExpressionMatcher;
import com.itelg.spring.xom.unmarshaller.test.domain.Order;
import com.itelg.xpath.helper.XPathHelper;

import nu.xom.Element;

@XPathExpressionMatcher("//response/order")
public class XPathExpressionOrderParser implements Parser<Order>
{
    @Override
    public Order parse(Element rootElement)
    {
        Order order = new Order();
        order.setId(XPathHelper.getPLong("//response/order/id", rootElement));
        return order;
    }
}
