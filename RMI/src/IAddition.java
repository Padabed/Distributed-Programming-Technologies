import java.rmi.*;

public interface IAddition
        extends Remote {

   AdditionResponse add(AdditionRequest request) throws RemoteException;
}