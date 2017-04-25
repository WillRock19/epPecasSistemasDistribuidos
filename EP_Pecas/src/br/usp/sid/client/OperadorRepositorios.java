package br.usp.sid.client;

import br.usp.sid.server.PartRepository;
import br.usp.sid.util.Part;
import br.usp.sid.util.Subcomponente;

import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;
import java.util.UUID;

public class OperadorRepositorios 
{
	private Part pecaAtual;

	private List<Subcomponente> subcomponentesAtuais;

	private PartRepository repositorioAtual;

	private Registry rmiRegistry;

	private GeradorMensagens mensagens;



    public OperadorRepositorios()
	{
		mensagens = new GeradorMensagens();
		subcomponentesAtuais = new LinkedList<Subcomponente>();
	}

    protected void ExecutarOperacoes(String[] comandoEParametro)
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
                    mensagens.ExibirRepositoriosCorrente(Cliente.host, MostrarNomeDoRepositorio());
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
            		AdicionarPecaAtualComoSubcomponenteAtual(parametro);
                    break;

                case Comandos.help:
                    mensagens.ExibirComandos();
                    break;
                    
                default:
                    mensagens.Erro("O comando informado n�o foi aceito pelo sistema.");
                    break;
            }
        }
	}

    protected boolean ConectarAoRmiRegistry(String host, int port)
	{
		try {

			this.rmiRegistry = LocateRegistry.getRegistry(host, port);

		} catch (Exception e) {
            mensagens.Erro("N�o foi poss�vel se conectar ao RMI Registry. O seguinte erro ocorreu: \n" + e.getMessage() + "\r");
            return false;
		}

		return true;
	}

    protected String[] ListarRepositoriosDoRmiRegistry()
	{
		try {
			return this.rmiRegistry.list();
		} catch(Exception e) {
			return new String[0];
		}
	}

	private String MostrarNomeDoRepositorio () {

        try {
            return this.repositorioAtual.getRepositoryName();
        } catch(Exception e) {
            mensagens.OperacaoNaoPodeSerRealizada();
        }
        return null;
    }
	
	private void VincularAoRepositorio(String nomeRepositorio)
	{
		if (nomeRepositorio.equals("")) {
			mensagens.Erro("Voc� precisa informar o nome do reposit�rio desejado.");
		} else {
			try {

                this.repositorioAtual = (PartRepository) this.rmiRegistry.lookup(nomeRepositorio);

				mensagens.RepositorioConectado();
			} catch(Exception e) {
                mensagens.OperacaoNaoPodeSerRealizada();
			}
		}
	}
	
	private void ListarPecasRepositorio()
	{
		try {
            List<Part> response = this.repositorioAtual.getAll();
            
            if (response.isEmpty()) {
                mensagens.MensagemSimples("N�o existem pe�as no reposit�rio atual.");
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
            Part response = this.repositorioAtual.get(codigo);
			
            if (response != null) {
            	pecaAtual = response;
            	mensagens.MensagemSimples("A Pe�a de c�digo " + codigo + " foi encontrada e definida como Pe�a corrente.");
            } else
            	mensagens.MensagemSimples("Nao existe uma pe�a com o c�digo informado.");
            
		} catch(Exception e){
			mensagens.OperacaoNaoPodeSerRealizada();
		}
	}
	
	private void MostrarPecaAtual()
	{
		if (pecaAtual != null)
			mensagens.ExibirPecaNoNivel(pecaAtual, 1, false);
		else
			mensagens.Erro("N�o existe uma pe�a atual definida neste reposit�rio.");
	}
	
	private void LimparListaDeSubcomponentes()
	{
		if(pecaAtual == null)
			mensagens.Erro("N�o � poss�vel limpar a lista de subcomponentes; n�o h� nenhuma pe�a definida como 'Pe�a corrente'.");
		else
		{
			pecaAtual.LimparSubcomponentes();
			mensagens.MensagemSimples("A lista de subcomponentes da Pe�a " + pecaAtual.getNome() + " foi limpa com sucesso!");
		}
	}
	
	private void AdicionarPecaAoRepositorio()
	{
        if (this.repositorioAtual != null){

            try {
                Part novaPeca = ReceberInformacoesECriarNovaPeca();
                novaPeca.setSubComponentes(subcomponentesAtuais);

                this.repositorioAtual.add(novaPeca);
                mensagens.MensagemSimples("A Pe�a adicionada ao repostorio atual com sucesso!");
            }
            catch(Exception e){
                e.printStackTrace();
                mensagens.OperacaoNaoPodeSerRealizada();
            }
        } else {
            mensagens.Erro("Ainda n�o h� um reposit�rio definido.\n" +
                    "Por favor, use o comando '" + Comandos.VincularAoRepositorio + "' para vincular a um reposit�rio.");
        }
	}
	
	private Part ReceberInformacoesECriarNovaPeca()
	{
		Scanner sc = new Scanner(System.in);			
		mensagens.MensagemSimples("Para adicionar uma Pe�a ao reposit�rio atual, informe os dados: ");
		
		mensagens.MensagemSimples("Nome da Pe�a: ");
		String nome = sc.next();
		
		mensagens.MensagemSimples("Descri��o da Pe�a: ");
		String descricao = sc.next();

		String codigo = UUID.randomUUID().toString();

		mensagens.MensagemSimples("O c�digo da nova pe�a �: " + codigo);

        return new Part(nome, codigo, descricao);
	}
	
	private void AdicionarPecaAtualComoSubcomponenteAtual(String quantidade)
	{
	    int qtd;
		try {

		    if (quantidade.equals("")) {
                Scanner sc = new Scanner(System.in);
                mensagens.MensagemSimples("Informe a quantidade de subcomponentes a serem adicionados: ");
                qtd = sc.nextInt();
            } else {
		        qtd = Integer.parseInt(quantidade);
            }

			this.subcomponentesAtuais.add(new Subcomponente(pecaAtual, qtd));
			
			mensagens.MensagemSimples("A 'Pe�a atual' foi adicionada � lista de 'Subcomponentes atuais' com sucesso!");
		}
		catch(Exception e){
			mensagens.OperacaoNaoPodeSerRealizada();
		}
	}
}
