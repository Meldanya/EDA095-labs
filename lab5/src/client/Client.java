package client;

import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.List;

import crawler.Blubb;

public class Client {
	public static void main(String[] args) {
		try {
			Registry registry = LocateRegistry.getRegistry("localhost");
			Blubb stub = (Blubb) registry.lookup("Blubb");
			stub.startCrawling();
			Thread.sleep(5000);
			List<String>[] stuff = stub.fetch();

			for (String s : stuff[0]) {
				System.out.println(s);
			}

		} catch (Exception e) {
			System.err.println("Client exception: " + e.toString());
			e.printStackTrace();
		}
	}
}
