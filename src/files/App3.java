package files;


import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/*Μία εφαρμογή που διαβάζει έναν-έναν τους χαρακτήρες ενός αρχείου και
τους εισάγει σε ένα πίνακα 128x2. Υποθέστε ότι οι χαρακτήρες είναι λατινικοί. Κάθε
θέση του πίνακα είναι επομένως ένας πίνακας δύο θέσεων, όπου στην 1η θέση
αποθηκεύεται ο χαρακτήρας που έχει διαβαστεί (αν δεν υπάρχει ήδη στον πίνακα)
και στην 2η θέση αποθηκεύεται το πλήθος των φορών που έχει διαβαστεί (βρεθεί)
κάθε χαρακτήρας. Αγνοήστε τα κενά και τις αλλαγές γραμμής και γενικά τα
whitespaces.
Στο τέλος η main() παρουσιάζει στατιστικά στοιχεία για κάθε χαρακτήρα όπως η
συχνότητα εμφάνισής του στο κείμενο ταξινομημένα ανά χαρακτήρα και ανά
συχνότητα εμφάνισης. */

public class App3 {
    public static void main(String[] args) throws IOException {
            File fdIn = new File("C:/Users/eleni/IdeaProjects/Epanaliptiko_Project/src/files/text.txt");
            CharFreqCounter.CharFrequency[] charArray = CharFreqCounter.FileToArray(fdIn);
            Statistics(charArray);
    }

    public class CharFreqCounter {
        public static class CharFrequency {
            char character;
            int frequency;

            CharFrequency(char character, int frequency) {
                this.character = character;
                this.frequency = frequency;
            }
        }

        public static CharFrequency[] FileToArray(File fdIn) throws IOException {

            CharFrequency[] charArray = new CharFrequency[128];
            for (int i = 0; i < 128; i++) {
                charArray[i] = new CharFrequency((char) i, 0);
            }

            try (BufferedReader reader = new BufferedReader(new FileReader(fdIn))) {
                int a;
                while ((a = reader.read()) != -1) {
                    if (Character.isWhitespace(a)) {
                        continue;
                    }
                    if (a < 128) {
                        charArray[a].frequency++;
                    }
                }
            } catch (IOException e) {
                System.err.println("Παρουσιάστηκε κάποιο πρόβλημα με το αρχείο: " + e.getMessage());

            }
            return charArray;
        }
    }

    public static void Statistics(CharFreqCounter.CharFrequency[] charArray) {
        List<CharFreqCounter.CharFrequency> filtered = new ArrayList<>();
        for (CharFreqCounter.CharFrequency c : charArray) {
            if (c.frequency > 0) {
                filtered.add(c);
            }
        }

        Collections.sort(filtered, Comparator.comparingInt(c -> c.character));
        System.out.println("Τα στατιστικά ανά χαρακτήρα είναι:");
        for (CharFreqCounter.CharFrequency c : filtered) {
            System.out.printf("Χαρακτήρας: %c Συχνότητα εμφάνισης: %d%n", c.character, c.frequency);
        }

    }
}


