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

package com.nineworldsdeep.droidmuse;

import java.util.HashMap;

public class Note {

	private int positionalValue;
	private String noteName;
	
	
	public Note(int notePosVal, String name) {
		//TODO: this should be posVal
		//logic is currently for absVal (prototyping)
		this.positionalValue = notePosVal;
		this.noteName = name; 
	}
	

	


	public int getPositionalValue() {
		return positionalValue;
	}

	public String getNoteName() {
		return noteName;
	}

}
