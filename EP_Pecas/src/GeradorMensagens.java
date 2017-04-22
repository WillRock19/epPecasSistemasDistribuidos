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
		MensagemSimples("Bem-vindo à aplicação de Peças!");
	}
	
	public void RepositorioConectado()
	{
		MensagemSimples("Repositório conectado com sucesso!");
	}
	
	public void ExibirComandos()
	{
		MensagemSimples("Os seguintes comandos sao validos: \r");
		
		//TO DO: imprimir lista com todos os comandos (usar enum), explicando o que cad aum eh
	}
	
	public void ExibirRepositoriosExistentes(String host, String[] repositorios)
	{
		MensagemSimples("O host '" + host + "' possui os seguintes repositórios: \r");
		
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
			MensagemSimples("Encerrando aplicação Cliente...");
			TimeUnit.SECONDS.sleep(3);
		}
		catch(Exception e){
			MensagemDeErro("Uma exceção não tratada ocorreu. A exceção é: " + e.getMessage());
		}
	}
	
	public void ComandoContemParametrosDemais()
	{
		MensagemSimples("O comando inserido contém parâmetros a mais do que é permitido.");
		MensagemSimples("Favor, insira um comando com até UM parâmetro.");
		MensagemSimples("\r");
	}
}
