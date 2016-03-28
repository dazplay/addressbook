package address;

class Entry {

    private String name;
    private Sex sex;

    private Entry(String name, Sex sex) {
        this.name = name;
        this.sex = sex;
    }

    public Sex sex() {
        return sex;
    }

    public static class EntryBuilder {

        public static EntryBuilder personNamed(String name) {
            return new EntryBuilder(name);
        }

        private String name;
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

        public Entry make() {
            return new Entry(name, sex);
        }
    }
}
