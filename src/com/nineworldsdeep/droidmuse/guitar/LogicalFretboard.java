/*
 * Copyright 2011 Brent Buchholz
 *
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

package com.nineworldsdeep.droidmuse.guitar;

import java.util.ArrayList;
import java.util.HashMap;

import android.graphics.Point;
import android.graphics.PointF;
import android.graphics.RectF;

public class LogicalFretboard {

	private int fretSpacing, stringSpacing;
	
	//TODO: come up with a better variable name (lol)
	private HashMap<Integer,Integer> stringFretPresses =
						new HashMap<Integer,Integer>();
	
	private ArrayList<FretboardPosition> positions = 
						new ArrayList<FretboardPosition>();
	
	public LogicalFretboard(int fretSpacing, int stringSpacing){
		
		this.fretSpacing = fretSpacing;
		this.stringSpacing = stringSpacing;
	}
	
	
	
	public void registerPosition(GuitarString string, Fret fret){
		
		Point[] fretPts, stringPts;
		
		fretPts = fret.getPoints();
		stringPts = string.getPoints();
		
		int stringOffset = this.stringSpacing / 2;
		int bottom = fretPts[0].y;
		int top = bottom - this.fretSpacing;
		int left = stringPts[0].x - stringOffset;
		int right = stringPts[0].x + stringOffset;
				
		this.positions.add(new FretboardPosition(left, top, right, bottom, fret, string));
		//register string and set to silent string
		this.stringFretPresses.put(string.getPositionalId(), -1);
	}
	
	public boolean togglePosition(float x, float y){
		boolean found = false;
		for(FretboardPosition fp : this.positions){
			if(fp.getTouchRegion().contains(x,y)){
				if(this.stringFretPresses.get(fp.getStringPosId()) == fp.getFretPosId()){
					//silent string
					this.stringFretPresses.put(fp.getStringPosId(), -1);
				}else{
					this.stringFretPresses.put(fp.getStringPosId(), fp.getFretPosId());
				}
				found = true;				
			}
		}		
		return found;
	}
	
	public void setHeldPositions(int[] posMap){
		for(int i = 0; i < posMap.length; i++){
			if(this.stringFretPresses.containsKey(i)){
				this.stringFretPresses.put(i, posMap[i]);
			}
		}
	}
	
	public int[] getHeldPositions(){
		int[] posMap = new int[this.stringFretPresses.size()];
		
		for(int i = 0; i < this.stringFretPresses.size(); i++){
			if(this.stringFretPresses.containsKey(i)){
				posMap[i] = this.stringFretPresses.get(i);
			}
		}
		
		return posMap;
	}

	public boolean isHighestHeldNoteOnString(FretboardPosition pos){
		int stringPosId = pos.getStringPosId();
		int fretPosId = pos.getFretPosId();
		return this.getHighestHeldNoteOnString(stringPosId) == fretPosId;
	}
	
	public int getHighestHeldNoteOnString(int stringPosId){
		if(this.stringFretPresses.containsKey(stringPosId)){
			return this.stringFretPresses.get(stringPosId);
		}else{
			return -1;
		}		
	}
	
	public boolean isSilent(int stringPosId){
		if(this.stringFretPresses.containsKey(stringPosId)){
			return this.stringFretPresses.get(stringPosId) == -1;
		}else{
			return false;
		}
	}
	
	public Iterable<FretboardPosition> getPositions() {
		return this.positions;
	}
}


