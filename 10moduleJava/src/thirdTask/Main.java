package thirdTask;

//Завдання 3
//Напишіть метод, який буде рахувати частоту кожного слова у файлі words.txt.
//
//Вважаємо що:
//
//words.txt містить лише слова в нижньому регістрі, розділені пробілом
//Кожне слово містить лише літери в нижньому регістрі
//Слова розділені одним або декількома пробілами, або переносом рядка
//Приклад:
//
//Для файлу words.txt із вмістом:
//
//the day is sunny the the
//the sunny is is
//
//Метод повинен повернути результат на кшталт:
//
//the 4
//is 3
//sunny 2
//day 1


import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;

public class Main {
    public static void main(String[] args) {
        HashMap<String, Integer> list = new HashMap<>();
        String inputFile = "10moduleJava/src/thirdTask/file.txt";

        try (BufferedReader reader = new BufferedReader(new FileReader(inputFile))) {

            String line;
            while ((line = reader.readLine()) != null) {
                String[] words = line.toLowerCase().split(" ");
                for (String word : words) {
                    list.put(word, list.getOrDefault(word, 0) + 1);
                }
            }
            list.entrySet()
                    .stream()
                    .sorted((entry1, entry2) -> entry2.getValue().compareTo(entry1.getValue()))
                    .forEach(entry -> System.out.println(entry.getKey() + ": " + entry.getValue()));
        } catch (IOException e) {
            System.err.println("Error: " + e.getMessage());
        }
    }
}
