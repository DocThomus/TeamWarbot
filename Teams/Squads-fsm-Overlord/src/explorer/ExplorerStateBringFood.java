package explorer;

import brains.WarExplorerBrainController;
import edu.warbot.agents.actions.constants.ControllableActions;
import edu.warbot.agents.agents.WarExplorer;
import edu.warbot.communications.WarMessage;
import fsm.Fsm;
import fsm.State;

public class ExplorerStateBringFood extends State
{
	public WarExplorerBrainController brain;
	
	public ExplorerStateBringFood(Fsm fsm, WarExplorerBrainController brain) {
		super(fsm, brain);
		this.brain = brain;
		brain.distanceBase = 50000;
		brain.directionBase = 0;
	}
	
	public String execute() {
		brain.setDebugString("bring");
		if (brain.distanceBase < ControllableActions.MAX_DISTANCE_GIVE) {
			brain.setIdNextAgentToGive(brain.idOverlord);
			return WarExplorer.ACTION_GIVE;
		} else {
			brain.setHeading(brain.directionBase);
			return WarExplorer.ACTION_MOVE;
		}
	}
	
	public void reflexe() {
		this.update();
		if (brain.isBagEmpty()) {
			fsm.pop();
			fsm.reflexe();
		}
	}
	
	public void update() {
		for(WarMessage m : brain.mailbox) {
			if (m.getMessage() == "I'm the King !!") {
				brain.idOverlord = m.getSenderID();
				brain.distanceBase = m.getDistance();
				brain.directionBase = m.getAngle();
			}
		}
	}
}
