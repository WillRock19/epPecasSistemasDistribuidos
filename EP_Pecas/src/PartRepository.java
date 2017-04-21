import java.util.List;

public interface PartRepository 
{
	public List<Part> recuperarTodasPecas();
	
	public Part buscarPecaPorCodigo(long codigoPeca);
	
	public boolean adicionarAoRepositorio(Part peca);
	
	public String nomeDoRepositorio();	
}
