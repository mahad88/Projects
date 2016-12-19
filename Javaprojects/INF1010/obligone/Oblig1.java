

public class Oblig1 {
	public static void main(String[] args) {

		Bol<Mus> musebol = new Bol<Mus>();
		Bol<Rotte> rottebol = new Bol<Rotte>();

		Katt kt = new Katt("tom "); // katt objekt
		kt.jakt(musebol, rottebol);

		Rotte r = new Rotte("Ronny", true); // rat obcekt
		rottebol.setInn(r);
		kt.jakt(musebol, rottebol);

		Mus m1 = new Mus("Jerry ", true); // mouse objekt
		musebol.setInn(m1);

		Mus m2 = new Mus("panya", true);// secomd mouse object
		musebol.setInn(m2);
		kt.jakt(musebol, rottebol);
		
		
	
	}

}
