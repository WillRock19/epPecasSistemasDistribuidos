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
				mensagens.Erro("O comando informado não foi aceito pelo sistema.");
				break;
		}
	}
	
	public void ConectarAoRmiRegistry(String host)
	{
		try{
			RmiRegistry = LocateRegistry.getRegistry(host);	
		}
		catch(Exception e) {
            mensagens.Erro("Não foi possível se concetar ao RMI Registry especificado. O seguinte erro ocorreu: " + e.getMessage());
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
			mensagens.MensagemSimples("Repositório vinculado com sucesso!");
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
            	mensagens.MensagemSimples("Não existem peças no repositório atual.");
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
            	mensagens.MensagemSimples("A Peça de código" + codigo + " foi encontrada e definida como Peça corrente.");
            }
            else
            	mensagens.MensagemSimples("Nao existe uma peça com o código informado.");
            
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
			mensagens.Erro("Não existe uma peça atual definida neste repositório.");
	}
	
	private void LimparListaDeSubcomponentes()
	{
		if(pecaAtual == null)
			mensagens.Erro("Não é possível limpar a lista de subcomponentes; não há nenhuma peça definida como 'Peça corrente'.");
		else
		{
			
		}
	}
}
