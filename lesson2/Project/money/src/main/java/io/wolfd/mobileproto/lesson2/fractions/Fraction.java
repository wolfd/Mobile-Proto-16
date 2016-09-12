package io.wolfd.mobileproto.lesson2.fractions;

public class Fraction {
    private int numerator;
    private int denominator;

    public Fraction(int numerator, int denominator) {
        this.numerator = numerator;
        this.denominator = denominator;
    }

    public String toString() {
        return Integer.toString(numerator) + "/" + Integer.toString(denominator);
    }

    // It can be static because it doesn't depend on any of the instance variables
    public static int gcd(int a, int b) {
        if (b == 0) {
            return a;
        } else if (a <= b) {
            return gcd(b, a);
        } else {
            return gcd(b, a % b);
        }
    }

    public Fraction simplify() {
        int gcd = Fraction.gcd(numerator, denominator);
        this.numerator /= gcd;
        this.denominator /= gcd;

        return this;
    }

    public Fraction add(Fraction other) {
        return new Fraction(
                numerator * other.denominator + other.numerator * denominator,
                denominator * other.denominator
        ).simplify();
    }

}