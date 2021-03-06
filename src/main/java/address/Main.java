package address;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URI;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Stream;

import static address.Entry.EntryBuilder.personNamed;
import static address.Sex.sexFrom;
import static java.util.stream.Collectors.toList;

public class Main {

    private static final DateTimeFormatter DATE_FORMATTER = DateTimeFormatter.ofPattern("dd/MM/yy");

    public static void main(String[] args) throws IOException, URISyntaxException {
        AddressBook addresses = new AddressBook(loadedFrom(resourceFileStream("/addresses")));

        System.out.println("Number of males are: " + addresses.countMales());
        System.out.println("Oldest person is: " + addresses.oldest());
        System.out.printf("Bill is %d days older than Paul\n", addresses.ageInDaysBetween("Paul Robinson", "Bill McKnight"));

    }

    private static Stream<String> resourceFileStream(String path) {
        return new BufferedReader(new InputStreamReader(
                Main.class.getResourceAsStream(path))).lines();
    }

    private static List<Entry> loadedFrom(Stream<String> stream) throws IOException, URISyntaxException {
        return stream.map(deserialiseEntry()).collect(toList());
    }

    private static Function<String, Entry> deserialiseEntry() {
        return (line) -> {
            String[] tokens = line.split(",");
            return personNamed(tokens[0].trim())
                    .ofSex(sexFrom(tokens[1].trim()))
                    .withDOB(LocalDate.parse(tokens[2].trim(), DATE_FORMATTER))
                    .make();
        };
    }
}
