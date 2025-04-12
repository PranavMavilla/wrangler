package io.cdap.wrangler.api.parser;

public class TimeDuration extends Token {
    private final long millis;

    public TimeDuration(String value) {
        super(value);
        String val = value.trim().toLowerCase();
        double number;

        if (val.endsWith("ms")) {
            number = Double.parseDouble(val.replace("ms", ""));
            millis = (long) number;
        } else if (val.endsWith("s")) {
            number = Double.parseDouble(val.replace("s", ""));
            millis = (long)(number * 1000);
        } else if (val.endsWith("m")) {
            number = Double.parseDouble(val.replace("m", ""));
            millis = (long)(number * 60000);
        } else if (val.endsWith("h")) {
            number = Double.parseDouble(val.replace("h", ""));
            millis = (long)(number * 3600000);
        } else {
            throw new IllegalArgumentException("Invalid duration unit: " + value);
        }
    }

    public long getMillis() {
        return millis;
    }
}
