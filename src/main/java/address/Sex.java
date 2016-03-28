package address;

enum Sex {
    MALE,
    FEMALE,
    UNKNOWN;

    public static Sex sexFrom(String value) {
        try {
            return valueOf(value.trim().toUpperCase());
        } catch (IllegalArgumentException e) {
            return UNKNOWN;
        }
    }
}
