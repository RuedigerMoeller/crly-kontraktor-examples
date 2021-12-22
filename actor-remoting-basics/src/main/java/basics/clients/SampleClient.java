package basics.clients;

import basics.ExampleActor;

import java.util.concurrent.CountDownLatch;

public class SampleClient {

    ExampleActor server;

    public SampleClient(ExampleActor server) {
        this.server = server;
    }

    public void run() {
        System.out.println("running example actor client");
        server.timer(10, (res,e) -> {
            if ( res != null )
                System.out.println(res);
            else {
                if ( e == null ) {
                    System.out.println("Done");
                } else {
                    System.out.println("Erreur:"+e);
                }
            }
        });
    }

    static int CHUNK_SIZE = 300_000;
    static int REPETITIONS = 20;

    public void benchMark() {
        for (int ii = 0; ii < REPETITIONS; ii++) {
            long now = System.currentTimeMillis();
            CountDownLatch latch = new CountDownLatch(CHUNK_SIZE);
            for (int i = 0; i < CHUNK_SIZE; i++) {
                server.benchMe(i).then((r, e) -> latch.countDown());
            }
            try {
                latch.await();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            long dur = System.currentTimeMillis() - now;
            System.out.println("time for "+CHUNK_SIZE+" is "+ dur +" ms; per sec:"+( (double)CHUNK_SIZE/dur * 1000 ));
        }
        System.out.println("done");
    }

    public void benchMarkNoReply() {
        for (int ii = 0; ii < REPETITIONS; ii++) {
            long now = System.currentTimeMillis();
            for (int i = 0; i < CHUNK_SIZE; i++) {
                server.benchMeToo(i);
            }
            server.pingMe("Hello").await();
            long dur = System.currentTimeMillis() - now;
            System.out.println("time for "+CHUNK_SIZE+" is "+ dur +" ms; per sec:"+( (double)CHUNK_SIZE/dur * 1000 ));
        }
        System.out.println("done");
    }

}
