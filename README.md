# Benefits from using Java Modules with Java Service Provider Interface

Suppose that you have a two simple interfaces 

```java
package me.mfvitale.spi;

public interface Printer {
    void print();
}
```
```java
package me.mfvitale.spi;

public interface Scanner {
    void scan();
}
```
And you want to use `ServiceLoader` to dinamically load implementations. You can do it in the following way

```java
Printer printer = ServiceLoader.load(Printer.class).findFirst()
                .orElseThrow();
Scanner scanner = ServiceLoader.load(Scanner.class).findFirst()
                .orElseThrow();
```
Now we can provide a class that implements both beahavior 

```java
package me.mfvitale;

import me.mfvitale.spi.Printer;
import me.mfvitale.spi.Scanner;

public class AllInOnePrinter implements Printer, Scanner {

    @Override
    public void print() {
        System.out.println("Printing from " + this);
    }

    @Override
    public void scan() {
        System.out.println("Scanning from " + this);
    }
}
```

All ssems good right? Actually, when you ask to Java Service Loader first `Printer` and then `Scanner`, even if you have a sigle implementation for both interfaces, you will get two different instances. 

Starting from Java 9 there is a solution that uses the Java Module. 

From [java.util.ServiceLoader API docs:](https://docs.oracle.com/javase/9/docs/api/java/util/ServiceLoader.html)

> A service provider that is developed in a module has no control over when it is instantiated, since that occurs at the behest of the application, but it does have control over how it is instantiated:
> If the service provider declares a provider method, then the service loader invokes that method to obtain an instance of the service provider. A provider method is a public static method named "provider" with no formal parameters and a return type that is assignable to the service's interface or class.

> In this case, the service provider itself need not be assignable to the service's interface or class.

With this approach we can implement our `AllInOnePrinter` in this way

```java
package io.mfvitale;

import me.mfvitale.spi.Printer;
import me.mfvitale.spi.Scanner;

public class AllInOnePrinter implements Printer, Scanner {
    static final AllInOnePrinter INSTANCE = new AllInOnePrinter();
    private AllInOnePrinter() {}

    public static AllInOnePrinter provider() {
        return INSTANCE;
    }

    @Override
    public void print() {
        System.out.println("Printing from " + this);
    }

    @Override
    public void scan() {
        System.out.println("Scanning from " + this);
    }
}
```
and the we need to provide our `module-info.java`

```java
module io.mfvitale.extension {
    requires me.mfvitale.api;

    exports io.mfvitale;

    provides Scanner with AllInOnePrinter;
    provides Printer with AllInOnePrinter;

}
```

