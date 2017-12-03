package com.itelg.spring.xom.unmarshaller.parser;

import java.lang.reflect.Method;

import com.itelg.spring.xom.unmarshaller.parser.annotation.DisableRootTagTypeMatcher;
import com.itelg.spring.xom.unmarshaller.parser.annotation.RootTagMatcher;
import com.itelg.spring.xom.unmarshaller.parser.annotation.XPathExpressionMatcher;

public class ParserAnalyzer
{
    private ParserAnalyzer()
    {
    }

    public static ParserHolder analyze(Parser<?> parser)
    {
        ParserHolder holder = new ParserHolder();
        holder.setParser(parser);
        holder.setReturnType(getReturnType(parser));
        appendRootTagByType(holder);
        appendRootTags(holder);
        appendXPathExpression(holder);
        return holder;
    }

    private static Class<?> getReturnType(Parser<?> parser)
    {
        for (Method method : parser.getClass().getDeclaredMethods())
        {
            if (method.getName().equals("parse") && !method.getReturnType().equals(Object.class))
            {
                return method.getReturnType();
            }
        }

        throw new RuntimeException("Invalid parser-implementation!");
    }

    private static void appendRootTagByType(ParserHolder holder)
    {
        if (holder.getParser().getClass().getAnnotation(DisableRootTagTypeMatcher.class) == null)
        {
            holder.addSupportedRootTag(holder.getReturnType().getSimpleName());
        }
    }

    private static void appendRootTags(ParserHolder holder)
    {
        for (RootTagMatcher rootTagMatcher : holder.getParser().getClass().getAnnotationsByType(RootTagMatcher.class))
        {
            holder.addSupportedRootTag(rootTagMatcher.value());
        }
    }

    private static void appendXPathExpression(ParserHolder holder)
    {
        XPathExpressionMatcher xPathMatcher = holder.getParser().getClass().getAnnotation(XPathExpressionMatcher.class);

        if (xPathMatcher != null)
        {
            holder.setXPathExpression(xPathMatcher.value());
        }
    }
}