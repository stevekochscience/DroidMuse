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

import java.util.ArrayList;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Point;
import android.graphics.PointF;
import android.graphics.RectF;
import android.graphics.Paint.Align;
import android.view.MotionEvent;
import android.view.View;

public class Fretboard extends View {

	static final CharSequence[] stringNumOpts = {"4", "5", "6"};
	static final CharSequence[] fretRangeOpts = {"1", "2", "3", 
		"4", "5", "6", "7", "8", "9", "10", "11", "12", "13", 
		"14", "15", "16", "17", "18", "19", "20", "21", "22", "23", "24"};
	
	int _height;
	int _width;
	
	Bitmap _bitmap;
	Canvas _canvas;
	Paint _paint;
	
	private LogicalFretboard _logicalBoard;
	private IMapping _mapping;
	private int numberOfStrings = 6;
	private int numberOfFrets = 4;
	private int firstDisplayFret = 1;
	
	ArrayList<Fret> frets;
	ArrayList<GuitarString> strings;
		
	
	private void calculateLinePlacements() {
		
		int width = this._width;
		int height = this._height;
		
		int lowStringMargin = 30; 
		int highStringMargin = 10;
			
		//EDIT: trying to fix the layout distribution issues
		//int zeroFretMargin = 60;
		int zeroFretMargin = (int)(this._paint.getTextSize() * 1.5); //we want truncation
		int twelfthFretMargin = zeroFretMargin;
				
		int stringPadding = 15;		
		int fretPadding = 15;		
		
		/*
		 *  EDIT: trying to fix the layout distribution issues
		int fretSpacing = (height - zeroFretMargin - twelfthFretMargin - (fretPadding * 2)) 
										/ (numberOfFrets) ;
		*/
		
		int fretSpacing = (height - zeroFretMargin - twelfthFretMargin - fretPadding) 
										/ (numberOfFrets + 1);
		
		int stringSpacing = (width - lowStringMargin - highStringMargin - stringPadding * 2) 
										/ (numberOfStrings - 1);
		
		
		Point p1, p2;
		int xVal, yVal;
		
		int lowStringEdge = lowStringMargin;
		int highStringEdge = width - highStringMargin;
		//EDIT: trying to fix the layout distribution issues
		//int zeroFretEdge = zeroFretMargin;
		int zeroFretEdge = zeroFretMargin + fretSpacing - fretPadding;
		int twelfthFretEdge = height - twelfthFretMargin;
		
		float fretLabelOffsetX = lowStringMargin / 2;
		float fretLabelOffsetY = this._paint.getTextSize() / 2;
		float stringLabelOffsetY = this._paint.getTextSize() * 1.5F;
		
		//initializing these here fixes responsivity loss on screen flip
		this.frets = new ArrayList<Fret>();
		this.strings = new ArrayList<GuitarString>();
		
		//frets		
		for(int i = 0; i <= numberOfFrets; i++){
			Point[] fretPoints = new Point[2];
			
			yVal = zeroFretEdge + fretPadding + i * fretSpacing;
			p1 = new Point(lowStringEdge, yVal);
			p2 = new Point(highStringEdge, yVal);
			fretPoints[0] = p1;
			fretPoints[1] = p2;
			PointF labelPosition = new PointF(fretLabelOffsetX, yVal + fretLabelOffsetY);
			int posId = 0;
			if(i > 0){
				posId = i + firstDisplayFret - 1;
			}
			this.frets.add(new Fret(fretPoints[0], fretPoints[1], i == 0, posId, labelPosition));
		}
		
		//strings
		for(int i = 0; i < numberOfStrings; i++){
			Point[] stringPoints = new Point[2];
			xVal = lowStringEdge + stringPadding + i * stringSpacing;
			p1 = new Point(xVal, zeroFretEdge);
			p2 = new Point(xVal, twelfthFretEdge);
			stringPoints[0] = p1;
			stringPoints[1] = p2;
			PointF labelPos = new PointF(xVal, stringLabelOffsetY);
			PointF noteLabelPos = new PointF(xVal, twelfthFretEdge + stringLabelOffsetY);
			this.strings.add(new GuitarString(stringPoints[0], stringPoints[1], i, labelPos, noteLabelPos));
		}	
		
		_logicalBoard = new LogicalFretboard(fretSpacing, stringSpacing);
	
		this.SetupPositions();
	}
	
	private void SetupPositions() {
		for(GuitarString string : this.strings){
			for(Fret fret : this.frets){
				_logicalBoard.registerPosition(string, fret);
			}
		}
	}
	
	
	
	
	
