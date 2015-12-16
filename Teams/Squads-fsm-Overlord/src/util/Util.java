package util;

import java.util.List;

import edu.warbot.agents.percepts.WarAgentPercept;

public final class Util {

	public static WarAgentPercept lePlusProche(List<WarAgentPercept> percepts) {
		WarAgentPercept p = null;
		if (percepts != null && !percepts.isEmpty()) {
			p = percepts.get(0);
			double distance = p.getDistance();
			for(int i = 1; i < percepts.size(); i++) {
				if (percepts.get(i).getDistance() < distance) {
					p = percepts.get(i);
				}
			}
		}		
		return p;
	}

	

}
