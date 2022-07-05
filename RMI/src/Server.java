import java.rmi.*;
import java.rmi.registry.*;
import java.rmi.server.*;

public class Server {

    public Server() throws RemoteException {
    }

    public static void main(String[] args){
        try {
            LocateRegistry.createRegistry(1099);
            RObject rObject = new RObject();
            System.out.println("server has been started");
            Naming.bind("echo", rObject);
            Naming.bind("add",rObject);
        }
        catch(Exception e){
            System.err.println(e);
        }
    }


}