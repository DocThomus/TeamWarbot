package rocketlauncher;

import util.Vecteur;
import brains.WarRocketLauncherBrainController;
import edu.warbot.agents.agents.WarRocketLauncher;
import edu.warbot.brains.WarBrain;
import edu.warbot.communications.WarMessage;
import fsm.Fsm;
import fsm.State;

public class RocketLauncherStateIdle extends State {
	
	private WarRocketLauncherBrainController brain;
	private boolean enemyUnitSpotted;
	private boolean enemyBaseSpotted;

	public RocketLauncherStateIdle(Fsm fsm, WarBrain brain) {
		super(fsm, brain);
		this.brain = (WarRocketLauncherBrainController) brain;
		this.enemyUnitSpotted = false;
		this.enemyBaseSpotted = false;
	}

	@Override
	public String execute() {
		brain.setDebugString("Idle");
		
		brain.broadcastMessageToAll("RLHere", "");
		
		return WarRocketLauncher.ACTION_IDLE;
	}

	@Override
	public void reflexe() {
		this.update();	
		if (enemyUnitSpotted) {
			fsm.push(new RocketLauncherStateDefendAllyBase(fsm, brain));
			fsm.reflexe();
		} else if (enemyBaseSpotted) {
			fsm.push(new RocketLauncherStateAttackEnemyBase(fsm, brain));
			fsm.reflexe();
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
