package address;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.function.Function;

import static address.Entry.EntryBuilder.personNamed;
import static java.util.stream.Collectors.toList;

public class Main {

    public static void main(String[] args) throws IOException, URISyntaxException {
        AddressBook addresses = new AddressBook(loadedFrom(uriToResource("/addresses")));

        System.out.println("Number of males are: " + addresses.countMales());

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
            return personNamed(tokens[0].trim()).ofSex(Sex.sexFrom(tokens[1].trim())).make();
        };
    }
}
