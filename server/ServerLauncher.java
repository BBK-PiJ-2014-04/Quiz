package server;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;

public class ServerLauncher {

	public static void main(String[] args) {
		ServerLauncher server = new ServerLauncher();
		server.launch();
	}

	private void launch() {
		// 1. If there is no security manager, start one
		if (System.getSecurityManager() == null) {
			System.setSecurityManager(new SecurityManager());
		}
		try {
			// 2. Create the registry if there is not one
			LocateRegistry.createRegistry(1099);
			// 3. Create the server object
			QuizServer server = new QuizServer();
			// 4. Register (bind) the server object on the registry.
			// The registry may be on a different machine
			String registryHost = "//localhost/";
			String serviceName = "QuizServer";
			Naming.rebind(registryHost + serviceName, server);
		} catch (MalformedURLException ex) {
			ex.printStackTrace();
		} catch (RemoteException ex) {
			ex.printStackTrace();
		}
	}
}
