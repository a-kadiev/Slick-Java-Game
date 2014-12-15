package javagame;


public class Shoot {
	
	float beamPositionY;
	float beamPositionX;
	
	public Shoot(float posY, float posX){
		beamPositionY = posY + 12.5f;
		beamPositionX = posX;
	}
	
	public void setBeamPositionY(float posY){
		beamPositionY = posY;
	}
	
	public float getBeamPositionY(){
		return beamPositionY;
	}
	
	public void setBeamPositionX(float posX){
		beamPositionX = posX;
	}
	
	public float getBeamPositionX(){
		return beamPositionX;
	}
	
	public void moveShot(float shift){
		beamPositionX += shift;
	}
}