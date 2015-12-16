package base;

import brains.WarBaseBrainController;
import edu.warbot.agents.agents.WarBase;
import fsm.Fsm;
import fsm.State;

public class BaseStateElection extends State
{
	public WarBaseBrainController brain;
	public boolean elected;
	
	public BaseStateElection(Fsm fsm, WarBaseBrainController brain)
	{
		super(fsm, brain);
		this.brain = brain;
		this.elected = false;
	}
	
	public String execute()
	{
		reflexe();
		return WarBase.ACTION_IDLE;
	}
	
	public void reflexe()
	{
		update();
		if(brain.myRoles("bases").contains("manager"))
		{
			fsm.pop();
			fsm.push(new BaseStateKing(fsm, brain));
			brain.broadcastMessageToAll("I'm the King !!", "");
			return;
		}
		else
		{
			fsm.pop();
			fsm.push(new BaseStateIdle(fsm, brain));
			return;
		}
	}
	
	public void update()
	{
		if(!elected)
		{
			brain.requestRole("bases", "base");
			elected = true;
		}
	}
}
