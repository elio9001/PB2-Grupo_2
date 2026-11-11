package ar.unlam.tienda;

import java.util.Comparator;

public class OrdenarProdPerifericoPorIdAscYPrecioDesc implements Comparator<Periferico> {
	@Override
	public int compare(Periferico o1, Periferico o2) {
		int porId=o1.getCodProducto().compareTo(o2.getCodProducto());
		if(porId==0) {
			return o2.getPrecio().compareTo(o1.getPrecio());
		}
		return porId;
	}

}
