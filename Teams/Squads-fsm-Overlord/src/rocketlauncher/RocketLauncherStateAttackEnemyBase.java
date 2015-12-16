package rocketlauncher;

import util.Vecteur;
import edu.warbot.agents.agents.WarRocketLauncher;
import edu.warbot.brains.WarBrain;
import edu.warbot.communications.WarMessage;
import fsm.Fsm;
import fsm.State;
import brains.WarRocketLauncherBrainController;

public class RocketLauncherStateAttackEnemyBase extends State {
	
	private WarRocketLauncherBrainController brain;
	private boolean enemyUnitSpotted;
	
	public RocketLauncherStateAttackEnemyBase(Fsm fsm, WarBrain brain) {
		super(fsm, brain);
		this.brain = (WarRocketLauncherBrainController) brain;
		this.enemyUnitSpotted = false;
	}

	@Override
	public String execute() {
		brain.setDebugString("Attaque");
		
		brain.broadcastMessageToAll("RLHere", "");
		
		brain.setHeading(brain.angleEnemyBase);
		
		if (brain.isBlocked()) {
			brain.setRandomHeading();
		}
		
		if (brain.distanceEnemyBase > 60) {
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
		
		if (enemyUnitSpotted) {
			fsm.pop();
			fsm.push(new RocketLauncherStateDefendAllyBase(fsm, brain));
			fsm.reflexe();
		}
	}

	@Override
	public void update() {
		this.enemyUnitSpotted = false;
		for (WarMessage m : brain.mailbox) {
			if (m.getMessage() == "EnemyUnit") {
				this.enemyUnitSpotted = true;
				Vecteur base = new Vecteur(m.getDistance(), m.getAngle());
				Vecteur enemyRelBase = new Vecteur(Double.parseDouble(m.getContent()[0]), Double.parseDouble(m.getContent()[1]));
				Vecteur enemy = base.add(enemyRelBase);
				brain.distanceEnemyUnit = enemy.getLongueur();
				brain.angleEnemyUnit = enemy.getAngle();
			} else if (m.getMessage() == "EnemyBase") {
				Vecteur base = new Vecteur(m.getDistance(), m.getAngle());
				Vecteur enemyRelBase = new Vecteur(Double.parseDouble(m.getContent()[0]), Double.parseDouble(m.getContent()[1]));
				Vecteur enemy = base.add(enemyRelBase);
				brain.distanceEnemyBase = enemy.getLongueur();
				brain.angleEnemyBase = enemy.getAngle();
			}
		}
	}	
	
}
