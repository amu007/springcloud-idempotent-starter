# springcloud-idempotent-starter

A solution of idempotent for SpringBoot.
Based on SpringMVC components HandlerInterceptorAdapter + ResponseBodyAdvice + redis‘s NX。

This is something that you get for free just by adding the following dependency inside your project:

```xml
<dependency>
	<groupId>org.amu.starter</groupId>
	<artifactId>springcloud-idempotent-starter</artifactId>
	<version>0.0.1-SNAPSHOT</version>
</dependency>
```
