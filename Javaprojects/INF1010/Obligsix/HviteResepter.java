

public class HviteResepter  extends Resepter{
private  double pris;
	HviteResepter( String legenavn, String pasientnavn, int reit,double pris) {
		super( legenavn, pasientnavn, reit);
		this.pris=pris;
	}
public double hentPris(){
	return pris;
}
}
