package ar.unlam.tienda;

import java.util.Comparator;

public class ordenarProdPerifericoPorIdAscYPrecioDesc implements Comparator< ProductoPeriferico> {

	@Override
	public int compare(ProductoPeriferico o1, ProductoPeriferico o2) {
		int porId=o1.getId().compareTo(o2.getId());
		if(porId==0) {
			return o2.getPrecio().compareTo(o1.getPrecio());
		}
		return porId;
	}

}
