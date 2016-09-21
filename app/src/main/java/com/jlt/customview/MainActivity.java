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
import android.graphics.drawable.Animatable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.graphics.drawable.AnimatedVectorDrawableCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.jlt.customview.databinding.ActivityMainBinding;

import java.util.Locale;
import java.util.Random;

/** The main activity. */
// begin activity MainActivity
public class MainActivity extends AppCompatActivity {

    /* CONSTANTS */

    /* Integers */

    /* Strings */

    /* VARIABLES */

    /* Activity Main Bindings */

    private ActivityMainBinding binding; // ditto

    /* Animated Vector Drawable Compats */

    private AnimatedVectorDrawableCompat refreshVectorDrawable; // ditto, for the fab

    /* Primitives */

    private float mWindDirectionDegrees; // ditto

    /* Strings */

    private String mWindSpeed; // ditto

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
        // 2. initialize the refresh animated vector drawable

        // 0. super things

        super.onCreate( savedInstanceState );

        // 1. use the main layout

        binding = DataBindingUtil.setContentView( this, R.layout.activity_main );

        // 2. initialize the refresh animated vector drawable

        refreshVectorDrawable = AnimatedVectorDrawableCompat.create( this, R.drawable.avd_refresh );

    } // end onCreate

    // begin method buttonTapped
    public void buttonTapped( View view ) {

        // 0. animate the refresh
        // 1. generate wind data and put it in the
        // 1a. custom view
        // 1b. display text view

        // 0. animate the refresh

        if ( refreshVectorDrawable != null ) { refreshVectorDrawable.start(); }

        Drawable drawable = binding.amFabRefresh.getDrawable();

        if ( drawable instanceof Animatable ) { ( ( Animatable ) drawable ).start(); }

        // 1. generate wind data and put it in the

        String generatedWindString = generateWindData();

        // 1a. custom view

        binding.amWdasvWindSpeedAndDirection.setArrowAngle( mWindDirectionDegrees );

        binding.amWdasvWindSpeedAndDirection.setSpeedText( mWindSpeed );

        // 1b. display text view

        binding.amTvDisplay.setText( generatedWindString );

    } // end method buttonTapped

    /* Other Methods */

    /**
     * Generates some random wind direction and speed.
     *
     * @return A string of random wind direction and speed of the form
     *         <speed> <units> <direction degrees>.
     * */
    // begin method generateWindData
    private String generateWindData() {

        // 0. generate random boolean for the metric boolean
        // 1. generate random numbers between 1 and, say, 200, for wind speed
        // 2. generate random numbers between 0 and 360 for wind direction
        // 3. return a formatted string of the generated data

        // 0. generate random boolean for the metric boolean

        Random random = new Random();

        boolean isMetric = random.nextBoolean();

        // 1. generate random numbers between 1 and, say, 200, for wind speed

        float windSpeed = random.nextInt( 201 );

        // 2. generate random numbers between 0 and 360 for wind direction

        float windDirection = random.nextInt( 361 );

        // 3. return a formatted string of the generated data

        return getFormattedWind( isMetric, windSpeed, windDirection );

    } // end method generateWindData


    /**
     * Gets the wind speed and direction in a form friendly to the user.
     *
     * Copied from the Sunshine project.
     * */
    // begin method getFormattedWind
    private String getFormattedWind( boolean isMetric, float windSpeed, float windDirectionDegrees ) {

        // 0. format the wind speed based on preferred units
        // 1. determine the readable wind direction based on the wind direction degrees
        // 2. return the formatted wind text

        // 0. format the wind speed based on preferred units

        int windFormat,
                windFormatWithoutDirection; // used for setting the wind speed member string

        // begin if we are metric
        if ( isMetric == true ) {

            windFormat = R.string.format_wind_kmh;
            windFormatWithoutDirection = R.string.format_wind_kmh_sans_direction;

        } // end if we are metric

        // begin else we are not on metric
        else {

            windFormat = R.string.format_wind_mph;
            windFormatWithoutDirection = R.string.format_wind_mph_sans_direction;
            windSpeed = .621371192237334f * windSpeed; // convert the wind speed from kmh to mph

        } // end else we are not on metric

        mWindSpeed = getString( windFormatWithoutDirection, windSpeed );

        // 1. determine the readable wind direction based on the wind direction degrees

        String direction = "Unknown";

        if ( windDirectionDegrees >= 337.5 || windDirectionDegrees < 22.5 ) { direction = "N"; }

        else if ( windDirectionDegrees >= 22.5 && windDirectionDegrees < 67.5 ) { direction = "NE"; }

        else if ( windDirectionDegrees >= 67.5 && windDirectionDegrees < 112.5 ) { direction = "E"; }

        else if ( windDirectionDegrees >= 112.5 && windDirectionDegrees < 157.5 ) { direction = "SE"; }

        else if ( windDirectionDegrees >= 157.5 && windDirectionDegrees < 202.5 ) { direction = "S"; }

        else if ( windDirectionDegrees >= 202.5 && windDirectionDegrees < 247.5 ) { direction = "SW"; }

        else if ( windDirectionDegrees >= 247.5 && windDirectionDegrees < 292.5 ) { direction = "W"; }

        else if ( windDirectionDegrees >= 292.5 && windDirectionDegrees < 337.5 ) { direction = "NW"; }

        mWindDirectionDegrees = windDirectionDegrees;

        // 2. return the formatted wind text

        return String.format( Locale.ENGLISH, getString( windFormat ), windSpeed, direction );

    } // end method getFormattedWind

    /* INNER CLASSES */

} // end activity MainActivity
