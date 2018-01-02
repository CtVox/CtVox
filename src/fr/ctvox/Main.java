package fr.ctvox;

public class Main
{
    public static final int     FRAME_CAP = 666666;
    public static Main          main;

    private boolean             running = false;

    public Main()
    {

    }

    public void update() // 60 Fois par secondes = TPS
    {

    }

    public void render() // X Fois par secondes = FPS | FPSMAX = FRAME_CAP
    {

    }

    public void loop()
    {
        long lastTickTime = System.nanoTime();
        long lastRenderTime = System.nanoTime();

        double tickTime = 1000000000.0 / 60.0;
        double renderTime = 1000000000.0 / FRAME_CAP;

        int ticks = 0;
        int frames = 0;

        while(running)
        {
            if(System.nanoTime() - lastTickTime > tickTime)
            {
                update();

                ticks++;
                lastTickTime += tickTime;
            }
            else if(System.nanoTime() - lastRenderTime > renderTime)
            {
                render();

                frames++;
                lastRenderTime += renderTime;
            }
        }

    }

    public void start()
    {
        running = true;
        loop();
    }

    public void stop()
    {
        running = false;
    }

    public void exit()
    {
        System.exit(0);
    }

    public static void main(String[] args)
    {
        main = new Main();
        main.start();
    }
}
