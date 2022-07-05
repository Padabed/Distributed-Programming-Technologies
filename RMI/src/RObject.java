import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

public class RObject extends UnicastRemoteObject
        implements IEcho, IAddition {
    protected RObject() throws RemoteException {
    }

    @Override
    public AdditionResponse add(AdditionRequest request) throws RemoteException {
        return new AdditionResponse(request.parm2,request.parm1);
    }

    @Override
    public EchoResponse echo(EchoRequest request) throws RemoteException {
        return new EchoResponse(request.messageToEcho);
    }
}
