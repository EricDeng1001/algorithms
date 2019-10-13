package me.ericdeng.algcollection.common;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.HashMap;

@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class Complex {

    public static final Complex ZERO = new Complex(0, 0);

    public static final Complex I = new Complex(0, 1);

    public static final Complex ONE = new Complex(1, 0);

    private static final HashMap<Double, Complex> complexPool = new HashMap<>();

    static {
        complexPool.put(0.0, ZERO);
        complexPool.put(1.0, ONE);
        complexPool.put(2.0, I);
    }

    @Getter
    private final double real;

    @Getter
    private final double imagine;

    public static Complex getComplex(double real, double imagine) {
        //double key = cantorPairing(real, imagine);
        //Complex result = complexPool.get(key);
        //if (result == null) {
        //     result = new Complex(real, imagine);
        //     complexPool.put(key, result);
        // }
        // return result;
        return new Complex(real, imagine);
    }

    public Complex conjugate() {
        return getComplex(real, -imagine);
    }

    public Complex scale(double scale) {
        return getComplex(real * scale, imagine * scale);
    }

    public Complex add(Complex complex) {
        return getComplex(real + complex.real, imagine + complex.imagine);
    }

    public Complex multiply(Complex complex) {
        return getComplex(real * complex.real - imagine * complex.imagine,
                          real * complex.imagine + imagine * complex.real);
    }

    public Complex subtract(Complex complex) {
        return getComplex(real - complex.real, imagine - complex.imagine);
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == this) { return true; }
        if (obj instanceof Complex) {
            return real == ((Complex) obj).real && imagine == ((Complex) obj).imagine;
        }
        return false;
    }

    @Override
    public int hashCode() {
        return (int) cantorPairing(real, imagine);
    }

    @Override
    public String toString() {
        return String.format("(%f, %f)", real, imagine);
    }

    private static double cantorPairing(double real, double virtual) {
        return (real + virtual) * (real + virtual + 1) / 2 + virtual;
    }

}
