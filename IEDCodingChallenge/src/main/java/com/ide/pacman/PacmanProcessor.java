package com.ide.pacman;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Stream;

public class PacmanProcessor {

	int posX;
	int posY;
	String direction;
	List<String> validMoves = new ArrayList<String>();

	public int getPosX() {
		return posX;
	}

	public void setPosX(int posX) {
		this.posX = posX;
	}

	public int getPosY() {
		return posY;
	}

	public void setPosY(int posY) {
		this.posY = posY;
	}

	public String getDirection() {
		return direction;
	}

	public void setDirection(String direction) {
		this.direction = direction;
	}

	public List<String> getValidMoves() {
		return validMoves;
	}

	public void setValidMoves(List<String> validMoves) {
		this.validMoves = validMoves;
	}

	public static final Map<String, String> rightMap;
	static {
		rightMap = new HashMap<String,String>();
		rightMap.put("EAST", "SOUTH");
		rightMap.put("SOUTH", "WEST");
		rightMap.put("WEST", "NORTH");
		rightMap.put("NORTH", "EAST");
		
	}
	public static final Map<String, String> leftMap;
	static {
		leftMap = new HashMap<String,String>();
		leftMap.put("EAST", "NORTH");
		leftMap.put("SOUTH", "EAST");
		leftMap.put("WEST", "SOUTH");
		leftMap.put("NORTH", "WEST");
		
	}
	
	public void processPacman(Stream<String> stream){
		stream.forEach(line -> {
			String[] instrSplit = line.trim().split(",|\\s+");
			if(instrSplit.length>0){
				if(validMoves.isEmpty() && instrSplit[0].toUpperCase().equals("PLACE")){
					Boolean valid = place(instrSplit);
					if(valid){
						validMoves.add(line);
					}
				}else if(!validMoves.isEmpty()){
					validMoves.add(line);
					
					switch(instrSplit[0].toUpperCase()){
					case "PLACE" : {
						place(instrSplit);
						break;
					}
					case "MOVE" : {
						move(instrSplit);
						break;
					}
					case "LEFT" : {
						left(instrSplit);
						break;
					}
					case "RIGHT" : {
						right(instrSplit);
						break;
					}
					case "REPORT" : {
						report();
						break;
					}
					default : {
						break;
					}
					}
				}
			}
		});
	}
	private void report() {
		int x = getPosX();
		int y = getPosY();
		String dir = getDirection();
		String pos = x+","+y+","+dir;
		System.out.println("Output : "+pos);
	}

	private Boolean left(String[] instrSplit) {
		if(instrSplit.length==1){
			String currDir = getDirection();
			setDirection(leftMap.get(currDir));
			return true;
		}
		return false;
	}

	private Boolean right(String[] instrSplit) {
		if(instrSplit.length==1){
			String currDir = getDirection();
			setDirection(rightMap.get(currDir));
			return true;
		}
		return false;
	}

	private Boolean move(String[] instrSplit) {
		if(instrSplit.length==1){
			String dir = getDirection();
			int pos = 0;
			if(dir.equals("NORTH")){
				pos = getPosY();
				if(pos<5){
					setPosY(pos+1);
					return true;
				}
			} else if(dir.equals("SOUTH")){
				pos = getPosY();
				if(pos>0){
					setPosY(pos-1);
					return true;
				}
			} else if(dir.equals("EAST")) {
				pos= getPosX();
				if(pos<5){
					setPosX(pos+1);
					return true;
				}
			} else if(dir.equals("WEST")) {
				pos= getPosX();
				if(pos>0){
					setPosX(pos-1);
					return true;
				}
			}
		}
		return false;
	}

	public Boolean place(String[] instrSplit) {
		if(instrSplit.length>0 && instrSplit.length==4){
			int x = Integer.parseInt(instrSplit[1]);
			int y = Integer.parseInt(instrSplit[2]);
			if(x>=0 && y>=0 && x<=5 && y<=5){
				setPosX(x);
				setPosY(y);
				setDirection(instrSplit[3].toUpperCase());
				return true;
			}
		}
		return false;
	}
}
