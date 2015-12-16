package explorer;

import brain.WarExplorerBrainController;
import edu.warbot.agents.agents.WarExplorer;
import edu.warbot.agents.enums.WarAgentType;
import edu.warbot.communications.WarMessage;
import fsm.Fsm;
import fsm.State;

public class ExplorerStateElection extends State
{
	public WarExplorerBrainController webc;
	
	public ExplorerStateElection(Fsm fsm, WarExplorerBrainController webc)
	{
		super(fsm, webc);
		this.webc = webc;
	}
	
	public String execute()
	{
		reflexe();
		return WarExplorer.ACTION_IDLE;
	}
	
	public void reflexe()
	{
		update();
		if(webc.idOfKing != -1)
		{
			webc.leaveGroup("explorers");
			fsm.pop();
			fsm.push(new ExplorerStateCollectorBringBack(fsm, webc));
			fsm.reflexe();
			return;
		}
	}
	
	public void update()
	{
		for(WarMessage m : webc.mailbox)
		{
			webc.setDebugString(m.getMessage());
			if(m.getSenderType() == WarAgentType.WarBase && m.getMessage().equals("I'm the King !!"))
			{
				webc.idOfKing = m.getSenderID();
			}
		}
	}
}