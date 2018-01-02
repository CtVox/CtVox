package fr.ctvox;

import fr.ctvox.game.Game;
import fr.ctvox.renderer.DisplayManager;
import org.lwjgl.opengl.Display;

public class Main
{
    public static final int     FRAME_CAP = 666666;
    public static Main          main;

    private boolean             running = false;
    private Game                game;

    public Main()
    {
        DisplayManager.create(1200, 600, "CTVox");
        game = new Game();
    }

    public void update() // 60 Fois par secondes = TPS
    {
        game.update();
    }

    public void render() // X Fois par secondes = FPS | FPSMAX = FRAME_CAP
    {
        DisplayManager.update();
        DisplayManager.clearBuffers();

        game.render();
    }

    public void loop()
    {
        long lastTickTime = System.nanoTime(); // Temps en nanosecondes 1000000000.0 = 1 sec
        long lastRenderTime = System.nanoTime();
        long timer = System.currentTimeMillis(); // Temps en millisecondes 1000.0 = 1 sec

        double tickTime = 1000000000.0 / 60.0;
        double renderTime = 1000000000.0 / FRAME_CAP;

        int ticks = 0;
        int frames = 0;

        while(running)
        {
            if(DisplayManager.isClosed())
                stop();

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
            else {
                try {
                    Thread.sleep(1);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }

            if(System.currentTimeMillis() - timer > 1000)
            {
                Display.setTitle("CTVox FPS : " + frames + " | " + " TPS : " + ticks);

                timer += 1000/*Une seconde*/;

                //Reset du compteur
                ticks = 0;
                frames = 0;
            }
        }

        exit();
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
        DisplayManager.dispose();
        System.exit(0);
    }

    public static void main(String... args)
    {
        main = new Main();
        main.start();
    }

    public static Main getMain() { return main; }
    public Game getGame() { return game; }
}
