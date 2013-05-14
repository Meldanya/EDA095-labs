package crawler;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;

public interface Blubb extends Remote {
	public List<String>[] fetch() throws RemoteException;
	public List<String>[] fetchAndSet(List<String> trav, List<String> rem) throws RemoteException;

	public void startCrawling() throws RemoteException;
}
