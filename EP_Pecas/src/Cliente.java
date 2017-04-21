
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

public class Cliente 
{
	private String repositorioAtual;
	private Part pecaAtual;
	private static boolean executarPrograma;
	private static GeradorMensagens mensagens;
	
	public Cliente()
	{
		executarPrograma = true;
		mensagens = new GeradorMensagens();	
	}
	
	public static void main(String[] args) 
	{
		
		
		while(executarPrograma)
		{
			
			
		}
		mensagens.EncerrarAplicacao();
	}
}
