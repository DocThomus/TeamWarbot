package explorer;

import java.util.ArrayList;

import brains.WarExplorerBrainController;

import edu.warbot.agents.agents.WarExplorer;
import edu.warbot.agents.enums.WarAgentType;
import edu.warbot.agents.percepts.WarAgentPercept;
import edu.warbot.agents.resources.WarFood;
import fsm.Fsm;

public class ExplorerStateCollectorSearchAndTakeFoods extends ExplorerState
{
	ArrayList<WarAgentPercept> foods;
	
	public ExplorerStateCollectorSearchAndTakeFoods(Fsm fsm, WarExplorerBrainController webc)
	{
		super(fsm, webc);
	}
	
	public String execute()
	{
		webc.setDebugString("Taking foods");
		WarAgentPercept food = foods.get(0);
		for(WarAgentPercept f : foods)
		{
			if(food.getDistance() > f.getDistance())
			{
				food = f;
			}
		}
		webc.setHeading(food.getAngle());
		if(food.getDistance() <= WarFood.MAX_DISTANCE_TAKE)
		{
			return WarExplorer.ACTION_TAKE;
		}
		return WarExplorer.ACTION_MOVE;
	}
	
	public void reflexe()
	{
		this.update();
		super.reflexe();
		if(webc.isBagFull() || (!webc.isBagEmpty() && (bases != null) && (bases.size() > 0)))
		{
			fsm.pop();
			fsm.reflexe();
			return;
		}
		if(foods.size() == 0)
		{
			webc.setDebugString("Searching foods" + webc.idOverlord);
			fsm.push(new ExplorerStateWiggle(fsm, webc));
			fsm.reflexe();
			return;
		}
	}
	
	public void update()
	{
		super.update();
		foods = new ArrayList<WarAgentPercept>();
		for(WarAgentPercept p : webc.percepts)
		{
			if(p.getType() == WarAgentType.WarFood)
			{
				foods.add(p);
			}
		}
	}
}
