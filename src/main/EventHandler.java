package main;


import entity.Entity;
import object.OBJ_Apple;
import object.OBJ_Pear;

public class EventHandler {

	GamePanel gp;
	EventRect eventRect[][][];
	Entity eventMaster;
	
	int previousEventX, previousEventY;
	boolean canTouchEvent = true;
	int tempMap, tempCol, tempRow;
	
	public EventHandler(GamePanel gp) {
		this.gp = gp;
		
		eventMaster = new Entity(gp);
		eventRect = new EventRect[gp.maxMap][gp.maxWorldCol][gp.maxWorldRow];
		
		int map = 0;
		int col = 0;
		int row = 0;
		while(map < gp.maxMap&& col < gp.maxWorldCol && row < gp.maxWorldRow) {
		
		eventRect[map][col][row] = new EventRect();
		eventRect[map][col][row].x = 23;
		eventRect[map][col][row].y = 23;
		eventRect[map][col][row].width = 2;
		eventRect[map][col][row].height = 2;
		eventRect[map][col][row].eventRectDefaultX = eventRect[map][col][row].x;
		eventRect[map][col][row].eventRectDefaultY = eventRect[map][col][row].y;
		
		col++;
		if(col == gp.maxWorldCol) {
			col = 0;
			row++;
			
			if(row == gp.maxWorldRow) {
				row = 0;
				map++;
			}
		}
		}
		setDialogue();
	}
	public void setDialogue() {
		
		eventMaster.dialogues[0][0]= "You fell into a pit!";
		
		eventMaster.dialogues[1][0]= "You drank some healing water. \nYour life has been restored!\n"+
				"Your progress is saved!";
	}
	
