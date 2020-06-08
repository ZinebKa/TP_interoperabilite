package fr.ensim.interop.eval.exo3;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.Base64;
import java.util.Scanner;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.http.client.ClientHttpRequestExecution;
import org.springframework.http.client.ClientHttpRequestInterceptor;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.web.client.RestTemplate;


public class ClientInteropMessenger {
    // TODO: Exercice 3 - Implémentation d'un client du service Interop Messenger (si langage de programmation Java).

	public static void main(String[] args) throws URISyntaxException {
		// Client REST fourni par le framework Spring
		RestTemplate restTemplate = new RestTemplate();

		restTemplate.getInterceptors().add(new ClientHttpRequestInterceptor() {
			@Override
		public ClientHttpResponse intercept(HttpRequest request, byte[] body, ClientHttpRequestExecution execution) throws IOException {
							// positionnement de l'entête Authorization
							String loginPassword = "zineb.kabbab.etu@univ-lemans.fr"+":" + "nU6pjPlY";
							String basicAuth = "Basic " + Base64.getEncoder().encodeToString(loginPassword.getBytes());
							request.getHeaders().add(HttpHeaders.AUTHORIZATION, basicAuth);
							System.out.println("Entêtes HTTP de la requête : "+request.getHeaders());

							// exécution de la requête
							return execution.execute(request, body);
						}
					});
		
				Message mes1 = new Message("Bonjour", null, "zineb.kabbab.etu@univ-lemans.fr");
				//Envoie d'un message fixe dans un canal fixe
				/*Message message1 = restTemplate.postForObject("https://ensim.flox.dev/channels/friendshipChannel/messages", mes1.getContent(), Message.class,"Test");
				System.out.println("POST => " + message1);					
				
				//Envoie d'un message saisi dans un canal fixe
				
				Message mes2 = new Message();
				mes2.setDateTime(null);
				mes2.setSender("zineb.kabbab.etu@univ-lemans.fr");
				Scanner reader = new Scanner(System.in);
				System.out.println("Veuillez saisir votre message: ");
				String s = reader.next();
				mes2.setContent(s);
				reader.close();
				Message message2 = restTemplate.postForObject("https://ensim.flox.dev/channels/friendshipChannel/messages",
								mes2.getContent(),
								Message.class,
								"Test");
						
				System.out.println("POST => " + message2);

				// Envoie d'un message saisi en boucle sur un canal fixe
				
				Message mes3 = new Message();
				Scanner reader = new Scanner(System.in);
				String monScanner;
				mes3.setDateTime(null);
				mes3.setSender("zineb.kabbab.etu@univ-lemans.fr");
				do{
					System.out.println("Enter a message: ");
					monScanner = reader.next(); 
					mes3.setContent(monScanner); 
					Message message4 = restTemplate.postForObject("https://ensim.flox.dev/channels/friendshipChannel/messages",
								mes3.getContent(),
								Message.class,
								"TOTO");
					System.out.println("POST => " + message4);
				}while(!monScanner.equals("finish")); // finish est le dernier message envoyé		
				reader.close();


				// Récupération des messages au lancement du programme
				/*ResponseEntity<Message[]> response = restTemplate.getForEntity("https://ensim.flox.dev/channels/friendshipChannel/messages", Message[].class);
				Message[] messages = response.getBody();
				System.out.println("GET => " +messages);*/
				
				//Récupération des messages chaque 5 secondes
				
				
				while(true){
					try {
					        Thread.sleep(5000);
					        ResponseEntity<Message[]> response = restTemplate.getForEntity("https://ensim.flox.dev/channels/friendshipChannel/messages", Message[].class);
							Message[] messages = response.getBody();
					        System.out.println("GET => " +messages);
					} catch(InterruptedException ie) {}
					
				}
	}
	
}

