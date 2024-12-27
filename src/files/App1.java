package files;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;



public class App1 {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);

        System.out.println("Πληκτρολογήστε το όνομα του παραγόμενου αρχείου:");
        String outputFileName = in.nextLine();
        String tempDir = System.getProperty("java.io.tmpdir");
        String inputFilePath = tempDir + "nums";
        String outputFilePath = tempDir + outputFileName;

        try {
            int[] numbers = FileReader("nums");
            Arrays.sort(numbers);
            List<int[]> combinations = CombinationGenerator(numbers, 6);

            List<int[]> Validity = new ArrayList<>();
            for (int[] combination : combinations) {
                if (ValidityCheck(combination)) {
                    Validity.add(combination);
                }
            }

            outputFile(Validity, outputFileName);
        } catch (IOException e) {
            e.printStackTrace();
        }

        in.close();

    }

    private static int[] FileReader(String sampletxt) throws IOException {
        BufferedReader filereader = new BufferedReader(new FileReader( "nums"));
        List<Integer> NumList = new ArrayList<>();
        String chr;

        while ((chr = filereader.readLine()) != null) {
            int number = Integer.parseInt(chr.trim());
            if (number >= 1 && number <= 49) {
                NumList.add(number);
            }
        }
        filereader.close();

        if (NumList.size() < 7 || NumList.size() > 49) {
            throw new IllegalArgumentException("Το αρχείο θα πρέπει να περιέχει απο 7 έως 49 ακεραίους.");
        }

        int[] numbers = new int[NumList.size()];
        for (int i = 0; i < NumList.size(); i++) {
            numbers[i] = NumList.get(i);
        }
        return numbers;
    }

    private static void outputFile(List<int[]> combinations, String sampletxt) throws IOException {
        BufferedWriter writer = new BufferedWriter(new FileWriter(sampletxt));
        for (int[] combination : combinations) {
            writer.write(Arrays.toString(combination));
            writer.newLine();
        }
        writer.close();
    }

    private static List<int[]> CombinationGenerator(int[] numbers, int k) {
        List<int[]> combinations = new ArrayList<>();
        CombinationGenerator(combinations, new int[k], numbers, 0, 0, k);
        return combinations;
    }

    private static void CombinationGenerator(List<int[]> combinations, int[] combination, int[] numbers, int start, int index, int k) {
        if (index == k) {
            combinations.add(combination.clone());
            return;
        }

        for (int i = start; i <= numbers.length - (k - index); i++) {
            combination[index] = numbers[i];
            CombinationGenerator(combinations, combination, numbers, i + 1, index + 1, k);
        }
    }

    private static boolean ValidityCheck(int[] combination) {
        return countEven(combination) <= 4 &&
                countOdd(combination) <= 4 &&
                countConsecutive(combination) <= 2 &&
                countSameLastDigit(combination) <= 3 &&
                countSameDecade(combination) <= 3;
    }

    private static int countEven(int[] combination) {
        int counter = 0;
        for (int number : combination) {
            if (number % 2 == 0) {
                counter++;
            }
        }
        return counter;
    }

    private static int countOdd(int[] combination) {
        int count = 0;
        for (int number : combination) {
            if (number % 2 != 0) {
                count++;
            }
        }
        return count;
    }

    private static int countConsecutive(int[] combination) {
        int count = 0;
        for (int i = 0; i < combination.length - 1; i++) {
            if (combination[i + 1] == combination[i] + 1) {
                count++;
            }
        }
        return count;
    }

    private static int countSameLastDigit(int[] combination) {
        int[] lastDigitCount = new int[10];
        for (int number : combination) {
            lastDigitCount[number % 10]++;
        }
        int maxCount = 0;
        for (int count : lastDigitCount) {
            if (count > maxCount) {
                maxCount = count;
            }
        }
        return maxCount;
    }

    private static int countSameDecade(int[] combination) {
        int[] decadeCount = new int[5];
        for (int number : combination) {
            decadeCount[number / 10]++;
        }
        int maxCount = 0;
        for (int count : decadeCount) {
            if (count > maxCount) {
                maxCount = count;
            }
        }
        return maxCount;
    }
}
