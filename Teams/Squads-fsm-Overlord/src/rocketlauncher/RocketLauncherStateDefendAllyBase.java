package rocketlauncher;

import util.Vecteur;
import edu.warbot.agents.agents.WarRocketLauncher;
import edu.warbot.brains.WarBrain;
import edu.warbot.communications.WarMessage;
import fsm.Fsm;
import fsm.State;
import brains.WarRocketLauncherBrainController;

public class RocketLauncherStateDefendAllyBase extends State {
	
	private WarRocketLauncherBrainController brain;
	private boolean enemyUnitSpotted;
	private boolean enemyBaseSpotted;

	public RocketLauncherStateDefendAllyBase(Fsm fsm, WarBrain brain) {
		super(fsm, brain);
		this.brain = (WarRocketLauncherBrainController) brain;
		this.enemyUnitSpotted = true;
		this.enemyBaseSpotted = false;
	}

	@Override
	public String execute() {
		brain.setDebugString("Defend");
		
		brain.broadcastMessageToAll("RLHere", "");
		
		brain.setHeading(brain.angleEnemyUnit);
		
		if (brain.isBlocked()) {
			brain.setRandomHeading();
		}
		
		if (brain.distanceEnemyUnit > 60) {
			return WarRocketLauncher.ACTION_MOVE;
		} else {
			if (brain.isReloaded())
	             return WarRocketLauncher.ACTION_FIRE;
	         else if (brain.isReloading())
	             return WarRocketLauncher.ACTION_IDLE;
	         else
	             return WarRocketLauncher.ACTION_RELOAD;
		}
	}

	@Override
	public void reflexe() {
		this.update();
		
		if (!enemyUnitSpotted) {
			if (!enemyBaseSpotted) {
				fsm.pop();
				fsm.reflexe();
			} else {
				fsm.pop();
				fsm.push(new RocketLauncherStateAttackEnemyBase(fsm, brain));
				fsm.reflexe();
			}
		} 
	}

	@Override
	public void update() {
		this.enemyUnitSpotted = false;
		this.enemyBaseSpotted = false;
		for (WarMessage m : brain.mailbox) {
			if (m.getMessage() == "EnemyUnit") {
				this.enemyUnitSpotted = true;
				Vecteur base = new Vecteur(m.getDistance(), m.getAngle());
				Vecteur enemyRelBase = new Vecteur(Double.parseDouble(m.getContent()[0]), Double.parseDouble(m.getContent()[1]));
				Vecteur enemy = base.add(enemyRelBase);
				brain.distanceEnemyUnit = enemy.getLongueur();
				brain.angleEnemyUnit = enemy.getAngle();
			} else if (m.getMessage() == "EnemyBase") {
				this.enemyBaseSpotted = true;
				Vecteur base = new Vecteur(m.getDistance(), m.getAngle());
				Vecteur enemyRelBase = new Vecteur(Double.parseDouble(m.getContent()[0]), Double.parseDouble(m.getContent()[1]));
				Vecteur enemy = base.add(enemyRelBase);
				brain.distanceEnemyBase = enemy.getLongueur();
				brain.angleEnemyBase = enemy.getAngle();
			}
		}
	}
}
