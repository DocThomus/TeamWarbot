package Util;

import java.lang.Math;

public class Vecteur {
	
	private double longueur;
	private double angle;
	
	public Vecteur() {
		this.angle = 0;
		this.longueur = 0;
	}
	
	public Vecteur(double longueur, double angle) {
		this.angle = angle;
		this.longueur = longueur;				
	}
	
	public double getLongueur() {
		return longueur;
	}
	
	public double getAngle() {
		return angle;
	}
	
	public void setLongueur(double longueur) {
		this.longueur = longueur;
	}
	
	public void setAngle(double angle) {
		this.angle = angle;
	}
	
	// http://www.fsg.ulaval.ca/opus/physique534/resumes/vecteursb.shtml
	public Vecteur add(Vecteur b) {
		Vecteur r = new Vecteur();
		
		double ax = (this.getLongueur() * Math.cos(Math.toRadians(this.getAngle()))); 
		double ay = (this.getLongueur() * Math.sin(Math.toRadians(this.getAngle())));
		double bx = (b.getLongueur() * Math.cos(Math.toRadians(b.getAngle())));
		double by = (b.getLongueur() * Math.sin(Math.toRadians(b.getAngle())));
		double rx = ax + bx;
		double ry = ay + by;
		r.setLongueur(Math.sqrt(rx * rx + ry * ry));
		r.setAngle(0);
		
		return r;
	}
}
