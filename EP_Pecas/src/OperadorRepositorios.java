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
				mensagens.MensagemDeErro("O comando informado não foi aceito pelo sistema.");
				break;
		}
	}
	
	public void ConectarAoRmiRegistry(String host)
	{
		try{
			RmiRegistry = LocateRegistry.getRegistry(host);	
		}
		catch(Exception e) {
            mensagens.MensagemDeErro("Não foi possível se concetar ao RMI Registry especificado. O seguinte erro ocorreu: " + e.getMessage());
		}
	}
	
	public String[] ListarRepositoriosDoRmiRegistry()
	{
		try{
			return RmiRegistry.list();	
		}
		catch(Exception e){
			mensagens.MensagemDeErro("Não foi possível encontrar uma lista de Registros no RMI Registry.");
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
			mensagens.MensagemDeErro("Não foi possível vincular ao repositório desejado.");
		}
	}
	
	private void ListarPecasRepositorioAtual()
	{
		try{
            List<Part> response = repositorioAtual.recuperarTodasPecas();
			
		}
		catch(Exception e){
			mensagens.MensagemDeErro("Não foi possível listar as Pecas do repositorio.");
		}
	}
}