	private void drawBoard(){
		
		_bitmap = Bitmap.createBitmap(this._width, this._height, Bitmap.Config.ARGB_8888);		
		this._canvas = new Canvas(_bitmap); 
				
		for(Fret f : this.frets){
			Point[] fPts = f.getPoints();
			PointF fretLabelPos = f.getFretLabelPosition();
			String fretLabel = this._mapping.getFretViewLabel(f.getPositionalId());
			this._canvas.drawLine(fPts[0].x, fPts[0].y, fPts[1].x, fPts[1].y, this._paint);
			this._canvas.drawText(fretLabel, fretLabelPos.x, fretLabelPos.y, this._paint);
		}  
		
		for(GuitarString string : this.strings){
			Point[] sPts = string.getPoints();
			PointF stringLabelPos = string.getStringLabelPosition();
			String stringLabel = this._mapping.getStringViewLabel(string.getPositionalId());						
			this._canvas.drawLine(sPts[0].x, sPts[0].y,	sPts[1].x, sPts[1].y, this._paint);
			this._canvas.drawText(stringLabel, stringLabelPos.x, stringLabelPos.y, this._paint);
			int noteViewPos = this._logicalBoard.getHighestHeldNoteOnString(string.getPositionalId());
			if(noteViewPos > -1){
				PointF noteLabelPos = string.getNoteLabelPosition();
				String noteLabel = this._mapping.getNoteName(noteViewPos, string.getPositionalId());
				this._canvas.drawText(noteLabel, noteLabelPos.x, noteLabelPos.y, this._paint);
			} 
		}
		
		for(FretboardPosition pos : this._logicalBoard.getPositions()){
			if(this._logicalBoard.isHighestHeldNoteOnString(pos)){
				//TODO: path.addArc(oval) and path.setFill to make solid(? guessing)
				//this._paint.setStyle(Paint.Style.FILL_AND_STROKE);
				this._canvas.drawOval(pos.getDrawRegion(), this._paint);
				
			}else if(pos.isZeroFret()){
				if(this._logicalBoard.isSilent(pos.getStringPosId())){
					//draw x
					RectF reg = pos.getDrawRegion();
					this._canvas.drawLine(reg.left, reg.top,
							reg.right, reg.bottom, this._paint);
	                this._canvas.drawLine(reg.right, reg.top,
	                		reg.left, reg.bottom, this._paint);
				}
			}
		}

		//tell runtime to refresh view
		this.invalidate();
		
	}
	
	public Fretboard(Context context) {
		super(context);
		this._paint = new Paint();
		this._paint.setColor(Color.WHITE);
		this._paint.setStyle(Paint.Style.STROKE);
		this._paint.setTextAlign(Align.CENTER);
		
		this._mapping = new DefaultMapping();
	}
	
	@Override
	protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec){
		this._height = View.MeasureSpec.getSize(heightMeasureSpec);
		this._width = View.MeasureSpec.getSize(widthMeasureSpec);
		
		this.setMeasuredDimension(this._width, this._height);
				
		this.calculateLinePlacements();
		this.drawBoard();
	}

	@Override
	protected void onDraw(Canvas canvas){
		canvas.drawBitmap(this._bitmap, 0, 0, this._paint);
	}
	
	
	public boolean onTouchEvent(MotionEvent evt){
		if(evt.getAction() == MotionEvent.ACTION_DOWN){
			boolean positionFound = this._logicalBoard.togglePosition(evt.getX(), evt.getY());
			if(positionFound){
				this.drawBoard();
			}
		}
		return true;
	}

	public String changeTuning(String tuningValues) {
		String success = this._mapping.setSixStringTuning(tuningValues);
		
		this.calculateLinePlacements();
		this.drawBoard();
		
		return success;
	}

	public boolean setNumberOfStrings(int i) {
		if(i >= 4 && i <= 6){
			this.numberOfStrings = i;
			this.calculateLinePlacements();
			this.drawBoard();
			return true;
		}else{
			return false;
		}
	}

		
	public int getNumberOfStrings() {
		return numberOfStrings;
	}

	
	public int getFirstDisplayFret() {
		return firstDisplayFret;
	}

	public void setFirstDisplayFret(int firstFret) {
		// TODO Auto-generated method stub
		this.firstDisplayFret = firstFret;
		this.calculateLinePlacements();
		this.drawBoard();
	}

	public void setSelectedPositions(int[] selectedPositions) {
		// needs to be in this order, first method resets logical board
		this.calculateLinePlacements();		
		this._logicalBoard.setHeldPositions(selectedPositions);
		this.drawBoard();		
	}
	
	public int[] getSelectedPositions(){
		return this._logicalBoard.getHeldPositions();
	}

	public String getTuning() {
		return this._mapping.getSixStringTuning();
	}

	public void setTuning(String tuning) {
		this._mapping.setSixStringTuning(tuning);
	}

	public void setNumberOfStringsNoDraw(int numStrings) {
		// TODO Auto-generated method stub
		this.numberOfStrings = numStrings;
	}

	public void setFirstDisplayFretNoDraw(int firstFret) {
		// TODO Auto-generated method stub
		this.firstDisplayFret = firstFret;
	}

	public void setSelectedPositionsNoDraw(int[] posMap) {
		// TODO Auto-generated method stub
		this._logicalBoard.setHeldPositions(posMap);
	}

	public void setTuningNoDraw(String tuning) {
		// TODO Auto-generated method stub
		this._mapping.setSixStringTuning(tuning);
	}
}
