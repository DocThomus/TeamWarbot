package squads.explorer;

import java.util.ArrayList;

import squads.WarExplorerBrainController;
import squads.fsm.Fsm;
import squads.fsm.MovableAgentState;

import edu.warbot.agents.enums.WarAgentType;
import edu.warbot.agents.percepts.WarAgentPercept;

public abstract class ExplorerState extends MovableAgentState
{
	public WarExplorerBrainController web;
	
	ArrayList<WarAgentPercept> bases;
	
	public ExplorerState(Fsm fsm, WarExplorerBrainController web)
	{
		super(fsm, web);
		this.web = web;
	}
	
	// Mettre traitement commun à tous les explorer
	public void update()
	{
		bases = new ArrayList<WarAgentPercept>(web.getPerceptsAlliesByType(WarAgentType.WarBase));
	}
}
