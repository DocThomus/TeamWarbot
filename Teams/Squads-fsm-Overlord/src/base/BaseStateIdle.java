package base;

import brains.WarBaseBrainController;
import edu.warbot.agents.agents.WarBase;
import fsm.Fsm;
import fsm.State;

public class BaseStateIdle extends State
{
	public WarBaseBrainController wbbc;
	
	public BaseStateIdle(Fsm fsm, WarBaseBrainController wbbc)
	{
		super(fsm, wbbc);
		this.wbbc = wbbc;
	}
	
	public String execute()
	{
		wbbc.setDebugString("I'm a shit...");
		return WarBase.ACTION_IDLE;
	}
	
	public void reflexe()
	{
		
	}
	
	public void update()
	{
		
	}
}
