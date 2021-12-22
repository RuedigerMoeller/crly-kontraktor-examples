package basics.clients;

import basics.ExampleActor;
import org.nustaq.kontraktor.Actors;

public class BenchLocal {
    public static void main(String[] args) {
        ExampleActor exampleActor = Actors.AsActor(ExampleActor.class);
        SampleClient.CHUNK_SIZE = 4_000_000; // need more size to get valid numbers
        System.out.println("no reply");
        new SampleClient(exampleActor).benchMarkNoReply();
        new SampleClient(exampleActor).benchMark();
    }
}
