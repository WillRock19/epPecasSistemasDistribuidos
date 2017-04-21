import java.util.concurrent.TimeUnit;

public class GeradorMensagens 
{
	public GeradorMensagens()
	{
		
	}
	
	public void RequisitarComandoAoUsuario()
	{
		System.out.print("Informe um comando para iniciar a aplicação: ");
	} 
	
	public void IniciarAplicacao()
	{
		System.out.println("Bem-vindo à aplicação de Peças!");
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
			System.out.println("Encerrando aplicação Cliente...");
			TimeUnit.SECONDS.sleep(3);
		}
		catch(Exception e){
			System.out.print("Uma exceção não tratada ocorreu. A exceção é: " + e.getMessage());
		}
	}
	
	public void ComandoContemParametrosDemais()
	{
		System.out.println("O comando inserido contém parâmetros a mais do que é permitido.");
		System.out.println("Favor, insira um comando com até UM parâmetro.");
		System.out.println("\r");
	}
}
