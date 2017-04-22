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
		try {
			return RmiRegistry.list();	
		}
		catch(Exception e) {
			return new String[0];
		}
	}
	
	private void VincularAoRepositorio(String nomeRepositorio)
	{
		if (nomeRepositorio.equals("")) {
			mensagens.MensagemDeErro("Voc� precisa informar o nome do reposit�rio desejado.");
		} else {
			try{
				repositorioAtual = (PartRepository) RmiRegistry.lookup(nomeRepositorio);
				mensagens.MensagemSimples("Reposit�rio vinculado com sucesso!");
			}
			catch(Exception e) {
				mensagens.MensagemDeErro("N�o foi poss�vel vincular ao reposit�rio desejado.");
			}
		}
	}
	
	private void ListarPecasRepositorio()
	{
		try
		{
            List<Part> response = repositorioAtual.recuperarTodasPecas();
			mensagens.ExibirPecasRepositorio(response);
		}
		catch(Exception e){
			mensagens.MensagemDeErro("N�o foi poss�vel listar as Pecas do repositorio.");
		}
	}
}
