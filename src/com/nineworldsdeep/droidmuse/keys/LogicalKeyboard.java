/*
 * This file is part of DroidMuse.
 *
 * DroidMuse is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 * 
 * DroidMuse is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 * 
 * You should have received a copy of the GNU General Public License
 * along with DroidMuse.  If not, see <http://www.gnu.org/licenses/>.
 * 
 */

package com.nineworldsdeep.droidmuse.keys;

public class LogicalKeyboard {

	private boolean[] keys = new boolean[24];	
	
	public void clearAll(){
		for(int i = 0; i < keys.length; i++){
			keys[i] = false;
		}
	}
	
	public boolean get(int keyIndex){
		return keys[keyIndex];
	}
	
	public void set(int keyIndex, boolean val){
		keys[keyIndex] = val; 
	}
	
	public void toggle(int keyIndex){
		keys[keyIndex] = !keys[keyIndex];
	}
	
	public void toggleBlackKey(int blackKeyIndex){
		setBlackKey(blackKeyIndex,!getBlackKey(blackKeyIndex));
	}
	
	public void toggleWhiteKey(int whiteKeyIndex){
		setWhiteKey(whiteKeyIndex,!getWhiteKey(whiteKeyIndex));
	}
	
	public boolean getBlackKey(int blackKeyIndex){
		
		switch(blackKeyIndex){
			case 0:
				return keys[1];
			case 1:
				return keys[3];
			case 2:
				return keys[6];
			case 3:
				return keys[8];
			case 4: 
				return keys[10];
			case 5:
				return keys[13];
			case 6:
				return keys[15];
			case 7: 
				return keys[18];
			case 8:
				return keys[20];
			case 9:
				return keys[22];
			default:
				return false;				
		}
	}
	
	public boolean getWhiteKey(int whiteKeyIndex){
	
		switch(whiteKeyIndex){
		
			case 0:
				return keys[0];
			case 1:
				return keys[2];
			case 2:
				return keys[4];
			case 3:
				return keys[5];
			case 4: 
				return keys[7];
			case 5:
				return keys[9];
			case 6:
				return keys[11];
			case 7: 
				return keys[12];
			case 8:
				return keys[14];
			case 9:
				return keys[16];
			case 10: 
				return keys[17];
			case 11:
				return keys[19];
			case 12:
				return keys[21];
			case 13:
				return keys[23];					
			default:
				return false;				
		}
	}
	
	public void setBlackKey(int blackKeyIndex,boolean val){

		switch(blackKeyIndex){
		
			case 0:
				keys[1] = val; 
				break;				
			case 1:
				keys[3] = val; 
				break;
			case 2:
				keys[6] = val; 
				break;
			case 3:
				keys[8] = val; 
				break;
			case 4: 
				keys[10] = val; 
				break;
			case 5:
				keys[13] = val; 
				break;
			case 6:
				keys[15] = val; 
				break;
			case 7: 
				keys[18] = val; 
				break;
			case 8:
				keys[20] = val; 
				break;
			case 9:
				keys[22] = val; 
				break;
			default:
				break;				
			}
	}
	
	public void setWhiteKey(int whiteKeyIndex,boolean val){
		
		switch(whiteKeyIndex){
		
			case 0:
				keys[0] = val;
				break;
			case 1:
				keys[2] = val;
				break;
			case 2:
				keys[4] = val;
				break;
			case 3:
				keys[5] = val; 
				break;
			case 4: 
				keys[7] = val;
				break;
			case 5:
				keys[9] = val; 
				break;
			case 6:
				keys[11] = val; 
				break;
			case 7: 
				keys[12] = val; 
				break;
			case 8:
				keys[14] = val; 
				break;
			case 9:
				keys[16] = val; 
				break;
			case 10: 
				keys[17] = val; 
				break;
			case 11:
				keys[19] = val; 
				break;
			case 12:
				keys[21] = val; 
				break;
			case 13:
				keys[23] = val; 
				break;					
			default:
				break;				
		}
	}
	
}


