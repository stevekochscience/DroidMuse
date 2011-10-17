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

package com.nineworldsdeep.droidmuse;

import java.util.HashMap;

public class NoteHelper {

	private HashMap<String,Integer> absValMap = 
						new HashMap<String,Integer>();
	
	public NoteHelper(){
		for(int i = 0; i < 12; i++){
			Note n = new Note(i, this.toNoteName(i));
			this.absValMap.put(n.getNoteName().toLowerCase(), i);
		}
	}
	
	public int toAbsValue(int noteVal){
		while(noteVal < 0){
			noteVal += 12;
		}
		while(noteVal > 11){
			noteVal -= 12;
		}
		return noteVal;
	}
	
	
	public Note fromName(String name){
		int i = 0;
		if(this.absValMap.containsKey(name.toLowerCase())){
			i = this.absValMap.get(name.toLowerCase());
		}
		return new Note(i, this.toNoteName(i));
	}

	public boolean isValidNoteName(String name){
		return this.absValMap.containsKey(name.toLowerCase());
	}
	
	public String toNoteName(int posVal) {
		
		posVal = this.toAbsValue(posVal);
		
		String name = "?";
		//TODO: name parse should be in scale
		//then based on the scale you will know whether 
		//accidentals should be displayed as flats or sharps
		
		switch(posVal){
		case 0:
			name = "C";
			break;
		case 1:
			name = "C#/Db";
			break;
		case 2:
			name = "D";
			break;
		case 3:
			name = "D#/Eb";
			break;
		case 4:
			name = "E";
			break;
		case 5:
			name = "F";
			break;
		case 6:
			name = "F#/Gb";
			break;
		case 7:
			name = "G";
			break;
		case 8:
			name = "G#/Ab";
			break;
		case 9:
			name = "A";
			break;
		case 10:
			name = "A#/Bb";
			break;
		case 11:
			name = "B";
			break;		
		}
		return name;
	}
}
