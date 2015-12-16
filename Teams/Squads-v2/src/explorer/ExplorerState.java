package explorer;

import java.util.ArrayList;

import brains.WarExplorerBrainController;

import edu.warbot.agents.enums.WarAgentType;
import edu.warbot.agents.percepts.WarAgentPercept;
import fsm.Fsm;
import fsm.MovableAgentState;

public abstract class ExplorerState extends MovableAgentState
{
	public WarExplorerBrainController webc;
	
	ArrayList<WarAgentPercept> bases;
	
	public ExplorerState(Fsm fsm, WarExplorerBrainController webc)
	{
		super(fsm, webc);
		this.webc = webc;
	}
	
	// Mettre traitement commun à tous les explorer
	public void update()
	{
		bases = new ArrayList<WarAgentPercept>(webc.getPerceptsAlliesByType(WarAgentType.WarBase));
		for(WarAgentPercept p : webc.percepts)
		{
			if(webc.isEnemy(p) && p.getType() == WarAgentType.WarBase)
			{
				webc.broadcastMessageToAll("EnemyBaseSpotted",
            			String.valueOf(p.getDistance()), String.valueOf(p.getAngle()));
			}
		}
	}
}