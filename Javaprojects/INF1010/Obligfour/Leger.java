

public class Leger implements Lik {
	protected String navn;

	Leger(String navn) {
		this.navn = navn;
	}

	public String hentNavn() {

		return navn;
	}

	@Override
	public boolean samme(String navn) {

		return this.navn.equals(navn);

	}

}
