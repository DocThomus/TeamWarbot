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
import edu.warbot.communications.WarMessage;

public class ExplorerStateSearchFood extends State
{
	public WarExplorerBrainController brain;
	private WarAgentPercept foodLePlusProche;
	private boolean justPickedFood;
	
	public ExplorerStateSearchFood(Fsm fsm, WarExplorerBrainController brain) {
		super(fsm, brain);
		this.brain = brain;
		this.justPickedFood = false;
	}
	
	public String execute() {
		brain.setDebugString("Farmer");
		
		if(brain.isBlocked()) {
			brain.setRandomHeading();
		}
		
		
		if (foodLePlusProche != null) {
			brain.setHeading(foodLePlusProche.getAngle());
			if(foodLePlusProche.getDistance() < WarFood.MAX_DISTANCE_TAKE) {
				this.justPickedFood = true;
				return WarExplorer.ACTION_TAKE;
			}
		} else if (this.justPickedFood) {
			brain.setHeading((brain.getHeading() + 180) % 360);
			this.justPickedFood = false;
		}	
		
		return WarExplorer.ACTION_MOVE;
	}
	
	public void reflexe() {
		this.update();
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
		
		for(WarMessage m : brain.mailbox) {
			if (m.getMessage() == "I'm the King !!") {
				brain.idOverlord = m.getSenderID();
				brain.distanceBase = m.getDistance();
				brain.directionBase = m.getAngle();
			}
		}
	}
}
