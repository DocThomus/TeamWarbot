package squads.explorer;

//import java.util.ArrayList;

import java.util.ArrayList;

import squads.WarExplorerBrainController;
import squads.fsm.Fsm;
import edu.warbot.agents.agents.WarExplorer;
import edu.warbot.agents.enums.WarAgentType;
import edu.warbot.agents.percepts.WarAgentPercept;
import edu.warbot.agents.resources.WarFood;

public class ExplorerStateCollectorSearchAndTakeFoods extends ExplorerState
{
	ArrayList<WarAgentPercept> foods;
	
	public ExplorerStateCollectorSearchAndTakeFoods(Fsm fsm, WarExplorerBrainController web)
	{
		super(fsm, web);
	}
	
	public String execute()
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
		if(food.getDistance() <= WarFood.MAX_DISTANCE_TAKE)
		{
			return WarExplorer.ACTION_TAKE;
		}
		return WarExplorer.ACTION_MOVE;
	}
	
	public void reflexe()
	{
		update();
		super.reflexe();
		if(web.isBagFull() || (!web.isBagEmpty() && (bases != null) && (bases.size() > 0)))
		{
			fsm.pop();
			fsm.reflexe();
			return;
		}
		if(foods.size() == 0)
		{
			web.setDebugString("Searching foods");
			fsm.push(new ExplorerStateWiggle(fsm, web));
			fsm.reflexe();
			return;
		}
	}
	
	public void update()
	{
		
		foods = new ArrayList<WarAgentPercept>();
		for(WarAgentPercept p : web.percepts)
		{
			if(p.getType() == WarAgentType.WarFood)
			{
				foods.add(p);
			}
		}
	}
}