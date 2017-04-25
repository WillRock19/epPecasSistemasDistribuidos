package br.usp.sid.client;

import java.rmi.registry.Registry;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Cliente 
{

    protected static String host = "127.0.0.1";

    protected static int port = Registry.REGISTRY_PORT;

	private static OperadorRepositorios operadorRepositorios;

	private static GeradorMensagens mensagens;

	private static boolean executarPrograma;

	
	public static void main(String[] args) 
	{

        setupClient(args);

		IniciarPrograma();

		while(executarPrograma)
		{
			String[] comando = CapturarComandoUsuario();

			if(comando.length <= 2)
			{
				if(Comandos.Encerrar.equals(comando[0]))
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

		if (operadorRepositorios.ConectarAoRmiRegistry(host, port)) {
            mensagens.IniciarAplicacao();
            mensagens.ExibirComandos();
            mensagens.ExibirRepositoriosExistentes(host, operadorRepositorios.ListarRepositoriosDoRmiRegistry());
        } else {
            executarPrograma = false;
        }
	}
		
	private static String[] CapturarComandoUsuario()
    {
		Scanner sc = new Scanner(System.in);
		mensagens.RequisitarComandoAoUsuario();
		
		String comando = sc.nextLine();
		return comando.split(" ");
	}

    private static void setupClient (String[] args) {

        String patternString = "^([01]?\\d\\d?|2[0-4]\\d|25[0-5])\\." +
                "([01]?\\d\\d?|2[0-4]\\d|25[0-5])\\." +
                "([01]?\\d\\d?|2[0-4]\\d|25[0-5])\\." +
                "([01]?\\d\\d?|2[0-4]\\d|25[0-5])$";

        Pattern pattern = Pattern.compile(patternString);
        Matcher matcher;

        switch (args.length) {
            case 0:
                break;

            case 1:

                matcher = pattern.matcher(args[0]);

                if (matcher.matches()) {

                    host = args[0];
                } else {

                    try {
                        port = Integer.parseInt(args[0]);
                    } catch (NumberFormatException e) {
                        System.err.println("Host or number format port '" + args[0] + "' are invalid.\n" +
                                "Trying to connect with default host: '" + host + "' and port: '" + port + "'");
                    }
                }
                break;

            default:

                matcher = pattern.matcher(args[0]);

                if (matcher.matches()) {

                    host = args[0];
                } else {
                    System.err.println("Host '" + args[0] + "' is invalid.\n" +
                            "Trying to connect with default host: '" + host + "'");
                }

                try {

                    port = Integer.parseInt(args[1]);
                } catch (NumberFormatException e) {
                    System.err.println("Number format port '" + args[1] + "' is invalid.\n" +
                            "Trying to connect with default port: '" + port + "'");
                }

                break;
        }
    }
}
