package util;

import java.lang.Math;

public class Vecteur {
	private double longueur;
	private double angle;
	private double x;
	private double y;
	
	public Vecteur()
	{
		this.angle = 0;
		this.longueur = 0;
		this.x = 0;
		this.y = 0;
	}
	
	public Vecteur(double longueur, double angle)
	{
		this.angle = angle;
		this.longueur = longueur;
		this.updateCoordCart();
	}
	
	public double getLongueur()
	{
		return this.longueur;
	}
	
	public double getAngle()
	{
		return this.angle;
	}
	
	public double getX()
	{
		return this.x;
	}
	
	public double getY()
	{
		return this.y;
	}
	
	public void setLongueur(double longueur)
	{
		this.longueur = longueur;
		this.updateCoordCart();
	}
	
	public void setAngle(double angle)
	{
		this.angle = angle;
		this.updateCoordCart();
	}
	
	public void setX(double x)
	{
		this.x = x;
		this.updateCoordPol();
	}
	
	public void setY(double y)
	{
		this.y = y;
		this.updateCoordPol();
	}
	
	public void updateCoordPol()
	{
		this.longueur = Math.sqrt(this.getX() * this.getX() + this.getY() * this.getY());
		double teta = Math.atan2(this.getY(), this.getX());
		if(teta > 0)
		{
			this.angle = Math.toDegrees(teta);
		}
		else
		{
			this.angle = Math.toDegrees(Math.PI*2 + teta);
		}
	}
	
	public void updateCoordCart()
	{
		this.x = (this.getLongueur() * Math.cos(Math.toRadians(this.getAngle()))); 
		this.y = (this.getLongueur() * Math.sin(Math.toRadians(this.getAngle())));
	}
	
	public Vecteur add(Vecteur b)
	{
		Vecteur r = new Vecteur();
		r.setX(this.getX() + b.getX());
		r.setY(this.getY() + b.getY());
		return r;
	}
	
	public Vecteur sub(Vecteur b)
	{
		Vecteur r = new Vecteur();
		r.setX(this.getX() - b.getX());
		r.setY(this.getY() - b.getY());
		return r;
	}
}
