package squads.base;

import java.util.HashMap;

import squads.WarBaseBrainController;
import squads.fsm.Fsm;
import squads.fsm.State;
import util.Vecteur;

import edu.warbot.agents.agents.WarBase;
import edu.warbot.agents.enums.WarAgentType;
import edu.warbot.communications.WarMessage;

public class BaseState extends State
{
	public WarBaseBrainController wbbc;
	
	public HashMap<Integer, Vecteur> enemyBases;
	
	public boolean _alreadyCreated;
	
	public BaseState(Fsm fsm, WarBaseBrainController wbbc)
	{
		super(fsm, wbbc);
		this.wbbc = wbbc;
		
		enemyBases = new HashMap<Integer, Vecteur>();
		
		_alreadyCreated = false;
	}
	
	public String execute()
	{
		reflexe();
		
		if(enemyBases.size() > 0)
		{
			wbbc.setDebugString("EnemyBase : "
					+ String.valueOf(((Vecteur) enemyBases.values().toArray()[0]).getLongueur())
					+ " "
					+ String.valueOf(((Vecteur) enemyBases.values().toArray()[0]).getAngle()));
		}
		
		for(WarMessage message : wbbc.mailbox)
		{
			if(message.getMessage() == "WhereAreYou")
			{
				wbbc.sendMessage(message.getSenderID(), "Here", "");
			}
			if(message.getMessage() == "EnemyBase?" && enemyBases.size() > 0)
			{
				wbbc.sendMessage(message.getSenderID(), "EnemyBase",
						String.valueOf(((Vecteur) enemyBases.values().toArray()[0]).getLongueur()),
						String.valueOf(((Vecteur) enemyBases.values().toArray()[0]).getAngle()));
			}
			if(message.getMessage() == "EnemyBase")
			{
				Vecteur a = new Vecteur(Double.parseDouble(message.getContent()[1]), Double.parseDouble(message.getContent()[2]));
				Vecteur b = new Vecteur(message.getDistance(), message.getAngle());
				Vecteur c = a.add(b);
				if(enemyBases.containsKey(Integer.parseInt(message.getContent()[0])))
				{
					enemyBases.remove(Integer.parseInt(message.getContent()[0]));
				}
				enemyBases.put(Integer.parseInt(message.getContent()[0]), c);
			}
		}
		
		if (!_alreadyCreated)
		{
            wbbc.setNextAgentToCreate(WarAgentType.WarEngineer);
            _alreadyCreated = true;
            return WarBase.ACTION_CREATE;
        }
		
		if(wbbc.getHealth() < wbbc.getMaxHealth() && !wbbc.isBagEmpty())
		{
			return WarBase.ACTION_EAT;
		}
		
		return WarBase.ACTION_IDLE;
	}
	
	public void reflexe()
	{
		
	}

	public void update()
	{
		
	}
}