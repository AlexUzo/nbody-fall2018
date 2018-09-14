
public class Body {
	// Create instance variables
	private double myXPos;
	private double myYPos;
	private double myXVel;
	private double myYVel;
	private double myMass;
	private String myFileName;
	
	// Assigns instance variables to chosen parameters
	public Body(double xp, double yp, double xv, double yv, double mass, String filename) {
		myXPos = xp;
		myYPos = yp;
		myXVel = xv; 
		myYVel = yv;
		myMass = mass;
		myFileName = filename;
	}
	
	// Constructor copies the other
	public Body(Body b) {
		myXPos = b.myXPos;
		myYPos = b.myYPos;
		myXVel = b.myXVel;
		myYVel = b.myYVel;
		myMass = b.myMass;
		myFileName = b.myFileName;
	}
	
	//getter methods to return corresponding instance variables
	public double getX() {
		return myXPos;
	}
	
	public double getY() {
		return myYPos;
	}
	
	public double getXVel() {
		return myXVel;
	}
	
	public double getYVel() {
		return myYVel;
	}
	
	public double getMass() {
		return myMass;
	}
	
	public String getName() {
		return myFileName;
	}
	
	//Calculates the distance between two bodies
	public double calcDistance(Body b) {
		double r;
		r = Math.sqrt(Math.pow(b.myXPos-myXPos, 2) + Math.pow(
				b.myYPos-myYPos, 2));
		return r; 
	}
	
	//Calculates the force exerted on this body by another body
	public double calcForceExertedBy(Body p) { 
		double G = 6.67*1e-11;
		double F = (G * (myMass * p.myMass))/ Math.pow(
				calcDistance(p), 2);
		return F;
	}
	
	//Return the force exerted in the X and Y directions
	public double calcForceExertedByX(Body p) {
		double Fx = calcForceExertedBy(p) * (p.myXPos-myXPos)/
				calcDistance(p);
		return Fx;
	}
	
	public double calcForceExertedByY(Body p) {
		double Fy= calcForceExertedBy(p) * (p.myYPos-myYPos)/
				calcDistance(p);
		return Fy;
	}
	
	//returns the net force exerted on this body by all the bodies
	public double calcNetForceExertedByX(Body[] bodies) {
		double sumX = 0; 
		for (Body b : bodies) {
			if (!b.equals(this)) {
				sumX += calcForceExertedByX(b);
			}
		}
		return sumX;
	}
	
	public double calcNetForceExertedByY(Body[] bodies) {
		double sumY = 0; 
		for (Body b : bodies) {
			if (!b.equals(this)) {
				sumY += calcForceExertedByY(b);
			}
		}
		return sumY;
	}
	
	//Updates the instance variables
	public void update(double deltaT, double xforce, double yforce) {
		//calculate acceleration
		double ax = xforce/myMass;
		double ay = yforce/myMass;
		//calculate new velocities
		double nvx = myXVel + deltaT * ax;
		double nvy = myYVel + deltaT * ay;
		//calculate new positions
		double nx = myXPos + deltaT * nvx;
		double ny = myYPos + deltaT * nvy;
		//Assign new variables to instance variables
		myXPos = nx;
		myYPos = ny;
		myXVel = nvx; 
		myYVel = nvy;
	}
	
	//Draws the bodies
	public void draw() {
		StdDraw.picture(myXPos, myYPos, "images/"+myFileName);
	}
	
}
