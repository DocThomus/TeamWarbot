package base;

import brain.WarBaseBrainController;
import edu.warbot.agents.agents.WarBase;
//import edu.warbot.agents.enums.WarAgentType;
import fsm.Fsm;
import fsm.State;

public class BaseStateElection extends State
{
	public WarBaseBrainController wbbc;
	public boolean election;
	
	public BaseStateElection(Fsm fsm, WarBaseBrainController wbbc)
	{
		super(fsm, wbbc);
		this.wbbc = wbbc;
		this.election = false;
	}
	
	public String execute()
	{
		reflexe();
		return WarBase.ACTION_IDLE;
	}
	
	public void reflexe()
	{
		update();
		if(wbbc.myRoles("bases").contains("manager"))
		{
			fsm.pop();
			fsm.push(new BaseStateKing(fsm, wbbc));
			wbbc.broadcastMessageToAll("I'm the King !!", "");
			return;
		}
		else
		{
			fsm.pop();
			fsm.push(new BaseStateShit(fsm, wbbc));
			return;
		}
	}
	
	public void update()
	{
		if(!election)
		{
			wbbc.requestRole("bases", "base");
			election = true;
		}
	}
}
