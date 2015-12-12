package Test;

import java.util.List;

import edu.warbot.agents.percepts.WarAgentPercept;

public final class Util {

	public static WarAgentPercept lePlusProche(List<WarAgentPercept> percepts) {
		WarAgentPercept food = null;
		if (percepts != null && !percepts.isEmpty()) {
			food = percepts.get(0);
			double distance = food.getDistance();
			for(int i = 1; i < percepts.size(); i++) {
				if (distance < percepts.get(i).getDistance()) {
					food = percepts.get(i);
				}
			}
		}		
		return food;
	}
	
	

}
