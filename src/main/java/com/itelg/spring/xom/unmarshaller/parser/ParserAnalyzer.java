package com.itelg.spring.xom.unmarshaller.parser;

import java.lang.reflect.Method;

import org.apache.commons.lang3.StringUtils;

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

        for (Method method : parser.getClass().getSuperclass().getDeclaredMethods())
        {
            if (method.getName().equals("parse") && !method.getReturnType().equals(Object.class))
            {
                return method.getReturnType();
            }
        }

        throw new IllegalArgumentException("Invalid parser-implementation!");
    }

    private static void appendRootTagByType(ParserHolder holder)
    {
        if (holder.getParser().getClass().getAnnotation(DisableRootTagTypeMatcher.class) == null && holder.getParser()
                .getClass()
                .getSuperclass()
                .getAnnotation(DisableRootTagTypeMatcher.class) == null)
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

        for (RootTagMatcher rootTagMatcher : holder.getParser().getClass().getSuperclass().getAnnotationsByType(RootTagMatcher.class))
        {
            holder.addSupportedRootTag(rootTagMatcher.value());
        }
    }

    private static void appendXPathExpression(ParserHolder holder)
    {
        XPathExpressionMatcher classAnnotation = holder.getParser().getClass().getAnnotation(XPathExpressionMatcher.class);

        if (classAnnotation != null)
        {
            holder.setXPathExpression(classAnnotation.value());

            if (StringUtils.isNotBlank(classAnnotation.expressionValue()))
            {
                holder.setXpathExpressionValue(classAnnotation.expressionValue());
            }
        }

        XPathExpressionMatcher superclassAnnotation = holder.getParser().getClass().getSuperclass().getAnnotation(XPathExpressionMatcher.class);

        if (superclassAnnotation != null)
        {
            holder.setXPathExpression(superclassAnnotation.value());

            if (StringUtils.isNotBlank(superclassAnnotation.expressionValue()))
            {
                holder.setXpathExpressionValue(superclassAnnotation.expressionValue());
            }
        }
    }
}
