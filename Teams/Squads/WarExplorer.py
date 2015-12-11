
def actionWarExplorer():

	setDebugString("");

	# Base ennemi en vue ? Donner sa position à tous.
	if getPerceptsEnemiesWarBase():
		broadcastMessageToAll("EnemyBaseSpoted","");

	# Nourriture à porter ? Indiquer aux explorers, et ramasser la plus proche si le sac n'est pas plein.
	foods = getPerceptsFood();
	if(len(foods) > 0):
		sendMessageToExplorers("FoodHere","")
		if (isNotBagFull()):
			setDebugString("Pick Food");
			food = foods[LePlusProche(foods)];
			if(pickableFood(food)):
				return take()
			else:
				followTarget(food)
	# Pas de nourriture à porter ? Encore de la place dans le sac ? Message d'un explorer allié ayant trouvé de la nourriture ? Se diriger vers cet endroit.
	else: 
		if (isNotBagFull()):
			for message in getMessages():
				if(isMessageOfWarExplorer(message)):
					if(message.getMessage() == "FoodHere"):
						setDebugString("Follow Food Found");
						followTarget(message);
						setRandomHeading(5);
						return move();
						# A améliorer : garder en mémoire l'état et la position du messager, et s'y rendre, une fois arrivé, si pas de food trouvée en chemin, se dirige dans une direction aléatoire.


	# Sac plein ? Retourner à la base 
	if(not isNotBagFull()):
		setDebugString("Returning Base");
		# Base hors de vue ? Demander sa position à la base.
		basesAllie = getPerceptsAlliesWarBase();
		if(haveNoTargets(basesAllie)):
			for message in getMessages():
				if(isMessageOfWarBase(message)):
					followTarget(message)
			sendMessageToBases("BaseWhere", "");
		else :
			baseAllie = basesAllie[0];
			if(isPossibleToGiveFood(baseAllie)) :
				giveToTarget(baseAllie);
				return give();
			else:
				followTarget(baseAllie);
				return move();

	if (isBlocked()) :
		RandomHeading();
	
	return move();



#Percepts ne doit pas être vide
def LePlusProche(percepts):
	posMin = 0;
	distanceMin = percepts[0].getDistance();
	for i in range(0,len(percepts)):
		if (percepts[i].getDistance() < distanceMin):
			distanceMin = percepts[i].getDistance();
			posMin = i;
	return posMin;