spring-xom-unmarshaller
=======================

[![Maven Central](https://img.shields.io/maven-metadata/v/http/central.maven.org/maven2/com/itelg/spring/spring-xom-unmarshaller/maven-metadata.xml.svg)](https://search.maven.org/#search%7Cgav%7C1%7Cg%3A%22com.itelg.spring%22%20AND%20a%3A%22spring-xom-unmarshaller%22)
[![Codacy Badge](https://api.codacy.com/project/badge/Grade/d134d532488b44e5aaaf1b9775999035)](https://www.codacy.com/app/eggers-julian/spring-xom-unmarshaller)
[![Codacy Badge](https://api.codacy.com/project/badge/Coverage/d134d532488b44e5aaaf1b9775999035)](https://www.codacy.com/app/eggers-julian/spring-xom-unmarshaller)
[![Build Status](https://travis-ci.org/julian-eggers/spring-xom-unmarshaller.svg?branch=master)](https://travis-ci.org/julian-eggers/spring-xom-unmarshaller)

Spring XML Unmarshalling with [XOM](http://www.xom.nu/)

#### Maven
```xml
<dependency>
  <groupId>com.itelg.spring</groupId>
  <artifactId>spring-xom-unmarshaller</artifactId>
  <version>1.3.0</version>
</dependency>
```


#### Configuration

##### Enable auto-configuration via annotation

[@Autowire](http://docs.spring.io/spring/docs/current/javadoc-api/org/springframework/beans/factory/annotation/Autowire.html) XomUnmarshaller for further use in [MarshallingHttpMessageConverter](http://docs.spring.io/spring/docs/current/javadoc-api/org/springframework/http/converter/xml/MarshallingHttpMessageConverter.html) or [MarshallingMessageConverter](http://docs.spring.io/spring/docs/current/javadoc-api/org/springframework/messaging/converter/MarshallingMessageConverter.html).

```java
@SpringBootApplication
@EnableXomUnmarshaller
public class Application
{
    @Autowired
    private XomUnmarshaller xomUnmarshaller;
    
    public static void main(String[] args) throws Exception
    {
        SpringApplication.run(Application.class, args);
    }
}
```


#### Parser resolving
A matching parser can be resolved either via return-type, root-tag or an xpath-expression.

##### Root-tag resolving via return-type (Root-Tag: integer)
```java
@Component
public class IntegerParser implements Parser<Integer>
{
    @Override
    public Integer parse(Element rootElement)
    {
        return XPathHelper.getInteger("data/@value", rootElement);
    }
}
```

##### Root-tag resolving via annotation (Root-Tag: text, blob or string)
```java
@Component
@RootTagMatcher("text")
@RootTagMatcher("blob")
public class TextParser implements Parser<String>
{
    @Override
    public Integer parse(Element rootElement)
    {
        return XPathHelper.getString("data/@value", rootElement);
    }
}
```

##### Disable type-resolving via annotation (Root-Tag: text)
```java
@Component
@RootTagMatcher("text")
@DisableRootTagTypeMatcher
public class TextParser implements Parser<String>
{
    @Override
    public Integer parse(Element rootElement)
    {
        return XPathHelper.getString("data/@value", rootElement);
    }
}
```

##### Resolving via xpath-expression
```java
@Component
@XPathExpressionMatcher("//response/customer")
public class CustomerParser implements Parser<Customer>
{
    @Override
    public Customer parse(Element rootElement)
    {
        Customer customer = new Customer();
        customer.setId(XPathHelper.getPLong("//response/customer/id", rootElement));
        return customer;
    }
}
```

##### Resolving via xpath-expression-value
```java
@Component
@XPathExpressionMatcher(value = "//response/@type", expressionValue = "customer")
public class XPathExpressionValueCustomerParser implements Parser<Customer>
{
    @Override
    public Customer parse(Element rootElement)
    {
        Customer customer = new Customer();
        customer.setId(XPathHelper.getPLong("//response/data/id", rootElement));
        return customer;
    }
}
```


#### Test-Support
```java
Parser<?> parser = new IntegerParser();
Assert.assertTrue(XomUnmarshallerTestUtil.resolves(parser, "<integer><data value=\"11\" /></integer>"));
```



## Build & Release

### Build
```
mvn clean package
```

### Release
```
mvn clean deploy
```
