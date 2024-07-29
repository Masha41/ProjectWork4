package data;

import io.opentelemetry.api.common.Attributes;

public enum EventData implements io.opentelemetry.sdk.trace.data.EventData {

    OPEN("Открытый вебинар");

    private String name;

    EventData(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    @Override
    public Attributes getAttributes() {
        return null;
    }

    @Override
    public long getEpochNanos() {
        return 0;
    }

    @Override
    public int getTotalAttributeCount() {
        return 0;
    }
}
