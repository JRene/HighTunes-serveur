import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
import java.util.Date;
import java.util.Scanner;


public class HightunesServer {
	private Catalogue catalogue;
	private GestionnairePaniers paniers;
	private GestionnaireCommandes commandes;
	
	public HightunesServer() {
		try {
			catalogue = new Catalogue();
			paniers = new GestionnairePaniers(this);
			commandes = new GestionnaireCommandes(this);
		} catch (RemoteException e) {
			e.printStackTrace();
		}
	}
	
	public Catalogue getCatalogue() {
		return catalogue;
	}
	
	public GestionnairePaniers getPaniers() {
		return paniers;
	}
	
	public GestionnaireCommandes getCommandes() {
		return commandes;
	}
	
	public static void main(String args[]) {
		//System.setSecurityManager(new SecurityManager());
		try {
			HightunesServer serveur = new HightunesServer();
			Registry registry = LocateRegistry.createRegistry(1099);
			registry.rebind("leCatalogue", serveur.getCatalogue());
			registry.rebind("lesPaniers", serveur.getPaniers());
			registry.rebind("lesCommandes", serveur.getCommandes());
			System.out.println("Serveur prêt");

			Scanner sc = new Scanner(System.in);
			String descTmp;
			float prixTmp;
			String dateTmp;
			do {
				System.out.print("\nAjout d'articles\nDescription ? ");
				descTmp = sc.nextLine();
				System.out.print("Prix ? ");
				prixTmp = sc.nextFloat();
				System.out.print("Disponibilité ? ");
				dateTmp = sc.nextLine();	// Date non initialisée
				serveur.getCatalogue().ajouterArticle(new Article(descTmp, prixTmp));
				System.out.println("Article ajouté");
			} while (true);
			//sc.close();
		} catch (Exception e) {
			System.out.println("Erreur serveur : " + e.getMessage());
			e.printStackTrace();
		}
	}
}
