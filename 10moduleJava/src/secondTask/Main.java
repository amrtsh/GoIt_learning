package secondTask;
//Є текстовий файл file.txt. Необхідно прочитати файл,
// перетворити його в список об'єктів типу User, і записати їх у новий файл user.json.
//
//Формат файлу:
//
//перший рядок - заголовок
//кожний наступний рядок - окремий об'єкт, кожна колонка розділена пробілом

import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Main {
    public static void main(String[] args) {
        String inputFile = "10moduleJava/src/secondTask/file.txt";
        String outputFile = "10moduleJava/src/secondTask/out.json";

        try (BufferedReader reader = new BufferedReader(new FileReader(inputFile));
             FileWriter writer = new FileWriter(outputFile)) {

            String headerLine = reader.readLine();
            if (headerLine == null) {
                System.out.println("The file is empty.");
                return;
            }
            String[] headers = headerLine.split(" ");

            // Read the rest of the lines and create a list of HashMaps to store user data
            List<Map<String, Object>> users = new ArrayList<>();
            String line;
            while ((line = reader.readLine()) != null) {
                String[] values = line.split(" ");

                // Ensure the values array is the same length as the headers array
                if (values.length != headers.length) {
                    System.out.println("Skipping invalid line: " + line);
                    continue;
                }

                Map<String, Object> user = getStringObjectMap(headers, values);

                users.add(user); // Add the user HashMap to the list
            }

            // Write the list of HashMaps to the output file as JSON
            writer.write("[\n");
            for (int i = 0; i < users.size(); i++) {
                Map<String, Object> user = users.get(i);
                writer.write("    {\n");

                int fieldCount = 0;
                for (Map.Entry<String, Object> entry : user.entrySet()) {
                    writer.write("        \"" + entry.getKey() + "\": ");
                    if (entry.getValue() instanceof String) {
                        writer.write("\"" + entry.getValue() + "\"");
                    } else {
                        writer.write(entry.getValue().toString());
                    }

                    fieldCount++;
                    if (fieldCount < user.size()) {
                        writer.write(",");
                    }
                    writer.write("\n");
                }

                writer.write("    }");
                if (i < users.size() - 1) {
                    writer.write(",");
                }
                writer.write("\n");
            }
            writer.write("]");

            System.out.println("JSON file created successfully: " + outputFile);

        } catch (IOException e) {
            System.err.println("Error: " + e.getMessage());
        }
    }

    private static Map<String, Object> getStringObjectMap(String[] headers, String[] values) {
        Map<String, Object> user = new HashMap<>();

        // Populate the HashMap for each user
        for (int i = 0; i < headers.length; i++) {
            String key = headers[i];
            String value = values[i];
            if (key.equalsIgnoreCase("age")) {
                user.put(key, Integer.parseInt(value)); // Convert "age" to an integer
            } else {
                user.put(key, value); // Keep other fields as strings
            }
        }
        return user;
    }
}
