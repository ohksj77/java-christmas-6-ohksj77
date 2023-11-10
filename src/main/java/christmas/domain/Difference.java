package christmas.domain;

public final class Difference {

    private final Integer value;

    public Difference(final Integer value) {
        this.value = value;
    }

    public Integer toValue() {
        return this.value;
    }
}
