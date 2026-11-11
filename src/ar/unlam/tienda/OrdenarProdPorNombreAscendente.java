package ar.unlam.tienda;

import java.util.Comparator;

public class OrdenarProdPorNombreAscendente implements Comparator<Producto> {

	@Override
	public int compare(Producto o1, Producto o2) {
		return o1.getDescripcion().compareTo(o2.getDescripcion());
	}

}
