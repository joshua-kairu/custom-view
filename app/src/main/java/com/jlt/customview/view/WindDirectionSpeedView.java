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

package com.jlt.customview.view;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;

import com.jlt.customview.R;

/**
 * A {@link android.view.View} that shows wind direction and speed.
 * */
// begin class WindDirectionSpeedView
public class WindDirectionSpeedView extends View {

    /* CONSTANTS */
    
    /* Integers */
    
    /* Strings */
        
    /* VARIABLES */

    /* Paints */

    private Paint mPaint; // ditto

    /* Primitives */

    private int mOuterCircleColor; // ditto
    private int mInnerCircleColor; // ditto
    private int mNorthIndicatorColor; //ditto
    private int mArrowColor; // ditto

    private float mArrowAngle; // ditto
    private float mRadiusDifference; // ditto

    /* Strings */

    private String mNorthIndicatorText; //ditto

    /* CONSTRUCTOR */

    // begin default constructor for XML
    public WindDirectionSpeedView( Context context, AttributeSet attrs ) {

        // 0. super stuff
        // 1. initialize paint
        // 2. initialize member variables from the XML

        // 0. super stuff

        super( context, attrs );

        // 1. initialize paint

        mPaint = new Paint();

        // 2. initialize member variables from the XML

        TypedArray a = context.getTheme().obtainStyledAttributes(
                attrs, R.styleable.WindDirectionSpeedView, 0, 0
        );

        // begin trying to get things from XML
        try {

            mOuterCircleColor = a.getColor( R.styleable.WindDirectionSpeedView_outerCircleColor,
                    getResources().getColor( R.color.colorPrimary )
            );

            mInnerCircleColor = a.getColor( R.styleable.WindDirectionSpeedView_innerCircleColor,
                    getResources().getColor( android.R.color.white )
            );

            mNorthIndicatorText = a.getString( R.styleable.WindDirectionSpeedView_northIndicatorText );

            mNorthIndicatorColor = a.getColor( R.styleable.WindDirectionSpeedView_northIndicatorColor,
                    getResources().getColor( android.R.color.black )
            );

            mArrowColor = a.getColor( R.styleable.WindDirectionSpeedView_arrowColor,
                    getResources().getColor( R.color.colorAccent )
            );

            mArrowAngle = a.getFloat( R.styleable.WindDirectionSpeedView_arrowAngle, 0f );

            mRadiusDifference = a.getFloat( R.styleable.WindDirectionSpeedView_radiusDifference,
                    getResources().getDimension( R.dimen.default_radius_difference )
            );

        } // end trying to get things from XML

        finally { a.recycle(); }

    } // end default constructor for XML
    
    /* METHODS */
    
    /* Getters and Setters */

    // getter for the outer circle color
    public int getOuterCircleColor() {
        return mOuterCircleColor;
    }

    // setter for the outer circle color
    public void setOuterCircleColor( int outerCircleColor ) {

        this.mOuterCircleColor = outerCircleColor;

        // refresh the layout
        invalidate();
        requestLayout();

    }

    // getter for the inner circle color
    public int getInnerCircleColor() {
        return mInnerCircleColor;
    }

    // setter for the inner circle color
    public void setInnerCircleColor( int innerCircleColor ) {

        this.mInnerCircleColor = innerCircleColor;

        // refresh the layout
        invalidate();
        requestLayout();

    }

    // getter for the north indicator color
    public int getNorthIndicatorColor() {
        return mNorthIndicatorColor;
    }

    // setter for the north indicator color
    public void setmNorthIndicatorColor( int northIndicatorColor ) {

        this.mNorthIndicatorColor = northIndicatorColor;

        // refresh the layout
        invalidate();
        requestLayout();

    }

    // getter for the arrow color
    public int getArrowColor() {
        return mArrowColor;
    }

    // setter for the arrow color
    public void setArrowColor( int arrowColor ) {

        this.mArrowColor = arrowColor;

        // refresh the layout
        invalidate();
        requestLayout();

    }

    // getter for the arrow angle
    public float getArrowAngle() {
        return mArrowAngle;
    }

    // setter for the arrow angle
    public void setArrowAngle( float arrowAngle ) {

        this.mArrowAngle = arrowAngle;

        // refresh the layout
        invalidate();
        requestLayout();

    }

    // getter for the north indicator text
    public String getNorthIndicatorText() {
        return mNorthIndicatorText;
    }

    // setter for the north indicator text
    public void setNorthIndicatorText( String northIndicatorText ) {

        this.mNorthIndicatorText = northIndicatorText;

        // refresh the layout
        invalidate();
        requestLayout();

    }

    // getter for the radius difference
    public float getRadiusDifference() {
        return mRadiusDifference;
    }

    // setter for the radius difference
    public void setRadiusDifference( float radiusDifference ) {

        this.mRadiusDifference = radiusDifference;

        // refresh the layout
        invalidate();
        requestLayout();

    }

    /* Overrides */

    @Override
    // begin onDraw
    protected void onDraw( Canvas canvas ) {

        // 0. super stuff
        // 1. draw the outer circle
        // 1a. radius should be half the smaller of the lengths
        // 1b. use the outer circle color
        // 1c. draw
        // 2. draw the inner circle
        // 2a. radius should be less than the outer circle by the radius difference
        // 2b. use the inner circle color
        // 2c. draw

        // 0. super stuff

        super.onDraw( canvas );

        // 1. draw the outer circle

        // 1a. radius should be half the smaller of the lengths

        int viewHalfWidth = ( this.getMeasuredWidth() + getPaddingLeft() + getPaddingRight() ) / 2;
        int viewHalfHeight = ( this.getMeasuredHeight() + getPaddingTop() + getPaddingBottom() ) / 2;

        int radius = -1;

        if ( viewHalfWidth < viewHalfHeight ) { radius = viewHalfWidth; }
        else if ( viewHalfHeight < viewHalfWidth ) { radius = viewHalfHeight; }

        // 1b. use the outer circle color

        mPaint.setColor( mOuterCircleColor );

        // 1c. draw

        canvas.drawCircle( viewHalfWidth, viewHalfHeight, radius, mPaint );

        // 2. draw the inner circle

        // 2a. radius should be less than the outer circle by the radius difference

        radius -= mRadiusDifference;

        // 2b. use the inner circle color

        mPaint.setColor( mInnerCircleColor );

        // 2c. draw

        canvas.drawCircle( viewHalfWidth, viewHalfHeight, radius, mPaint );


    } // end onDraw

    /* Other Methods */
    
    /* INNER CLASSES */

} // end class WindDirectionSpeedView