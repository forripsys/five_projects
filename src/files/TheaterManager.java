package files;

/*Έστω ένα θέατρο που έχει θέσεις όπου η κάθε θέση περιγράφεται με ένα χαρακτήρα
που είναι η στήλη και ένα αριθμό που είναι η σειρά. Για παράδειγμα η θέση C2
βρίσκεται στην 2η σειρά και 3η στήλη.
Αναπτύξτε ένα πρόγραμμα διαχείρισης θεάτρου με 30 σειρές και 12 στήλες. Πιο
συγκεκριμένα γράψτε μία μέθοδο void book(char column, int row) που να κάνει book
6
μία θέση αν δεν είναι ήδη booked και μία μέθοδο void cancel(char column, int row)
που να ακυρώνει την κράτηση μία θέσης αν είναι ήδη booked.
*/

public class TheaterManager {

    private boolean[][] seats;
    public TheaterManager() {
        seats = new boolean[30][12];
    }

    public static void main(String[] args) {
        TheaterManager theater = new TheaterManager();

        //TESTS

        theater.book('C', 4); //book
        theater.book('C',4); //duplicate book
        theater.book('Z',31); //invalid
        theater.cancel('C',4); //cancel
        theater.book('C',4); //rebook

        theater.displaySeating();
    }


    public void book(char column, int row) {
        int colIndex = column - 'A'; //converts ASCII char to int index
        int rowIndex = row - 1;

        if (rowIndex < 0 || rowIndex >=30 || colIndex < 0 || colIndex >= 12) {
            System.out.println("Λανθασμένη επιλογή θέσης");
            return;
        }

        if (!seats[rowIndex][colIndex]) {
            seats[rowIndex][colIndex] = true;
            System.out.println("Επιτυχής κράτηση θέσης " + column + row);
        } else {
            System.out.println("Η θέση δεν είναι διαθέσιμη.");
        }
    }

    public void cancel(char column, int row) {
        int colIndex = column - 'A'; //converts ASCII char to int index
        int rowIndex = row - 1;

        if (rowIndex < 0 || rowIndex >=30 || colIndex < 0 || colIndex >= 12) {
            System.out.println("Λανθασμένη επιλογή θέσης");
            return;
        }

        if (seats[rowIndex][colIndex]) {
            seats[rowIndex][colIndex] = false;
            System.out.println("Επιτυχής ακύρωση θέσης " + column + row);
        } else {
            System.out.printf("Η θέση %c%c είναι διαθέσιμη.", row,column);
        }

    }

public void displaySeating() {
    for (int i = 0; i < seats.length; i++) {
        for (int j = 0; j < seats[i].length; j++) {
            System.out.print(seats[i][j] ? "X " : ". "); // X for booked, . for free
        }
        System.out.println();
    }
}

}
