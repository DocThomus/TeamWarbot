package explorer;

import brains.WarExplorerBrainController;
import fsm.Fsm;
import fsm.State;
import edu.warbot.agents.agents.WarExplorer;
//import edu.warbot.agents.enums.WarAgentType;
//import edu.warbot.communications.WarMessage;

public class ExplorerStateIdle extends State
{
	public WarExplorerBrainController brain;
	
	public ExplorerStateIdle(Fsm fsm, WarExplorerBrainController brain)
	{
		super(fsm, brain);
	}

	public String execute()
	{
		brain.setDebugString("test");
		return WarExplorer.ACTION_IDLE;
	}
	
	public void reflexe()
	{
//		this.update();
//		if(brain.idOverlord != -1) {
//			brain.sendMessage(brain.idOverlord, "What is my role ?", "");
//		}
//		for(WarMessage m : brain.mailbox)
//		{
//			if (m.getSenderType() == WarAgentType.WarBase && m.getMessage().equals("Your role is farming")) {
//				brain.sendMessage(m.getSenderID(), "OK, I am farmer", "");
//				fsm.pop();
//				fsm.push(new ExplorerStateSearchFood(fsm, brain));
//			} else if (m.getSenderType() == WarAgentType.WarBase && m.getMessage().equals("Your role is scouting")) {
//				brain.sendMessage(m.getSenderID(), "OK, I am scout", "");
//				fsm.pop();
//				fsm.push(new ExplorerStateScout(fsm, brain));
//			}
//		}
	}

	public void update() 
	{		
		brain.setDebugString("test");
//		for(WarMessage m : brain.mailbox)
//		{
//			if(m.getSenderType() == WarAgentType.WarBase && m.getMessage().equals("I'm the King !!"))
//			{
//				brain.idOverlord = m.getSenderID();
//			}
//		}
	}
}
