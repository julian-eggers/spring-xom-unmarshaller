package com.itelg.spring.xom.unmarshaller.parser;

import java.lang.reflect.Method;
import java.util.List;

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

    /**
     * The parser class is inspected before its superclass, but for {@link #appendXPathExpression} a match on the
     * superclass intentionally overwrites one already found on the class (see the Overwritten*Parser test fixtures).
     */
    private static List<Class<?>> classAndSuperclass(Parser<?> parser)
    {
        return List.of(parser.getClass(), parser.getClass().getSuperclass());
    }

    private static Class<?> getReturnType(Parser<?> parser)
    {
        for (Class<?> clazz : classAndSuperclass(parser))
        {
            for (Method method : clazz.getDeclaredMethods())
            {
                if (method.getName().equals("parse") && !method.getReturnType().equals(Object.class))
                {
                    return method.getReturnType();
                }
            }
        }

        throw new IllegalArgumentException("Invalid parser-implementation!");
    }

    private static void appendRootTagByType(ParserHolder holder)
    {
        boolean disabled = classAndSuperclass(holder.getParser()).stream()
                .anyMatch(clazz -> clazz.getAnnotation(DisableRootTagTypeMatcher.class) != null);

        if (!disabled)
        {
            holder.addSupportedRootTag(holder.getReturnType().getSimpleName());
        }
    }

    private static void appendRootTags(ParserHolder holder)
    {
        for (Class<?> clazz : classAndSuperclass(holder.getParser()))
        {
            for (RootTagMatcher rootTagMatcher : clazz.getAnnotationsByType(RootTagMatcher.class))
            {
                holder.addSupportedRootTag(rootTagMatcher.value());
            }
        }
    }

    private static void appendXPathExpression(ParserHolder holder)
    {
        for (Class<?> clazz : classAndSuperclass(holder.getParser()))
        {
            XPathExpressionMatcher annotation = clazz.getAnnotation(XPathExpressionMatcher.class);

            if (annotation != null)
            {
                holder.setXPathExpression(annotation.value());

                if (StringUtils.isNotBlank(annotation.expressionValue()))
                {
                    holder.setXpathExpressionValue(annotation.expressionValue());
                }
            }
        }
    }
}
