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

import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Paint.Style;

public class WhiteUiKey extends UiPianoKey {

	private static final Paint myDotPaint, myKeyPaint;
	
	static {
		myDotPaint = new Paint();
		myKeyPaint = new Paint();
	
		myDotPaint.setStyle(Style.FILL_AND_STROKE);
		myDotPaint.setColor(Color.WHITE);
		
		myKeyPaint.setStyle(Style.STROKE);
		myKeyPaint.setColor(Color.WHITE);
	}

	public WhiteUiKey(float left, float top, float right, float bottom, int chromaticIndex) {
		super(left, top, right, bottom, chromaticIndex);
		
	}
	
	public Paint getMyDotPaint() {

		return myDotPaint;
	}
	
	public Paint getMyKeyPaint() {
		
		return myKeyPaint;
	}

}
