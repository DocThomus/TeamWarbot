package explorer;

//import java.util.ArrayList;

import java.util.ArrayList;

import brains.WarExplorerBrainController;
import fsm.Fsm;
import edu.warbot.agents.agents.WarExplorer;
//import edu.warbot.agents.agents.WarExplorer;
//import edu.warbot.agents.enums.WarAgentType;
import edu.warbot.agents.percepts.WarAgentPercept;
//import edu.warbot.agents.resources.WarFood;

public class ExplorerStateSearchFood extends ExplorerState
{
	ArrayList<WarAgentPercept> foods;
	
	public ExplorerStateSearchFood(Fsm fsm, WarExplorerBrainController webc)
	{
		super(fsm, webc);
	}
	
	public String execute()
	{
		webc.setDebugString("StateSearchFood");
		return WarExplorer.ACTION_IDLE;
	}
	
	public void reflexe()
	{
		
	}
	
	public void update()
	{
		super.update();
	}
}
