package basics.clients;

import basics.ExampleActor;
import org.nustaq.kontraktor.remoting.tcp.TCPConnectable;

public class BenchNoReplyTCP {
    public static void main(String[] args) {
        TCPConnectable con = new TCPConnectable().actorClass(ExampleActor.class).host("127.0.0.1").port(7777);
        ExampleActor server = (ExampleActor) con.connect(
            (acc, err) -> {
                // server disconnected (old callback for backward compatibility)
                System.out.println("server disconnected");
            }
        ).await();

        new SampleClient(server).benchMarkNoReply();
    }
}
