package base;

import brains.WarBaseBrainController;
import edu.warbot.agents.agents.WarBase;
import edu.warbot.agents.enums.WarAgentType;
import edu.warbot.agents.percepts.WarAgentPercept;
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
		//brain.setDebugString("Idle");
		return WarBase.ACTION_IDLE;
	}
	
	public void reflexe()
	{
		
	}
	
	public void update()
	{
		for (WarAgentPercept p : brain.percepts) {
			if (p.getTeamName() != brain.getTeamName() && p.getType() != WarAgentType.WarExplorer) {
				brain.broadcastMessageToAll("EnemyUnit", "" + p.getDistance(), "" + p.getAngle());
			}
		}
	}
}
