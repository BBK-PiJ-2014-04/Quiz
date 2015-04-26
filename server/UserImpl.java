package server;

import interfaces.User;

public class UserImpl implements User {
	private String name;
	private int id;
	
	public UserImpl(String name, int id) {
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
