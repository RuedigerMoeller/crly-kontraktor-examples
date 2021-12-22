package basics.clients;

import basics.ExampleActor;
import org.nustaq.kontraktor.Actors;

/**
 * runs example actor inside VM, then executes SampleClient
 */
public class ViaLocal {

    public static void main(String[] args) {
        ExampleActor exampleActor = Actors.AsActor(ExampleActor.class);
        new SampleClient(exampleActor).run();
    }

}
