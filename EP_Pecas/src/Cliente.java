
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.Scanner;

public class Cliente 
{
	private String repositorioAtual;
	private Part pecaAtual;
	private static boolean executarPrograma;
	private static GeradorMensagens mensagens;
	private static Comandos comandos;
	
	public Cliente()
	{
		executarPrograma = true;
		mensagens = new GeradorMensagens();	
	}
	
	public static void main(String[] args) 
	{
		IniciarPrograma();
		
		while(executarPrograma)
		{
			String comando = CapturarComandoUsuario();
			String[] comandoComParametro = comando.split(" ");
			
			if(comandoComParametro.length <= 2)
			{
				ExecutarComandoDoUsuario(comandoComParametro);
			}
			else
				mensagens.ComandoContemParametrosDemais();
		}
		mensagens.EncerrarAplicacao();
	}
	
	private static void IniciarPrograma()
	{
		executarPrograma = true;
		mensagens = new GeradorMensagens();
		
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
	
	private static void ExecutarComandoDoUsuario(String[] comandoEParametro)
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
}
