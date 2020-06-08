package fr.ensim.interop.eval.exo1;

import javax.xml.ws.Endpoint;

public class AvisServiceRunner {

	public static void main(String[] args) {
		String url = "http://localhost:8089/";
		Endpoint.publish(url,new AvisService());
		System.out.println("L'url :" +url);
		
	}

}
