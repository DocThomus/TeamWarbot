package explorer;

import util.Util;
import brain.WarExplorerBrainController;
import edu.warbot.agents.agents.WarExplorer;
import edu.warbot.agents.enums.WarAgentType;
import edu.warbot.agents.percepts.WarAgentPercept;
import fsm.Fsm;
import fsm.MovableAgentState;

public class ExplorerStateScoutSearchBase extends MovableAgentState
{
	public WarExplorerBrainController webc;
	
	int ticksgene;
	int ticks;
	double headingmem;
	
	public ExplorerStateScoutSearchBase(Fsm fsm, WarExplorerBrainController webc)
	{
		super(fsm, webc);
		this.webc = webc;
		
		ticks = 0;
		headingmem = 0;
	}
	
	public String execute()
	{
		webc.setDebugString("searchenemybase");
		if(webc.getHeading() == 0 || webc.getHeading() == 180)
		{
			ticks++;
			if(ticks%35 == 0)
			{
				ticks=0;
				webc.setHeading(headingmem+180);
			}
		}
		
		return WarExplorer.ACTION_MOVE;
	}

	
	public void reflexe()
	{
		if(isBlockedByNoAgent())
		{
			ticks = 0;
			headingmem = webc.getHeading();
			if(webc.myRoles("scouts").contains("scoutright"))
			{
				webc.setHeading(180);
			}
			else
			{
				webc.setHeading(0);
			}
		}
		else if (webc.isBlocked())
		{
			super.reflexe();
		}
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
