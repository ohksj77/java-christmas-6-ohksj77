package christmas.domain;

public final class PurchaseNum {

    private final Integer value;

    public PurchaseNum(final Integer value) {
        this.value = value;
    }

    public int toValue() {
        return this.value;
    }

    public String toString() {
        return String.valueOf(value);
    }
}
