package fsm;

import edu.warbot.brains.WarBrain;

public abstract class State
{
	protected Fsm fsm;
	protected WarBrain wb;
	
	public State(Fsm fsm, WarBrain wb)
	{
		this.fsm = fsm;
		this.wb = wb;
	}
	
	public abstract String execute();
	public abstract void reflexe();
	public abstract void update();
}
