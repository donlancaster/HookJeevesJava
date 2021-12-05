
import java.util.Arrays;

public class Main {

    static final double A = 0.5;
    static final double[] V = {500, 100, 200, 150, 400};
    static final double[] K = {20, 10, 5, 3, 7};
    static final double[] S = {5, 10, 4, 2, 20};

    static double[] q = {1, 1, 1, 1, 1};

    public static void main(String[] args) {


        int[][] binaryArray = {
                {0, 0, 0, 0, 1},
                {0, 0, 0, 1, 0},
                {0, 0, 0, 1, 1},
                {0, 0, 1, 0, 0},
                {0, 0, 1, 0, 1},
                {0, 0, 1, 1, 0},
                {0, 0, 1, 1, 1},
                {0, 1, 0, 0, 0},
                {0, 1, 0, 0, 1},
                {0, 1, 0, 1, 0},
                {0, 1, 0, 1, 1},
                {0, 1, 1, 0, 0},
                {0, 1, 1, 0, 1},
                {0, 1, 1, 1, 0},
                {0, 1, 1, 1, 1},
                {1, 0, 0, 0, 0},
                {1, 0, 0, 0, 1},
                {1, 0, 0, 1, 0},
                {1, 0, 0, 1, 1},
                {1, 0, 1, 0, 0},
                {1, 0, 1, 0, 1},
                {1, 0, 1, 1, 0},
                {1, 0, 1, 1, 1},
                {1, 1, 0, 0, 0},
                {1, 1, 0, 0, 1},
                {1, 1, 0, 1, 0},
                {1, 1, 0, 1, 1},
                {1, 1, 1, 0, 0},
                {1, 1, 1, 0, 1},
                {1, 1, 1, 1, 0},
                {1, 1, 1, 1, 1},
        };


        double step = 1;
        double e = 0.1;

        double[] qPrev = {q[0], q[1], q[2], q[3], q[4]};
        int n = 0;
        while (step > e) {
            boolean luck = false;
            n++;
            for (int[] ma : binaryArray) {

                double[] qAdd = {q[0], q[1], q[2], q[3], q[4]};
                double[] qSubtract = {q[0], q[1], q[2], q[3], q[4]};

                for (int j = 0; j < 5; j++) {
                    if (ma[j] == 1) {
                        qSubtract[j] -= step;
                        qAdd[j] += step;
                    }
                }
                String result = Arrays.toString(q);
                System.out.println("L = " + function(q) + " | " + result.substring(1, result.length() - 1));

                if (function(qSubtract) < function(q) && isPositive(qSubtract)) {
                    q = qSubtract;
                    luck = true;
                    continue;
                }
                if (function(qAdd) < function(q) && isPositive(qAdd)) {
                    q = qAdd;
                    luck = true;
                    continue;
                }
            }

            if (luck) {
                double[] qTemp = {2 * q[0] - qPrev[0], 2 * q[1] - qPrev[1], 2 * q[2] - qPrev[2], 2 * q[3] - qPrev[3], 2 * q[4] - qPrev[4]};

                for (int[] ma : binaryArray) {

                    double[] qAdd = {qTemp[0], qTemp[1], qTemp[2], qTemp[3], qTemp[4]};
                    double[] qSubtract = {qTemp[0], qTemp[1], qTemp[2], qTemp[3], qTemp[4]};

                    for (int j = 0; j < 5; j++) {
                        if (ma[j] == 1) {
                            qSubtract[j] -= step;
                            qAdd[j] += step;
                        }
                    }
                    String result = Arrays.toString(qTemp);
                    System.out.println("L = " + function(qTemp) + " | " + result.substring(1, result.length() - 1) + " ");


                    if (function(qSubtract) < function(qTemp) && isPositive(qTemp)) {
                        qTemp = qSubtract;
                        continue;
                    }
                    if (function(qAdd) < function(qTemp) && isPositive(qTemp)) {
                        qTemp = qAdd;
                        continue;
                    }
                }

                if (function(qTemp) < function(q) && isPositive(qTemp)) {
                    qPrev = q;
                    q = qTemp;
                }

            } else {
                step *= A;
            }
        }

        for (double value : q) {
            System.out.print(value + " ");
        }
        System.out.println(function(q));
        System.out.println(n);
    }

    static boolean isPositive(double[] q) {
        for (double value : q) {
            if (value < 0) {
                return false;
            }
        }
        return true;
    }

    static double function(double[] q) {

        double result = 0;

        for (int i = 0; i < q.length; i++) {
            result += K[i] * V[i] / q[i] + 0.5 * S[i] * q[i];
        }
        return result;
    }
    }