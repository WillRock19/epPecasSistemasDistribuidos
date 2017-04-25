package br.usp.sid.server;

import br.usp.sid.util.Part;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;

public interface PartRepository extends Remote {

	String repositoryName = null;

	List<Part> parts = null;

	List<Part> getAll () throws RemoteException;
	
	Part get (String codigo) throws RemoteException;
	
	boolean add (Part p) throws RemoteException;
	
	String getRepositoryName() throws RemoteException;
}
