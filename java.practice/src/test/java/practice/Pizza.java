package practice;

import java.util.ArrayList;
import java.util.EnumMap;
import java.util.EnumSet;
import java.util.List;
import java.util.stream.Collectors;

public class Pizza {
	private static EnumSet<PizzaStatus> undeliveredPizzaStatuses = EnumSet.of(PizzaStatus.ORDERED, PizzaStatus.READY);
	private PizzaStatus status;

	public PizzaStatus getStatus() {
		return status;
	}

	public void setStatus(PizzaStatus status) {
		this.status = status;
	}

	public boolean isDeliverable() {
		return this.status.isReady();
	}

	public void printTimeToDeliver() {
		System.out.println("Time to delivery is " + this.getStatus().getTimeToDelivery());
	}

	public static List<Pizza> getAllUndeliveriedPizzas(List<Pizza> input) {
		return input.stream().filter((s) -> undeliveredPizzaStatuses.contains(s.getStatus()))
				.collect(Collectors.toList());
	}
	
	public void deliver() {
	    if (isDeliverable()) {
	        PizzaDeliverySystemConfiguration.getInstance().getDeliveryStrategy()
	          .deliver(this);
	        this.setStatus(PizzaStatus.DELIVERED);
	    }
	}

	public static EnumMap<PizzaStatus, List<Pizza>> groupPizzaByStatus(List<Pizza> pizzaList) {
		EnumMap<PizzaStatus, List<Pizza>> pzByStatus = new EnumMap<PizzaStatus, List<Pizza>>(PizzaStatus.class);

		for (Pizza pz : pizzaList) {
			PizzaStatus status = pz.getStatus();
			if (pzByStatus.containsKey(status)) {
				pzByStatus.get(status).add(pz);
			} else {
				List<Pizza> newPzList = new ArrayList<Pizza>();
				newPzList.add(pz);
				pzByStatus.put(status, newPzList);
			}
		}
		return pzByStatus;
	}
	
	public enum PizzaDeliveryStrategy {
	    EXPRESS {
	        @Override
	        public void deliver(Pizza pz) {
	            System.out.println("Pizza will be delivered in express mode");
	        }
	    },
	    NORMAL {
	        @Override
	        public void deliver(Pizza pz) {
	            System.out.println("Pizza will be delivered in normal mode");
	        }
	    };

	    public abstract void deliver(Pizza pz);
	}
	
	public enum PizzaDeliverySystemConfiguration {
	    INSTANCE;
	    PizzaDeliverySystemConfiguration() {
	        // Initialization configuration which involves
	        // overriding defaults like delivery strategy
	    }

	    private PizzaDeliveryStrategy deliveryStrategy = PizzaDeliveryStrategy.NORMAL;

	    public static PizzaDeliverySystemConfiguration getInstance() {
	        return INSTANCE;
	    }

	    public PizzaDeliveryStrategy getDeliveryStrategy() {
	        return deliveryStrategy;
	    }
	}

	public enum PizzaStatus {
		ORDERED(5) {
			@Override
			public boolean isOrdered() {
				return true;
			}
		},
		READY(2) {
			@Override
			public boolean isReady() {
				return true;
			}
		},
		DELIVERED(0) {
			@Override
			public boolean isDelivered() {
				return true;
			}
		};

		private int timeToDelivery;

		public boolean isOrdered() {
			return false;
		}

		public boolean isReady() {
			return false;
		}

		public boolean isDelivered() {
			return false;
		}

		public int getTimeToDelivery() {
			return timeToDelivery;
		}

		PizzaStatus(int timeToDelivery) {
			this.timeToDelivery = timeToDelivery;
		}
	}
}
