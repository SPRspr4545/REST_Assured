package org.loonycorn.bugs_api.controller;

import org.loonycorn.bugs_api.model.Bug;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.ResponseEntity;

import java.time.LocalDateTime;
import java.util.*;

// REST Assured API Testing: Testing Different Types of HTTP Endpoints
    // Exploring the Bug API Endpoints

// "@RestController" il s'agit d'une annotation stéréotypée du FWK Spring MVC qui est utilisé pour définir un contrôleur dans une application Web
// spécialement conçu pour créer des services Web RESful
// Spring traitera cette Classe comme un composant de contrôleur, capable de gérer les requêtes HTTP
// Ainsi toutes les valeurs renvoyées par l'une de ses méthodes, seront sérialisés au format JSON
@RestController
public class BugController {

    private List<Bug> bugs = new ArrayList<Bug>(); // initialise une liste vide de <Bug>
    // chaque fois qu'on redémarre cette application API Bug, la liste des Bug est remise à 0

    // Toutes les méthodes annotées "@GetMapping" répondent aux requêtes GET
    // le ("/") indique le chemin que la requête GET doit effectuer pour invoquer cette méthode
    @GetMapping("/")
    public String welcome() {
        return  "Welcome to the Bug Tracking API!";
    }

    // le ("/bugs") indique le chemin que la requête GET doit effectuer pour invoquer cette méthode
    @GetMapping("/bugs")
    public ResponseEntity<List<Bug>> getBugs() {
        return ResponseEntity.ok(bugs); // renvoit la liste des (bugs), que nous avons encapsulé dans "ResponseEntity.ok"
        // "ResponseEntity.ok" renverra le code d'état 200 et la liste des bugs automatiquement sérialisé au format JSON
    }

    // méthode pour récupérer un seul bug spécifié par ID
    // cette méthode sera invoquée si le ("/bugs/{bugId}") indique le chemin du bug qu'on souhaite récupérer
    @GetMapping("/bugs/{bugId}")
    public ResponseEntity<?> getBug(@PathVariable String bugId) {
        // la méthode prend un seul argument en entrée le "bugId" annoté sous la forme d'un...
        // ..."@PathVariable" qui extraira l'ID du bug indiqué dans le chemin pour le transmettre en tant qu'argument d'entrée

        Bug bug = bugs.stream() // utilise la manipulation de flux en JAVA
                .filter(b -> b.getBugId().equals(bugId))
                .findFirst()
                .orElse(null); // la variable "bug" stockera le bug s'il existe, sinon il sera null

        if (bug != null) {
            // "ResponseEntity.ok" renverra le code d'état 200 et le bug automatiquement sérialisé au format JSON
            return ResponseEntity.ok(bug);
        } else {
            return ResponseEntity
                    .status(HttpStatus.NOT_FOUND)
                    .body(Collections.singletonMap("error", "Bug not found"));
        }

    }

    // méthode invoquée lors d'une requête _POST
    @PostMapping("/bugs")
    public ResponseEntity<?> createBug(@RequestBody Bug bug) {
        // @RequestBody signifie que quels que soitent les attributs du bug (en entrée de la méthode), ils seront utilisés pour créer cette instance de bug
        bugs.add(bug); // ajoute le bug à la liste

        return ResponseEntity.status(HttpStatus.CREATED).body(bug); // renvoi les attributs du bug dans la réponse
    }

    // méthode invoquée lors d'une requête _PUT
    // Les requêtes PUT sont utilisés lorsqu'on veut mettre à jour tous les attributs d'une entité
    @PutMapping("/bugs/{bugId}")
    public ResponseEntity<?> updateBug(@PathVariable String bugId, @RequestBody Bug updatedBug) {

        // on commence par utiliser les flux JAVA pour détecter le bug identifié
        Bug bugToUpdate = bugs.stream()
                .filter(b -> b.getBugId().equals(bugId))
                .findFirst()
                .orElse(null);

        if (bugToUpdate != null) {
            bugToUpdate.setCreatedBy(updatedBug.getCreatedBy());
            bugToUpdate.setPriority(updatedBug.getPriority());
            bugToUpdate.setSeverity(updatedBug.getSeverity());
            bugToUpdate.setTitle(updatedBug.getTitle());
            bugToUpdate.setCompleted(updatedBug.getCompleted());
            bugToUpdate.setUpdatedOn(LocalDateTime.now());

            return ResponseEntity.ok(bugToUpdate);
        } else {
            return ResponseEntity
                    .status(HttpStatus.NOT_FOUND)
                    .body(Collections.singletonMap("error", "Bug not found"));
        }
    }

    // Les requêtes PATCH concernent des mises à jour partielles
    @PatchMapping("/bugs/{bugId}")
    public ResponseEntity<?> patchBug(@PathVariable String bugId, @RequestBody Bug updatedBug) {

        // on commence par utiliser les flux JAVA pour détecter le bug identifié
        Bug bugToUpdate = bugs.stream()
                .filter(b -> b.getBugId().equals(bugId))
                .findFirst()
                .orElse(null);

        if (bugToUpdate != null) {
            // nous ne mettons à jour que les attributs pour lesquels nous avons une valeur en entrée
            if (updatedBug.getCreatedBy() != null) {
                bugToUpdate.setCreatedBy(updatedBug.getCreatedBy());
            }
            if (updatedBug.getPriority() != null) {
                bugToUpdate.setPriority(updatedBug.getPriority());
            }
            if (updatedBug.getSeverity() != null) {
                bugToUpdate.setSeverity(updatedBug.getSeverity());
            }
            if (updatedBug.getTitle() != null) {
                bugToUpdate.setTitle(updatedBug.getTitle());
            }
            if (updatedBug.getCompleted() != null) {
                bugToUpdate.setCompleted(updatedBug.getCompleted());
            }
            bugToUpdate.setUpdatedOn(LocalDateTime.now());

            return ResponseEntity.ok(bugToUpdate);
        } else {
            return ResponseEntity
                    .status(HttpStatus.NOT_FOUND)
                    .body(Collections.singletonMap("error", "Bug not found"));
        }

    }

    @DeleteMapping("/bugs/{bugId}")
    public ResponseEntity<?> deleteBug(@PathVariable String bugId) {

        // on commence par utiliser les flux JAVA pour détecter le bug identifié
        Bug bug = bugs.stream()
                .filter(b -> b.getBugId().equals(bugId))
                .findFirst()
                .orElse(null);

        if (bug != null) {
            bugs.remove(bug);

            Map<String, String> responseMessage = new HashMap<>();
            responseMessage.put("message", "Bug deleted");
            responseMessage.put("bug_id", bugId);

            return  ResponseEntity.ok(responseMessage);
        } else {
            return ResponseEntity
                    .status(HttpStatus.NOT_FOUND)
                    .body(Collections.singletonMap("error", "Bug not found"));
        }
    }
}
