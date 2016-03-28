package address;

import java.time.LocalDate;

final class Entry {
    final String name;
    final Sex sex;
    final LocalDate dob;

    private Entry(String name, Sex sex, LocalDate dob) {
        this.name = name;
        this.sex = sex;
        this.dob = dob;
    }

    public static class EntryBuilder {

        public static EntryBuilder personNamed(String name) {
            return new EntryBuilder(name);
        }

        private String name;
        private LocalDate dob;
        private Sex sex = Sex.UNKNOWN;

        private EntryBuilder(String name) {
            this.name = name;
        }

        public EntryBuilder isMale() {
            this.sex = Sex.MALE;
            return this;
        }

        public EntryBuilder isFemale() {
            this.sex = Sex.FEMALE;
            return this;
        }

        public EntryBuilder ofSex(Sex sex) {
            this.sex = sex;
            return this;
        }

        public EntryBuilder withDOB(LocalDate dob) {
            this.dob = dob;
            return this;
        }

        public Entry make() {
            return new Entry(name, sex, dob);
        }
    }
}
