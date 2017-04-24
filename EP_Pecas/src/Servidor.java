import java.rmi.Naming;
import java.rmi.server.UnicastRemoteObject;
import java.util.List;
import java.rmi.registry.Registry;
import java.rmi.registry.LocateRegistry;
import java.rmi.RemoteException;
import java.rmi.AlreadyBoundException;
import java.rmi.server.UnicastRemoteObject;

public class Servidor implements PartRepository
{
	public Servidor() throws RemoteException {
	    super();
    }

    private List<Part> partes = null;

    public List<Part> recuperarTodasPecas() throws RemoteException
    {
        // TODO Auto-generated method stub
        return null;
    }

    public Part buscarPecaPorCodigo(long codigoPeca) throws RemoteException
    {
        // TODO Auto-generated method stub
        return null;
    }

    public boolean adicionarAoRepositorio(Part peca) throws RemoteException
    {
        // TODO Auto-generated method stub
        return false;
    }

    public String nomeDoRepositorio() throws RemoteException
    {
        // TODO Auto-generated method stub
        return null;
    }
	
	public static void main(String[] args) throws RemoteException
	{
        String naming = "PartRepository";
	    Registry registry = null;
	    PartRepository stub = null;

        try {
			PartRepository obj = new Servidor();
            stub = (PartRepository) UnicastRemoteObject.exportObject(obj, 0);
			
            // Bind the remote object's stub in the registry
            registry = LocateRegistry.getRegistry();

            registry.bind(naming, stub);

            System.out.println("Server ready: bind [" + naming + "]");

        } catch (AlreadyBoundException e) {

            registry.rebind(naming, stub);

            System.out.println("Server ready: rebind [" + naming + "]");

        } catch (Exception e) {
            System.err.println("Server exception: " + e.toString());
            e.printStackTrace();
        }


	}
}
