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

import android.content.Context;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.widget.EditText;
import android.widget.Toast;

public class TuningListener implements OnClickListener {

	private GuitarActivity _guitarActivity;
	private EditText _input;
	
	public TuningListener(GuitarActivity guitarActivity, EditText input) {
		// TODO Auto-generated constructor stub
		this._guitarActivity = guitarActivity;
		this._input = input;
	}

	public void onClick(DialogInterface dialog, int which) {
		
		Context context = this._guitarActivity.getApplicationContext();
		CharSequence text = "undefined";
		int duration = Toast.LENGTH_SHORT;
		Toast toast;
		
		text = this._guitarActivity.changeTuning(this._input.getText().toString());
		
		toast = Toast.makeText(context, text, duration);
		toast.show();
	}

}
