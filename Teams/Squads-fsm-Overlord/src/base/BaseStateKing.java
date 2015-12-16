package base;

import java.util.ArrayList;
import java.util.HashMap;

import util.Vecteur;
import brains.WarBaseBrainController;
import edu.warbot.agents.agents.WarBase;
import edu.warbot.agents.enums.WarAgentType;
import edu.warbot.agents.percepts.WarAgentPercept;
import edu.warbot.communications.WarMessage;
import fsm.Fsm;
import fsm.State;

public class BaseStateKing extends State
{
	public WarBaseBrainController brain;
	public HashMap<Integer, Vecteur> enemyBases;
	public int nbExplorerScouts;
	public int nbExplorerFarmers;
	public int idUnitPerceptedCible;
	public boolean enemyBaseSpotted;
	public double distanceEnemyBase;
	public double angleEnemyBase;
	
	public BaseStateKing(Fsm fsm, WarBaseBrainController brain) {
		super(fsm, brain);
		this.brain = brain;
		
		this.enemyBases = new HashMap<Integer, Vecteur>();
		this.nbExplorerFarmers = 0;
		this.nbExplorerScouts = 0;
		this.idUnitPerceptedCible = -1;
		this.enemyBaseSpotted = false;
	}
	
	public String execute() {
		//brain.setDebugString("I'm the King !!");
		brain.broadcastMessageToAll("I'm the King !!", "");
		return WarBase.ACTION_IDLE;
	}
	
	public void reflexe() {
		this.update();
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
				if (!enemyBaseSpotted) {
					this.enemyBaseSpotted = true;
					Vecteur a = new Vecteur(Double.parseDouble(m.getContent()[0]), Double.parseDouble(m.getContent()[1]));
					Vecteur b = new Vecteur(m.getDistance(), m.getAngle());
					Vecteur c = a.add(b);
					this.distanceEnemyBase = c.getLongueur();
					this.angleEnemyBase = c.getAngle();
				}
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
		ArrayList<Integer> idUnitsPercepted = new ArrayList<Integer>(); 
		ArrayList<Double> distanceUnitsPercepted = new ArrayList<Double>();
		ArrayList<Double> angleUnitsPercepted = new ArrayList<Double>();
		for (WarAgentPercept p : brain.percepts) {
			if (p.getTeamName() != brain.getTeamName() && p.getType() != WarAgentType.WarExplorer) {
				idUnitsPercepted.add(p.getID());
				distanceUnitsPercepted.add(p.getDistance());
				angleUnitsPercepted.add(p.getAngle());
				brain.setDebugString("test2");
			}
		}
		if (!idUnitsPercepted.isEmpty() && idUnitsPercepted.contains(idUnitPerceptedCible)) {
			int i = idUnitsPercepted.indexOf(idUnitPerceptedCible);
			brain.setDebugString("EnemyUnit:"+ distanceUnitsPercepted.get(i) + ";" + angleUnitsPercepted.get(i));
			brain.broadcastMessageToAll("EnemyUnit", "" + distanceUnitsPercepted.get(i), "" + angleUnitsPercepted.get(i));
		} else if (!idUnitsPercepted.isEmpty()) {
			idUnitPerceptedCible = idUnitsPercepted.get(0);
			brain.setDebugString("EnemyUnit:"+ distanceUnitsPercepted.get(0) + ";" + angleUnitsPercepted.get(0));
			brain.broadcastMessageToAll("EnemyUnit", "" + distanceUnitsPercepted.get(0), "" + angleUnitsPercepted.get(0));
		}
		if (enemyBaseSpotted) {
			brain.broadcastMessageToAll("EnemyBase", "" + this.distanceEnemyBase, "" + this.angleEnemyBase);
		}
	}
}
