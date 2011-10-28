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

import java.util.HashMap;

import com.nineworldsdeep.droidmuse.Note;
import com.nineworldsdeep.droidmuse.NoteHelper;

public class DefaultMapping implements IMapping {

	private HashMap<Integer,Note> stringTunings;
	private NoteHelper helper = new NoteHelper();
	
	
	public DefaultMapping(){
		//TODO: map to midi
		//currently just works off absoluteVal, should be midi positionalVal
		this.stringTunings = new HashMap<Integer,Note>();
		
		Note eNote = new Note(4, helper.toNoteName(4));
		Note aNote = new Note(9, helper.toNoteName(9));
		Note dNote = new Note(2, helper.toNoteName(2));
		Note gNote = new Note(7, helper.toNoteName(7));
		Note bNote = new Note(11, helper.toNoteName(11));
		Note eNoteHigh = new Note(4, helper.toNoteName(4)); 
		
		this.stringTunings.put(0, eNote);
		this.stringTunings.put(1, aNote);
		this.stringTunings.put(2, dNote);
		this.stringTunings.put(3, gNote);
		this.stringTunings.put(4, bNote);
		this.stringTunings.put(5, eNoteHigh);
		
		//this.setSixStringTuning("E,A,D,G,B,E");
	}
	
	public String setSixStringTuning(String tuning){
		String[] notes = tuning.split(",");
		if(notes.length != 6){
			return "tuning failed length check";
		}
				
		
		for(int i = 0; i < 6; i++){
			if(!helper.isValidNoteName(notes[i])){
				return "tuning failed name check";
			}
		}
		
		for(int i = 0; i < 6; i++){
			if(!this.stringTunings.containsKey(i)){
				return "tuning failed key check";
			}
		}
		
		for(int i = 0; i < 6; i++){
			this.stringTunings.put(i, helper.fromName(notes[i]));
		}
		return "tuning changed.";
	}
	
	public String getFretViewLabel(int viewFretPosition){
		return "" + viewFretPosition;
	}

	public String getNoteName(int viewFretPosition, int viewStringPosition) {
		int zeroFretPosVal = this.stringTunings.get(viewStringPosition).getPositionalValue();
		int posVal = zeroFretPosVal + viewFretPosition;
		return helper.toNoteName(posVal);
	}

	public String getStringViewLabel(int viewStringPosition) {
	
		String s = "?";

		if(this.stringTunings.containsKey(viewStringPosition)){
			s = this.stringTunings.get(viewStringPosition).getNoteName();
		}

		return s;
	
	}

	public String getSixStringTuning() {

		String s = "";
		
		for(int i = 0; i < this.stringTunings.size(); i++){
			if(this.stringTunings.containsKey(i)){
				if(s.length() > 0){
					s += ",";
				}
				s += this.stringTunings.get(i).getNoteName();
			}
		}
		
		return s;
	}

}
