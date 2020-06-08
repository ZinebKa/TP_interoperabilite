package fr.ensim.interop.eval.exo2;

import java.net.URI;
import java.util.Collection;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

import javax.validation.constraints.NotNull;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

@RestController
public class PlantationRestController {
	// TODO Exercice 2 - Implémentation d'une API REST pour la gestion de plantation
  // Simulation de la BDD par une map
	private Map<Integer, Plantation> planteBD = new ConcurrentHashMap<Integer, Plantation>();
	
	
 // Simulation d'un séquenceur pour générer l'identifiant des plantations
    private AtomicInteger fakeSeq = new AtomicInteger(0);
    
    @PostMapping("/plantations")
    public ResponseEntity<Plantation> addPlantation(@RequestParam("id") int id, @RequestParam("date") String date, @RequestParam("parcelle") String parcelle,
    		@RequestParam("nomPlante") String nomPlante, @RequestParam("famillePlante") String famillePlante ) {
    	
    	//Affectation
    	
    	Plantation plantation = new Plantation();
    	plantation.setId(id);
    	plantation.setDate(date);
    	plantation.setParcelle(parcelle);
    	plantation.setNomPlante(nomPlante);
    	plantation.setFamillePlante(famillePlante);
    	planteBD.put(plantation.getId(), plantation);
    	
        // URI de localisation de la ressource
        URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").build(plantation.getId());

        // réponse 201 avec la localisation et la ressource créée
        return ResponseEntity.created(location).body(plantation);
		
    } 

    @GetMapping("/plantations/{id}")
    public ResponseEntity<Plantation> getPlantation(@PathVariable("id") @NotNull int id) {
    	if (planteBD.containsKey(id)) {
    		return ResponseEntity.ok(planteBD.get(id));
    	}
    	return ResponseEntity.notFound().build();
    }
    
    
    @GetMapping("/plantations")
    public ResponseEntity<Collection<Plantation>> getPlantations() {
        return ResponseEntity.ok().body(planteBD.values());
    }

    @DeleteMapping("/plantations/{id}")
    public ResponseEntity<Plantation> deletePlantation(@PathVariable("id") @NotNull int id) {
        if (planteBD.containsKey(id)) {
            planteBD.remove(id);
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }

}