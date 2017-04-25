package br.usp.sid.client;

import br.usp.sid.server.PartRepository;
import br.usp.sid.util.Part;
import br.usp.sid.util.Subcomponente;

import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

public class OperadorRepositorios 
{
	private Part pecaAtual;

	private List<Subcomponente> subcomponentesAtuais;

	private PartRepository repositorioAtual;

	private Registry rmiRegistry;

	private GeradorMensagens mensagens;

	private String serverNameCurrent = null;


	
	public OperadorRepositorios()
	{
		mensagens = new GeradorMensagens();
		subcomponentesAtuais = new LinkedList<Subcomponente>();
	}
	
	public void ExecutarOperacoes(String[] comandoEParametro)
	{
	    if (comandoEParametro.length != 0) {
            String comando = comandoEParametro[0];
            String parametro = (comandoEParametro.length > 1) ? comandoEParametro[1] : "";

            switch(comando)
            {
                case Comandos.VincularAoRepositorio:
                    VincularAoRepositorio(parametro);
                    break;

                case Comandos.listRepositories:
                    mensagens.ExibirRepositoriosExistentes(Cliente.host, ListarRepositoriosDoRmiRegistry());
                    break;

                case Comandos.currentRepository:
                    mensagens.ExibirRepositoriosCorrente(Cliente.host, getServerNameCurrent());
                    break;

                case Comandos.ListarPeca:
                    ListarPecasRepositorio();
                    break;

                case Comandos.RecuperarPecaPorCodigo:
                    RecuperarPecaPorCodigo(parametro);
                    break;

                case Comandos.MostrarPeca:
                    MostrarPecaAtual();
                    break;
                
                case Comandos.LimparLista:
                    LimparListaDeSubcomponentes();
                    break;
                
                case Comandos.AddPeca:
            		AdicionarPecaAoRepositorio();
                    break;
                    
                case Comandos.AddSubPeca:
            		AdicionarPecaAtualComoSubcomponenteAtual();
                    break;

                case Comandos.help:
                    mensagens.ExibirComandos();
                    break;
                    
                default:
                    mensagens.Erro("O comando informado não foi aceito pelo sistema.");
                    break;
            }
        }
	}
	
	public boolean ConectarAoRmiRegistry(String host, int port)
	{
		try {

			this.rmiRegistry = LocateRegistry.getRegistry(host, port);

		} catch (Exception e) {
            mensagens.Erro("Não foi possível se conectar ao RMI Registry. O seguinte erro ocorreu: \n" + e.getMessage() + "\r");
            return false;
		}

		return true;
	}
	
	public String[] ListarRepositoriosDoRmiRegistry()
	{
		try {
			return this.rmiRegistry.list();
		} catch(Exception e) {
			return new String[0];
		}
	}

	public String getServerNameCurrent () {
	    return this.serverNameCurrent;
    }
	
	private void VincularAoRepositorio(String nomeRepositorio)
	{
	    this.serverNameCurrent = nomeRepositorio;
		if (this.serverNameCurrent.equals("")) {
			mensagens.Erro("Você precisa informar o nome do repositório desejado.");
		} else {
			try {

                this.repositorioAtual = (PartRepository) this.rmiRegistry.lookup(this.serverNameCurrent);

                System.out.println(this.repositorioAtual.toString());

				mensagens.MensagemSimples("Repositório vinculado com sucesso!");
			} catch(Exception e) {
                mensagens.OperacaoNaoPodeSerRealizada();
			}
		}
	}
	
	private void ListarPecasRepositorio()
	{
		try {
            List<Part> response = repositorioAtual.recuperarTodasPecas();
            
            if (response.isEmpty()) {
                mensagens.MensagemSimples("Não existem peças no repositório atual.");
            } else {
                mensagens.ExibirPecasRepositorio(response);
            }
		} catch(Exception e){
			mensagens.OperacaoNaoPodeSerRealizada();
		}
	}
	
	private void RecuperarPecaPorCodigo(String codigo)
	{
		try {
            Part response = repositorioAtual.buscarPecaPorCodigo(Integer.parseInt(codigo));
			
            if (response != null) {
            	pecaAtual = response;
            	mensagens.MensagemSimples("A Peça de código" + codigo + " foi encontrada e definida como Peça corrente.");
            } else
            	mensagens.MensagemSimples("Nao existe uma peça com o código informado.");
            
		} catch(Exception e){
			mensagens.OperacaoNaoPodeSerRealizada();
		}
	}
	
	private void MostrarPecaAtual()
	{
		if (pecaAtual != null)
			mensagens.ExibirPecaNoNivel(pecaAtual, 1);
		else
			mensagens.Erro("Não existe uma peça atual definida neste repositório.");
	}
	
	private void LimparListaDeSubcomponentes()
	{
		if(pecaAtual == null)
			mensagens.Erro("Não é possível limpar a lista de subcomponentes; não há nenhuma peça definida como 'Peça corrente'.");
		else
		{
			pecaAtual.LimparSubcomponentes();
			mensagens.MensagemSimples("A lista de subcomponentes da Peça " + pecaAtual.getNome() + " foi limpa com sucesso!");
		}
	}
	
	private void AdicionarPecaAoRepositorio()
	{
		try
		{
			Part novaPeca = ReceberInformacoesECriarNovaPeca();
			novaPeca.setSubcomponentes(subcomponentesAtuais);
			
			repositorioAtual.adicionarAoRepositorio(novaPeca);
			mensagens.MensagemSimples("A Peça adicionada ao repostorio atual com sucesso!");
		}
		catch(Exception e){
			mensagens.OperacaoNaoPodeSerRealizada();
		}
	}
	
	private Part ReceberInformacoesECriarNovaPeca()
	{
		Scanner sc = new Scanner(System.in);			
		mensagens.MensagemSimples("Para adicionar uma Peça ao repositório atual, informe os dados: ");

		mensagens.MensagemSimples("Codigo da Peça: ");
		String codigo = sc.next();
		
		mensagens.MensagemSimples("Nome da Peça: ");
		String nome = sc.next();
		
		mensagens.MensagemSimples("Descrição da Peça: ");
		String descricao = sc.next();
		
		return new Part(nome, codigo, descricao);
	}
	
	private void AdicionarPecaAtualComoSubcomponenteAtual()
	{
		try
		{
			Scanner sc = new Scanner(System.in);
			mensagens.MensagemSimples("Informe a quantidade de subcomponentes a serem adicionados: ");
			int quantidade = sc.nextInt();
			
			this.subcomponentesAtuais.add(new Subcomponente(pecaAtual, quantidade));
			
			mensagens.MensagemSimples("A 'Peça atual' foi adicionada à lista de 'Subcomponentes atuais' com sucesso!");
		}
		catch(Exception e){
			mensagens.OperacaoNaoPodeSerRealizada();
		}
	}
}
