package nu.ist.iosf.commons.models.response;

public class Crup<T> {

    private T id;

    public Crup() {
    }

    public Crup(T id) {
        this.id = id;
    }

    public T getId() {
        return id;
    }

    public void setId(T id) {
        this.id = id;
    }
}
