package server;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

import interfaces.User;

public class UserImpl extends UnicastRemoteObject implements User {
	/**
	 * 
	 */
	private String name;
	private int id;
	
	public UserImpl(String name, int id) throws RemoteException {
		this.name = name;
		this.id = id;
	}
	
	@Override
	public String getName() {
		return name;
	}

	@Override
	public int getId() {
		return id;
	}

}
