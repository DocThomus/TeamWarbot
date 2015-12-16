package base;

import java.util.HashMap;

import util.Vecteur;
import brain.WarBaseBrainController;
import edu.warbot.agents.agents.WarBase;
import edu.warbot.communications.WarMessage;
import fsm.Fsm;
import fsm.State;

public class BaseStateKing extends State
{
	public WarBaseBrainController wbbc;
	
	public HashMap<Integer, Vecteur> enemyBases;
	
	public BaseStateKing(Fsm fsm, WarBaseBrainController wbbc)
	{
		super(fsm, wbbc);
		this.wbbc = wbbc;
		
		enemyBases = new HashMap<Integer, Vecteur>();
	}
	
	public String execute()
	{
		reflexe();
		wbbc.setDebugString("I'm the King !!" + enemyBases.toString());
		return WarBase.ACTION_IDLE;
	}
	
	public void reflexe()
	{
		update();
	}
	
	public void update()
	{
		
		for(WarMessage m : wbbc.mailbox)
		{
			if(m.getMessage() == "Who's the King ?")
			{
				wbbc.sendMessage(m.getSenderID(), "I'm the King !!", "");
			}
			
			if(m.getMessage() == "WhereAreYou")
			{
				wbbc.sendMessage(m.getSenderID(), "Here", "");
			}
			if(m.getMessage() == "EnemyBase?" && enemyBases.size() > 0)
			{
				wbbc.sendMessage(m.getSenderID(), "EnemyBase",
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
