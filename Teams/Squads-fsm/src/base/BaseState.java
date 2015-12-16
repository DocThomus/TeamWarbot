package base;

import java.util.HashMap;
import java.util.HashSet;

import brain.WarBaseBrainController;

import util.Vecteur;

import edu.warbot.agents.agents.WarBase;
import edu.warbot.agents.enums.WarAgentType;
import edu.warbot.communications.WarMessage;
import fsm.Fsm;
import fsm.State;

public class BaseState extends State
{
	public WarBaseBrainController wbbc;
	
	public HashMap<Integer, Vecteur> enemyBases;
	public HashSet<Integer> bases;
	
	public boolean _alreadyCreated;
	
	public String debugElec;
	public boolean election;
	
	public BaseState(Fsm fsm, WarBaseBrainController wbbc)
	{
		super(fsm, wbbc);
		this.wbbc = wbbc;
		
		enemyBases = new HashMap<Integer, Vecteur>();
		bases = new HashSet<Integer>();
		
		_alreadyCreated = false;
		
		debugElec = "";
		election = false;
	}
	
	public String execute()
	{
		reflexe();
		
		//wbbc.setDebugString(debugElec + bases.toString() + wbbc.broadcastMessage("bases", "base", "", "").name());
		
		if(wbbc.myRoles("bases").contains("manager"))
		{
			wbbc.setDebugString("I'm the King !!");
		}
		else
		{
			wbbc.setDebugString("I'm a shit...");
		}
		
//		if (!_alreadyCreated)
//		{
//            wbbc.setNextAgentToCreate(WarAgentType.WarEngineer);
//            _alreadyCreated = true;
//            return WarBase.ACTION_CREATE;
//        }
		
		if(wbbc.getHealth() < wbbc.getMaxHealth() && !wbbc.isBagEmpty())
		{
			return WarBase.ACTION_EAT;
		}
		
		return WarBase.ACTION_IDLE;
	}
	
	public void reflexe()
	{
		update();
	}

	public void update()
	{
		if(enemyBases.size() > 0)
		{
			wbbc.setDebugString("EnemyBase : "
					+ String.valueOf(((Vecteur) enemyBases.values().toArray()[0]).getLongueur())
					+ " "
					+ String.valueOf(((Vecteur) enemyBases.values().toArray()[0]).getAngle()));
		}
		
		//wbbc.requestRole(InGameTeam.DEFAULT_GROUP_NAME, "base");
		if(election == false)
		{
			wbbc.requestRole("bases", "base");
			debugElec = "I'am the KING !!" + "//";
			election = true;
		}
		else
		{
			debugElec = "I'am a shit... !!" + "//";
		}
		
		for(String s1 : wbbc.myGroups())
		{
			debugElec += s1 + "->";
			for(String s2 : wbbc.myRoles(s1))
			{
				debugElec += s2 + ";";
			}
			debugElec += ";";
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
			if(message.getSenderType() == WarAgentType.WarBase)
			{
				bases.add(message.getSenderID());
			}
		}
		wbbc.setDebugString(debugElec + "//" +bases.toString());
	}
}
