package basics;
import basics.complexobjects.DTOMixin;
import org.nustaq.kontraktor.*;

public class ExampleActor extends Actor<ExampleActor> implements DTOMixin {

    public IPromise pingMe(String ping) {
        return resolve("hi "+ping);
    }

    public IPromise benchMe(int value) {
        return resolve(value);
    }

    public void benchMeToo(int value) {
    }

    public void timer(int count, Callback<String> cb) {
        for ( int i=0; i<count; i++ ) {
            int finalI = i;
            delayed( i * 1000l, () -> cb.pipe(finalI +" second") );
        }
        delayed(count*1000l, () -> cb.complete()); // close callback stream
    }

}
