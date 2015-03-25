import java.util.HashMap;


public class Commande {
	private static int nextId = 1;
	private int id;
	private HashMap<Article, Integer> contenu;
	private float prixTotal;
	
	public Commande(Panier panier) {
		id = nextId++;
		contenu = panier.getContenu();
		prixTotal = panier.getPrixTotal();
	}
	
	public int getId() {
		return id;
	}
}
