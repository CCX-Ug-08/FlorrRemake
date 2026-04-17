package noob.ccxug.florr.client.render;

import noob.ccxug.florr.other.Identifier;

public interface Renderable {
    void render(Object context, double deltaTime);
    Identifier getId();
}
