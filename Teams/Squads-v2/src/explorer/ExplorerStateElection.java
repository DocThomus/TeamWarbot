package explorer;

import brains.WarExplorerBrainController;
import edu.warbot.agents.agents.WarExplorer;
import edu.warbot.agents.enums.WarAgentType;
import edu.warbot.communications.WarMessage;
import fsm.Fsm;

public class ExplorerStateElection extends ExplorerState
{
	public WarExplorerBrainController webc;
	
	int ticks;
	
	public ExplorerStateElection(Fsm fsm, WarExplorerBrainController webc)
	{
		super(fsm, webc);
		this.webc = webc;
		
		ticks = 0;
	}
	
	public String execute()
	{
		reflexe();
		ticks++;
		if(ticks >= 5)
		{
			webc.broadcastMessage("bases", "manager", "Who's the King ?", "");
		}
		
		return WarExplorer.ACTION_IDLE;
	}
	
	public void reflexe()
	{
		this.update();
		if(webc.idOverlord != -1)
		{
			webc.requestRole("scoutright","scoutright");
			webc.requestRole("scoutleft","scoutleft");
			if(webc.myRoles("scoutright").contains("manager"))
			{
				webc.leaveGroup("scoutleft");
				webc.requestRole("scouts","scoutright");
				fsm.pop();
				fsm.push(new ExplorerStateScout(fsm, webc));
				fsm.reflexe();
				return;
			}
			else if(webc.myRoles("scoutleft").contains("manager"))
			{
				webc.leaveGroup("scoutright");
				webc.requestRole("scouts","scoutleft");
				fsm.pop();
				fsm.push(new ExplorerStateScout(fsm, webc));
				fsm.reflexe();
				return;
			}
			else
			{
				webc.leaveGroup("scouts");
			}
			webc.leaveGroup("explorers");
			fsm.pop();
			fsm.push(new ExplorerStateCollectorBringBack(fsm, webc));
			fsm.reflexe();
			return;
		}
	}
	
	public void update()
	{
		super.update();
		for(WarMessage m : webc.mailbox)
		{
			webc.setDebugString(m.getMessage());
			if(m.getSenderType() == WarAgentType.WarBase && m.getMessage().equals("I'm the King !!"))
			{
				webc.idOverlord = m.getSenderID();
			}
		}
	}
}