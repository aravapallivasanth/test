/**
 * 
 */
package shoppingCart;

import inventory.Inventory;
import inventory.InventoryImpl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

/**
 * @author vasanth
 *
 */
public class ShoppingCartImpl implements ShoppingCart {

	private static final String APPLE = "apple";
	private static final String ORANGE = "orange";
	private Double price = 0.0;

	@Override
	public Map<String, Long> addProducts(List<String> prodcutList) {
		Map<String, Long> productMap = new HashMap<>();
		long appleCount = prodcutList.stream().filter(s -> s.equalsIgnoreCase(APPLE)).count();
		long orangeCount = prodcutList.stream().filter(s -> s.equalsIgnoreCase(ORANGE)).count();
		productMap.put(APPLE, appleCount);
		productMap.put(ORANGE, orangeCount);		
		return productMap;
	}

	@Override
	public Double calcuateTotalPrice(Map<String, Long> productMap) {		
		productMap.entrySet().stream().forEach(map -> {
			if (APPLE.equals(map.getKey())) {
				price = price + calculatePrice(map, 2, APPLE);
			} else if (ORANGE.equals(map.getKey())) {
				price = price + calculatePrice(map, 3, ORANGE);				
			}		
		});
		return price;
	}

	private Double calculatePrice(Entry<String, Long> map, int offer, String productName) {
		Inventory inventory = new InventoryImpl();
		int totalCount = map.getValue().intValue();
		int discountabel = totalCount / offer;
		return (totalCount - discountabel) * inventory.getProductPrice(productName);
	}
}
