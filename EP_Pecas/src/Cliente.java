
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.concurrent.TimeUnit;

public class Cliente 
{
	private String repositorioAtual;
	private Part pecaAtual;
	private static boolean executarPrograma;
	
	public Cliente()
	{
		executarPrograma = true;
	}
	
	public static void main(String[] args) 
	{
		try
		{
			while(executarPrograma)
			{
				
				
			}
			EncerrarAplicacao();	
		}
		catch(Exception e){
			System.out.print("Uma exce��o n�o tratada ocorreu. A exce��o �: " + e.getMessage());
		}
	}
	
	private static void EncerrarAplicacao() throws InterruptedException
	{
		System.out.println("Encerrando aplica��o Cliente...");
		TimeUnit.SECONDS.sleep(3);
	}
}
