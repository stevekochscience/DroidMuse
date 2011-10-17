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

package com.nineworldsdeep.droidmuse.guitar;

import android.graphics.Point;
import android.graphics.PointF;

public class Fret {

	Point[] points = new Point[2];
	boolean zeroFret = false;
	int positionalId;
	private PointF labelPosition;
	
	public Fret(Point left, Point right, boolean isZeroFret, int positionalId, PointF labelPosition){
		this.points[0] = left;
		this.points[1] = right;
		this.zeroFret = isZeroFret;
		this.positionalId = positionalId;
		this.labelPosition = labelPosition;
	}

	public Point[] getPoints() {
		return points;
	}

	public boolean isZeroFret() {
		return zeroFret;
	}

	public int getPositionalId() {
		return positionalId;
	}

	public PointF getFretLabelPosition() {
		return labelPosition;
	}	
	
}
