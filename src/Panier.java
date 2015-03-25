import java.rmi.RemoteException;
import java.util.HashMap;
import java.util.Iterator;


public class Panier {
	private static int nextId = 1;
	private int id;
	private HashMap<Article, Integer> contenu;
	private float prixTotal;
	
	protected Panier() throws RemoteException {
		super();
		id = nextId++;
		contenu = new HashMap<Article, Integer>();
		prixTotal = 0;
	}
	
	public void ajouterArticle(Article a) {
		contenu.put(a, 1);
	}

	public void modifierQteArticle(Article a, int qte) {
		contenu.put(a, qte);
	}
	
	public void supprimerArticle(Article a) {
		contenu.remove(a);
	}
	
	public float calculerPrix() throws RemoteException {
		prixTotal = 0;
		int qte;
		Article a;
		Iterator<Article> i = contenu.keySet().iterator();
		while (i.hasNext()) {
			a = i.next();
			qte = contenu.get(a).intValue();
			prixTotal += qte * a.getPrix();
		}
		return prixTotal;
	}
	
	public String toString() {
		String res = "=== Panier ===\n";
		if (contenu.isEmpty())
			res += "vide\n";
		else {
			Article a;
			Iterator<Article> i = contenu.keySet().iterator();
			while (i.hasNext()) {
				a = i.next();
				res += a.getDescription() + "\t\t" + a.getPrix() + "\t\t" + contenu.get(a) + "\n";
			}
			try {
				calculerPrix();
			} catch (RemoteException e) {
			}
			res += "Prix total : " + prixTotal + "\n";
		}
		return res;
	}
	
	public int getId() {
		return id;
	}

	public HashMap<Article, Integer> getContenu() {
		return contenu;
	}
	
	public float getPrixTotal() {
		return prixTotal;
	}
}
