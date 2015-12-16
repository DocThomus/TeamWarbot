package fsm;

import edu.warbot.brains.WarBrain;
import edu.warbot.brains.capacities.Movable;

public abstract class MovableAgentState extends State
{
	public MovableAgentState(Fsm fsm, WarBrain brain)
	{
		super(fsm, brain);
	}
	
	public void reflexe()
	{
		if(((Movable) wb).isBlocked())
		{
			fsm.push(new StateUnblocking(fsm, wb));
		}
	}
}
