package main;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import entity.Entity;

public class KeyHandler implements KeyListener{
	
	GamePanel gp;
	public boolean upPressed, downPressed, leftPressed, rightPressed, enterPressed, shoot, spacePressed; 
    boolean checkDrawTime;
    
    
    public KeyHandler(GamePanel gp) {
    	this.gp = gp;
    }
	
	@Override
	public void keyTyped(KeyEvent e) {
		
	}

	@Override
	public void keyPressed(KeyEvent e) {
		
		int code = e.getKeyCode();
		//returns the integer keyCode associated with the key in this event.
		
		//title state
		if(gp.gameState == gp.titleState) {
			if(gp.ui.titleScreenState == 0) {
			titleState(code);
			}
//			else if(gp.ui.titleScreenState == 1) {
//				if(code == KeyEvent.VK_W || code == KeyEvent.VK_UP ) {
//					gp.ui.commandNum --;
//					if(gp.ui.commandNum < 0) {
//						gp.ui.commandNum = 3;
//					}
//				}
//		        if(code == KeyEvent.VK_S || code == KeyEvent.VK_DOWN) {
//					gp.ui.commandNum ++;
//					if(gp.ui.commandNum >3) {
//						gp.ui.commandNum = 0;
//					}
//				}
//		        if(code == KeyEvent.VK_ENTER) {
//		        	if(gp.ui.commandNum == 0) {
//		        		//fighter
//		        		gp.gameState = gp.playState;
//		        		gp.playMusic(0);
//		        	}
//		        	if(gp.ui.commandNum == 1) {
//		        		//thief
//		        		gp.gameState = gp.playState;
//		        		gp.playMusic(0);
//		        	}
//		        	if(gp.ui.commandNum == 2) {
//		        		//sorcerer
//		        		gp.gameState = gp.playState;
//		        		gp.playMusic(0);
//		        	}
//		        	if(gp.ui.commandNum == 3) {
//		        		gp.ui.titleScreenState = 0;
//		        	}
//		        }
//				}
		}
		
		//play state
		else if(gp.gameState == gp.playState) {
			playState(code);
		}
		
		//pause state
		else if(gp.gameState == gp.pauseState) {
	        pauseState(code);
		}
		
		//dialogue state
		else if(gp.gameState == gp.dialogueState || gp.gameState == gp.cutsceneState) {
	    	dialogueState(code);
		}
		//character state
		else if(gp.gameState == gp.characterState) {
			characterState(code);
		}
		//options state
		else if(gp.gameState == gp.optionsState) {
			optionsState(code);
		}
		// game over state
		else if (gp.gameState == gp.gameOverState) {
			GameOverState(code);
		}
		// trade state
		else if (gp.gameState == gp.tradeState) {
			tradeState(code);
		}
		// map state
		else if (gp.gameState == gp.mapState) {
			mapState(code);
		}
		// confirmation state
		else if (gp.gameState == gp.confirmationState) {
			conformationState(code);
		}
			
	
	}
	public void titleState(int code) {
		
		if(code == KeyEvent.VK_W ) {
			gp.ui.commandNum --;
			if(gp.ui.commandNum < 0) {
				gp.ui.commandNum = 2;
			}
		}
        if(code == KeyEvent.VK_S ) {
			gp.ui.commandNum ++;
			if(gp.ui.commandNum >2) {
				gp.ui.commandNum = 0;
			}
		}
        if(code == KeyEvent.VK_ENTER) {
        	if(gp.ui.commandNum == 0) {
        		gp.gameState = gp.playState;
        		//gp.ui.titleScreenState = 1;
        		gp.playMusic(0);
        	}
        	if(gp.ui.commandNum == 1) {
        		gp.saveLoad.load();
        		gp.gameState = gp.playState;
        		gp.playMusic(0);
        	}
        	if(gp.ui.commandNum == 2) {
        		System.exit(0);
        	}
        }
		
	}
	public void playState(int code) {
		
		if(code == KeyEvent.VK_W ) {
			upPressed = true;
		}
        if(code == KeyEvent.VK_S ) {
        	downPressed = true;
			
		}
        if(code == KeyEvent.VK_A ) {
			leftPressed = true;
		}
        if(code == KeyEvent.VK_D ) {
			rightPressed = true;
		}
        if(code == KeyEvent.VK_P) {
        		gp.gameState = gp.pauseState;
		}
        if(code == KeyEvent.VK_C) {
        	gp.gameState = gp.characterState;
        }
        if(code == KeyEvent.VK_ENTER) {
        	enterPressed = true;
        }
        if(code == KeyEvent.VK_F) {
        	shoot = true;
        }
        if(code == KeyEvent.VK_ESCAPE) {
        	gp.gameState = gp.optionsState;
        }
        if(code == KeyEvent.VK_M) {
        	gp.gameState = gp.mapState;
        }
        if(code == KeyEvent.VK_X) {
        	if(gp.map.miniMapOn == false) {
        		gp.map.miniMapOn = true;
        	}
        	else {
        		gp.map.miniMapOn = false;
        	}
        }
       
        if(code == KeyEvent.VK_SPACE) {
        	 if(gp.player.currentShield.life > 0) {
        	spacePressed = true;
        }
        }

        // debug
        if(code == KeyEvent.VK_T) {
        	if(checkDrawTime == false) {
			checkDrawTime = true;
		}
        else if(checkDrawTime == true) {
        	checkDrawTime = false;
        }}
        if(code== KeyEvent.VK_R) {
        	switch(gp.currentMap) {
        	case 0: gp.tileM.loadMap("/maps/world01.txt", 0); break;
        	case 1: gp.tileM.loadMap("/maps/interior01.txt", 1); break;
        	}
        }
		
	}
	public void pauseState(int code) {
		if(code == KeyEvent.VK_P) {
    		gp.gameState = gp.playState;
        }
		if(code == KeyEvent.VK_ENTER) {
			System.exit(0);
		}
	}
	public void dialogueState(int code) {
	    if(code == KeyEvent.VK_ENTER) {
        	enterPressed = true;
        }
		}
	public void characterState(int code) {
		if(code == KeyEvent.VK_C) {
			gp.gameState = gp.playState;
		}
	
		if(code == KeyEvent.VK_ENTER) {
		
			gp.player.selectItem();
		}
		playerInventory(code);
		
	}
    public void optionsState(int code) {
    	
    	if(code == KeyEvent.VK_ESCAPE) {
    		gp.gameState = gp.playState;
    	}
    	if(code == KeyEvent.VK_ENTER) {
    		enterPressed = true;
    	}
    	
    	int maxCommandNum = 0;
    	switch(gp.ui.subState) {
    	case 0: maxCommandNum = 5;break;
    	case 3: maxCommandNum = 1; break;
    	}
    	
    	if(code == KeyEvent.VK_W) {
    		gp.ui.commandNum--;
    		gp.playSE(9);
    		if(gp.ui.commandNum<0) {
    			gp.ui.commandNum = maxCommandNum;
    		}
    		
    	}
    	if(code == KeyEvent.VK_S) {
    		gp.ui.commandNum++;
    		gp.playSE(9);
    		if(gp.ui.commandNum > maxCommandNum) {
    			gp.ui.commandNum = 0;
    		}
    	}
    	if(code == KeyEvent.VK_A) {
    		if(gp.ui.subState == 0) {
    			if(gp.ui.commandNum == 1 && gp.music.volumeScale > 0) {
    				gp.music.volumeScale--;
    				gp.music.checkVolume();
    				gp.playSE(9);
    			}
    			if(gp.ui.commandNum == 2 && gp.se.volumeScale > 0) {
    				gp.se.volumeScale--;
    				gp.playSE(9);
    			}
    		}
    	}
    	if(code == KeyEvent.VK_D) {
    		if(gp.ui.subState == 0) {
    			if(gp.ui.commandNum == 1 && gp.music.volumeScale < 5) {
    				gp.music.volumeScale++;
    				gp.music.checkVolume();
    				gp.playSE(9);
    			}
    			if(gp.ui.commandNum == 2 && gp.se.volumeScale < 5) {
    				gp.se.volumeScale++;
    				gp.playSE(9);
    			}
    		}
    	}
    }
    public void GameOverState(int code) {
    	
    	if(code == KeyEvent.VK_W) {
    		gp.ui.commandNum--;
    		if(gp.ui.commandNum < 0) {
    			gp.ui.commandNum =1;
    		}
    		gp.playSE(9);
    	}
    	if(code == KeyEvent.VK_S) {
    		gp.ui.commandNum++;
    		if(gp.ui.commandNum > 1) {
    			gp.ui.commandNum = 0;
    		}
    		gp.playSE(9);
    	}
    	if(code == KeyEvent.VK_ENTER) {
    		if(gp.ui.commandNum ==0) {
    			//retry
    			gp.gameState = gp.playState;
    			gp.resetGame(false);
    			gp.playMusic(0);
    		}
    		else if(gp.ui.commandNum == 1) {
    			//reset
    			gp.gameState = gp.titleState;
    			gp.resetGame(true);
    		}
    	}
    }
	public void tradeState(int code) {
		
		if(code == KeyEvent.VK_ENTER) {
			enterPressed = true;
		}
		
		if(gp.ui.subState == 0) {
			if(code == KeyEvent.VK_W) {
				gp.ui.commandNum--;
				if(gp.ui.commandNum < 0) {
					gp.ui.commandNum = gp.ui.maxcommandNum;
				}
				gp.playSE(9);
			}
			if(code == KeyEvent.VK_S) {
				gp.ui.commandNum++;
				if(gp.ui.commandNum > gp.ui.maxcommandNum) {
					gp.ui.commandNum = 0;
				}
				gp.playSE(9);
			}
		}
		if(gp.ui.subState == 1) {
			npcInventory(code);
			if(code == KeyEvent.VK_ESCAPE) {
				gp.ui.subState = 0;
			}
		}
		if(gp.ui.subState == 2) {
			playerInventory(code);
			if(code == KeyEvent.VK_ESCAPE) {
				gp.ui.subState = 0;
			}
		}
	}
	public void mapState(int code) {
		
		if(code == KeyEvent.VK_M) {
			gp.gameState = gp.playState;
		}
	}
    public void playerInventory(int code) {
    	
    	if(code == KeyEvent.VK_W) {
			if(gp.ui.playerSlotRow != 0) {
			gp.ui.playerSlotRow--;
			gp.playSE(9);
			}
		}
		if(code == KeyEvent.VK_A) {
			if(gp.ui.playerSlotCol != 0) {
		    gp.ui.playerSlotCol--;
		    gp.playSE(9);
			}
		}
		if(code == KeyEvent.VK_S) {
			if(gp.ui.playerSlotRow != 3) {
			gp.ui.playerSlotRow++;
			gp.playSE(9);
			}
		}
		if(code == KeyEvent.VK_D) {
			if(gp.ui.playerSlotCol != 4) {
			gp.ui.playerSlotCol++;
			gp.playSE(9);
			}
		}
    }

