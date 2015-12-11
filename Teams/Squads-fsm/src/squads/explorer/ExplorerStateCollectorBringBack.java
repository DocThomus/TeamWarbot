package squads.explorer;

import squads.WarExplorerBrainController;
import squads.fsm.Fsm;

import edu.warbot.agents.actions.constants.ControllableActions;
import edu.warbot.agents.agents.WarExplorer;
import edu.warbot.agents.enums.WarAgentType;
import edu.warbot.agents.percepts.WarAgentPercept;
import edu.warbot.communications.WarMessage;

public class ExplorerStateCollectorBringBack extends ExplorerState
{
	public ExplorerStateCollectorBringBack(Fsm fsm, WarExplorerBrainController web)
	{
		super(fsm, web);
	}
	
	public String execute()
	{
		if((bases != null) && (bases.size() > 0))
		{
			web.setDebugString("Giving foods to base");
			WarAgentPercept base = bases.get(0);
			for(WarAgentPercept b : bases)
			{
				if(base.getDistance() > b.getDistance())
				{
					base = b;
				}
			}
			if(base.getDistance() <= ControllableActions.MAX_DISTANCE_GIVE)
			{
				web.setIdNextAgentToGive(base.getID());
				return WarExplorer.ACTION_GIVE;
			}
			else
			{
				web.setHeading(base.getAngle());
				return WarExplorer.ACTION_MOVE;
			}
		}
		else
		{
			web.setDebugString("Bringing back foods to base");
			web.broadcastMessageToAgentType(WarAgentType.WarBase, "WhereAreYou","");
			for(WarMessage message : web.mailbox)
			{
				if(message.getSenderType() == WarAgentType.WarBase)
				{
					web.setHeading(message.getAngle());
				}
			}
			return WarExplorer.ACTION_MOVE;
		}
	}
	
	public void reflexe()
	{
		update();
		super.reflexe();
		if(web.isBagEmpty())
		{
			web.setHeading((web.getHeading()+180) / 360);
			fsm.push(new ExplorerStateCollectorSearchAndTakeFoods(fsm, web));
			fsm.reflexe();
			return;
		}
	}
	
	public void update()
	{
		super.update();
	}
}
