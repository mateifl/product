package ro.zizicu.mservice.product.data.exceptions;

import lombok.NoArgsConstructor;

@NoArgsConstructor
public class ProductNotFound extends RuntimeException {

	public ProductNotFound(String s) {
		super(s);
	}

}
