package ar.unlam.tienda;

import java.util.Comparator;

public class ordenarProdPorNombreAscendente implements Comparator<Producto> {

	@Override
	public int compare(Producto o1, Producto o2) {
		// TODO Auto-generated method stub
		return o1.getNombre().compareTo(o2.getNombre());
	}

}
