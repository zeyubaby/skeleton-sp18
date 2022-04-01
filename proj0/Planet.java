public class Planet {
    public double xxPos;
    public double yyPos;
    public double xxVel;
    public double yyVel;
    public double mass;
    public String imgFileName;
    public static double G = 6.67e-11;

    public Planet(double xP, double yP, double xV, double yV, double m, String img) {
        xxPos = xP;
        yyPos = yP;
        xxVel = xV;
        yyVel = yV;
        mass = m;
        imgFileName = img;

    }

    public Planet(Planet b) {
        xxPos = b.xxPos;
        yyPos = b.yyPos;
        xxVel = b.xxVel;
        yyVel = b.yyVel;
        mass = b.mass;
        imgFileName = b.imgFileName;
    }

    public double calcDistance(Planet body2) {
        double xDis = this.xxPos - body2.xxPos;
        double yDis = this.yyPos - body2.yyPos;
        double distance = Math.sqrt(Math.pow(xDis, 2) + Math.pow(yDis, 2));
        return distance;
    }

    public double calcForceExertedBy(Planet body2) {
        double force = G * this.mass * body2.mass / Math.pow(calcDistance(body2), 2);
        return force;
    }

    public double calcForceExertedByX(Planet body2) {
        double xForce = calcForceExertedBy(body2) * (body2.xxPos - this.xxPos) / calcDistance(body2);
        return xForce;
    }

    public double calcForceExertedByY(Planet body2) {
        double yForce = calcForceExertedBy(body2) * (body2.yyPos - this.yyPos) / calcDistance(body2);
        return yForce;
    }

    public double calcNetForceExertedByX(Planet[] allBodies) {
        double netForceX = 0;
        for (Planet body : allBodies
        ) {
            if (body != this) {
                netForceX += calcForceExertedByX(body);
            }
        }
        return netForceX;
    }

    public double calcNetForceExertedByY(Planet[] allBodies) {
        double netForceY = 0;
        for (Planet body : allBodies
        ) {
            if (body != this) {
                netForceY += calcForceExertedByY(body);
            }
        }
        return netForceY;
    }

    public void update(double dt, double xForce, double yForce) {
        double aX = xForce / this.mass;
        double aY = yForce / this.mass;
        this.xxVel = this.xxVel + dt * aX;
        this.yyVel = this.yyVel + dt * aY;
        this.xxPos = this.xxPos + dt * this.xxVel;
        this.yyPos = this.yyPos + dt * this.yyVel;
    }

    public void draw(){
        StdDraw.picture(this.xxPos,this.yyPos,"images/"+ this.imgFileName);
    }
}