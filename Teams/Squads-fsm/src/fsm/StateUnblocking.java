package fsm;

import edu.warbot.agents.agents.WarExplorer;
import edu.warbot.brains.WarBrain;

public class StateUnblocking extends MovableAgentState
{
	public StateUnblocking(Fsm fsm, WarBrain wb)
	{
		super(fsm, wb);
	}
	
	public String execute()
	{
		wb.setRandomHeading();
		fsm.pop();
		return WarExplorer.ACTION_MOVE;
	}
	
	public void reflexe()
	{
		
	}
	
	public void update()
	{
		
	}
}
