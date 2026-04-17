package noob.ccxug.florr.other;

import java.util.Objects;

public final class Identifier {
    private final String type;
    private final String name;
    public Identifier(String type, String name)
    {
        if (type == null || name == null)
            throw new IllegalArgumentException("Type and name should not be null!");
        if (type.contains(":") || name.contains(":"))
            throw new IllegalArgumentException("Type and name should not contain ':' !");
        this.type = type;
        this.name = name;
    }
    public static Identifier parsed(String combined)
    {
        int colonIdx = combined.indexOf(':');
        if (colonIdx == -1)
            return new Identifier("default", combined);
        String type = combined.substring(0, colonIdx);
        String name = combined.substring(colonIdx + 1);
        if (type.isEmpty() || name.isEmpty()) {
            throw new IllegalArgumentException("Identifier should not have empty type or name: " + combined);
        }
        return new Identifier(type, name);
    }
    public static Identifier of(String type, String name)
    {
        return new Identifier(type, name);
    }
    public String getType()
    {
        return this.type;
    }
    public String getName()
    {
        return this.name;
    }
    @Override
    public String toString()
    {
        return type + ":" + name;
    }
    @Override
    public boolean equals(Object o)
    {
        if (o == null || getClass() != o.getClass())
            return false;
        if (this == o)
            return true;
        Identifier that = (Identifier) o;
        return type.equals(that.type) && name.equals(that.name);
    }
    @Override
    public int hashCode()
    {
        return Objects.hash(type, name);
    }
}
