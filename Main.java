import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class Main {
   public static void main(String[] args) throws FileNotFoundException {
       Scanner scanner = new Scanner(System.in); // Ввод имени файла
       Scanner fileScanner; // Сканнер для файла

       ArrayList<String> input = new ArrayList<>(); // Строки

       Map<String, Integer> frequency = new HashMap<>(); // Карта для определения частоты стречания символов
       Map<String, String> alphabet = new HashMap<>(); // Карта для хранения кода символа

       String fileName; // Имя файла
       String line; // Строка в файле

       System.out.print("Enter coding file: ");
       fileName = scanner.nextLine();
       System.out.println();
       scanner.close();

       System.out.print("Starting reading file...");
       System.out.println();
       fileScanner = new Scanner(new File(fileName));
       while (fileScanner.hasNextLine()) {
           line = fileScanner.nextLine();
           input.add(line);
       }
       fileScanner.close();
       System.out.println("File was succesfully read...");

       System.out.println("Starting counting frequency of symbols...");
       System.out.println(input.size());
       for (String cur : input) {
           for (int j = 0; j < cur.length(); j++) {
               String currentChar = cur.substring(j, j+1);
               if (frequency.containsKey(currentChar)) {
                   frequency.replace(currentChar, frequency.get(currentChar) + 1);
               } else {
                   frequency.put(currentChar, 1);
               }
           }
       }

       System.out.println("Frequency map: ");
       for (Map.Entry<String, Integer> pair : frequency.entrySet()) {
           System.out.println(pair.getKey() + " : " + pair.getValue());
       }
       System.out.println("End of frequency map.");

       while (frequency.size() > 1) {
           String min1 = (String) frequency.keySet().toArray()[0];
           int val1 = frequency.get(min1);
           String min2 = (String) frequency.keySet().toArray()[1];
           int val2 = frequency.get(min2);
           if (frequency.size() > 2) {
               Iterator iter = frequency.entrySet().iterator();
               iter.next();
               iter.next();
               while(iter.hasNext()) {
                   Map.Entry pair = (Map.Entry) iter.next();
                   if (val1 > (int)pair.getValue()) {
                       if (val2 > val1) {
                           min2 = min1;
                           val2 = val1;
                       }
                       min1 = (String) pair.getKey();
                       val1 = (int) pair.getValue();
                   } else if (val2 > (int)pair.getValue()) {
                       min2 = (String) pair.getKey();
                       val2 = (int)pair.getValue();
                   }
               }
           }

           System.out.println(min1 + ":" + min2);
           frequency.remove(min1);
           frequency.remove(min2);
           frequency.put(min1 + min2, val1 + val2);

           for (int i = 0; i < min1.length(); i++) {
               String curChar = min1.substring(i, i+1);
               if (alphabet.containsKey(curChar)) {
                   alphabet.replace(curChar, "0" + alphabet.get(curChar).intern());
               } else {
                   alphabet.put(curChar, "0");
               }
           }

           for (int i = 0; i < min2.length(); i++) {
               String curChar = min2.substring(i, i+1);
               if (alphabet.containsKey(curChar)) {
                   alphabet.replace(curChar, "1" + alphabet.get(curChar).intern());
               } else {
                   alphabet.put(curChar, "1");
               }
           }
       }

       System.out.println();
       System.out.println("Alphabet: ");
       for (Map.Entry<String, String> pair : alphabet.entrySet()) {
           System.out.println(pair.getKey() + " : " + pair.getValue());
       }

       System.out.println("Coded phrase: ");
       for (String curStr : input) {
           for (int j = 0; j < curStr.length(); j++) {
               String curChar = curStr.substring(j, j+1);
               System.out.print(alphabet.get(curChar));
           }
       }
   }
}
