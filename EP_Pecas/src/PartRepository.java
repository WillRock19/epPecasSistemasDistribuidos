import java.util.List;
import java.rmi.Remote;
import java.rmi.RemoteException;

public interface PartRepository extends Remote 
{
	public List<Part> recuperarTodasPecas() throws RemoteException;;
	
	public Part buscarPecaPorCodigo(long codigoPeca) throws RemoteException;;
	
	public boolean adicionarAoRepositorio(Part peca) throws RemoteException;;
	
	public String nomeDoRepositorio() throws RemoteException;;	
}
