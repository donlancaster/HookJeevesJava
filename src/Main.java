
import java.util.Arrays;

public class Main {
    static final double A = 0.5;
    static final double[] V = {500, 100, 200, 150, 400};
    static final double[] K = {20, 10, 5, 3, 7};
    static final double[] S = {5, 10, 10, 4, 2};
    static final double[] f = {10, 20, 5, 2, 8};

    static final double Fn = 1000;
    static final double N = 25;
    static double[] q = {1, 1, 1, 1};

    public static void main(String[] args) {


        int[][] mas = {
                {0, 0, 0, 1},
                {0, 0, 1, 0},
                {0, 0, 1, 1},
                {0, 1, 0, 0},
                {0, 1, 0, 1},
                {0, 1, 1, 0},
                {0, 1, 1, 1},
                {1, 0, 0, 0},
                {1, 0, 0, 1},
                {1, 0, 1, 0},
                {1, 0, 1, 1},
                {1, 1, 0, 0},
                {1, 1, 0, 1},
                {1, 1, 1, 0},
                {1, 1, 1, 1},
        };


        double deltaX = 1;
        double e = 0.1;

        double[] qPrev = {q[0], q[1], q[2], q[3]};
     //   double n = 0;
        while (deltaX > e) {
            boolean luck = false;
       //     n++;
            for (int i = 0; i < mas.length; i++) {

                double[] masQPlus = {q[0], q[1], q[2], q[3]};
                double[] masQMinus = {q[0], q[1], q[2], q[3]};

                for (int j = 0; j < 4; j++) {
                    if (mas[i][j] == 1) {
                        masQMinus[j] -= deltaX;
                        masQPlus[j] += deltaX;
                    }
                }
                System.out.println("До поиска по образцу");
                System.out.println("Arrays.toString(masQ):" + Arrays.toString(q));
                System.out.println("function(masQ):" + function(q));
                System.out.println("function(masQMinus):" + function(masQMinus));
                System.out.println("function(masQPlus):" + function(masQPlus));
                System.out.println("delta X:" + deltaX);
                System.out.println();

                if (function(masQMinus) < function(q) && q5(masQMinus) >= 0) {
                    q = masQMinus;
                    luck = true;
                    continue;
                }
                if (function(masQPlus) < function(q) && q5(masQMinus) >= 0) {
                    q = masQPlus;
                    luck = true;
                    continue;
                }
            }

            if (luck) {
                double[] masQTemp = {2 * q[0] - qPrev[0], 2 * q[1] - qPrev[1], 2 * q[2] - qPrev[2], 2 * q[3] - qPrev[3]};

                for (int i = 0; i < mas.length; i++) {

                    double[] masQPlus = {masQTemp[0], masQTemp[1], masQTemp[2], masQTemp[3]};
                    double[] masQMinus = {masQTemp[0], masQTemp[1], masQTemp[2], masQTemp[3]};

                    for (int j = 0; j < 4; j++) {
                        if (mas[i][j] == 1) {
                            masQMinus[j] -= deltaX;
                            masQPlus[j] += deltaX;
                        }
                    }

                    System.out.println("После поиска по образцу");
                    System.out.println("Arrays.toString(masQ):" + Arrays.toString(masQTemp));
                    System.out.println("function(masQ):" + function(masQTemp));
                    System.out.println("function(masQMinus):" + function(masQMinus));
                    System.out.println("function(masQPlus):" + function(masQPlus));
                    System.out.println("delta X:" + deltaX);
                    System.out.println();

                    if (function(masQMinus) < function(masQTemp) && q5(masQMinus) >= 0) {
                        masQTemp = masQMinus;
                        luck = true;
                        continue;
                    }
                    if (function(masQPlus) < function(masQTemp) && q5(masQMinus) >= 0) {
                        masQTemp = masQPlus;
                        luck = true;
                        continue;
                    }
                }

                if (function(masQTemp) < function(q) && q5(masQTemp) >= 0) {
                    qPrev = q;
                    q = masQTemp;
                }

            } else {
                deltaX *= A;
            }

        }

        for (double v : q) {
            System.out.print(v + " ");
        }
        System.out.println(q5(q));
        System.out.println(function(q));

    }

    static double function(double[] q) {

        double result = 0;

        for (int i = 0; i < 4; i++) {
            result += K[i] * V[i] / q[i] + 0.5 * S[i] * q[i];
        }

        double fiqi = 0;
        for (int i = 0; i < 4; i++) {
            fiqi += f[i] * q[i];
        }

        result += (K[4] * V[4] * f[4]) / (Fn - fiqi) + 0.5 * S[4] / f[4] * (Fn - fiqi);

        return result;
    }

    static double q5(double[] mas) {
        double temp = 0;
        for (int i = 0; i < mas.length; i++) {
            temp += mas[i] * f[i];
        }
        return (Fn - temp) / f[4];
    }
}


