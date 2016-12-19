

public class Oblig4 {
	public static void main(String[] args) {

		TypeA a = new TypeA("panadol", 12, 234);
		TypeB b = new TypeB("panadol", 12, 234);
		TypeC c = new TypeC("asprin", 12);

		HviteResepter br = new HviteResepter("abuld", "weli", 15, 23);
		Leger lege = new Leger("ben carson");
		Fastlege fl = new Fastlege("kjetil", 232);
		Pasienter p = new Pasienter("ali", "23457522701", "bispe gata 1044",
				234);

		TypeApiller tAp = new TypeApiller("panadol", 12, 2434, 452);
		TypeAmikstur tAm = new TypeAmikstur("panadol", 12, 234, 452);
		TypeBpiller tBp = new TypeBpiller("panadol", 12, 2434, 452);
		TypeBmikstur tBm = new TypeBmikstur("panadol", 12, 234, 452);
		TypeCpiller tCp = new TypeCpiller("panadol", 12, 2434);
		TypeCmikstur tCm = new TypeCmikstur("panadol", 12, 234);
		System.out.println(br.hentLegenavn());

	}
}