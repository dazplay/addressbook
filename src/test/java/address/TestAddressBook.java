package address;

import address.Entry.EntryBuilder;
import org.junit.Test;

import static address.Entry.EntryBuilder.personNamed;
import static java.util.Arrays.stream;
import static java.util.stream.Collectors.toList;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;
import static org.hamcrest.core.IsEqual.equalTo;

public class TestAddressBook {

    @Test public void countsOnlyMales() {
        final AddressBook addressBook =
                addressBookWithEntries(
                        personNamed("John").isMale(),
                        personNamed("Jane").isFemale(),
                        personNamed("Stuart").isMale());
        assertThat(addressBook.countMales(), is(equalTo(2L)));
    }

    public AddressBook addressBookWithEntries(EntryBuilder... entries) {
        return new AddressBook(
                stream(entries).map(EntryBuilder::make).collect(toList()));
    }


}
