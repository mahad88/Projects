

public class TypeB  extends Legemidler{
protected int vanedannende;
	TypeB(String navn,  double pris, int vanedannende) {
		super(navn, pris);
		this.vanedannende=vanedannende;
	}
     public int hentVanedannende(){
	return vanedannende;
}
}
