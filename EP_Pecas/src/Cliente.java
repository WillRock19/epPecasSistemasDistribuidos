
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.Scanner;

public class Cliente 
{
	private String repositorioAtual;
	private Part pecaAtual;
	private static boolean executarPrograma;
	private static GeradorMensagens mensagens;
	
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
				if(!Comandos.ENCERRAR.Equals(comandoComParametro[0]))
				{
					ExecutarComandoDoUsuario(comandoComParametro);
				}
				else
					executarPrograma = false;
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
		
	}
}
