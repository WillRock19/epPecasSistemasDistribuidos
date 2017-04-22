import java.rmi.Naming;
import java.rmi.server.UnicastRemoteObject;
import java.util.List;
import java.rmi.registry.Registry;
import java.rmi.registry.LocateRegistry;
import java.rmi.RemoteException;
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
	
	public static void main(String[] args) 
	{

        try {
			PartRepository obj = new Servidor();
			PartRepository stub = (PartRepository) UnicastRemoteObject.exportObject(obj, 0);
			
            // Bind the remote object's stub in the registry
            Registry registry = LocateRegistry.getRegistry();
            registry.bind("PartRepository", stub);
		
            System.out.println("Server ready");
        } catch (Exception e) {
	            System.err.println("Server exception: " + e.toString());
	            e.printStackTrace();
        }
	}
}
