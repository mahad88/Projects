

public class HviteResepter  extends Resepter{
private  double pris;
	HviteResepter(int unikNr, String reseptType, int pasientNr, String legenavn,
			int legemiddelNr, int reit) {
		super(  unikNr,  reseptType,  pasientNr,  legenavn,
				 legemiddelNr, reit);
	 this.pris=0;
	
	}
public double hentPris(){
	return pris;
}
public String tilFile() {
	return super.tilFile();
}
}
