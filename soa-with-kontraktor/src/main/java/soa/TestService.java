package soa;

import org.nustaq.kontraktor.IPromise;
import org.nustaq.kontraktor.Promise;
import org.nustaq.kontraktor.remoting.base.ConnectableActor;
import org.nustaq.kontraktor.remoting.base.ServiceDescription;
import org.nustaq.kontraktor.services.ServiceActor;
import org.nustaq.kontraktor.services.ServiceArgs;
import org.nustaq.kontraktor.services.datacluster.dynamic.DynDataServiceRegistry;
import org.nustaq.reallive.api.RealLiveTable;

public class TestService extends ServiceActor<TestService> {

    @Override
    public IPromise init(ConnectableActor registryConnectable, ServiceArgs options, boolean autoRegister) {
        Promise res = new Promise();
        super.init(registryConnectable, options, autoRegister).then( (r,e) -> {
            res.complete(r,e);
            runTest();
        });
        return res;
    }

    private void runTest() {
        RealLiveTable test = dclient.tbl("test");
        test.subscribeOn( rec -> rec.getBool("flag"), change -> {
            System.out.println("received"+change);
        });
        delayed( 5000, () -> {
            System.out.println("updating");
            test.update("42", "flag", true );
        });
    }

    @Override
    protected ServiceDescription createServiceDescription() {
        return new ServiceDescription("TestService");
    }

    @Override
    protected boolean isDynamicDataCluster() {
        return true;
    }

    @Override
    protected String[] getRequiredServiceNames() {
        return new String[0];
    }

    public static void main(String[] args) {
        ServiceActor.RunTCP(
            args, // args passed to the service
            TestService.class, // name of the service actor implementing class
            ServiceArgs.class, // class parsing/holding commandline args (can be altered to add service specific arguments, need subclass of ServiceArgs)
            DynDataServiceRegistry.class // type of ServiceRegistry expected
        );
    }
}
