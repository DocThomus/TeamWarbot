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
	
	private double distanceBase;
	private double directionBase;
	
	public ExplorerStateBringFood(Fsm fsm, WarExplorerBrainController brain)
	{
		super(fsm, brain);
		this.brain = brain;
	}
	
	public String execute()
	{
		brain.setDebugString("bring");
		if (this.distanceBase < ControllableActions.MAX_DISTANCE_GIVE) {
			return WarExplorer.ACTION_GIVE;
		} else {
			brain.setHeading(this.directionBase);
			return WarExplorer.ACTION_MOVE;
		}
	}
	
	public void reflexe()
	{
		if (brain.isBagEmpty()) {
			fsm.pop();
			fsm.reflexe();
		}
	}
	
	public void update()
	{
		for(WarMessage m : brain.mailbox) {
			if (m.getMessage() == "I'm the King !!") {
				brain.idOverlord = m.getSenderID();
				this.distanceBase = m.getDistance();
				this.directionBase = m.getAngle();
			}
		}
	}
}
