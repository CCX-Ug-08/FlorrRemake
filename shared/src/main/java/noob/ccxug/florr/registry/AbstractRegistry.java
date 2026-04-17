package noob.ccxug.florr.registry;

import noob.ccxug.florr.other.Identifier;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.Set;

public abstract class AbstractRegistry<T> {
    protected final Map<Identifier, T> registry = new HashMap<>();
    public T register(Identifier id, T obj)
    {
        if (registry.containsKey(id))
            throw new IllegalArgumentException("Duplicate registry entry:" + id.toString());
        registry.put(id, obj);
        return obj;
    }
    public Optional<T> get(Identifier id)
    {
        return Optional.ofNullable(registry.get(id));
    }
    public boolean containsKey(Identifier id)
    {
        return registry.containsKey(id);
    }
    public Set<Identifier> keySet()
    {
        return registry.keySet();
    }
    //this should never be used
    public void clear()
    {
        registry.clear();
    }
}
