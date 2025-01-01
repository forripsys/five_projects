package files;

/*Αναπτύξτε ένα παιχνίδι Τρίλιζα, όπου δύο παίκτες παίζουν Χ και Ο (ή 1 και 2 αν θέλετε
να υλοποιήσετε με πίνακα ακεραίων και όχι με πίνακα char) και κερδίζει ο παίκτης
που έχει συμπληρώσει τρία ίδια σύμβολα ή αριθμούς σε οποιαδήποτε διάσταση του
πίνακα, οριζόντια, κάθετα ή διαγώνια.
Η main() μπορεί να ελέγχει τη ροή του παιχνιδιού, όπως ποιος παίκτης παίζει κάθε
φορά (εναλλαγή μεταξύ των δύο παικτών), να διαβάζει από το stdin το σύμβολο που
δίνει ο κάθε παίκτης και να εμφανίζει με γραφικό τρόπο την τρίλιζα μετά από κάθε
κίνηση κάθε παίκτη.
Ενώ, μπορείτε να δημιουργήσετε και μία μέθοδο που να ελέγχει (μετά από κάθε
κίνηση) αν ο παίκτης που έκανε την κίνηση έκανε τρίλιζα.
Το πρόγραμμα θα πρέπει να λαμβάνει υπόψη την περίπτωση ισοπαλίας όπως και να
μην επιτρέπει ένας παίκτης να παίξει σε θέση που είναι ήδη κατειλημμένη. */

import java.lang.ref.PhantomReference;
import java.util.Scanner;

public class App4 {

    private static char[][] board = new char[3][3];
    private static char currentPlayer = 'X';

    public static void main(String[] args) {

        BoardInit();

        while (true) {
            ConsolePrinter();
            Input();
            if (isWinner()) {
                ConsolePrinter();
                System.out.println("To " + currentPlayer + " κέρδισε!");
                break;
            }
            if (isBoardFull()) {
                ConsolePrinter();
                System.out.println("Ισοπαλία!");
                break;
            }
            PlayerSwitcher();
        }

    }

    private static void BoardInit() {
        for (int i =0; i <3; i++) {
            for (int j = 0; j < 3; j++) {
                board[i][j] = ' ';
            }
        }
    }

    private static void ConsolePrinter() {
        System.out.println("--TIC--TAC--TOE--");
        for (int i = 0; i < 3; i++) {
            System.out.print("| ");
            for (int j = 0; j < 3; j++) {
                System.out.print(board[i][j] + " | ");
            }
            System.out.println();
            System.out.println("-------------");
        }
    }

    private static void Input() {
        int col;
        int row;
        Scanner in = new Scanner(System.in);

        while (true) {
            System.out.println(currentPlayer + " κάνε την επόμενη κίνηση γράφοντας γραμμή και στήλη.");
            row = in.nextInt();
            col = in.nextInt();

            if (row >= 0 && row < 3 && col >= 0 && col < 3 && board[row][col] == ' ') {
                board[row][col] = currentPlayer;
                break;
            } else {
                System.out.println("Παράνομη κίνηση.");
            }
        }
    }

    private static void PlayerSwitcher() {
        currentPlayer = (currentPlayer == 'X') ? 'O' : 'X';
    }

    private static boolean isWinner() {

        for (int i = 0; i < 3; i++) {
            if ((board[i][0] == currentPlayer && board[i][1] == currentPlayer && board[i][2] == currentPlayer) ||
                    (board[0][i] == currentPlayer && board[1][i] == currentPlayer && board[2][i] == currentPlayer)) {
                return true;
            }
        }

        if ((board[0][0] == currentPlayer && board[1][1] == currentPlayer && board[2][2] == currentPlayer) ||
                (board[0][2] == currentPlayer && board[1][1] == currentPlayer && board[2][0] == currentPlayer)) {
            return true;
        }

        return false;
    }

    private static boolean isBoardFull() {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (board[i][j] == ' ') {
                    return false;
                }
            }
        }
        return true;
    }


}
