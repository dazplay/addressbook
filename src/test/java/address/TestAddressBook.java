package address;

import address.Entry.EntryBuilder;
import org.junit.Test;

import java.time.LocalDate;
import java.util.ArrayList;

import static address.Entry.EntryBuilder.personNamed;
import static java.util.Arrays.stream;
import static java.util.stream.Collectors.toList;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;
import static org.hamcrest.core.IsEqual.equalTo;

public class TestAddressBook {
    private static final LocalDate SOME_DATE = LocalDate.now();
    private static final LocalDate SOME_EARLIER_DATE = LocalDate.now().minusMonths(42);

    @Test public void canQueryAgeDifferenceInDaysBetweenTwoEntries() {
        final AddressBook addressBook =
                addressBookWithEntries(
                        personNamed("John").withDOB(SOME_DATE),
                        personNamed("Jane").withDOB(SOME_DATE.minusDays(3)));
        assertThat(addressBook.ageInDaysBetween("John", "Jane"), is(equalTo(3L)));
    }

    @Test public void oldestPersonInAddressBook() {
        final AddressBook addressBook =
                addressBookWithEntries(
                        personNamed("John").withDOB(SOME_DATE),
                        personNamed("Jane").withDOB(SOME_EARLIER_DATE));
        assertThat(addressBook.oldest(), is("Jane"));
    }


    @Test(expected = AddressBook.OperationOnEmptyAddressBook.class)
    public void findingOldestOnEmptyAddressBookYieldsException() {
        emptyAddressBook().oldest();
    }

    @Test public void countsOnlyMales() {
        final AddressBook addressBook =
                addressBookWithEntries(
                        personNamed("John").isMale(),
                        personNamed("Jane").isFemale(),
                        personNamed("Stuart").isMale());
        assertThat(addressBook.countMales(), is(equalTo(2L)));
    }

    private AddressBook emptyAddressBook() {
        return new AddressBook(new ArrayList<>());
    }

    public AddressBook addressBookWithEntries(EntryBuilder... entries) {
        return new AddressBook(
                stream(entries).map(EntryBuilder::make).collect(toList()));
    }


}
