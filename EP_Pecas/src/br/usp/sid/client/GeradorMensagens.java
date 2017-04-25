package br.usp.sid.client;

import br.usp.sid.util.Part;
import br.usp.sid.util.Subcomponente;

import java.util.List;
import java.util.Collections;
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
		MensagemSimples("Bem-vindo à aplicação de Peças!");
	}
	
	public void RepositorioConectado()
	{
		MensagemSimples("Repositório conectado com sucesso!");
	}
	
	public void ExibirComandos()
	{
		MensagemSimples("Os seguintes comandos sao validos: \r");
		MensagemSimples("	bind  [RepositoryName] : Conecta com o outro servidor e seta o novo repositório corrente\r");
        MensagemSimples("	list                   : Lista todos os repositórios disponíveis\r");
        MensagemSimples("	current                : Mostra o nome do repositório corrente\r");

		MensagemSimples("	listp                  : Lista todas as peças do repositório atual\r");
        MensagemSimples("	getp                   : Busca uma peça por código no repositório corrente e a seta como nova peça corrente\r");
        MensagemSimples("	showp                  : Mostra os atributos da peça corrente\r");
        MensagemSimples("	clearlist              : Esvazia a lista de subpeças da peça corrente\r");
        MensagemSimples("	addsubpart    [Number] : Adiciona [Number] unidades da peça corrente à lista de subpeças\r");
        MensagemSimples("	addp                   : \r");
        MensagemSimples("	quit                   : Encerra a aplicação do cliente\r");
        MensagemSimples("	help                   : Mostra o menu de comandos disponíveis\r");
	}
	
	public void ExibirRepositoriosExistentes(String host, String[] repositorios)
	{
		if (host == null) {
			MensagemSimples("Os seguintes repositórios estão disponíveis: \r");
		} else {
			MensagemSimples("O host '" + host + "' possui os seguintes repositórios: \r");
		}

		if (repositorios.length == 0) {
			MensagemSimples("---- Não existe repositórios para o host solicitado ----");
		}

		for(String repositorio : repositorios)
		{
			MensagemSimples("** " + repositorio);
		}
		MensagemSimples("----------------------------------------------------------------------");
	}

	public void ExibirRepositoriosCorrente (String host, String serverName) {

        if (host == null && serverName != null) {
            MensagemSimples("Você está conectado ao repositório '" + serverName + "'\r");
        } else if (serverName != null) {
            MensagemSimples("Você está conectado ao repositório '" + serverName + "' no RMI Service de host '" + host + "'\r");
        } else {
            MensagemSimples("Você não está conectado a nenhum repositório\r");
        }
    }
	
	public void ExibirPecasRepositorio(List<Part> pecas)
	{
		if (pecas == null) {
			MensagemSimples("---- Não existe peças para o repositório solicitado ---- \r");
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
				MensagemSimples(marker  + "Dados do br.usp.sid.util.Subcomponente: ");
				ExibirPecaNoNivel(subcomponente.subPeca, nivel + 1);
			}
		}
	}
	
	public void EncerrarAplicacao()
	{
		try
		{
			MensagemSimples("Encerrando aplicação br.usp.sid.client.Cliente...");
			TimeUnit.SECONDS.sleep(3);
		}
		catch(Exception e){
			Erro("Uma exceção não tratada ocorreu. A exceção é: " + e.getMessage());
		}
	}
	
	public void ComandoContemParametrosDemais()
	{
		MensagemSimples("O comando inserido contém parâmetros a mais do que é permitido.");
		MensagemSimples("Favor, insira um comando com até UM parâmetro.");
		MensagemSimples("\r");
	}
	
	public void OperacaoNaoPodeSerRealizada()
	{
		Erro("A operacao requisitada nao pode ser executada.");
	}
}
