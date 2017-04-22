import java.util.Collections;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class GeradorMensagens 
{
	public GeradorMensagens() { }
	
	public void Erro(String mensagem)
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
	
	public void ExibirPecasRepositorio(List<Part> pecas)
	{
		MensagemSimples("O repositorio possui as seguintes Pecas: \r");
		
		for(Part peca : pecas)
		{
			ExibirPecaNoNivel(peca, 1);
		}
		MensagemSimples("----------------------------------------------------------------------");
	}
	
	public void ExibirPecaNoNivel(Part peca, int nivel)
	{
		String marker = String.join("", Collections.nCopies(nivel, "*"));
		MensagemSimples(marker  + "Codigo: " + peca.codigo);
		MensagemSimples(marker  + "Nome: " + peca.nome);
		MensagemSimples(marker  + "Descricao: " + peca.descricao);
		
		if(!peca.subcomponentes.isEmpty())
		{
			for(Subcomponente subcomponente : peca.subcomponentes)
			{
				MensagemSimples(marker  + "Quantidade Subcomponentes: " + subcomponente.quantidadeSubpartes);
				MensagemSimples(marker  + "Dados do Subcomponente: ");
				ExibirPecaNoNivel(subcomponente.subParte, nivel + 1);
			}
		}
	}
	
	public void EncerrarAplicacao()
	{
		try
		{
			MensagemSimples("Encerrando aplica��o Cliente...");
			TimeUnit.SECONDS.sleep(3);
		}
		catch(Exception e){
			Erro("Uma exce��o n�o tratada ocorreu. A exce��o �: " + e.getMessage());
		}
	}
	
	public void ComandoContemParametrosDemais()
	{
		MensagemSimples("O comando inserido cont�m par�metros a mais do que � permitido.");
		MensagemSimples("Favor, insira um comando com at� UM par�metro.");
		MensagemSimples("\r");
	}
	
	public void OperacaoNaoPodeSerRealizada()
	{
		Erro("A operacao requisitada nao pode ser executada.");
	}
}
