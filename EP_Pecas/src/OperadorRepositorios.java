import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.List;

public class OperadorRepositorios 
{
	private Part pecaAtual;
	private PartRepository repositorioAtual;
	private Registry RmiRegistry;
	private GeradorMensagens mensagens;
	
	public OperadorRepositorios()
	{
		mensagens = new GeradorMensagens();	
	}
	
	public void ExecutarOperacoes(String[] comandoEParametro)
	{
		String comando = comandoEParametro[0];
		String parametro = comandoEParametro[1];
		
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
				
			case Comandos.AddSubPeca:
	
				break;
			case Comandos.AddPeca:
	
				break;
				
			default:
				mensagens.Erro("O comando informado n�o foi aceito pelo sistema.");
				break;
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
		try{
			return RmiRegistry.list();	
		}
		catch(Exception e){
			mensagens.OperacaoNaoPodeSerRealizada();
			return null;
		}
	}
	
	private void VincularAoRepositorio(String nomeRepositorio)
	{
		try{
			repositorioAtual = (PartRepository) RmiRegistry.lookup(nomeRepositorio);
			mensagens.MensagemSimples("Reposit�rio vinculado com sucesso!");
		}
		catch(Exception e){
			mensagens.OperacaoNaoPodeSerRealizada();
		}
	}
	
	private void ListarPecasRepositorio()
	{
		try{
            List<Part> response = repositorioAtual.recuperarTodasPecas();
            
            if(response.isEmpty())
            	mensagens.MensagemSimples("N�o existem pe�as no reposit�rio atual.");
            else
            	mensagens.ExibirPecasRepositorio(response);
		}
		catch(Exception e){
			mensagens.OperacaoNaoPodeSerRealizada();
		}
	}
	
	private void RecuperarPecaPorCodigo(String codigo)
	{
		try{
            Part response = repositorioAtual.buscarPecaPorCodigo(Integer.parseInt(codigo));
			
            if(response != null)
            {
            	pecaAtual = response;
            	mensagens.MensagemSimples("A Pe�a de c�digo" + codigo + " foi encontrada e definida como Pe�a corrente.");
            }
            else
            	mensagens.MensagemSimples("Nao existe uma pe�a com o c�digo informado.");
            
		}
		catch(Exception e){
			mensagens.OperacaoNaoPodeSerRealizada();
		}
	}
	
	private void MostrarPecaAtual()
	{
		if(pecaAtual != null)
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
			
		}
	}
}
