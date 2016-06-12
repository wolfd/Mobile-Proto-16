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

1. Click File > New > Module. Select Java Library and click Next. For Java class name, use "HelloWorld". Click Finish. You should now see a Java module (in a folder with whatever name you gave it) inside your Android project.

### Hello World

Copy paste into `HelloWorld.java` and read over the following code:

### Hello World

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

On the dropdown to the left of the run button in the menu header (should say "app"), click "Save "Hello World" configuration".