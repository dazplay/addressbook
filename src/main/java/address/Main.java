package address;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.function.Function;

import static address.Entry.EntryBuilder.personNamed;
import static address.Sex.sexFrom;
import static java.util.stream.Collectors.toList;

public class Main {

    private static final DateTimeFormatter DATE_FORMATTER = DateTimeFormatter.ofPattern("dd/MM/yy");

    public static void main(String[] args) throws IOException, URISyntaxException {
        AddressBook addresses = new AddressBook(loadedFrom(uriToResource("/addresses")));

        System.out.println("Number of males are: " + addresses.countMales());
        System.out.println("Oldest person is: " + addresses.oldest());

    }

    private static URI uriToResource(String path) throws URISyntaxException {
        return Main.class.getResource(path).toURI();
    }

    private static List<Entry> loadedFrom(URI resource) throws IOException, URISyntaxException {
        return Files.lines(Paths.get(resource)).map(deserialiseEntry()).collect(toList());
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
