/*
    CustomView

    Simple Android app to show the use of a custom view.

    Copyright(C)2016Kairu Joshua Wambugu

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

package com.jlt.customview.view;

import android.content.Context;
import android.graphics.Canvas;
import android.util.AttributeSet;
import android.view.View;

/**
 * A simple custom {@link android.view.View}.
 * */
// begin class MyView
public class MyView extends View {

    /* CONSTANTS */
    
    /* Integers */
    
    /* Strings */
        
    /* VARIABLES */
    
    /* CONSTRUCTOR */

    // default super constructors

    public MyView( Context context, AttributeSet attrs, int defStyleAttr ) {
        super( context, attrs, defStyleAttr );
    }

    public MyView( Context context, AttributeSet attrs ) {
        super( context, attrs );
    }

    public MyView( Context context ) {
        super( context );
    }
    
    /* METHODS */
    
    /* Getters and Setters */
    
    /* Overrides */

    @Override
    // begin onMeasure
    protected void onMeasure( int widthMeasureSpec, int heightMeasureSpec ) {

        super.onMeasure( widthMeasureSpec, heightMeasureSpec );

    } // end onMeasure

    @Override
    // called during layout when the size of this view has changed.
    // begin onSizeChanged
    protected void onSizeChanged( int w, int h, int oldw, int oldh ) {
        super.onSizeChanged( w, h, oldw, oldh );
    } // end onSizeChanged

    @Override
    // does the drawing.
    // begin onDraw
    protected void onDraw( Canvas canvas ) {
        super.onDraw( canvas );
    } // end onDraw

    /* Other Methods */
    
    /* INNER CLASSES */

} // end class MyView