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
			MensagemSimples("Encerrando aplicação Cliente...");
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
