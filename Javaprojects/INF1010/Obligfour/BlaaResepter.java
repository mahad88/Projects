

public class BlaaResepter  extends Resepter{
private double pris;
	BlaaResepter( String legenavn, String pasientnavn, int reit,double pris) {
		super( legenavn, pasientnavn, reit);
	 this.pris=0;
	}
public double hentPris(){
	return pris;
}
}
