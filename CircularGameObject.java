class CircularGameObject extends GameObject{
	float radius ;
	private float xPos;
    private float yPos;
    boolean isCollided(CircularGameObject ob){
    	double distsq = (this.xPos-ob.xPos)*(this.xPos-ob.xPos)+(this.yPos-ob.yPos)*(this.yPos-ob.yPos) ;
    	return distsq<=(this.radius+ob.radius) ;
    }
}

class CircularMovingGameObject extends CircularGameObject{
	private float xAccel;
    private float yAccel;
    private float xVel;
    private float yVel;
    private float mass ;
    void changeVelocities(CircularMovingGameObject ob){
    	float u1x = (this.xVel*(ob.xPos-this.xPos)+this.yVel*(ob.yPos-this.yPos))*(ob.xPos-this.xPos) ;
    	u1x/=((radius+this.radius)*(radius+this.radius)) ;
    	float u1y = (this.xVel*(ob.xPos-this.xPos)+this.yVel*(ob.yPos-this.yPos))*(ob.yPos-this.yPos) ;
    	u1y/=((radius+this.radius)*(radius+this.radius)) ;
    	float u2x = (ob.xVel*(ob.xPos-this.xPos)+ob.yVel*(ob.yPos-this.yPos))*(ob.xPos-this.xPos) ;
    	u2x/=((radius+this.radius)*(radius+this.radius)) ;
    	float u2y = (ob.xVel*(ob.xPos-this.xPos)+ob.yVel*(ob.yPos-this.yPos))*(ob.yPos-this.yPxos) ;
    	u2y/=((radius+this.radius)*(radius+this.radius)) ;
    	float u1 = Math.sqrt(u1x*u1x+u1y*u1y) ;
    	float u2 = Math.sqrt(u2x*u2x+u2y*u2y) ;
    	float v1 = (((this.mass-ob.mass)*u1)+((ob.mass+ob.mass)*u2))/(this.mass+ob.mass) ;
    	float v2 = (((this.mass+this.mass)*u1)+((ob.mass-this.mass)*u2))/(this.mass+ob.mass) ;
    	float v1radx = (v1*(ob.xPos-this.xPos))/(radius+this.radius) ;
    	float v1rady = (v1*(ob.yPos-this.yPos))/(radius+this.radius) ;
    	float v2radx = (v2*(ob.xPos-this.xPos))/(radius+this.radius) ;
    	float v2rady = (v2*(ob.yPos-this.yPos))/(radius+this.radius) ;
    	float finalval1x = (this.xVel-u1x)+v1radx ;
    	float finalval1y = (this.yVel-u1y)+v1rady ;
    	float finalval2x = (ob.xVel-u2x)+v2radx ;
    	float finalval2y = (ob.yVel-u2y)+v2rady ;
    	this.xVel = finalval1x ;
    	this.yVel = finalval1y ;
    	ob.xVel = finalval2x ;
    	ob.yVel = finalval2y ;
    }    
}