

public class Leger implements Lik,Comparable<Leger> {
	protected String navn;
	protected  EldsteForstReseptListe  e_ForstReseptListe;
	protected int avtaleNummer;
	Leger(String navn,int avtaleNummer) {
		this.avtaleNummer=avtaleNummer;
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
	public int hentAvtaleNt(){
		return avtaleNummer;}

	@Override
	public int compareTo(Leger o) {
		
		return (this.navn).compareTo(o.navn);
	}
	
	public  EldsteForstReseptListe hentEldsteForstReseptListe(){
		return e_ForstReseptListe;}
	
	public String toString (){
		return hentNavn();}
	public String tilFile(){
		return ""+ navn+","+avtaleNummer;}

}
