import java.util.*;

public class DeviceRegistry {
    private final java.util.List<Object> devices = new ArrayList<>();

    public void add(Object device) { devices.add(device); }

    public <T> T getByCapabilities(Class<T> capability) {
    for (Object d : devices) {
        if (capability.isInstance(d)) {
            return capability.cast(d);
        }
    }
    throw new IllegalStateException("Missing device for " + capability.getSimpleName());
}
}