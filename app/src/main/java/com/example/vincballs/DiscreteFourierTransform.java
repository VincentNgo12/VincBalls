package com.example.vincballs;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;
import java.util.Comparator;

import processing.core.PVector;

public class DiscreteFourierTransform {
    static class Complex{
        public double re;
        public double im;

        public Complex(double re, double im){
            this.re = re;
            this.im = im;
        }

        public void add(Complex c){
            this.re += c.re;
            this.im += c.im;
        }

        public Complex multiply(Complex c){
            final double re = this.re * c.re - this.im * c.im;
            final double im = this.re * c.im + this.im * c.re;
            return new Complex(re,im);
        }
    }

    static class FourierCoefficient{
        public double mag;
        public double freq;
        public double phase;

        public FourierCoefficient(Complex c, double freq, int N){
            double re = c.re/N;
            double im = c.im/N;
            this.freq = freq;
            this.phase = Math.atan2(im,re);
            this.mag = Math.sqrt(re*re+im*im);
        }
    }


    static class FourierCoefficientComparator implements Comparator<FourierCoefficient> {
        @Override
        public int compare(FourierCoefficient c1, FourierCoefficient c2) {
            return (int) (c2.mag - c1.mag);
        }
    }


    public static ArrayList<FourierCoefficient> DFT(ArrayList<PVector> points){
        ArrayList<FourierCoefficient> X = new ArrayList<FourierCoefficient>();
        ArrayList<Complex> values = new ArrayList<>();
        for(PVector point:points){
            values.add(new Complex(point.x,point.y));
        }
        final int N = values.size();
        final int m = (int) Math.floor(N/2);
        for(int k=-m;k<m;k++){
            Complex sum = new Complex(0,0);
            for(int n=0;n<N;n++){
                double angle = k*n*((2*Math.PI)/N);
                Complex c = new Complex(Math.cos(angle),-Math.sin(angle));
                sum.add(values.get(n).multiply(c));

            }
            X.add(new FourierCoefficient(sum, k, N));
        }
        return X;
    }
}
