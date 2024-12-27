package files;

import java.util.Scanner;
//import static java.lang.Math.max();

/*Με τον παρακάτω αλγόριθμο διατρέχουμε κάθε στοιχείο του πίνακα σε μια επανάληψη
* κάνοντας μια πράξη. Άρα η πολυπλοκότητα χρόνου είναι Ο(n) */

public class App2 {
    public static void main(String[] args) {
        int[] arr = {-2, 1, -3, 4, -1, 2, 1, -5, 4};
        int sum = MaxSubSum(arr);
        System.out.println("Το μέγιστο άθροισμα του συνεχόμενου υποπίνακα είναι: " + sum);

    }

    public static int MaxSubSum(int[] arr) {
        if (arr == null) {
            return 0;
        }

        int localMax = arr[0];
        int globalMax = arr[0];

        for (int i=1; i < arr.length; i++) {
            localMax = Math.max(localMax + arr[i], arr[i]);
            globalMax = Math.max(globalMax, localMax);
        }
        return globalMax;
    }

}
