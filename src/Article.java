import java.util.Date;


public class Article {
	private static int nextId = 1;
	private int id;
	private String desc;
	private float prix;
	private Date dateDispo;
	
	public Article() {
		id = nextId++;
		desc = "";
		prix = 0;
		dateDispo = null;
	}
	
	public Article(String desc, float prix) {
		id = nextId++;
		this.desc = desc;
		this.prix = prix;
		dateDispo = null;
	}

	public Article(String desc, float prix, Date dateDispo) {
		id = nextId++;
		this.desc = desc;
		this.prix = prix;
		this.dateDispo = dateDispo;
	}
	
	public int getId() {
		return id;
	}
	
	public String getDescription() {
		return desc;
	}
	
	public float getPrix() {
		return prix;
	}
	
	public Date getDateDispo() {
		return dateDispo;
	}
	
	@Override
	public String toString() {
		return (id + "\t\t" + desc);
	}
}
