import java.rmi.server.UnicastRemoteObject;
import java.util.List;
import java.rmi.registry.Registry;
import java.rmi.registry.LocateRegistry;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

public class Servidor implements PartRepository
{
	public Servidor(){}
	
	public static void main(String[] args) 
	{

		 try 
		 {
			Servidor obj = new Servidor();			
			PartRepository stub = (PartRepository) UnicastRemoteObject.exportObject(obj, 0);
			
            // Bind the remote object's stub in the registry
            Registry registry = LocateRegistry.getRegistry();
            
            registry.bind("PartRepository", stub);

		
            System.out.println("Server ready");
        } 
		catch (Exception e) 
		 {
	            System.err.println("Server exception: " + e.toString());
	            e.printStackTrace();
         }
	}

	public List<Part> recuperarTodasPecas() 
	{
		// TODO Auto-generated method stub
		return null;
	}

	public Part buscarPecaPorCodigo(long codigoPeca) 
	{
		// TODO Auto-generated method stub
		return null;
	}

	public boolean adicionarAoRepositorio(Part peca) 
	{
		// TODO Auto-generated method stub
		return false;
	}

	public String nomeDoRepositorio() 
	{
		// TODO Auto-generated method stub
		return null;
	}
}
