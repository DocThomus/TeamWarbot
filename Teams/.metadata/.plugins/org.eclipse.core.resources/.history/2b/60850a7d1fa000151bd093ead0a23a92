package squads.fsm;

import edu.warbot.brains.WarBrain;

public abstract class State
{
	public Fsm fsm;
	public WarBrain wb;
	
	public State(Fsm fsm, WarBrain wb)
	{
		this.fsm = fsm;
		this.wb = wb;
	}
	
	public abstract String execute();
	public abstract void reflexe();
}
