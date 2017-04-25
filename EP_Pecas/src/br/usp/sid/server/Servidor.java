package br.usp.sid.server;

import br.usp.sid.util.Part;

import java.rmi.RemoteException;
import java.rmi.AlreadyBoundException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
import java.util.LinkedList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Servidor implements PartRepository {

    private List<Part> parts = null;

    private String repositoryName = null;

    private static String host = "127.0.0.1";

    private static int port = Registry.REGISTRY_PORT;


	public Servidor (String serverName) throws RemoteException {

	    this.repositoryName = serverName;
	    this.parts = new LinkedList<>();
    }

    public String getRepositoryName () throws RemoteException {

        return this.repositoryName;
    }

    public Part get (String codigo) throws RemoteException {

        for (Part p : this.parts) {
            if (p.getCodigo().equalsIgnoreCase(codigo)) {
                return p;
            }
        }
        return null;
    }

    public List<Part> getAll () throws RemoteException {

        return this.parts;
    }

    public boolean add (Part p) throws RemoteException {

        return this.parts.add(p);
    }


	
	public static void main(String[] args) throws RemoteException {

	    String serverName = setupServer(args);

	    if (serverName == null) {
	        System.exit(1);
        }

        PartRepository stub = null;
        Registry registry = null;

        try {

            stub = (PartRepository) UnicastRemoteObject.exportObject(new Servidor(serverName), 0);

            registry = LocateRegistry.getRegistry(host, port);

            // Bind the remote object's stub in the registry
            registry.bind(serverName, stub);

            System.out.println("Server '" + serverName + "' is ready!\n" +
                    "RMI Service was found on the host: '" + host + "' and port: '" + port + "'");

        } catch (AlreadyBoundException e) {

            registry.rebind(serverName, stub);

            System.out.println("The new server '" + serverName + "' is ready!\n" +
                    "A server with the same name has already been registered in the RMI Service and has been overwritten.\n" +
                    "RMI Service was found on the host: '" + host + "' and port: '" + port + "'");

        } catch (Exception e) {
            System.err.println("Server exception: " + e.toString());
            e.printStackTrace();
        }


	}

	private static String setupServer (String[] args) {

        String patternString = "^([01]?\\d\\d?|2[0-4]\\d|25[0-5])\\." +
                        "([01]?\\d\\d?|2[0-4]\\d|25[0-5])\\." +
                        "([01]?\\d\\d?|2[0-4]\\d|25[0-5])\\." +
                        "([01]?\\d\\d?|2[0-4]\\d|25[0-5])$";

        Pattern pattern = Pattern.compile(patternString);
        Matcher matcher;

        String serverName = null;

	    switch (args.length) {
            case 0:
                break;

            case 1:
                serverName = args[0];
                break;

            case 2:
                serverName = args[1];

                matcher = pattern.matcher(args[0]);

                if (matcher.matches()) {

                    host = args[0];
                } else {

                    try {
                        port = Integer.parseInt(args[0]);
                    } catch (NumberFormatException e) {
                        System.err.println("Host or number format port '" + args[0] + "' are invalid.\n" +
                                "Trying to connect with default host: '" + host + "' and port: '" + port + "' on the server name: '" + serverName + "'");
                    }
                }
                break;

            default:
                serverName = args[2];

                matcher = pattern.matcher(args[0]);

                if (matcher.matches()) {

                    host = args[0];
                } else {
                    System.err.println("Host '" + args[0] + "' is invalid.\n" +
                            "Trying to connect with default host: '" + host + "' on the server name: '" + serverName + "'");
                }

                try {

                    port = Integer.parseInt(args[1]);
                } catch (NumberFormatException e) {
                    System.err.println("Number format port '" + args[1] + "' is invalid.\n" +
                            "Trying to connect with default port: '" + port + "' on the server name: '" + serverName + "'");
                }

                break;
        }

        return serverName;

    }
}
