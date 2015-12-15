package explorer;

import brains.WarExplorerBrainController;
import edu.warbot.agents.agents.WarExplorer;
import fsm.Fsm;

//import edu.warbot.agents.actions.constants.ControllableActions;
//import edu.warbot.agents.agents.WarExplorer;
//import edu.warbot.agents.enums.WarAgentType;
//import edu.warbot.agents.percepts.WarAgentPercept;
//import edu.warbot.communications.WarMessage;

public class ExplorerStateBringFood extends ExplorerState
{
	public ExplorerStateBringFood(Fsm fsm, WarExplorerBrainController webc)
	{
		super(fsm, webc);
	}
	
	public String execute()
	{
		webc.setDebugString("StateBringFood");
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
