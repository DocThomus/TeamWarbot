package base;

import java.util.HashMap;

import util.Vecteur;
import brains.WarBaseBrainController;
import edu.warbot.agents.agents.WarBase;
import edu.warbot.communications.WarMessage;
import fsm.Fsm;
import fsm.State;

public class BaseStateKing extends State
{
	public WarBaseBrainController brain;
	
	public HashMap<Integer, Vecteur> enemyBases;
	
	public BaseStateKing(Fsm fsm, WarBaseBrainController brain)
	{
		super(fsm, brain);
		this.brain = brain;
		
		enemyBases = new HashMap<Integer, Vecteur>();
	}
	
	public String execute()
	{
		reflexe();
		brain.setDebugString("I'm the King !!" + brain.myRoles("bases").toString());
		return WarBase.ACTION_IDLE;
	}
	
	public void reflexe()
	{
		update();
	}
	
	public void update()
	{
		
		for(WarMessage m : brain.mailbox)
		{
			if(m.getMessage() == "Who's the King ?")
			{
				brain.sendMessage(m.getSenderID(), "I'm the King !!", "");
			}
			
			if(m.getMessage() == "WhereAreYou")
			{
				brain.sendMessage(m.getSenderID(), "Here", "");
			}
			if(m.getMessage() == "EnemyBase?" && enemyBases.size() > 0)
			{
				brain.sendMessage(m.getSenderID(), "EnemyBase",
						String.valueOf(((Vecteur) enemyBases.values().toArray()[0]).getLongueur()),
						String.valueOf(((Vecteur) enemyBases.values().toArray()[0]).getAngle()));
			}
			if(m.getMessage() == "EnemyBase")
			{
				Vecteur a = new Vecteur(Double.parseDouble(m.getContent()[1]), Double.parseDouble(m.getContent()[2]));
				Vecteur b = new Vecteur(m.getDistance(), m.getAngle());
				Vecteur c = a.add(b);
				if(enemyBases.containsKey(Integer.parseInt(m.getContent()[0])))
				{
					enemyBases.remove(Integer.parseInt(m.getContent()[0]));
				}
				enemyBases.put(Integer.parseInt(m.getContent()[0]), c);
			}
		}
	}
}
