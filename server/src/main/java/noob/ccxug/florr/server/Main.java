package noob.ccxug.florr.server;

import java.util.concurrent.atomic.AtomicBoolean;

public class Main {
    private static final int TARGET_TPS = 30;
    private static final long TICK_DURATION_MS = 1000 / TARGET_TPS;
    private final AtomicBoolean running = new AtomicBoolean(false);
    private Thread serverThread;
    public void start()
    {
        if (running.get())
            return;
        running.set(true);
        serverThread = new Thread(this::run, "FlorrServer-Main");
        serverThread.start();
        System.out.println("[Server] Started with target TPS: " + TARGET_TPS);
    }
    private void run()
    {
        long lastTickTime = System.currentTimeMillis();
        long accumulator = 0;
        while (running.get())
        {
            long now = System.currentTimeMillis();
            long delta = now - lastTickTime;
            lastTickTime = now;
            accumulator += delta;
            while (accumulator >= TICK_DURATION_MS && running.get())
            {
                tick();
                accumulator -= TICK_DURATION_MS;
            }
            long sleepTime = TICK_DURATION_MS - (System.currentTimeMillis() - lastTickTime);
            if (sleepTime > 0) {
                try {
                    Thread.sleep(sleepTime);
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                    break;
                }
            }
        }
        System.out.println("[Server] Main loop exited.");
    }
    private void tick()
    {

    }
    public void stop()
    {
        running.set(false);
        if (serverThread != null)
            serverThread.interrupt();
    }
    static void main(String[] args)
    {
        Main server = new Main();
        server.start();
        try { Thread.sleep(10000); } catch (InterruptedException ignored) {}
        server.stop();
        System.out.println("Server stopped");
    }
}
