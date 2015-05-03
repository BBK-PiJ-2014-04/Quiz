package interfaces;

import java.rmi.Remote;
import java.rmi.RemoteException;

/**
 * 
 * @author SalvatoreCardali
 *
 */
public interface User extends Remote{
	
	/**
	 * Gets the name of the User
	 * 
	 * @return
	 */
	public String getName() throws RemoteException;
	/**
	 * Gets the Id of the User
	 * 
	 * @return
	 */
	public int getId() throws RemoteException;

}
