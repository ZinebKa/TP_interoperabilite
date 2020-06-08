package fr.ensim.interop.eval.exo1;


import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebService;

@WebService(name="AvisWS")
public class AvisService {

	public static ArrayList<Avis> listeAvis = new ArrayList<Avis>();

	@WebMethod
	public int enregistrerAvis(@WebParam(name="redProd")String refProd, @WebParam(name="note") int note, @WebParam(name = "commentaire") String commentaire, @WebParam(name = "refFourniss") String refFourn) {
		
		Avis avis = new Avis(refProd, note, commentaire, refFourn);
		
		avis.setId(randomIdGeneretor(10));
		avis.setDate(new Date());
		avis.setCountryCodes(Locale.getISOCountries());
		
		listeAvis.add(avis);
		
		int nbAvisFourn = 0;
		for(int i=0; i< listeAvis.size(); i++) {
			if(listeAvis.get(i).getRefFourn().equals(refFourn)) {
				nbAvisFourn++;
			}
		}
		return nbAvisFourn;
		}
	
	@WebMethod
	public ArrayList <Avis> listerAvis(@WebParam(name = "refProduit") String refProduit) throws IllegalArgumentException{
		if(!refProduit.matches("[a-zA-Z0-9]+") || refProduit.length() > 20)
			throw new IllegalArgumentException("la référence est incorrect");
		
		ArrayList <Avis> listResultat = new ArrayList<Avis>();
		
		for(int i = listeAvis.size() - 1; i >= 0 ; i--) {
			if(listeAvis.get(i).getRefProd().equals(refProduit)) {
				listResultat.add(listeAvis.get(i));
			}
		}
		
		
		return listResultat;
		
	}

	
	private static final String ALPHA_NUMERIC_STRING = "ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
	
	private static String randomIdGeneretor(int i) {
		StringBuilder builder = new StringBuilder();
		while (i-- != 0) {
			int element = (int)(Math.random()*ALPHA_NUMERIC_STRING.length());
			builder.append(ALPHA_NUMERIC_STRING.charAt(element));
		}
		return builder.toString();
	}

}