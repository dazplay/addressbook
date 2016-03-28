package address;

import java.util.List;
import java.util.function.Predicate;

import static address.Sex.MALE;

class AddressBook {
    private List<Entry> entries;

    public AddressBook(List<Entry> entries) {
        this.entries = entries;
    }

    public long countMales() {
        return entries.stream().filter(forMales()).count();
    }

    private Predicate<Entry> forMales() {
        return e -> e.sex() == MALE;
    }

}
