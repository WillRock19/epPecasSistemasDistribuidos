import java.util.concurrent.TimeUnit;

public class GeradorMensagens 
{
	public GeradorMensagens()
	{
		
	}
	
	public void RequisitarComandoAoUsuario()
	{
		System.out.print("Informe um comando para iniciar a aplica��o: ");
	} 
	
	public void IniciarAplicacao()
	{
		System.out.println("Bem-vindo � aplica��o de Pe�as!");
	}
	
	public void ExibirListaDeComandos()
	{
		System.out.println("Os comandos aceitos sao:");
		
		//TO DO: imprimir lista com todos os comandos (usar enum), explicando o que cad aum eh
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
	
	public void ComandoContemParametrosDemais()
	{
		System.out.println("O comando inserido cont�m par�metros a mais do que � permitido.");
		System.out.println("Favor, insira um comando com at� UM par�metro.");
		System.out.println("\r");
	}
}
