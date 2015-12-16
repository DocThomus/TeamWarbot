package explorer;

import java.util.ArrayList;
import brains.WarExplorerBrainController;
import fsm.Fsm;
import fsm.State;
import edu.warbot.agents.agents.WarExplorer;
import edu.warbot.agents.percepts.WarAgentPercept;

public class ExplorerStateSearchFood extends State
{
	public WarExplorerBrainController brain;
	ArrayList<WarAgentPercept> foods;
	
	public ExplorerStateSearchFood(Fsm fsm, WarExplorerBrainController brain)
	{
		super(fsm, brain);
	}
	
	public String execute()
	{
		brain.setDebugString("StateSearchFood");
		return WarExplorer.ACTION_IDLE;
	}
	
	public void reflexe()
	{
		
	}
	
	public void update()
	{
		
	}
}
