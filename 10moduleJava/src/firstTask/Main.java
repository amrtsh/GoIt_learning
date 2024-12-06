package firstTask;
//Дано текстовий файл file.txt.txt, в якому є список номерів телефонів (один рядок - один телефон).
//
//Необхідно написати метод, який буде читати файл, і виводити в консоль всі валідні номери телефонів.
//
//Телефон вважається валідним, якщо він відповідає одному з двох форматів (x - це одна цифра):
//
//(xxx) xxx-xxxx
//xxx-xxx-xxxx

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class Main {
    public static void main(String[] args) {
        String firstRegex = "^\\(\\d{3}\\)\\s\\d{3}-\\d{4}$|^\\d{3}-\\d{3}-\\d{4}$";

        try (BufferedReader reader = new BufferedReader(new FileReader("10moduleJava/src/firstTask/file.txt"))) {
            String line;
            while ((line = reader.readLine()) != null) {
                if (line.matches(firstRegex)) {
                    System.out.println(line);
                } else System.out.println("There are no matches in the file!");
            }
        } catch (IOException e) {
            System.out.println("Error reading file: " + e.getMessage());
        }
    }
}
