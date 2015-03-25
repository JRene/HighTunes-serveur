import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.HashMap;


public class GestionnaireCommandes extends UnicastRemoteObject implements GestionnaireCommandesInterface {
	private static final long serialVersionUID = -8265337369338424131L;
	private HashMap<Integer, Commande> gestionnaire;
	private HightunesServer serveur;

	public GestionnaireCommandes(HightunesServer serveur) throws RemoteException {
		gestionnaire = new HashMap<Integer, Commande>();
		this.serveur = serveur;
	}
	
	public int creerCommande(Panier p) throws RemoteException {
		Commande c = new Commande(p);
		gestionnaire.put(c.getId(), c);
		return c.getId();
	}

	@Override
	public void payer(int id) throws RemoteException {
		System.out.println("\n PAIEMENT RECU : " + gestionnaire.get(id) + "€\n");
	}
}
