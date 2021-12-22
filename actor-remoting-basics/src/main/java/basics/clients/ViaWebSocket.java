package basics.clients;

import basics.ExampleActor;
import org.nustaq.kontraktor.remoting.tcp.TCPConnectable;
import org.nustaq.kontraktor.remoting.websockets.WebSocketConnectable;

public class ViaWebSocket {
    public static void main(String[] args) {
        WebSocketConnectable con = new WebSocketConnectable(ExampleActor.class,"ws://localhost:7778/ws");
        ExampleActor server = (ExampleActor) con.connect(
            (acc, err) -> {
                // server disconnected (old callback for backward compatibility)
                System.out.println("server disconnected");
            }
        ).await();

        new SampleClient(server).run();
    }
}