	public void conformationState(int code) {

		if (code == KeyEvent.VK_W) {
			gp.ui.commandNum--;
			if (gp.ui.commandNum < 0) {
				gp.ui.commandNum = 1;
			}
			gp.playSE(9);
		}
		if (code == KeyEvent.VK_S) {
			gp.ui.commandNum++;
			if (gp.ui.commandNum > 1) {
				gp.ui.commandNum = 0;
			}
			gp.playSE(9);
		}
		if (code == KeyEvent.VK_ENTER) {
			if (gp.ui.commandNum == 0) {
				// use
				gp.player.canUse = true;
				gp.player.consumable();
				//gp.gameState = gp.dialogueState;
				
			} else if (gp.ui.commandNum == 1) {
			
				gp.player.canUse = false;
				gp.gameState = gp.characterState;
				
			}
		}

	}
    public void npcInventory(int code) {
    	
    	if(code == KeyEvent.VK_W) {
			if(gp.ui.npcSlotRow != 0) {
			gp.ui.npcSlotRow--;
			gp.playSE(9);
			}
		}
		if(code == KeyEvent.VK_A) {
			if(gp.ui.npcSlotCol != 0) {
		    gp.ui.npcSlotCol--;
		    gp.playSE(9);
			}
		}
		if(code == KeyEvent.VK_S) {
			if(gp.ui.npcSlotRow != 3) {
			gp.ui.npcSlotRow++;
			gp.playSE(9);
			}
		}
		if(code == KeyEvent.VK_D) {
			if(gp.ui.npcSlotCol != 4) {
			gp.ui.npcSlotCol++;
			gp.playSE(9);
			}
		}
    }

	@Override
	public void keyReleased(KeyEvent e) {
		int code = e.getKeyCode();
		
		if(code == KeyEvent.VK_W) {
			upPressed = false;
		}
        if(code == KeyEvent.VK_S) {
        	downPressed = false;
			
		}
        if(code == KeyEvent.VK_A) {
			leftPressed = false;
		}
        if(code == KeyEvent.VK_D) {
			rightPressed = false;
		}
        if(code == KeyEvent.VK_F) {
        	shoot = false;
        }
        if(code == KeyEvent.VK_ENTER) {
        	enterPressed = false;
        }
        if(code == KeyEvent.VK_SPACE) {
        	spacePressed = false;
        }
      
	}

}