	public void checkEvent() {
		
		//check if the player  character is more than 1 tile away from the  last event
		int xDistance =  Math.abs(gp.player.worldX - previousEventX); //gives back the absolute value
		int yDistance = Math.abs(gp.player.worldY - previousEventY);
		int distance = Math.max(xDistance, yDistance);// gives back the highest value
		if(distance > gp.tileSize*5) {
			canTouchEvent = true;
		}
		
		if(canTouchEvent == true) {		
		//if(hit(0,18,39,"left") == true) {teleport(0,18,39,gp.dialogueState);}
		if(hit(0,20,23,"left") == true ) {healingPool(gp.dialogueState);}
		else if(hit(0,20,21,"left") == true ) {healingPool(gp.dialogueState);}
		else if(hit(0,20,22,"left") == true ) {healingPool(gp.dialogueState);}
//		else if(hit(0,34,20,"any") == true) {teleport(1,12,13);}
//		else if(hit(1,12,13,"any") == true) {teleport(0,34,20);}
		else if(hit(0, 56, 18,"any") == true) {find();}
		else if(hit(0, 45, 59,"any") == true) {addItem(new OBJ_Pear(gp)) ;}
		else if(hit(0, 53, 26,"any") == true) {addItem(new OBJ_Apple(gp)) ;}
		else if(hit(0, 57, 30,"any") == true) {addItem(new OBJ_Apple(gp)) ;}
		else if(hit(0, 64,25,"any") == true) {addItem(new OBJ_Apple(gp)) ;}
		else if(hit(0, 64, 18,"any") == true) {addItem(new OBJ_Apple(gp)) ;}
		else if(hit(0, 56, 43,"any") == true) {addItem(new OBJ_Apple(gp)) ;}
		else if(hit(0,56,56,"any") == true) {teleport(1,12,7,gp.dungeon);}//enter d1
		else if(hit(1,12,7,"any") == true) {teleport(0,56,56,gp.outside);}//exit d1
		else if(hit(1,13,7,"any") == true) {teleport(0,56,56,gp.outside);}
		else if(hit(1,19,74,"any") == true) {teleport(2,38,9,gp.dungeon);}//enter d2
		else if(hit(1,20,74,"any") == true) {teleport(2,38,9,gp.dungeon);}
		else if(hit(2,38,9,"any") == true) {teleport(1,19,74,gp.dungeon);}//exitd2
		else if(hit(2,37,9,"any") == true) {teleport(1,19,74,gp.dungeon);}
		else if(hit(2,40,75,"any") == true) {teleport(0,56,56,gp.outside);}//outside
		else if(hit(2,39,75,"any") == true) {teleport(0,56,56,gp.outside);}
		else if(hit(2,37,36,"any") == true) {skeletonLord();}
		}
		
		
	}
	public boolean hit(int map,int col, int row, String reqDirection) {
		
		boolean hit = false;
		
		if(map == gp.currentMap) {
		gp.player.solidArea.x = gp.player.worldX + gp.player.solidArea.x;
		gp.player.solidArea.y = gp.player.worldY + gp.player.solidArea.y;
		eventRect[map][col][row].x = col*gp.tileSize + eventRect[map][col][row].x;
		eventRect[map][col][row].y = row*gp.tileSize + eventRect[map][col][row].y;
		
        if(gp.player.solidArea.intersects(eventRect[map][col][row]) && eventRect[map][col][row].eventDone == false) {
        	if(gp.player.direction.contentEquals(reqDirection) || reqDirection.contentEquals("any")) {
        		hit = true;
        		
        		previousEventX = gp.player.worldX;
        		previousEventY = gp.player.worldY;
        		
        	}
        }
        
        //reset solidArea x and y
        gp.player.solidArea.x = gp.player.solidAreaDefaultX;
        gp.player.solidArea.y = gp.player.solidAreaDefaultY;
        eventRect[map][col][row].x = eventRect[map][col][row].eventRectDefaultX;
        eventRect[map][col][row].y = eventRect[map][col][row].eventRectDefaultY;
		}
		
		
		return hit;
	}
	public void find() {
		if(gp.keyH.enterPressed == true) {
			gp.player.attackCanceled = true;
		for(int i = 0; i< gp.npc[1].length; i++) {
    		if(gp.npc[gp.currentMap][i].name.equals("Merchant")) {
    			gp.npc[gp.currentMap][i].speak();
    			break;
    		}
    	}}
	}
	public void teleport(int map,int col,int row, int area) {
		
		if (gp.keyH.enterPressed == true) {
			gp.player.attackCanceled = true;
			gp.gameState = gp.transitionState;
			gp.nextArea = area;
			
			tempMap = map;
			tempCol = col;
			tempRow = row;

			// canTouchEvent = false;
			gp.playSE(14);

		}

	}
	public void addItem(Entity item) {
		
		if(gp.keyH.enterPressed == true) {
			gp.player.attackCanceled = true;
			gp.player.canObtainItem(item);
		//gp.player.inventory.add(item);
		eventMaster.startDialogue(item,0);
		canTouchEvent = false;
		}

	}
	public void damagePit(int gameState) {
		gp.gameState = gameState;
		gp.playSE(6);
		eventMaster.startDialogue(eventMaster, 0);
		gp.player.life -= 1;
		canTouchEvent = false;
	}
	public void healingPool(int gameState) {
		
		if(gp.keyH.enterPressed == true) {
			gp.gameState = gameState;
			gp.player.attackCanceled = true;
			gp.playSE(2);
			eventMaster.startDialogue(eventMaster,1);
			gp.player.life = gp.player.maxLife;
			gp.aSetter.setMonster();
			gp.saveLoad.save();
		}
	}
	public void speak(Entity entity) {
		
		if(gp.keyH.enterPressed == true) {
			gp.gameState = gp.dialogueState;
			gp.player.attackCanceled = true;
			entity.speak();
			
		}
	}
	public void skeletonLord() {
		
		if(gp.bossBattleOn == false) {
			gp.gameState = gp.cutsceneState;
			gp.csManager.sceneNum = gp.csManager.skeletonLord;
		}
	}
	
	
	
}
