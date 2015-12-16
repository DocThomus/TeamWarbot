package explorer;

import brains.WarExplorerBrainController;

import edu.warbot.agents.actions.constants.ControllableActions;
import edu.warbot.agents.agents.WarExplorer;
import edu.warbot.agents.enums.WarAgentType;
import edu.warbot.agents.percepts.WarAgentPercept;
import edu.warbot.communications.WarMessage;
import fsm.Fsm;

public class ExplorerStateCollectorBringBack extends ExplorerState
{
	public ExplorerStateCollectorBringBack(Fsm fsm, WarExplorerBrainController webc)
	{
		super(fsm, webc);
	}
	
	public String execute()
	{
		if((bases != null) && (bases.size() > 0))
		{
			webc.setDebugString("Giving foods to base");
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
				webc.setIdNextAgentToGive(base.getID());
				return WarExplorer.ACTION_GIVE;
			}
			else
			{
				webc.setHeading(base.getAngle());
				return WarExplorer.ACTION_MOVE;
			}
		}
		else
		{
			webc.setDebugString("Bringing back foods to base");
			webc.sendMessage(webc.idOverlord, "WhereAreYou","");
			for(WarMessage message : webc.mailbox)
			{
				if(message.getSenderType() == WarAgentType.WarBase)
				{
					webc.setHeading(message.getAngle());
				}
			}
			return WarExplorer.ACTION_MOVE;
		}
	}
	
	public void reflexe()
	{
		this.update();
		super.reflexe();
		if(webc.isBagEmpty())
		{
			webc.setHeading((webc.getHeading()+180) % 360);
			fsm.push(new ExplorerStateCollectorSearchAndTakeFoods(fsm, webc));
			fsm.reflexe();
			return;
		}
	}
	
	public void update()
	{
		super.update();
	}
}
