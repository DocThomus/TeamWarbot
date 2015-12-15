package explorer;

import java.util.ArrayList;

import brains.WarExplorerBrainController;
import fsm.Fsm;
import fsm.MovableAgentState;

import edu.warbot.agents.enums.WarAgentType;
import edu.warbot.agents.percepts.WarAgentPercept;

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
	}
}