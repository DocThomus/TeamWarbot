package explorer;

import java.util.ArrayList;

import util.Util;
import brains.WarExplorerBrainController;
import fsm.Fsm;
import fsm.State;
import edu.warbot.agents.agents.WarExplorer;
import edu.warbot.agents.enums.WarAgentType;
import edu.warbot.agents.percepts.WarAgentPercept;
import edu.warbot.agents.resources.WarFood;

public class ExplorerStateSearchFood extends State
{
	public WarExplorerBrainController brain;
	WarAgentPercept foodLePlusProche;
	
	public ExplorerStateSearchFood(Fsm fsm, WarExplorerBrainController brain) {
		super(fsm, brain);
		this.brain = brain;
	}
	
	public String execute() {
		brain.setDebugString("Farmer");
		
		if (foodLePlusProche != null) {
			brain.setHeading(foodLePlusProche.getAngle());
			if(foodLePlusProche.getDistance() < WarFood.MAX_DISTANCE_TAKE) {
				return WarExplorer.ACTION_TAKE;
			}
		}		
		return WarExplorer.ACTION_MOVE;
	}
	
	public void reflexe() {
		update();
		if(brain.isBlocked()) {
			brain.setRandomHeading();
		}
		if(brain.isBagFull()) {
			fsm.push(new ExplorerStateBringFood(fsm, brain));
			fsm.reflexe();
		}
	}
	
	public void update() {
		ArrayList<WarAgentPercept> foods = new ArrayList<WarAgentPercept>();
		for(WarAgentPercept p : brain.percepts) {
			if(p.getType() == WarAgentType.WarFood) {
				foods.add(p);
			}
		}
		this.foodLePlusProche = Util.lePlusProche(foods);
	}
}
