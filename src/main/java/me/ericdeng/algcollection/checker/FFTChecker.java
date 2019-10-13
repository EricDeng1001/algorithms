package me.ericdeng.algcollection.checker;

import me.ericdeng.algcollection.common.Complex;

public class FFTChecker {

    public static Complex[] naive(Complex[] a, Complex[] b) {
        int totalLength = a.length + b.length - 1;
        Complex[] result = new Complex[totalLength];

        for (int i = 0; i < totalLength; i++) {
            result[i] = Complex.ZERO;
        }
        for (int i = 0; i < a.length; i++) {
            for (int j = 0; j < b.length; j++) {
                result[i + j] = result[i + j].add(a[i].multiply(b[j]));
            }
        }
        return result;
    }

}
