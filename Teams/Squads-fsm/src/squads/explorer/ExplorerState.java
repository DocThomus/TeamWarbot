package squads.explorer;

//import edu.warbot.agents.agents.WarExplorer;
//import edu.warbot.brains.WarBrain;
import java.util.ArrayList;

import squads.fsm.Fsm;
import squads.fsm.State;

import edu.warbot.agents.enums.WarAgentType;
import edu.warbot.agents.percepts.WarAgentPercept;
import edu.warbot.brains.brains.WarExplorerBrain;
import edu.warbot.communications.WarMessage;

public abstract class ExplorerState extends State
{
	public WarExplorerBrain web;
	ArrayList<WarMessage> boiteAuxLettres;
	ArrayList<WarAgentPercept> bases;
	ArrayList<WarAgentPercept> foods;
	
	public ExplorerState(Fsm fsm, WarExplorerBrain web)
	{
		super(fsm);
		this.web = web;
	}
	
	public void reflexe()
	{
		boiteAuxLettres = new ArrayList<WarMessage>(web.getMessages());
		ArrayList<WarAgentPercept> percepts = new ArrayList<WarAgentPercept>(web.getPercepts());
		foods = new ArrayList<WarAgentPercept>();
		for(WarAgentPercept p : percepts)
		{
			if(p.getType() == WarAgentType.WarFood)
			{
				foods.add(p);
			}
		}
		bases = new ArrayList<WarAgentPercept>(web.getPerceptsAlliesByType(WarAgentType.WarBase));
		if(web.isBlocked())
		{
			web.setRandomHeading();
		}
	}
}
