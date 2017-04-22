
import java.util.Scanner;

public class Cliente 
{
	private static String hostRmiRegistry;	
	private static OperadorRepositorios operadorRepositorios;
	private static GeradorMensagens mensagens;
	private static boolean executarPrograma;
	
	public static void main(String[] args) 
	{
		DefinirHostRmiRegistry(args);
		IniciarPrograma();
		
		while(executarPrograma)
		{
			String[] comando = CapturarComandoUsuario();
				
			if(comando.length <= 2)
			{
				if(Comandos.Encerrar.equals(comando))
					executarPrograma = false;
				else	
					operadorRepositorios.ExecutarOperacoes(comando);
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
		operadorRepositorios = new OperadorRepositorios();
		
		operadorRepositorios.ConectarAoRmiRegistry(hostRmiRegistry);
		
		mensagens.IniciarAplicacao();
		mensagens.ExibirComandos();
		mensagens.ExibirRepositoriosExistentes(hostRmiRegistry, operadorRepositorios.ListarRepositoriosDoRmiRegistry());
	}
		
	private static String[] CapturarComandoUsuario()
	{
		Scanner sc = new Scanner(System.in);
		mensagens.RequisitarComandoAoUsuario();
		
		String comando = sc.nextLine();
		return comando.split(" ");
	}
	
	private static void DefinirHostRmiRegistry(String[] args)
	{
		if(args.length > 0)
			hostRmiRegistry = args[0];
	}
}
