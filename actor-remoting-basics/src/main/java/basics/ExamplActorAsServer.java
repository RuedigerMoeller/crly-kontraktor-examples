package basics;

import org.nustaq.kontraktor.Actors;
import org.nustaq.kontraktor.remoting.encoding.SerializerType;
import org.nustaq.kontraktor.remoting.http.undertow.HttpPublisher;
import org.nustaq.kontraktor.remoting.http.undertow.WebSocketPublisher;
import org.nustaq.kontraktor.remoting.tcp.TCPPublisher;

/**
 * publish an instance of ExampleActor via Http, WebSocket and TCP
 */
public class ExamplActorAsServer {

    public static void main(String[] args) {
        ExampleActor serv = Actors.AsActor(ExampleActor.class);

        new HttpPublisher(serv,"0.0.0.0","/api",7779)
            .serType(SerializerType.JsonNoRef)
            .publish();

        // websocket for javascript
        new WebSocketPublisher()
            .facade(serv)
            .serType(SerializerType.JsonNoRef)
            .urlPath("/ws")
            .port(7779)
            .publish();

        // websocket for java (different encoding for performance)
        new WebSocketPublisher()
            .facade(serv)
            .serType(SerializerType.FSTSer) // <= here
            .urlPath("/ws")
            .port(7778)
            .publish();

        new TCPPublisher(serv,7777).publish(); // defaults to Fast Serialization encoding

    }

}
