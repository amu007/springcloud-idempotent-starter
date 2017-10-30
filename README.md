# springcloud-idempotent-starter

A solution of idempotent for SpringBoot.
Based on SpringMVC components （HandlerInterceptorAdapter + ResponseBodyAdvice） And the Redis-NX implementions distribution lock。

This is something that you get for free just by adding the following dependency inside your project:

```xml
<dependency>
	<groupId>org.amu.starter</groupId>
	<artifactId>springcloud-idempotent-starter</artifactId>
	<version>0.0.1-SNAPSHOT</version>
</dependency>
```

Then you can configure the package in your code simply by:

```java
@ComponentScan(basePackages = { "org.amu.starter.springcloud.idempotent" })
```

All HTTP reqeusts (not GET method) need add header "X-REQ-IDEM-ID" with a UUID value.