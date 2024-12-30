package files;

import java.util.Scanner;
//import static java.lang.Math.max();

/*Έστω ένας πίνακας n ακεραίων. Τότε ο maximum sum subarray ο είναι ο συνεχόμενος
υποπίνακας (contiguous subarray - δυνητικά κενό) με το μεγαλύτερο άθροισμα.
Σχεδιάστε έναν γραμμικό αλγόριθμο (με πολυπλοκότητα O(n)) για να επιλύσετε τα
παραπάνω πρόβλημα. Για παράδειγμα, αν έχουμε τον πίνακα {−2, 1, −3, 4, −1, 2, 1,
−5, 4} τότε ο συνεχόμενος υποπίνακας με το μέγιστο άθροισμα είναι ο {4, −1, 2, 1},
του οποίου το άθροισμα είναι 6. (Kadane's algorithm)
*/

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
