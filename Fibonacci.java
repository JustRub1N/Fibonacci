import java.math.BigInteger;
import java.util.Scanner;

public class Fibonacci {

    public static BigInteger[][] matrixMultiply(BigInteger[][] A, BigInteger[][] B) {
        BigInteger[][] result = new BigInteger[2][2];
        result[0][0] = A[0][0].multiply(B[0][0]).add(A[0][1].multiply(B[1][0]));
        result[0][1] = A[0][0].multiply(B[0][1]).add(A[0][1].multiply(B[1][1]));
        result[1][0] = A[1][0].multiply(B[0][0]).add(A[1][1].multiply(B[1][0]));
        result[1][1] = A[1][0].multiply(B[0][1]).add(A[1][1].multiply(B[1][1]));
        return result;
    }

    public static BigInteger[][] matrixPower(BigInteger[][] matrix, int n) {
        BigInteger[][] result = new BigInteger[][] {
            { BigInteger.ONE, BigInteger.ZERO },
            { BigInteger.ZERO, BigInteger.ONE }
        };
        BigInteger[][] base = matrix;

        while (n > 0) {
            if (n % 2 == 1) {
                result = matrixMultiply(result, base);
            }
            base = matrixMultiply(base, base);
            n /= 2;
        }

        return result;
    }

    public static BigInteger fibonacci(int n) {
        if (n < 0) {
            throw new IllegalArgumentException("Fibonacci sequence does not exist for negative numbers");
        }

        if (n == 0) return BigInteger.ZERO;
        if (n == 1) return BigInteger.ONE;

        BigInteger[][] F = {{BigInteger.ONE, BigInteger.ONE}, {BigInteger.ONE, BigInteger.ZERO}};
        BigInteger[][] result = matrixPower(F, n - 1);

        return result[0][0];
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter the position of Fibonacci number to calculate: ");
        int n = scanner.nextInt();

        long startTime = System.nanoTime();

        BigInteger result = fibonacci(n);
        System.out.println("Fibonacci number at position " + n + " is: " + result);

        long endTime = System.nanoTime();

        long duration = endTime - startTime;

        long milliseconds = duration / 1_000_000;
        long seconds = milliseconds / 1000;
        long minutes = seconds / 60;

        if (minutes > 0) {
            System.out.println("Time taken: " + minutes + " minute(s) " + (seconds % 60) + " second(s)");
        } else if (seconds > 0) {
            System.out.println("Time taken: " + seconds + " second(s) " + (milliseconds % 1000) + " millisecond(s)");
        } else {
            System.out.println("Time taken: " + milliseconds + " millisecond(s)");
        }

        scanner.close();
    }
}
