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

package com.nineworldsdeep.droidmuse.keys;

import android.graphics.Paint;
import android.graphics.RectF;

public abstract class UiPianoKey extends RectF{
	
	private float dotX, dotY, dotRadius;
	private int chromaticIndex;
		
	public UiPianoKey(float left, float top, float right, float bottom, int chromaticIndex) {
		super(left,top,right,bottom);
		this.chromaticIndex = chromaticIndex;
		float width = right - left;
		dotX = (left + width / 2.0f);
		dotY = (bottom - width / 2.0f);
		dotRadius = (width / 4.0f);
	}

	public int getChromaticIndex(){
		return this.chromaticIndex;
	}
	
	public float getDotX() {
		return dotX;
	}

	public float getDotY() {
		return dotY;
	}

	public float getDotRadius() {
		return dotRadius;
	}
	
	public abstract Paint getMyDotPaint();
	
	public abstract Paint getMyKeyPaint();
	
}
