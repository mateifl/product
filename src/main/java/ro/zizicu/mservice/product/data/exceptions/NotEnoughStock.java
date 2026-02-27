package ro.zizicu.mservice.product.data.exceptions;

import lombok.NoArgsConstructor;

@NoArgsConstructor
public class NotEnoughStock extends RuntimeException {

    public NotEnoughStock(String s) {
        super(s);
    }
}
