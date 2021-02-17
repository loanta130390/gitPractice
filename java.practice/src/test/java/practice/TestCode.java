package practice;

import java.util.ArrayList;
import java.util.EnumMap;
import java.util.List;

public class TestCode {

	public static void main(String[] args) {
		List<Pizza> pzList = new ArrayList<>();
	    Pizza pz1 = new Pizza();
	    pz1.setStatus(Pizza.PizzaStatus.DELIVERED);

	    Pizza pz2 = new Pizza();
	    pz2.setStatus(Pizza.PizzaStatus.DELIVERED);

	    Pizza pz3 = new Pizza();
	    pz3.setStatus(Pizza.PizzaStatus.ORDERED);

	    Pizza pz4 = new Pizza();
	    pz4.setStatus(Pizza.PizzaStatus.READY);

	    pzList.add(pz1);
	    pzList.add(pz2);
	    pzList.add(pz3);
	    pzList.add(pz4);

	    List<Pizza> undeliveredPzs = Pizza.getAllUndeliveriedPizzas(pzList); 
	    System.out.println(undeliveredPzs.size());
	    
	    EnumMap<Pizza.PizzaStatus,List<Pizza>> map = Pizza.groupPizzaByStatus(pzList);
	    System.out.println(map.get(Pizza.PizzaStatus.DELIVERED).size());
	}

}
