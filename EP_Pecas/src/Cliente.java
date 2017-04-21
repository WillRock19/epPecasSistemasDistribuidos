
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.Scanner;

public class Cliente 
{
	private String repositorioAtual;
	private Part pecaAtual;
	private String hostRmiRegistry;
	
	private static Registry RmiRegistry;
	private static boolean executarPrograma;
	private static GeradorMensagens mensagens;
	
	
	public Cliente()
	{
		executarPrograma = true;
		mensagens = new GeradorMensagens();	
	}
	
	public static void main(String[] args) 
	{
		IniciarPrograma(args);
		
		while(executarPrograma)
		{
			String comando = CapturarComandoUsuario();
			String[] comandoComParametro = comando.split(" ");
			
			if(comandoComParametro.length <= 2)
			{
				ExecutarComandoUsuario(comandoComParametro);
			}
			else
				mensagens.ComandoContemParametrosDemais();
		}
		mensagens.EncerrarAplicacao();
	}
	
	private static void IniciarPrograma(String[] argumentos)
	{
		executarPrograma = true;
		mensagens = new GeradorMensagens();
		
		AcessarRmiRegistry(argumentos[0]);
		
		mensagens.IniciarAplicacao();
		mensagens.ExibirListaDeComandos();
	}
		
	private static String CapturarComandoUsuario()
	{
		Scanner sc = new Scanner(System.in);
		mensagens.RequisitarComandoAoUsuario();
		
		String comando = sc.nextLine();
		sc.close();
		
		return comando;
	}
	
	private static void ExecutarComandoUsuario(String[] comandoEParametro)
	{
		String comando = comandoEParametro[0];
		String parametro = comandoEParametro[1];
		
		switch(comando)
		{
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
				
			case Comandos.Encerrar:
				executarPrograma = false;
				break;

			default:
				mensagens.ComandoNaoReconhecido();
				break;
		}
	}
	
	private static void AcessarRmiRegistry(String endereco)
	{
		try{
			RmiRegistry = LocateRegistry.getRegistry(endereco);	
		}
		catch(Exception e) {
            mensagens.MensagemDeErro("Não foi possível se concetar ao RMI Registry especificado. O seguinte erro ocorreu: " + e.getMessage());
		}
	}
	
	private String[] ListaRepositorios()
	{
		try{
			return RmiRegistry.list();	
		}
		catch(Exception e){
			mensagens.MensagemDeErro("Não foi possível encontrar uma lista de Registros no RMI Registry.");
			return null;
		}
	}
}
