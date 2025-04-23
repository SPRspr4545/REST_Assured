package org.loonycorn.bugs_api;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

// l'annotation "SpringBootApplication" permet de faire fonctionner notre application Spring Web
@SpringBootApplication
public class BugsApiApplication {

	public static void main(String[] args) {
		// si le port est utilisé par une autre application, le changer dans "resources/application.properties"
		SpringApplication.run(BugsApiApplication.class, args);
	}

	//REST Assured API Testing: Testing Different Types of HTTP Endpoints
		// Exploring the Bug API Endpoints
		// Running an API Server

	// le fichier "BugsApiApplication.java" est le point d'entrée de ma candidature
	// lancer cette application pour faire apparaître le service API qui sera disponible sur le port 8089
		// une fois lancé le service sera opérationnel sur http://localhost:8090/

	// utiliser l'invite de commande pour effectuer des requêtes POST, PUT, PATCH et DELETE
		// curl --version
		// curl est un outil de ligne de commande intégré à la bibliothèque qui permet de trasférer des données via des URL
			// utilisé pour les tests, débogage et pour interagir avec les serveurs Web et les API
		// choco install jq
			// jq est un processeur JSON en ligne de commande, utilisé pour découper, filtrer, cartographier et transformer les données structurées
				// j'utilise jq pour embellir les réponses JSON

	// Pour commencer adressons une requête POST pour créer un nouveau bug sur notre serveur API:
		// curl -X POST \
		// http://localhost:8090/bugs \
		// 		-H 'Content-Type: application/json' \
		// 		-d '{
		// 			"createdBy": "Kim Doe",
		// 			"priority": 2,
		// 			"severity": "critical",
		// 			"title": "Database Connection Failure",
		// 			"completed": false
		// 		}' | jq
	// ChatGPT:
	// curl -X POST http://localhost:8090/bugs -H "Content-Type: application/json" -d "{\"createdBy\":\"Kim Doe\",\"priority\":2,\"severity\":\"critical\",\"title\":\"Database Connection Failure\",\"completed\":false}" | jq
	//curl -X POST http://localhost:8090/bugs -H "Content-Type: application/json" -d "{\"createdBy\":\"Jack Farley\",\"priority\":3,\"title\":\"Page does not render correctly\",\"completed\":false}" | jq

	// Requête GET pour voir si les bugs sont présents sur le serveur:
	// curl -X GET http://localhost:8090/bugs | jq
	// copier le "bugId" du 1er bug et faire une requête GET pour récupérer uniquement ce bug
	// curl -X GET http://localhost:8090/bugs/f9b6e4d9-b152-4089-9b23-58780dfdd30d | jq

	// Requête PUT pour mettre à jour l'un des bugs:
	// curl -X PUT http://localhost:8090/bugs/8791fdcd-56fb-4855-9e57-173462faf769 -H "Content-Type: application/json" -d "{\"createdBy\":\"Jack Farley\",\"priority\":3,\"severity\":\"High\",\"title\":\"Page does not render correctly\",\"completed\":false}" | jq
	// curl -X PUT http://localhost:8090/bugs/8791fdcd-56fb-4855-9e57-173462faf769 -H "Content-Type: application/json" -d "{\"createdBy\":\"Jack Farley\",\"priority\":3,\"severity\":\"High\",\"title\":\"Page does not render correctly\",\"completed\":false}" | jq

	// Requête PATCH pour mise à jour partielle:
	// curl -X PATCH http://localhost:8090/bugs/8791fdcd-56fb-4855-9e57-173462faf769 -H "Content-Type: application/json" -d "{\"priority\":3}" | jq

	// Requête DELETE pour supprimer le bug:
	//curl -X DELETE http://localhost:8090/bugs/2736908a-0716-4dda-b11c-1ea772af466d


	//REST Assured API Testing: Testing Different Types of HTTP Endpoints
		// Making and Testing POST Requests

}

