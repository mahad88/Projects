

import java.util.Iterator;





public class EnkelReseptListe implements Iterable<Resepter> {
	protected Node forste, siste;

	EnkelReseptListe() {

	}

	class Node {
		Node neste;
		Resepter data;

		Node(Resepter data) {
			this.data = data;
		}
	}
//sett inn paa siste plass.
	public void settInn(Resepter rr) {
		Node ny = new Node(rr);
		
		if (forste == null) {
			forste = ny;
			siste = ny;
		} else if(forste.neste==null) {

			forste.neste = ny;
			siste = ny;
		}
		else {
			siste.neste=ny;
					siste=ny;
	
			
		}
	}
	//finner resepter med resepter nummer
	public Resepter finnResepter(int reseptNr){
		
		try{
						for (Resepter r : this ) {
				if (r.hentTempnummer() == reseptNr) {
					
					return r;
				}
			}
		
		
			
			
				throw new NotFound();
			} catch (NotFound e) {
				System.out.println(e. message());
				return null;
			}
			}

	@Override
	public Iterator<Resepter> iterator() {
		
		return new ListIterator();
	}

	
	
	class ListIterator  implements Iterator<Resepter>{
		Node tt;
		
		ListIterator(){
			 tt=forste;
		}
		@Override
		public boolean hasNext() {
			
			return tt!=null;
		}

		@Override
		public Resepter next() {
			Resepter retur =tt.data;
			tt=tt.neste;
			return retur;
			
		}

		
		}
	
}
