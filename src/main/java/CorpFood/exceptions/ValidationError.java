package CorpFood.exceptions;

public class ValidationError {
    private String field;
    private String desctription;

    public ValidationError(String field, String desctription) {
        this.field = field;
        this.desctription = desctription;
    }

    @Override
    public String toString() {
        return String.format("{\"field\":\"%s\"," +
                "\"description\":\"%s\"}", field, desctription);
    }
}
