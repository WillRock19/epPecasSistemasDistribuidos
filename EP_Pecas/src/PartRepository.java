import java.util.List;
import java.rmi.Remote;
import java.rmi.RemoteException;

public interface PartRepository extends Remote
{
	List<Part> partes = null;

	List<Part> recuperarTodasPecas() throws RemoteException;
	
	Part buscarPecaPorCodigo(long codigoPeca) throws RemoteException;
	
	boolean adicionarAoRepositorio(Part peca) throws RemoteException;
	
	String nomeDoRepositorio() throws RemoteException;
}
