package se.kh.iv1350.pointofsale.integration.productFactory;

import se.kh.iv1350.pointofsale.dto.ItemDTO;

/**
 * This is the Product interface according to the Factory design pattern
 */
public interface Product {

    public ItemDTO getDTO();
}
