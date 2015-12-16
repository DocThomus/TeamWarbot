package base;

import brains.WarBaseBrainController;
import edu.warbot.agents.agents.WarBase;
import fsm.Fsm;
import fsm.State;

public class BaseStateIdle extends State
{
	public WarBaseBrainController brain;
	
	public BaseStateIdle(Fsm fsm, WarBaseBrainController brain)
	{
		super(fsm, brain);
		this.brain = brain;
	}
	
	public String execute()
	{
		brain.setDebugString("Idle");
		return WarBase.ACTION_IDLE;
	}
	
	public void reflexe()
	{
		
	}
	
	public void update()
	{
		
	}
}
