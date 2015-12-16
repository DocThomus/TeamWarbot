package explorer;

import brains.WarExplorerBrainController;
import fsm.Fsm;
import fsm.State;
import edu.warbot.agents.agents.WarExplorer;
import edu.warbot.communications.WarMessage;

public class ExplorerStateIdle extends State
{
	public WarExplorerBrainController brain;
	
	public ExplorerStateIdle(Fsm fsm, WarExplorerBrainController brain)
	{
		super(fsm, brain);
		this.brain = brain;
	}

	public String execute()
	{
		return WarExplorer.ACTION_IDLE;
	}
	
	public void reflexe()
	{
		this.update();
		if(brain.myRoles("explorers").contains("manager")) {
			fsm.pop();
			fsm.push(new ExplorerStateScout(fsm, brain));
		} else {
			fsm.pop();
			fsm.push(new ExplorerStateSearchFood(fsm, brain));
		}
	}

	public void update() 
	{		
		brain.requestRole("explorers", "scout");
		for(WarMessage m : brain.mailbox) {
			if (m.getMessage() == "I'm the King !!") {
				brain.idOverlord = m.getSenderID();
			}
		}
	}
}
