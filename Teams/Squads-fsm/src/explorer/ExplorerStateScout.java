package explorer;

import util.Util;
import brain.WarExplorerBrainController;
import edu.warbot.agents.agents.WarExplorer;
import edu.warbot.agents.enums.WarAgentType;
import edu.warbot.agents.percepts.WarAgentPercept;
import fsm.Fsm;
import fsm.MovableAgentState;

public class ExplorerStateScout extends MovableAgentState
{
	public WarExplorerBrainController webc;
	
	int ticks;
	
	public ExplorerStateScout(Fsm fsm, WarExplorerBrainController webc)
	{
		super(fsm, webc);
		this.webc = webc;
		
		ticks = 0;
	}
	
	public String execute()
	{
		if(webc.myRoles("scouts").contains("scoutright"))
		{
			webc.setHeading(0);
		}
		else
		{
			webc.setHeading(180);
		}
		
		return WarExplorer.ACTION_MOVE;
	}

	
	public void reflexe()
	{
		if(isBlockedByNoAgent())
		{
			webc.requestRole("walldiscovered", "scout");
			if(webc.myRoles("walldiscovered").contains("manager"))
			{
				webc.leaveGroup("scouts");
				fsm.pop();
				fsm.push(new ExplorerStateCollectorBringBack(fsm, webc));
				fsm.reflexe();
				return;
			}
			else
			{
				webc.setHeading(90);
				webc.requestRole("searchenemybase", "scout");
				fsm.pop();
				fsm.push(new ExplorerStateScoutSearchBase(fsm, webc));
				fsm.reflexe();
				return;
			}
		}
		super.reflexe();
	}
	
	public void update()
	{
		for(WarAgentPercept p : webc.getPerceptsEnemies())
		{
			if(p.getType() == WarAgentType.WarBase)
			{
				webc.broadcastMessage("bases", "manager", "EnemyBase", String.valueOf(p.getID()),
            			String.valueOf(p.getDistance()), String.valueOf(p.getAngle()));
			}
		}
	}
	
	private boolean isBlockedByNoAgent()
	{
		if(webc.isBlocked() && (webc.percepts == null || webc.percepts.size() == 0 || Util.lePlusProche(webc.percepts).getDistance() >= 1))
		{
			return true;
		}
		return false;
	}

}
