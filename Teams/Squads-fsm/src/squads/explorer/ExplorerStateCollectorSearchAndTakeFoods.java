package squads.explorer;

//import java.util.ArrayList;

import squads.fsm.Fsm;
import edu.warbot.agents.agents.WarExplorer;
import edu.warbot.agents.percepts.WarAgentPercept;
import edu.warbot.brains.brains.WarExplorerBrain;

public class ExplorerStateCollectorSearchAndTakeFoods extends ExplorerState
{
	public ExplorerStateCollectorSearchAndTakeFoods(Fsm fsm, WarExplorerBrain web)
	{
		super(fsm, web); 
	}
	
	public String execute()
	{
		reflexe();
		if(!fsm.getCurrentState().getClass().toString().equals(this.getClass().toString()))
		{
			return fsm.execute();
		}
		web.setDebugString("Searching foods pd");
		if(foods.size() > 0)
		{
			web.setDebugString("Taking foods");
			WarAgentPercept food = foods.get(0);
			for(WarAgentPercept f : foods)
			{
				if(food.getDistance() > f.getDistance())
				{
					food = f;
				}
			}
			web.setHeading(food.getAngle());
			if(food.getDistance() < 1)
			{
				return WarExplorer.ACTION_TAKE;
			}
			return WarExplorer.ACTION_MOVE;
		}
		else
		{
			fsm.push(new ExplorerStateWiggle(fsm, web));
			return fsm.execute();
		}
	}
	
	public void reflexe()
	{
		super.reflexe();
		if(web.isBagFull() || (!web.isBagEmpty() && (bases != null) && (bases.size() > 0)))
		{
			fsm.pop();
		}
	}
}
