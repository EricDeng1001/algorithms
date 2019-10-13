package me.ericdeng.algcollection.dc;

import me.ericdeng.algcollection.checker.FFTChecker;
import me.ericdeng.algcollection.common.Complex;

import java.util.Arrays;

public class FFT {

    public static void main(String[] args) {
        int n = 4;
        Complex[] x = new Complex[n];
        Complex[] y = new Complex[n];

        for (int i = 0; i < x.length; i++) {
            x[i] = Complex.getComplex(-2 * Math.random() + 1, 0);
        }
        for (int i = 0; i < n; i++) {
            y[i] = Complex.getComplex(-2 * Math.random() + 1, 0);
        }
        System.out.println("x: " + Arrays.toString(x));
        System.out.println("ifft(fft(x)): " + Arrays.toString(ifft(fft(x))));
        System.out.println("y: " + Arrays.toString(y));
        System.out.println("ifft(fft(y)): " + Arrays.toString(ifft(fft(y))));
        System.out.println("fft: x * y: " + Arrays.toString(FFT.convolution(x, y)));
        System.out.println("naive: x * y: " + Arrays.toString(FFTChecker.naive(x, y)));
    }

    /**
     * 计算两个多项式的卷积
     *
     * @param a 多项式a的系数表达式
     * @param b 多项式b的系数表达式
     * @return 乘积的多项式表达式，中间为0则按位填写。length = a.length + b.length - 1
     */
    public static Complex[] convolution(Complex[] a, Complex[] b) {
        int totalLength = a.length + b.length - 1;
        Complex[] extA = zeroExtend(a, totalLength);
        Complex[] extB = zeroExtend(b, totalLength);
        Complex[] result = extA;
        // length might be changed after fft
        extA = fft(extA);
        extB = fft(extB);
        Complex[] resultExt = extA;
        for (int i = 0; i < resultExt.length; i++) {
            resultExt[i] = extA[i].multiply(extB[i]);
        }
        resultExt = ifft(resultExt);
        // cut off zeros
        System.arraycopy(resultExt, 0, result, 0, totalLength);
        return result;
    }

    /**
     * 使用fft算法得到多项式A(x)的离散傅里叶变换，其中A(x)由输入的系数序列代表。
     * 结果长度会扩展到2的幂次
     *
     * @param x 输入多项式的系数序列，第0位为常数，最后一位为最高位系数，中间有0则按位填写
     * @return dft(x) x的离散傅里叶变换
     */
    public static Complex[] fft(Complex[] x) {
        int n = x.length;
        if (n == 1) { return new Complex[]{x[0]}; }
        if (!isPow2(n)) {
            x = extendToPow2(x);
            n = x.length;
        }
        Complex[] tmp = new Complex[n / 2];
        for (int k = 0; k < n / 2; k++) {
            tmp[k] = x[k * 2];
        }
        Complex[] even = fft(tmp);
        for (int k = 0; k < n / 2; k++) {
            tmp[k] = x[k * 2 + 1];
        }
        Complex[] odd = fft(tmp);
        Complex[] result = new Complex[n];
        for (int k = 0; k < n / 2; k++) {
            // update rotation parameter
            double kth = -2 * k * Math.PI / n;
            Complex wk = Complex.getComplex(Math.cos(kth), Math.sin(kth));
            // butterfly operation
            Complex m = wk.multiply(odd[k]);
            result[k] = even[k].add(m);
            result[k + n / 2] = even[k].subtract(m);
        }
        return result;
    }

    /**
     * 输入一个离散傅里叶变换，得到A(x)的系数表达式的系数序列。
     *
     * @param x 某个离散傅里叶变换
     * @return 该离散傅里叶变换对应的多项式系数序列，第0位为常数，最后一位为最高位系数，中间有0则按位填写
     */
    public static Complex[] ifft(Complex[] x) {
        int n = x.length;
        Complex[] result = new Complex[n];
        for (int i = 0; i < n; i++) {
            result[i] = x[i].conjugate();
        }
        result = fft(result);
        for (int i = 0; i < n; i++) {
            result[i] = result[i].conjugate().scale(1.0 / n);
        }
        return result;
    }

    private static boolean isPow2(int x) {
        return Integer.bitCount(x) == 1;
    }

    private static Complex[] extendToPow2(Complex[] x) {
        int leadingZeros = Integer.numberOfLeadingZeros(x.length);
        int rsh = 31 - leadingZeros;
        int targetLength = (x.length >> rsh) << (rsh + 1);
        return zeroExtend(x, targetLength);
    }

    private static Complex[] zeroExtend(Complex[] x, int targetLength) {
        Complex[] target = new Complex[targetLength];
        System.arraycopy(x, 0, target, 0, x.length);
        for (int i = x.length; i < targetLength; i++) {
            target[i] = Complex.ZERO;
        }
        return target;
    }

}
