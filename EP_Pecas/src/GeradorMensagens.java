import java.util.concurrent.TimeUnit;

public class GeradorMensagens 
{
	public GeradorMensagens() { }
	
	public void MensagemDeErro(String mensagem)
	{
        System.err.println(mensagem);
	}
	
	public void MensagemSimples(String mensagem)
	{
		System.out.println(mensagem);
		System.out.println("\r");
	}
	
	public void RequisitarComandoAoUsuario()
	{
		MensagemSimples("Informe um comando para ser executado: ");
	} 
	
	public void IniciarAplicacao()
	{
		MensagemSimples("Bem-vindo � aplica��o de Pe�as!");
	}
	
	public void RepositorioConectado()
	{
		MensagemSimples("Reposit�rio conectado com sucesso!");
	}
	
	public void ExibirComandos()
	{
		MensagemSimples("Os seguintes comandos sao validos: \r");
		
		//TO DO: imprimir lista com todos os comandos (usar enum), explicando o que cad aum eh
	}
	
	public void ExibirRepositoriosExistentes(String host, String[] repositorios)
	{
		MensagemSimples("O host '" + host + "' possui os seguintes reposit�rios: \r");
		
		for(String repositorio : repositorios)
		{
			MensagemSimples("** " + repositorio);
		}
		MensagemSimples("----------------------------------------------------------------------");
	}
	
	public void ExibirPecasRepositorio()
	{
		
	}
	
	public void EncerrarAplicacao()
	{
		try
		{
			MensagemSimples("Encerrando aplica��o Cliente...");
			TimeUnit.SECONDS.sleep(3);
		}
		catch(Exception e){
			MensagemDeErro("Uma exce��o n�o tratada ocorreu. A exce��o �: " + e.getMessage());
		}
	}
	
	public void ComandoContemParametrosDemais()
	{
		MensagemSimples("O comando inserido cont�m par�metros a mais do que � permitido.");
		MensagemSimples("Favor, insira um comando com at� UM par�metro.");
		MensagemSimples("\r");
	}
}
