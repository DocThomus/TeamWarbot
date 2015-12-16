package explorer;

import brains.WarExplorerBrainController;
import edu.warbot.agents.agents.WarExplorer;
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
		brain.setDebugString("Scout");
		return WarExplorer.ACTION_IDLE;
	}
	
	public void reflexe()
	{
	}

	@Override
	public void update() 
	{
		for(WarMessage m : brain.mailbox) {
			if (m.getMessage() == "I'm the King !!") {
				brain.idOverlord = m.getSenderID();
			}
		}
	}
}
