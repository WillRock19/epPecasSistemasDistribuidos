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
				
				break;
				
			case Comandos.RecuperarPeca:
				
				break;
			
			case Comandos.MostrarPeca:
	
				break;
			case Comandos.LimparLista:
	
				break;
			case Comandos.AddSubPeca:
	
				break;
			case Comandos.AddPeca:
	
				break;
				
			default:
				mensagens.MensagemDeErro("O comando informado n�o foi aceito pelo sistema.");
				break;
		}
	}
	
	public void ConectarAoRmiRegistry(String host)
	{
		try{
			RmiRegistry = LocateRegistry.getRegistry(host);	
		}
		catch(Exception e) {
            mensagens.MensagemDeErro("N�o foi poss�vel se concetar ao RMI Registry especificado. O seguinte erro ocorreu: " + e.getMessage());
		}
	}
	
	public String[] ListarRepositoriosDoRmiRegistry()
	{
		try{
			return RmiRegistry.list();	
		}
		catch(Exception e){
			mensagens.MensagemDeErro("N�o foi poss�vel encontrar uma lista de Registros no RMI Registry.");
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
			mensagens.MensagemDeErro("N�o foi poss�vel vincular ao reposit�rio desejado.");
		}
	}
	
	private void ListarPecasRepositorioAtual()
	{
		try{
            List<Part> response = repositorioAtual.recuperarTodasPecas();
			
		}
		catch(Exception e){
			mensagens.MensagemDeErro("N�o foi poss�vel listar as Pecas do repositorio.");
		}
	}
}
