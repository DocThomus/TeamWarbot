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
	public int nbExplorerScouts;
	public int nbExplorerFarmers;
	
	public BaseStateKing(Fsm fsm, WarBaseBrainController brain) {
		super(fsm, brain);
		this.brain = brain;
		
		this.enemyBases = new HashMap<Integer, Vecteur>();
		this.nbExplorerFarmers = 0;
		this.nbExplorerScouts = 0;
	}
	
	public String execute() {
		//brain.setDebugString("I'm the King !!");
		brain.broadcastMessageToAll("I'm the King !!");
		return WarBase.ACTION_IDLE;
	}
	
	public void reflexe() {
		update();
	}
	
	public void update() {
		int nbScoutsAffectes = 0;
		for(WarMessage m : brain.mailbox)
		{
			if(m.getMessage() == "Who's the King ?") {
				brain.sendMessage(m.getSenderID(), "I'm the King !!", "");
			} else if(m.getMessage() == "WhereAreYou") {
				brain.sendMessage(m.getSenderID(), "Here", "");
			} else if(m.getMessage() == "EnemyBase?" && enemyBases.size() > 0) {
				brain.sendMessage(m.getSenderID(), "EnemyBase",
						String.valueOf(((Vecteur) enemyBases.values().toArray()[0]).getLongueur()),
						String.valueOf(((Vecteur) enemyBases.values().toArray()[0]).getAngle()));
			} else if(m.getMessage() == "EnemyBaseSpotted") {
				Vecteur a = new Vecteur(Double.parseDouble(m.getContent()[1]), Double.parseDouble(m.getContent()[2]));
				Vecteur b = new Vecteur(m.getDistance(), m.getAngle());
				Vecteur c = a.add(b);
				if(enemyBases.containsKey(Integer.parseInt(m.getContent()[0])))
				{
					enemyBases.remove(Integer.parseInt(m.getContent()[0]));
				}
				enemyBases.put(Integer.parseInt(m.getContent()[0]), c);
			} else if (m.getMessage() == "What is my role ?") {
				//TODO A �quilibrer, faire des tests.
				if (this.nbExplorerScouts + nbScoutsAffectes == 0) {
					brain.sendMessage(m.getSenderID(), "Your role is scouting", "");
				} else {
					brain.sendMessage(m.getSenderID(), "Your role is farming", "");
				}
			} /*else if (m.getMessage() == "OK, I am farmer") {
				this.nbExplorerFarmers++;
			} else if (m.getMessage() == "OK, I am scout") {
				this.nbExplorerScouts++;
			}*/
		}
	}
}
