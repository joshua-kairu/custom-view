/*
    CustomView

    Simple Android app to show the use of a custom view.

    Copyright(C) 2016 Kairu Joshua Wambugu

    This program is free software:you can redistribute it and/or modify
    it under the terms of the GNU General Public License as published by
    the Free Software Foundation,either version 3of the License,or
    (at your option)any later version.

    This program is distributed in the hope that it will be useful,
    but WITHOUT ANY WARRANTY;without even the implied warranty of
    MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.See the
    GNU General Public License for more details.

    You should have received a copy of the GNU General Public License
    along with this program.If not,see http://www.gnu.org/licenses/.
*/

package com.jlt.customview;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.jlt.customview.databinding.ActivityMainBinding;

/** The main activity */
// begin activity MainActivity
public class MainActivity extends AppCompatActivity {

    /* CONSTANTS */

    /* Integers */

    /* Strings */

    /* VARIABLES */

    /* Activity Main Bindings */

    private ActivityMainBinding binding; // ditto

    /* My Views */

//    private MyView mMyView; // ditto

    /* METHODS */

    /* Getters and Setters */

    /* Overrides */

    @Override
    // begin onCreate
    public void onCreate( Bundle savedInstanceState ) {

        // 0. super things
        // 1. use the main layout

        // 0. super things

        super.onCreate( savedInstanceState );

        // 1. use the main layout

        binding = DataBindingUtil.setContentView( this, R.layout.activity_main );

    } // end onCreate

    // begin method buttonTapped
    public void buttonTapped( View view ) {

        // 0. change circle color
        // 1. change label color
        // 2. change label text

        // 0. change circle color

//        binding.amMvCustomView.setCircleColor( Color.GREEN );

        // 1. change label color

//        binding.amMvCustomView.setLabelColor( Color.MAGENTA );

        // 2. change label text

//        binding.amMvCustomView.setCircleText( getResources().getString( R.string.help ) );

    } // end method buttonTapped

    /* Other Methods */

    /* INNER CLASSES */

} // end activity MainActivity
