import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.Date;
import java.util.HashMap;


public class Catalogue extends UnicastRemoteObject implements CatalogueInterface {
	private static final long serialVersionUID = 2700710005372855890L;
	private HashMap<Integer, Article> contenu;

	protected Catalogue() throws RemoteException {
		super();
		contenu = new HashMap<Integer, Article>();
	}

	@Override
	public String listeArticles() throws RemoteException {
		String res = "Catalogue :\n";
		if (contenu.size() > 0)
			for (int i = 1; i <= contenu.size(); i++)
				res += contenu.get(Integer.valueOf(i)).toString() + "\n";
		else 
			res += "Vide\n";
		return res;
	}

	@Override
	public int nombreTotalArticles() throws RemoteException {
		return contenu.size();
	}

	@Override
	public String getEntree(int id) throws RemoteException {
		return contenu.get(Integer.valueOf(id)).toString();
	}

	@Override
	public String getDescArticle(int id) throws RemoteException {
		return contenu.get(Integer.valueOf(id)).getDescription();
	}

	@Override
	public String getPrixArticle(int id) throws RemoteException {
		return new String(contenu.get(Integer.valueOf(id)).getPrix() + "€");
	}

	@Override
	public Date getDateDispoArticle(int id) throws RemoteException {
		return contenu.get(Integer.valueOf(id)).getDateDispo();
	}

	public void ajouterArticle(Article article) {
		contenu.put(article.getId(), article);
	}

	public Article getArticle(int id) {
		return contenu.get(Integer.valueOf(id));
	}
}
