package com.itelg.spring.xom.unmarshaller.parser.annotation;

import static java.lang.annotation.ElementType.TYPE;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.Retention;
import java.lang.annotation.Target;

@Retention(RUNTIME)
@Target(TYPE)
public @interface XPathExpressionMatcher
{
    /**
     * Expression
     *
     * @return expression
     */
    String value();

    /**
     * Expression-value (optional)
     *
     * @return expression-value
     */
    String expressionValue() default "";
}
