package io.cdap.wrangler.api.parser;

public class ByteSize extends Token {
    private final long bytes;

    public ByteSize(String value) {
        super(value);
        String val = value.trim().toUpperCase();
        double number;

        if (val.endsWith("KB")) {
            number = Double.parseDouble(val.replace("KB", ""));
            bytes = (long)(number * 1024);
        } else if (val.endsWith("MB")) {
            number = Double.parseDouble(val.replace("MB", ""));
            bytes = (long)(number * 1024 * 1024);
        } else if (val.endsWith("GB")) {
            number = Double.parseDouble(val.replace("GB", ""));
            bytes = (long)(number * 1024 * 1024 * 1024);
        } else if (val.endsWith("B")) {
            number = Double.parseDouble(val.replace("B", ""));
            bytes = (long) number;
        } else {
            throw new IllegalArgumentException("Invalid unit: " + value);
        }
    }

    public long getBytes() {
        return bytes;
    }
}
