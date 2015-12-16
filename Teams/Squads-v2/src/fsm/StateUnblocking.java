package fsm;

import edu.warbot.agents.agents.WarExplorer;
import edu.warbot.brains.WarBrain;

public class StateUnblocking extends MovableAgentState
{
	int ticks;
	
	public StateUnblocking(Fsm fsm, WarBrain wb)
	{
		super(fsm, wb);
		
		ticks = 0;
	}
	
	public String execute()
	{
		ticks++;
		wb.setRandomHeading();
		if(ticks >= 3)
		{
			fsm.pop();
			fsm.reflexe();
		}
		return WarExplorer.ACTION_MOVE;
	}
	
	public void reflexe()
	{
		
	}
	
	public void update()
	{
		
	}
}
