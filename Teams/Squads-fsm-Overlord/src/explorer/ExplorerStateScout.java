package explorer;

import brains.WarExplorerBrainController;
import edu.warbot.agents.agents.WarExplorer;
import edu.warbot.agents.enums.WarAgentType;
import edu.warbot.agents.percepts.WarAgentPercept;
import edu.warbot.communications.WarMessage;
import fsm.Fsm;
import fsm.State;

public class ExplorerStateScout extends State
{
	public WarExplorerBrainController brain;
	
	public ExplorerStateScout(Fsm fsm, WarExplorerBrainController brain) 
	{
		super(fsm, brain);
		this.brain = brain;
	}

	public String execute()
	{
		
		if (brain.isBlocked()) {
			brain.setRandomHeading();
		}
		
		return WarExplorer.ACTION_MOVE;
	}
	
	public void reflexe()
	{
		this.update();
	}

	@Override
	public void update() 
	{
		for(WarMessage m : brain.mailbox) {
			if (m.getMessage() == "I'm the King !!") {
				brain.idOverlord = m.getSenderID();
			}
		}
		String s = "scout";
		
		for(WarAgentPercept p : brain.percepts) {
			if(brain.isEnemy(p) && p.getType() == WarAgentType.WarBase) {
				brain.broadcastMessageToAll("EnemyBaseSpotted", "" + p.getDistance(), "" + p.getAngle());
				s += "!!!";
			}
		}
		brain.setDebugString(s);
	}
}
