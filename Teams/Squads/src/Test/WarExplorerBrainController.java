package Squads;

import java.util.List;

import edu.warbot.agents.agents.WarExplorer;
import edu.warbot.agents.enums.WarAgentType;
//import edu.warbot.agents.enums.WarAgentType;
import edu.warbot.agents.percepts.WarAgentPercept;
import edu.warbot.agents.resources.WarFood;
import edu.warbot.brains.brains.WarExplorerBrain;

public abstract class WarExplorerBrainController extends WarExplorerBrain {
	
	private boolean justPickedFood;

    public WarExplorerBrainController() {
        super();
        
        this.justPickedFood = false;
    }

    @Override
    public String action() {
    	// Ennemies en vue.
    	List<WarAgentPercept> percepts = getPerceptsEnemies();
		for(WarAgentPercept p : percepts) {
			switch (p.getType()) {
				case WarBase:
					broadcastMessageToAll("EnemyBaseSpotted", String.valueOf(p.getAngle()), String.valueOf(p.getDistance()));
					setDebugString("EnemyBaseSpotted");
					break;
				case WarBomb:
					break;
				case WarEngineer:
					break;
				case WarExplorer:
					break;
				case WarKamikaze:
					break;
				case WarRocket:
					break;
				case WarRocketLauncher:
					break;
				case WarTurret:
					break;
				default:
					break;			
			}
		}
		
		// Nourriture en vue.
		WarAgentPercept food = Util.lePlusProche(getPerceptsResources());
		if (food != null) {
			// Peut être utile pour d'autres agents.
			broadcastMessageToAgentType(WarAgentType.WarExplorer, "FoodHere", String.valueOf(food.getAngle()), String.valueOf(food.getDistance()));
			if (!isBagFull()) {
				setDebugString("Pick Food");
				if(food.getDistance() < WarFood.MAX_DISTANCE_TAKE) {
					justPickedFood = true;
					return WarExplorer.ACTION_TAKE;
				} else {
					setHeading(food.getAngle());
				}
					
			}
		} else {
			if (justPickedFood) {
				setHeading((getHeading() + 180) % 360);
			}
		}
		
		if (isBlocked()) {
			setRandomHeading();
		}
		
		return WarExplorer.ACTION_MOVE;
    }

}
