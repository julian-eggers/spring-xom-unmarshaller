spring-xom-unmarshaller
============

[![Maven Central](https://maven-badges.herokuapp.com/maven-central/com.itelg.spring/spring-xom-unmarshaller/badge.svg)](https://maven-badges.herokuapp.com/maven-central/com.itelg.spring/spring-xom-unmarshaller)
[![Codacy Badge](https://api.codacy.com/project/badge/Grade/d134d532488b44e5aaaf1b9775999035)](https://www.codacy.com/app/eggers-julian/spring-xom-unmarshaller?utm_source=github.com&amp;utm_medium=referral&amp;utm_content=julian-eggers/spring-xom-unmarshaller&amp;utm_campaign=Badge_Grade)
[![Coverage Status](https://coveralls.io/repos/julian-eggers/spring-xom-unmarshaller/badge.svg)](https://coveralls.io/r/julian-eggers/spring-xom-unmarshaller)
[![Build Status](https://travis-ci.org/julian-eggers/spring-xom-unmarshaller.svg?branch=master)](https://travis-ci.org/julian-eggers/spring-xom-unmarshaller)

Spring XML Unmarshalling with [XOM](http://www.xom.nu/)

#### Maven
```xml
<dependencies>
	<dependency>
		<groupId>com.itelg.spring</groupId>
		<artifactId>spring-xom-unmarshaller</artifactId>
		<version>0.2.0-RELEASE</version>
	</dependency>
</dependencies>
```

#### Examples

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

##### Parser: root-tag resolving via target-class (Root-Tag: integer)
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

##### Parser: root-tag resolving via annotation (Root-Tag: text, blob or string)
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

##### Parser: disable resolving via annotation (Root-Tag: text)
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

#### Test-Support
```java
Parser<?> parser = new IntegerParser();
Assert.assertTrue(XomUnmarshallerTestUtil.resolves(parser, "<integer><data value=\"11\" /></integer>"));
```        
