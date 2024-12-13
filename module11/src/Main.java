import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.LongStream;

public class Main {

    public static String users(List<String> names) {
        return IntStream.range(0, names.size())
                .filter(name -> name % 2 != 0)
                .mapToObj(index -> (index) + ". " + names.get(index))
                .collect(Collectors.joining(", "));
    }

    public static String rows(List<String> names) {
        return names.stream()
                .map(String::toUpperCase)
                .sorted((a, b) -> b.compareTo(a))
                .toList()
                .toString();
    }

    public static String sortDigits(List<String> digits) {
        return digits.stream()
                .flatMap(digit -> Arrays.stream(digit.split(",\\s*")))
                .mapToInt(Integer::parseInt)
                .sorted()
                .mapToObj(Integer::toString)
                .collect(Collectors.joining(", "));
    }

    public static LongStream randomDigits(long a, long c, long m) {
        return LongStream.iterate(1, n -> ((a * n + c) % m))
                .limit(100);
    }


    public static void main(String[] args) {
        List<String> names = Arrays.asList("Ivan", "Peter", "Nadiya", "Alina", "Olya");
        System.out.println(users(names));
        System.out.println(rows(names));
        List<String> digits = Arrays.asList("1, 2, 0", "4, 5");
        System.out.println(sortDigits(digits));
        long a = 25214903917L;
        long c = 11;
        long m = (long) Math.pow(2, 48);
        randomDigits(a, c, m).forEach(System.out::println);
        ;

    }
}




