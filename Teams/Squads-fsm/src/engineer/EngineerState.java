package engineer;

import java.util.ArrayList;

import brain.WarEngineerBrainController;

import edu.warbot.agents.WarBuilding;
import edu.warbot.agents.agents.WarEngineer;
import edu.warbot.agents.agents.WarExplorer;
import edu.warbot.agents.enums.WarAgentType;
import edu.warbot.agents.percepts.WarAgentPercept;
import edu.warbot.communications.WarMessage;
import fsm.Fsm;
import fsm.MovableAgentState;

public class EngineerState extends MovableAgentState
{
	WarEngineerBrainController webc;
	
	public EngineerState(Fsm fsm, WarEngineerBrainController webc)
	{
		super(fsm, webc);
		this.webc = webc;
	}
	
	public String execute()
	{
		ArrayList<WarAgentPercept> bases = new ArrayList<WarAgentPercept>(webc.getPerceptsAlliesByType(WarAgentType.WarTurret));
		if(bases.size() > 0)
		{
			webc.setHeading(bases.get(0).getAngle());
			if(bases.get(0).getDistance() <= WarBuilding.MAX_DISTANCE_BUILD && bases.get(0).getHealth() < bases.get(0).getMaxHealth())
			{
				webc.setIdNextBuildingToRepair(bases.get(0).getID());
				webc.setDebugString("Repairing : " + webc.getIdNextBuildingToRepair());
				return WarEngineer.ACTION_REPAIR;
			}
			else
			{
				return WarEngineer.ACTION_MOVE;
			}
		}
		else
		{
			webc.setDebugString("Bringing back foods to base");
			webc.broadcastMessageToAgentType(WarAgentType.WarBase, "WhereAreYou","");
			for(WarMessage message : webc.mailbox)
			{
				if(message.getSenderType() == WarAgentType.WarBase)
				{
					webc.setHeading(message.getAngle());
				}
			}
			return WarExplorer.ACTION_MOVE;
		}
//		webc.setNextAgentToCreate(WarAgentType.WarTurret);
//        return WarEngineer.ACTION_CREATE;
	}
	
	public void reflexe()
	{
		
	}
	
	public void update()
	{
		
	}
}
