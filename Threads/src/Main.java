public class Main {
    public static void main(String[] args) {
        System.out.println(ThreadColor.ANSI_PURPLE + "Hello world!");
        Thread anotherThread = new AnotherThread();
        anotherThread.setName("=== Another Thread ===");
        anotherThread.start();

        new Thread(){
            @Override
            public void run() {
                System.out.println(ThreadColor.ANSI_GREEN + "Hello from the anonymous class thread");
            }
        }.start();

        //Thread myRunnableThread = new Thread(new MyRunnable());
        Thread myRunnableThread = new Thread(new MyRunnable() {
            @Override
            public void run() {
                System.out.println(ThreadColor.ANSI_RED+ "Hello from anonymous class\'s implementation of run");
                try {
                    anotherThread.join();
                    System.out.println(ThreadColor.ANSI_RED+ "Another thread terminated, so I am running again");
                } catch (InterruptedException e){
                    System.out.println(ThreadColor.ANSI_RED + "I couldn't wait after all, I was interrupted");
                }
            }
        });

        myRunnableThread.start();

        anotherThread.interrupt();

        System.out.println(ThreadColor.ANSI_PURPLE + "Hello again from the main thread");

        // Following should throw exception:
        // anotherThread.start();
    }
}