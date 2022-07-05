import java.rmi.*;

public interface IEcho
        extends Remote {
    EchoResponse echo(EchoRequest request) throws  RemoteException;
}