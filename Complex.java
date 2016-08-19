
class Complex {

    double re = 0, im = 0;

    static Complex c(double re, double im) {
        return new Complex(re, im);
    }

    public Complex(double re) {
        this.re = re;
        this.im = 0;
    }

    public Complex(double re, double im) {
        this.re = re;
        this.im = im;
    }

    Complex add(Complex c) {
        return new Complex(this.re + c.re, this.im + c.im);
    }

    Complex substract(Complex c) {
        return new Complex(this.re - c.re, this.im - c.im);
    }

    Complex multiply(Complex b) {
        return new Complex(this.re * b.re - this.im * b.im, this.im * b.re + this.re * b.im);
    }

    Complex square() {
        return this.multiply(this);
    }

    Complex scale(double x) {
        this.re *= x;
        this.im *= x;
        return this;
    }

    @Override
    public String toString() {
        if (re == 0 && im == 0) {
            return "0";
        }
        if (re == 0) {
            if (im < 0) {
                return "- j*" + (-im);
            }
            return "j*" + im;
        }
        if (im == 0) {
            return re + "";
        }
        String com = "";
        com = "(" + re;
        if (im < 0) {
            com = com + " - j*" + (-im) + ")";
        } else {
            com = com + " + j*" + im + ")";
        }
        return com;
    }

    static Complex[] FFT(Complex[] poly, int degree, Complex w) {
        if (degree == 1) {
            return new Complex[]{poly[0]};
        }
        Complex even[] = new Complex[poly.length / 2];
        Complex odd[] = new Complex[poly.length / 2];
        int e = 0, o = 0;
        for (int i = 0; i < poly.length; i++) {
            if (i % 2 == 0) {
                even[e++] = poly[i];
            } else {
                odd[o++] = poly[i];
            }
        }
        Complex[] fEven = FFT(even, degree >> 1, w.square());
        Complex[] fOdd = FFT(odd, degree >> 1, w.square());
        Complex x = new Complex(1, 0);
        Complex[] F = new Complex[degree];
        for (int j = 0; j < degree / 2; j++) {
            F[j] = fEven[j].add(x.multiply(fOdd[j]));
            F[j + degree / 2] = fEven[j].substract(x.multiply(fOdd[j]));
            x = x.multiply(w);
        }
        return F;
    }

    static Complex[] IFT(Complex[] poly, int degree, Complex w) {
        if (degree == 1) {
            return new Complex[]{poly[0]};
        }
        Complex even[] = new Complex[poly.length / 2];
        Complex odd[] = new Complex[poly.length / 2];
        int e = 0, o = 0;
        for (int i = 0; i < poly.length; i++) {
            if (i % 2 == 0) {
                even[e++] = poly[i];
            } else {
                odd[o++] = poly[i];
            }
        }
        Complex[] fEven = FFT(even, degree >> 1, w.square());
        Complex[] fOdd = FFT(odd, degree >> 1, w.square());
        Complex x = new Complex(1, 0);
        Complex[] F = new Complex[degree];
        for (int j = 0; j < degree / 2; j++) {
            F[j] = fEven[j].add(x.multiply(fOdd[j])).scale(1d / degree);
            F[j + degree / 2] = fEven[j].substract(x.multiply(fOdd[j])).scale(1d / degree);
            x = x.multiply(w);
        }
        return F;
    }

}
