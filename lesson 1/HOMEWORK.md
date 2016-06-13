# The `Fraction` class

Create a new class called `Fraction`.
```
public class Fraction {

    private int numerator;
    private int denominator;

    public String toString() {
        return Integer.toString(numerator) + "/" + Integer.toString(denominator);
    }

}
```

#### Implement a constructor

In the arguments, have numerator come first.

#### `gcd`

Give your `Fraction` a `gcd` function, which returns the greatest common diviser of two integers. The function should have the following header:

```
public static int gcd(int m, int n)
```

and **should work if `n > m`, and vice-versa**.

There's a pretty sweet recursive algorithm for `gcd` called *Euclid's Algorithm* which states that the `gcd(a, b)` is:

```
// This function assumes a>b. Your's should not!
function gcd(a, b)
	If b is 0:
		return a
	Else:
		return gcd(b, a mod b)
```

Why does it make sense for `gcd` to be `static`? If you're not sure, ask a peer or an instructor.

#### `simplify`

Give your `Fraction` a `simplify` function, which simplifies the fraction. We leave it up to you to determine the proper method header. 

#### `add`

Give your `Fraction` a `add` function, which add the other fraction by this fraction, and returns the simplified result. *This function should not modify either fraction*. You shouldn't change the [Access Modifiers](http://www.tutorialspoint.com/java/java_access_modifiers.htm) to the `numerator` and `denomenator` fields (they should be private).

## Test your functions

Create a new class called `FractionTester`. Copy-paste the following code into it. All the tests should pass:

```
public class FractionTester {

    public void runTests() {
        Fraction f1 = new Fraction(10,20);
        Fraction f2 = new Fraction(2,3);
        System.out.println(f1.add(f2) + " = 7/6");
        System.out.println(f1 + " = 10/20");
        System.out.println(f2 + " = 2/3");
        f1.simplify();
        System.out.println(f1 + " = 1/2");
        System.out.println(Fraction.gcd(9, 2) + " = 1");
        System.out.println(Fraction.gcd(24, 18) + " = 6");
    }

    public static void main(String[] args) {
        FractionTester tester = new FractionTester();
        tester.runTests();
    }
}

```

# Animal Farm

