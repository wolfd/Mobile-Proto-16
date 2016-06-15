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
public class MoneySaver {

    private long myMoney;
    private Account myAccount;
    private String name;


    public MoneySaver(String name, long money) {
        this.name = name;
        myMoney = money;
    }

    public String getName() {
        return name;
    }

    public Account getMyAccount() {
        return myAccount;
    }

    public String toString() {
        return name + ", " + "My balance is: "+ myMoney;
    }

    public void deposit(int amount) {
    }

    public void withdraw(int amount) {
    }
    
    public void signUpForChecking(int amount) {
    }

    public static void main(String[] args) {
    }
}

```

```
public class Account {

    private MoneySaver owner;
    private long amount;

    public String toString() {
        return "Account owner: " + owner.getName() + ", Account Balance: $" + amount;
    }

    public static Account largerAccount(Account acc1, Account acc2) {
    }

}

```

##### Constructors

The constructor is a special method used to initialize instances of your class (just like Python's `__init__` method). Classes by default inherit a no-argument constructor from the default `Object` class. That means that even without epxlicitly defining a constructor, we can already initalize an `Account`:

```Account a = new Account() // This works!```

Often, you will want to initialize your object with some values. In this case you must define your own constructor.

**Go ahead and implement the `Account` constructor. It should have input arguments `long amount, MoneySaver owner`**

#### Fields

You'll notice both classes also have fields. Fields are either attributes of members of a class (an `amount` describes an `Account`) or things that belong to the class (an `Account` has an `owner`). **Fields should almost exclusively be `private` unless you have a *really* good reason to make it `public`.** If you want an outside class to be able to access a field, you must create public getter/setter methods.

**Define and implement the `getAmount` and `setAmount` methods in `Account`. Make sure they have appropriate return types and input arguments.**

Add the following to your main method (`public static void main(String[] args)`) to test your code so far:

```
MoneySaver jim = new MoneySaver("Jim", 100);
Account a = new Account(100, jim);
System.out.println(a);
a.setAmount(20);
System.out.println("New amount: " + a.getAmount());
```

Expected output:

```
Account owner: Jim, Account Balance: $100
New amount: 20
```

#### Methods

These classes also have methods, which are things instances can "do". Each method must specify what it returns, (`void` if nothing). Generally speaking methods are `public`. However, you might find yourself writing a `private` method that nothing outside the class should be able to see. Often, "helper" methods are `private`.

**implement `deposit` in `Account`.**

Test your code:

```
MoneySaver jim = new MoneySaver("Jim", 100);
Account a = new Account(100, jim);
System.out.println(a);
a.setAmount(20);
a.deposit(10);
System.out.println("New amount: " + a.getAmount());
```

```
Account owner: Jim, Account Balance: $100
New amount: 30
```

##### What does `static` mean?

`static` methods operate on the *Class*, as opposed to *instances* of the class. You can call static methods without creating any object e.g. `Collections.sort()`. Make a method `static` if the method is something the *Class* can do.

**Implement `largerAccount` in `Account`**

Test your implementation!

```
MoneySaver jim = new MoneySaver("Jim", 100);
MoneySaver bob = new MoneySaver("Bob", 200);
Account small = new Account(20, jim);
Account big = new Account(30, bob);
System.out.println(Account.largerAccount(small, big));
```

```
Account owner: Bob, Account Balance: $30
```

#### Inheritance

##### `extends`

**Define a class `CheckingAccount` that `extends` from `Account`.** CheckingAccount now has all of the methods that `Account` does. **Give it a constructor with a similar signature to `Account`'s construtor.** If you need help implementing the constructor, check out [this](https://docs.oracle.com/javase/tutorial/java/IandI/super.html) website.

**Give your `CheckingAccount` a `withdraw` method.** This function should properly update the account balance.

Finally, you are ready for your `MoneySaver` to be able to sign up for a checking account. **Implement `signUpForChecking`, ensuring you properly modify and initialize the `MoneySaver's` fields.**

Realize that you can store a `CheckingAccount` instance in `myAccount` without changing its type from `Account`! This is an example of *polymorphism*. Because a `CheckingAccount` *is an* `Account`, you can store `CheckingAccount`s in `Account`s.

Note that you can't do it the other way around. Try putting this code in your project:

```
CheckingAccount specific = new Account(100, new MoneySaver("Bill", -20)); // Incompatible types error
```

This is because `CheckingAccount` is the specific subclass, and `Account` is the general `superclass`.

**Give your `MoneySaver` a `withdraw` method.** You will have to [cast](http://javarevisited.blogspot.com/2012/12/what-is-type-casting-in-java-class-interface-example.html) `myAccount` to a `CheckingAccount` in order to call its `withdraw` method. This is because `Account`s don't have `withdraw` methods, only `CheckingAccount`s do. You have to explicitly tell Java that the account is a `CheckingAccount` to call `withdraw`.

Test your code from this section:

```
MoneySaver jim = new MoneySaver("Jim", 100);
jim.signUpForChecking(30);
CheckingAccount acc = (CheckingAccount) jim.getMyAccount();
System.out.println(jim);
System.out.println(acc);
jim.deposit(40);
System.out.println(jim);
jim.withdraw(60);
System.out.println(jim);
System.out.println(acc);
```

```
Jim, My balance is: 70
Account owner: Jim, Account Balance: $30
Jim, My balance is: 30
Jim, My balance is: 90
Account owner: Jim, Account Balance: $10
```

##### Abstraction

The last thing we will learn about inheritance is the `abstract` keyword. The `abstract` keyword goes in the class declaration (`public abstact class...`). This keyword does two things:

* You can now declare `abstract` methods. These methods have no body, but classes that extend them must define these methods. For example, let's say you had an abstract `Vehicle` class:

```
public abstract class Vehicle {

    int wheels;
	
	public Vehicle(int wheels) {
        this.wheels = wheels;
	}

    public int getWheels() {
        return wheels;
    }

	public abstract void turnOn();
}
```

This says that all classes `extend`ing `Vehicle` must implement `turnOn`. Why doesn't Vehicle implement `turnOn`? Well, what a vehicle must do to turn on depends on what kind of vehicle it is! The implementation would be quite difficult for a `Car` as opposed to a `Boat`. (Boats turn on, right?)

Note that the `Car` and `Boat` classes would both have the inhereted method `getWheels()`, so you wouldn't have to implement it repetitively in each subclass.

* You can no longer initialize instances of the `abstract` class. Using the above example:

```
public class Car extends Vehicle {
	public void turnOn() {
		...
	}
}
Vehicle v = new Vehicle(); // This doesn't work.
Vehicle v = new Car(); // This works!
v.turnOn(); // This works because all Vehicles have a turnOn method.
Car c = new Car(); // This works too.
c.turnOn();
```

**Make your `Account` class `abstract`. Confirm that you can't initalize it anymore.**
```