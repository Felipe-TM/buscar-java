package br.com.unifil.buscar.enums;

import br.com.unifil.buscar.repositories.MongoDBRepository;

/**
 * Filter provides tree filter for the search options.<br><br>
 * 
 * ORIGIN_ONLY specifies that the search should occur only around
 * the area of the origin within the range given in the request.<br><br>
 * 
 * DESTINATION_ONLY specifies that the search should occur only around
 * the area of the destination within the range given in the request.<br><br>
 * 
 * BOTH is the combination of both
 * 
 * @author Felipe Torres
 * @version 1.0
 * @since 1.0
 * @see {@link MongoDBRepository}
 * */

public enum Filter {
	
	ORIGIN_ONLY,
	DESTINATION_ONLY,
	BOTH
}
