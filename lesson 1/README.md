# Lesson 0 - Github and an Intro to Java

##  Github

## Java

### Getting started with Android Studio

We will be using Android Studio as our IDE, even though (for this class) we won't actually be creating an Android app.

1. Create a new Android Studio Project
2. Name it (something?)
3. Select Phone and Tablet, API 15 (This doesn't really matter for now)
4. Empty Activity
5. Finish

This will create a basic Android application skeleton for you. We won't be using the skeleton, so we'll create our own Module.

1. Click File > New > Module.
2. Select Java Library and click Next.
3. For Java class name, use "HelloWorld". Click Finish.
4. You should now see a Java module (in a folder with whatever name you gave it) inside your Android project.

### Hello World

Copy paste into `HelloWorld.java` and read over the following code:

```java
public class HelloWorld {

    public static void main(String[] args) {
        System.out.println("Hello World!");
    }

}
```

A window button should appear next to the main method header. Click it to run your program!

```
Hello World!
```

Don't worry too much yet about what all the words in `public static void main(String[] args)` mean. For now, just remember that this is the **main method**, and that every java program must have a main method defined. This main method is what the Java Virtual Machine runs initially, and all subsequent code eventually stems from the main method.

### `Account` and `MoneySaver`

#### Classes

Create two new classes, and copy paste the following boilerplates into them:

```
public class Account {

    private MoneySaver owner;
    private long amount;

    public Account(long amount, MoneySaver owner) {
    }

    public String toString() {
        return "Account owner: " + owner.getName() + ", Account Balance: $" + amount;
    }

    public void deposit(long money) {
    }

    public long getAmount() {
    }

    public void setAmount(long amount) {
    }

}

```

```
public class MoneySaver {

    private long myMoney;
    private Account myAccount;
    private String name;


    public MoneySaver(String name, long money) {
        this.name = name;
        myMoney = money;
    }

    public String toString() {
        return name + ", " + "My balance is: "+ myMoney;
    }

    public String getName() {
        return name;
    }

    public void signUpForChecking(int amount) {
    }

    public Account getMyAccount() {
        return myAccount;
    }

    public void deposit(int amount) {
    }

    public void withdraw(int amount) {
    }

    public static void main(String[] args) {
    }
}

```

##### Constructors

Each of these classes has a constructor (in `Account`, the constructor is `public Account(long amount, MoneySaver owner)`). The constructor is a special method used to initialize instances of your class (just like Python's `__init__` method).

**Go ahead and implement the `Account`'s constructor.**

#### Fields

You'll notice both classes also have fields. Fields are either attributes of members of a class (an `Account` **has a** `amount`) or things that belong to the class. Fields should almost exclusively be `private` unless you have a *really* good reason to make it public. If you want an outside class to be able to access a field, you must create public getter/setter methods.

**Go ahead and implement the `get` and `setAmount` functions in `Account`.**

#### Methods

These classes also have methods, which are things instances can "do". Each method must specify what it returns, (`void` if nothing). Generally speaking methods are `public`. However, you might find yourself writing a `private` method that nothing outside the class should be able to see. Often, "helper" methods are `private`.

**implement `deposit`, `getAmount`, and `setAmount` in `Account`.**

##### What does `static` mean?

`static` methods operate on the *Class*, as opposed to *instances* of the class. You can call static methods without creating any object e.g. `Collections.sort()`. Make a method `static` if the method is something the *Class* can do.

#### Inheritance

Inheritance and polymorphism is one of Java's most powerful features. 

### Fraction

Create a new class called `Fraction`.

#### At the end of class

Your `Fraction` class should look something like this:

```
package com.example;

public class Fraction {

    private int numerator;
    private int denominator;

    public Fraction(int num, int den) {
        numerator = num;
        denominator = den;
    }

    public String toString() {
        return Integer.toString(numerator) + "/" + Integer.toString(denominator);
    }

}
```