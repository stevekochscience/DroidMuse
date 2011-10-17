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

import com.nineworldsdeep.droidmuse.R;
import com.nineworldsdeep.droidmuse.R.drawable;
import com.nineworldsdeep.droidmuse.R.layout;
import com.nineworldsdeep.droidmuse.guitar.GuitarActivity;
import com.nineworldsdeep.droidmuse.keys.KeyboardActivity;

import android.app.TabActivity;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.content.res.Resources;
import android.os.Bundle;
import android.widget.TabHost;
import android.widget.TabHost.OnTabChangeListener;

public class MainTabWidget extends TabActivity {
	public void onCreate(Bundle savedInstanceState) {
	    super.onCreate(savedInstanceState);
	    setContentView(R.layout.main);

	    TabHost tabHost = getTabHost();  // The activity TabHost
	    TabHost.TabSpec spec;  // Resusable TabSpec for each tab
	    Intent intent;  // Reusable Intent for each tab

	    // Create an Intent to launch an Activity for the tab (to be reused)
	    intent = new Intent().setClass(this, ClefActivity.class);

	    // Initialize a TabSpec for each tab and add it to the TabHost
	    spec = tabHost.newTabSpec("clef").setIndicator("one").setContent(intent);
	    tabHost.addTab(spec);

	    // Do the same for the other tabs
	    intent = new Intent().setClass(this, GuitarActivity.class);
	    spec = tabHost.newTabSpec("guitar").setIndicator("two").setContent(intent);
	    tabHost.addTab(spec);

	    intent = new Intent().setClass(this, KeyboardActivity.class);
	    spec = tabHost.newTabSpec("keyboard").setIndicator("three").setContent(intent);
	    tabHost.addTab(spec);
	    
	    
	    tabHost.setOnTabChangedListener(new OnTabChangeListener(){

			public void onTabChanged(String tabId) {
								
				if(tabId.equals("keyboard") || tabId.equals("clef")){
					
					setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
				}else if(tabId.equals("guitar")){
					
					setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
				}
			}
	    	
	    	
	    });

	    tabHost.setCurrentTab(2);
	}
}
