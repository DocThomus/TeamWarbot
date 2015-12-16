package explorer;

import util.Util;
import brains.WarExplorerBrainController;
import edu.warbot.agents.agents.WarExplorer;
import edu.warbot.agents.enums.WarAgentType;
import edu.warbot.agents.percepts.WarAgentPercept;
import fsm.Fsm;

public class ExplorerStateScout extends ExplorerState
{
	public WarExplorerBrainController webc;
	
	int ticks;
	double headingmem;
	boolean wasBlocked;
	
	public ExplorerStateScout(Fsm fsm, WarExplorerBrainController webc)
	{
		super(fsm, webc);
		this.webc = webc;
		
		ticks = 0;
		headingmem = 0;
		wasBlocked = false;
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
		this.update();
		
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
		else if (webc.isBlocked())
		{
			wasBlocked = true;
			headingmem = webc.getHeading();
			super.reflexe();
		}
	}
	
	public void update()
	{
		super.update();
		for(WarAgentPercept p : webc.getPerceptsEnemies())
		{
			if(p.getType() == WarAgentType.WarBase)
			{
				webc.broadcastMessage("bases", "manager", "EnemyBaseSpotted",
            			String.valueOf(p.getDistance()), String.valueOf(p.getAngle()));
			}
		}
	}
	
	private boolean isBlockedByNoAgent()
	{
		if(webc.isBlocked() && (webc.percepts == null || webc.percepts.size() == 0 || Util.lePlusProche(webc.percepts).getDistance() > 3))
		{
			return true;
		}
		return false;
	}

}
