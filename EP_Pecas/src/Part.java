import java.util.List;

public class Part 
{
	public String codigo;
	
	public String nome;
	
	public String descricao;
	
	public List<Subcomponente> subcomponentes;
	
	public Part()
	{
		
	}
	
	public boolean PecaEhPrimitiva()
	{
		 return SubComponentesEstaVazio();
	}
	
	public boolean PecaEhAgregada()
	{
		return !SubComponentesEstaVazio();
	}
	
	private boolean SubComponentesEstaVazio()
	{
		return subcomponentes.isEmpty();
	}
}
