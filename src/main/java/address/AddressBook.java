package address;

import java.time.Duration;
import java.util.Comparator;
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

    public String oldest() {
        if (entries.isEmpty()) throw new OperationOnEmptyAddressBook();
        return entries.stream().sorted(byDOB()).findFirst().get().name;
    }

    public long ageInDaysBetween(String name1, String name2) {
        Entry entry1 = findPersonNamed(name1);
        Entry entry2 = findPersonNamed(name2);
        return Math.abs(Duration.between(entry1.dob.atStartOfDay(), entry2.dob.atStartOfDay()).toDays());
    }

    private Entry findPersonNamed(String name) {
        return entries.stream().filter(e -> name.equals(e.name)).findFirst().get();
    }

    private Comparator<Entry> byDOB() {
        return (o1, o2) -> {
            if (o1.dob.isBefore(o2.dob)) return -1;
            else if (o1.dob.isEqual(o2.dob)) return 0;
            else return 1;
        };
    }

    private Predicate<Entry> forMales() {
        return e -> e.sex == MALE;
    }

    public class OperationOnEmptyAddressBook extends RuntimeException {
        public OperationOnEmptyAddressBook() {
            super("Cannot peform operation on empty address book.");
        }
    }
}
