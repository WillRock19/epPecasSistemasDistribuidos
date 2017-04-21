import java.util.concurrent.TimeUnit;

public class GeradorMensagens 
{
	public GeradorMensagens()
	{
		
	}
	
	
	
	public void EncerrarAplicacao()
	{
		try
		{
			System.out.println("Encerrando aplicação Cliente...");
			TimeUnit.SECONDS.sleep(3);
		}
		catch(Exception e){
			System.out.print("Uma exceção não tratada ocorreu. A exceção é: " + e.getMessage());
		}
	}
}
