import java.util.*;

public class DeviceRegistry {
    private final List<Object> devices = new ArrayList<>();

    public void add(Object d) { devices.add(d); }

    public <T> T getDevice(Class<T> clazz){
        for (Object d : devices){
            if(clazz.isInstance(d)){
                return clazz.cast(d);
            }
        }
        throw new IllegalStateException("Missing: " + clazz.getSimpleName());
    }
}
