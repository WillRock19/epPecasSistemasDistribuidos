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
			System.out.println("Encerrando aplica��o Cliente...");
			TimeUnit.SECONDS.sleep(3);
		}
		catch(Exception e){
			System.out.print("Uma exce��o n�o tratada ocorreu. A exce��o �: " + e.getMessage());
		}
	}
}
