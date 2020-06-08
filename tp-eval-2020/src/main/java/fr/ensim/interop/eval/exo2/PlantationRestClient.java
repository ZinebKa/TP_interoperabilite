package fr.ensim.interop.eval.exo2;

import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.Base64;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpRequest;
import org.springframework.http.client.ClientHttpRequestExecution;
import org.springframework.http.client.ClientHttpRequestInterceptor;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.web.client.RestTemplate;




public class PlantationRestClient {

	public static void main(String[] args) throws URISyntaxException {
		// Client REST fourni par le framework Spring
		RestTemplate restTemplate = new RestTemplate();

		// Créer une plantation 
		Plantation plantation = restTemplate.postForObject("http://localhost:9090/plantations?id={id}&date={date}&Plantation={Plantation}&nomPlante={Plante}&famillePlante={famillePlante}",
				null,
				Plantation.class,
				"TestPlantation");
					
		System.out.println("POST => " + plantation);

		// Modification
		plantation.setNomPlante("testModif");
		restTemplate.put("http://localhost:9090/plantations/{id}", plantation, plantation.getId());
		System.out.println("PUT");

		// Récupérer la définition de la plantation TestPlantation
		plantation = restTemplate.getForObject("http://localhost:9090/plantations/{id}", Plantation.class, plantation.getId());
		System.out.println("GET => " + plantation);
		

		// Supprimer la plantation
		restTemplate.delete("http://localhost:9090/plantations/{id}", plantation.getId());
		System.out.println("DELETE");

		restTemplate.getInterceptors().add(new ClientHttpRequestInterceptor() {
			@Override
		public ClientHttpResponse intercept(HttpRequest request, byte[] body, ClientHttpRequestExecution execution) throws IOException {
							// positionnement de l'entête Authorization
							String loginPassword = "monLogin"+":" + "monMotDePasse";
							String basicAuth = "Basic " + Base64.getEncoder().encodeToString(loginPassword.getBytes());
							request.getHeaders().add(HttpHeaders.AUTHORIZATION, basicAuth);
							System.out.println("Entêtes HTTP de la requête : "+request.getHeaders());

							// exécution de la requête
							return execution.execute(request, body);
						}
					});

					// tous les appels qui suivent spécifiront l'entête HTTP
					restTemplate.put("http://localhost:9090/plantations/{id}", plantation, plantation.getId());
				}



}
