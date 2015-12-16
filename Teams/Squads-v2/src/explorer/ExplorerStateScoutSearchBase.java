package explorer;

import util.Util;
import brains.WarExplorerBrainController;
import edu.warbot.agents.agents.WarExplorer;
import edu.warbot.agents.enums.WarAgentType;
import edu.warbot.agents.percepts.WarAgentPercept;
import fsm.Fsm;

public class ExplorerStateScoutSearchBase extends ExplorerState
{
	public WarExplorerBrainController webc;
	
	int ticksgene;
	int ticks;
	double headingmem;
	boolean wasBlocked;
	
	public ExplorerStateScoutSearchBase(Fsm fsm, WarExplorerBrainController webc)
	{
		super(fsm, webc);
		this.webc = webc;
		
		ticks = 0;
		headingmem = 0;
		wasBlocked = false;
	}
	
	public String execute()
	{
		webc.setDebugString("searchenemybase");
		if(webc.getHeading() == 0 || webc.getHeading() == 180)
		{
			ticks++;
			if(ticks%80 == 0)
			{
				ticks=0;
				webc.setHeading(headingmem+180);
			}
		}
		
		return WarExplorer.ACTION_MOVE;
	}

	
	public void reflexe()
	{
		this.update();
		if(wasBlocked == true)
		{
			wasBlocked = false;
			webc.setHeading(headingmem);
		}
		else if(isBlockedByNoAgent())
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
			wasBlocked = true;
			headingmem = webc.getHeading();
			super.reflexe();
		}
	}
	
	public void update()
	{
		super.update();
		for(WarAgentPercept p : webc.percepts)
		{
			if(webc.isEnemy(p) && p.getType() == WarAgentType.WarBase)
			{
				webc.broadcastMessageToAll("EnemyBaseSpotted",
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
