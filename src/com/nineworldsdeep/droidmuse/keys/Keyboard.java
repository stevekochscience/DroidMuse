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

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.view.MotionEvent;
import android.view.View;

public class Keyboard extends View {
	
	UiPianoKey[] whiteKeys = new UiPianoKey[14];
	UiPianoKey[] blackKeys = new UiPianoKey[10];

	int _height;
	int _width;
	
	int whiteKeyHeight, whiteKeyWidth, blackKeyHeight, blackKeyWidth;
	int outerMargin = 10;
	
	Bitmap _bitmap;
	Canvas _canvas;
	Paint _paint;
	
	private LogicalKeyboard _logicalBoard = new LogicalKeyboard();	
	
	public Keyboard(Context context) {
		super(context);
		
		this._paint = new Paint();
		this._paint.setColor(Color.WHITE);
		this._paint.setStyle(Paint.Style.STROKE);
	}	
	
	private void calculateKeys(){
		
		float top = this.outerMargin;
		float whiteKeyLeft = this.outerMargin;
		float whiteKeyRight = 0;
		float whiteKeyBottom = top + whiteKeyHeight;
		float blackKeyLeft = 0;
		float blackKeyRight = 0;
		float blackKeyBottom = top + blackKeyHeight;
		
		int blackKeyIndex = 0;
		int chromaticIndex = 0;
		
		for(int i = 0; i < whiteKeys.length; i++){
			
			whiteKeyRight = whiteKeyLeft + this.whiteKeyWidth;
			blackKeyLeft = whiteKeyLeft + whiteKeyWidth - blackKeyWidth / 2;
			blackKeyRight = blackKeyLeft + this.blackKeyWidth;
			
			this.whiteKeys[i] = 
				new WhiteUiKey(whiteKeyLeft,top,whiteKeyRight,whiteKeyBottom,chromaticIndex);
			chromaticIndex++;
			
			//since black keys are positioned relative to whiteKeys, we need
			//to skip over those whiteKey indexes for which no blackKey is drawn
			switch(i){
				case 2:	case 6:	case 9:	case 13:
					//do nothing
					break;			
				default:
					this.blackKeys[blackKeyIndex] = 
						new BlackUiKey(blackKeyLeft,top,blackKeyRight,blackKeyBottom,chromaticIndex);
					blackKeyIndex++;
					chromaticIndex++;
					break;
			}
			
			whiteKeyLeft = whiteKeyRight;
		}
	}

	@Override
	protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec){
		this._height = View.MeasureSpec.getSize(heightMeasureSpec);
		this._width = View.MeasureSpec.getSize(widthMeasureSpec);
		
		this.whiteKeyHeight = _height - 2 * outerMargin;
		this.whiteKeyWidth = (_width - 2 * outerMargin) / 14;
		this.blackKeyHeight = this.whiteKeyHeight * 2 / 3;
		this.blackKeyWidth = (_width - 2 * outerMargin) / 24;
		
		this.setMeasuredDimension(_width, _height);		
		
		this._bitmap = Bitmap.createBitmap(_width, _height, Bitmap.Config.ARGB_8888);
		this._canvas = new Canvas(_bitmap);
		
		this.calculateKeys();
		this.drawKeyboard();
	}

	@Override
	protected void onDraw(Canvas canvas){
		canvas.drawBitmap(this._bitmap, 0, 0, this._paint);
	}
	
	private void drawKeyboard() {
		
		this._canvas.drawColor(Color.BLACK);
		
		//we keep the two key sets separate so we can ensure that 
		//the black keys get drawn over the top of the white keys
		drawKeyArray(whiteKeys);
		drawKeyArray(blackKeys);
				
		invalidate();
	}

	private void drawKeyArray(UiPianoKey[] keys) {
		for(UiPianoKey key : keys){
			
			this._canvas.drawRect(key, key.getMyKeyPaint());
			if(this._logicalBoard.get(key.getChromaticIndex())){
				this._canvas.drawCircle(key.getDotX(), key.getDotY(), key.getDotRadius(), key.getMyDotPaint());
			} 
		}
	}
	
	@Override
	public boolean onTouchEvent(MotionEvent evt){
		if(evt.getAction() == MotionEvent.ACTION_DOWN){
			boolean positionFound = togglePosition(evt.getX(), evt.getY());
			if(positionFound){
				this.drawKeyboard();
			}
		}
		return true;
	}

	private boolean togglePosition(float x, float y) {
		
		boolean found = false;	    
				
		found = toggleKey(x,y,blackKeys);
		
		if(!found){
			
			found = toggleKey(x,y,whiteKeys);
		}
		
		
		
		return found;
	}
	
	private boolean toggleKey(float x, float y, UiPianoKey[] keys){
		
		boolean found = false;
		
		for(UiPianoKey key : keys){
			
			if(!found && key.contains(x, y)){
								
				this._logicalBoard.toggle(key.getChromaticIndex());
				found = true;
			}
		}
		
		return found;
	}
	
	
}
