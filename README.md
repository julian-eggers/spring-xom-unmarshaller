# spring-xom-unmarshaller

[![Maven Central](https://img.shields.io/maven-central/v/com.itelg.spring/spring-xom-unmarshaller.svg?label=maven-central)](https://search.maven.org/artifact/com.itelg.spring/spring-xom-unmarshaller)
[![Release](https://github.com/julian-eggers/spring-xom-unmarshaller/actions/workflows/release.yml/badge.svg)](https://github.com/julian-eggers/spring-xom-unmarshaller/actions/workflows/release.yml)
[![Nightly build](https://github.com/julian-eggers/spring-xom-unmarshaller/actions/workflows/nightly.yml/badge.svg)](https://github.com/julian-eggers/spring-xom-unmarshaller/actions/workflows/nightly.yml)
[![Quality Gate](https://sonarcloud.io/api/project_badges/measure?project=julian-eggers_spring-xom-unmarshaller&metric=alert_status)](https://sonarcloud.io/summary/new_code?id=julian-eggers_spring-xom-unmarshaller)
[![Coverage](https://sonarcloud.io/api/project_badges/measure?project=julian-eggers_spring-xom-unmarshaller&metric=coverage)](https://sonarcloud.io/summary/new_code?id=julian-eggers_spring-xom-unmarshaller)

Spring XML Unmarshalling with [XOM](http://www.xom.nu/)

#### Maven
```xml
<dependency>
  <groupId>com.itelg.spring</groupId>
  <artifactId>spring-xom-unmarshaller</artifactId>
  <version>2.0.0-RC2</version>
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


#### Additional features

##### Pre-parse interceptor
If you have to work with invalid XML`s you can create an interceptor to remove invalid chars or to fix the structure.

```java
@Bean
public XomUnmarshaller xomUnmarshaller(List<Parser<?>> parsers)
{
    return new XomUnmarshaller(parsers, xmlChars ->
    {
        var xml = new String(xmlChars);
        xml = xml.replace("&#x1e;", "");
        xml = xml.replace("&#x1f;", "");
        xml = xml.replace("&#x0;", "");
        xml = xml.replace("&#xb;", "");

        return xml.getBytes();
    });
}
```


#### Test-Support
```java
Parser<?> parser = new IntegerParser();
Assertions.assertTrue(XomUnmarshallerTestUtil.resolves(parser, "<integer><data value=\"11\" /></integer>"));
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
