import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.HashMap;
import java.util.Iterator;


public class GestionnairePaniers extends UnicastRemoteObject implements GestionnairePaniersInterface {
	private static final long serialVersionUID = -8040220216033384204L;
	private HashMap<Integer, Panier> gestionnaire;
	private HightunesServer serveur;

	protected GestionnairePaniers(HightunesServer serveur) throws RemoteException {
		super();
		this.serveur = serveur;
		gestionnaire = new HashMap<Integer, Panier>();
	}

	@Override
	public int creerPanier() throws RemoteException {
		Panier p = new Panier();
		gestionnaire.put(p.getId(), p);
		return p.getId();
	}
	
	@Override
	public String listeArticles(int id) throws RemoteException {
		return gestionnaire.get(id).toString();
	}

	@Override
	public int passerCommande(int id) throws RemoteException {
		Panier p = gestionnaire.remove(id);
		return serveur.getCommandes().creerCommande(p);
	}

	@Override
	public void ajouterArticle(int idPanier, int idArticle) throws RemoteException {
		Panier p = gestionnaire.get(idPanier);
		Article a = serveur.getCatalogue().getArticle(idArticle);
		p.ajouterArticle(a);
	}

	@Override
	public void modifierQteArticle(int idPanier, int idArticle, int qte) throws RemoteException {
		Panier p = gestionnaire.get(idPanier);
		Article a = serveur.getCatalogue().getArticle(idArticle);
		p.modifierQteArticle(a, qte);
	}

	@Override
	public void supprimerArticle(int idPanier, int idArticle) throws RemoteException {
		Panier p = gestionnaire.get(idPanier);
		Article a = serveur.getCatalogue().getArticle(idArticle);
		p.supprimerArticle(a);
	}

	@Override
	public float calculerPrix(int idPanier) throws RemoteException {
		Panier p = gestionnaire.get(idPanier);
		return p.calculerPrix();
	}
}
