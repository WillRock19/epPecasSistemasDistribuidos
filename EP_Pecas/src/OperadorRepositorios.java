import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.Remote;
import java.util.List;
import java.util.LinkedList;
import java.util.Scanner;

public class OperadorRepositorios 
{
	private Part pecaAtual;
	private List<Subcomponente> subcomponentesAtuais;
	private PartRepository repositorioAtual;
	private Registry RmiRegistry;
	private GeradorMensagens mensagens;
	
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
                    
                default:
                    mensagens.Erro("O comando informado n�o foi aceito pelo sistema.");
                    break;
            }
        }
	}
	
	public void ConectarAoRmiRegistry(String host)
	{
		try{
			RmiRegistry = LocateRegistry.getRegistry(host);
		}
		catch(Exception e) {
            mensagens.Erro("N�o foi poss�vel se concetar ao RMI Registry especificado. O seguinte erro ocorreu: " + e.getMessage());
		}
	}
	
	public String[] ListarRepositoriosDoRmiRegistry()
	{
		try {
			return RmiRegistry.list();	
		} catch(Exception e) {
			return new String[0];
		}
	}
	
	private void VincularAoRepositorio(String nomeRepositorio)
	{
		if (nomeRepositorio.equals("")) {
			mensagens.Erro("Voc� precisa informar o nome do reposit�rio desejado.");
		} else {
			try {
			    Remote remoteAux = RmiRegistry.lookup(nomeRepositorio);
                RmiRegistry.rebind(nomeRepositorio, remoteAux);
                repositorioAtual = (PartRepository) remoteAux;

//			    repositorioAtual = (PartRepository) RmiRegistry.lookup(nomeRepositorio);
				mensagens.MensagemSimples("Reposit�rio vinculado com sucesso!");
			} catch(Exception e) {
                e.printStackTrace();
//			    mensagens.Erro();
                mensagens.OperacaoNaoPodeSerRealizada();
			}
		}
	}
	
	private void ListarPecasRepositorio()
	{
		try {
            List<Part> response = repositorioAtual.recuperarTodasPecas();
            
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
            Part response = repositorioAtual.buscarPecaPorCodigo(Integer.parseInt(codigo));
			
            if (response != null) {
            	pecaAtual = response;
            	mensagens.MensagemSimples("A Pe�a de c�digo" + codigo + " foi encontrada e definida como Pe�a corrente.");
            } else
            	mensagens.MensagemSimples("Nao existe uma pe�a com o c�digo informado.");
            
		} catch(Exception e){
			mensagens.OperacaoNaoPodeSerRealizada();
		}
	}
	
	private void MostrarPecaAtual()
	{
		if (pecaAtual != null)
			mensagens.ExibirPecaNoNivel(pecaAtual, 1);
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
		try
		{
			Part novaPeca = ReceberInformacoesECriarNovaPeca();
			novaPeca.setSubcomponentes(subcomponentesAtuais);
			
			repositorioAtual.adicionarAoRepositorio(novaPeca);
			mensagens.MensagemSimples("A Pe�a adicionada ao repostorio atual com sucesso!");
		}
		catch(Exception e){
			mensagens.OperacaoNaoPodeSerRealizada();
		}
	}
	
	private Part ReceberInformacoesECriarNovaPeca()
	{
		Scanner sc = new Scanner(System.in);			
		mensagens.MensagemSimples("Para adicionar uma Pe�a ao reposit�rio atual, informe os dados: ");

		mensagens.MensagemSimples("Codigo da Pe�a: ");
		String codigo = sc.next();
		
		mensagens.MensagemSimples("Nome da Pe�a: ");
		String nome = sc.next();
		
		mensagens.MensagemSimples("Descri��o da Pe�a: ");
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
			
			mensagens.MensagemSimples("A 'Pe�a atual' foi adicionada � lista de 'Subcomponentes atuais' com sucesso!");
		}
		catch(Exception e){
			mensagens.OperacaoNaoPodeSerRealizada();
		}
	}
}
