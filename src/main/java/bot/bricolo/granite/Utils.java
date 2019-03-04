package bot.bricolo.granite;

class Utils {
    static void setTimeout(Runnable runnable, int delay) {
        new Thread(() -> {
            try {
                Thread.sleep(delay);
                runnable.run();
            } catch (Exception ignored) {
            }
        }).start();
    }
}
