package noob.ccxug.florr.client.render;

import noob.ccxug.florr.registry.AbstractRegistry;

public class RenderableRegistry extends AbstractRegistry<Renderable> {
    private static final RenderableRegistry INSTANCE = new RenderableRegistry();
    private RenderableRegistry(){}
    public static RenderableRegistry getInstance()
    {
        return INSTANCE;
    }
}
