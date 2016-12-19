



public class EldsteForstReseptListe extends EnkelReseptListe {
	private Resepter r;

	EldsteForstReseptListe() {
		
		
	}

	public void settInn(Resepter e){
		if (forste==null)
			forste = new Node(e);
		else {
			Node temp = new Node(e);
			temp.neste = forste;
			forste = temp;
			siste=temp;
		}
	 
	}
		
}
