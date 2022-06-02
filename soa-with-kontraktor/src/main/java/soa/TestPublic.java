package soa;

import org.nustaq.kontraktor.Actor;
import org.nustaq.kontraktor.remoting.tcp.TCPConnectable;

public class TestPublic {

    public static void main(String[] args) throws InterruptedException {
        PublicService pub = (PublicService) new TCPConnectable(PublicService.class, "localhost", 7900).connect().await();
        pub.helloFrom("outside");
        Thread.sleep(10000);
    }
}
