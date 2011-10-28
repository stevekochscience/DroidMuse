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

import com.nineworldsdeep.droidmuse.guitar.GuitarActivity;
import com.nineworldsdeep.droidmuse.keys.KeyboardActivity;

import android.app.Activity;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class ButtonListActivity extends Activity {

	@Override
	public void onCreate(Bundle savedInstanceState){
		super.onCreate(savedInstanceState);
		setContentView(R.layout.button_list);
		
		Button clef = (Button) findViewById(R.id.ButtonClef);
		clef.setOnClickListener(new View.OnClickListener() {
			
			public void onClick(View v) {
				
				Intent i = new Intent(v.getContext(), ClefActivity.class);
				startActivity(i);
			}
		});
		
		Button frets = (Button) findViewById(R.id.ButtonFrets);
		frets.setOnClickListener(new View.OnClickListener() {
			
			public void onClick(View v) {
				
				Intent i = new Intent(v.getContext(), GuitarActivity.class);
				startActivity(i);
			}
		});
		
		Button keys = (Button) findViewById(R.id.ButtonKeys);
		keys.setOnClickListener(new View.OnClickListener() {
			
			public void onClick(View v) {
				
				Intent i = new Intent(v.getContext(), KeyboardActivity.class);
				startActivity(i);
			}
		});
	}
}
