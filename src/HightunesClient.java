import java.rmi.Remote;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.Scanner;


public class HightunesClient implements Remote, Runnable {
	@Override
	public void run() {
		//System.setSecurityManager(new SecurityManager());
		try {
			Registry registry = LocateRegistry.getRegistry("localhost", 1099);
			CatalogueInterface catalogue = (CatalogueInterface) registry.lookup("leCatalogue");
			GestionnairePaniersInterface paniers = (GestionnairePaniersInterface) registry.lookup("lesPaniers");
			GestionnaireCommandesInterface commandes = (GestionnaireCommandesInterface) registry.lookup("lesCommandes");

			System.out.println("Connect�");

			int idPanier = paniers.creerPanier(), idCommande = -1, choixArticle, qteArticle;
			String choix;
			Scanner sc;
			do {
				sc = new Scanner(System.in);
				System.out.println("\nChoix de l'action :\n1) Lister les articles\n2) Ajouter un article au panier\n3) Voir le panier\n4) Confirmer la commande\n5) Quitter");
				choix = sc.nextLine();
				switch (choix) {
				case "1" :
					System.out.println(catalogue.listeArticles());
					break;
				case "2" :
					System.out.println("Choix de l'article : ");
					choixArticle = sc.nextInt();
					paniers.ajouterArticle(idPanier, choixArticle);
					System.out.println("Quantit� : ");
					qteArticle = sc.nextInt();
					paniers.modifierQteArticle(idPanier, choixArticle, qteArticle);
					break;
				case "3" :
					if (idPanier != -1)
						System.out.println(paniers.listeArticles(idPanier));
					break;
				case "4" :
					if (idCommande == -1) {
						idPanier = -1;
						//idCommande = paniers.passerCommande(idPanier);
						//commandes.payer(idCommande);
						System.out.println("Paiement effectu�");
						choix = "5";
					}
					break;
				default :
					choix = "5";
				}
			} while (choix != "5");
			sc.close();
		} catch (Exception e) {
			System.out.println("Erreur client : " + e.getMessage());
			e.printStackTrace();
		}
	}
}