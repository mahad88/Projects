

public class Leger implements Lik,Comparable<Leger> {
	protected String navn;
	protected  EldsteForstReseptListe  e_ForstReseptListe;
	Leger(String navn) {
		this.navn = navn;
		e_ForstReseptListe = new EldsteForstReseptListe();
	}

	public String hentNavn() {

		return navn;
	}

	@Override
	public boolean samme(String navn) {

		return this.navn.equals(navn);

	}

	@Override
	public int compareTo(Leger o) {
		
		return (this.navn).compareTo(o.navn);
	}
	
	public  EldsteForstReseptListe hentEldsteForstReseptListe(){
		return e_ForstReseptListe;}
	
	

}
