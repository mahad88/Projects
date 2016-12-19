
public class TypeA  extends Legemidler{
	protected int sterk;
	TypeA(String navn,  double pris, int sterk) {
		super(navn,  pris);
		this.sterk= sterk;
	}

	public int hentSterkhet(){
		return sterk;
	}

}
