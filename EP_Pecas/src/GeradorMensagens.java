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
		MensagemSimples("	bind  [RepositoryName] : Conecta com o outro servidor e conecta com o repos�torio especificado\r");
		MensagemSimples("	listp                  : Lista todas as pe�as do reposit�rio atual\r");
        MensagemSimples("	getp                   : Busca uma pe�a por c�digo no reposit�rio corrente e a seta como nova pe�a corrente\r");
        MensagemSimples("	showp                  : Mostra os atributos da pe�a corrente\r");
        MensagemSimples("	clearlist              : Esvazia a lista de subpe�as da pe�a corrente\r");
        MensagemSimples("	addsubpart    [Number] : Adiciona [Number] unidades da pe�a corrente � lista de subpe�as\r");
        MensagemSimples("	addp                   : \r");
        MensagemSimples("	quit                   : Encerra a aplica��o do cliente\r");
	}
	
	public void ExibirRepositoriosExistentes(String host, String[] repositorios)
	{
		if (host == null) {
			MensagemSimples("Os seguintes reposit�rios est�o dispon�veis: \r");
		} else {
			MensagemSimples("O host '" + host + "' possui os seguintes reposit�rios: \r");
		}

		if (repositorios.length == 0) {
			MensagemSimples("---- N�o existe reposit�rios para o host solicitado ----");
		}

		for(String repositorio : repositorios)
		{
			MensagemSimples("** " + repositorio);
		}
		MensagemSimples("----------------------------------------------------------------------");
	}
	
	public void ExibirPecasRepositorio(List<Part> pecas)
	{
		if (pecas == null) {
			MensagemSimples("---- N�o existe pe�as para o reposit�rio solicitado ---- \r");
		} else {
			MensagemSimples("O repositorio possui as seguintes Pecas: \r");
		}
		
		for(Part peca : pecas)
		{
			ExibirPecaNoNivel(peca, 1);
		}
		MensagemSimples("----------------------------------------------------------------------");
	}
	
	public void ExibirPecaNoNivel(Part peca, int nivel)
	{
		String marker = String.join("", Collections.nCopies(nivel, "*"));
		MensagemSimples(marker  + "Codigo: " + peca.getCodigo());
		MensagemSimples(marker  + "Nome: " + peca.getNome());
		MensagemSimples(marker  + "Descricao: " + peca.getDescricao());
		
		if(peca.PecaEhAgregada())
		{
			for(Subcomponente subcomponente : peca.getSubcomponentes())
			{
				MensagemSimples(marker  + "Quantidade Subcomponentes: " + subcomponente.quantidadeSubpecas);
				MensagemSimples(marker  + "Dados do Subcomponente: ");
				ExibirPecaNoNivel(subcomponente.subPeca, nivel + 1);
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
