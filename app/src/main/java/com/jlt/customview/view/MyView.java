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
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;

import com.jlt.customview.R;

/**
 * A simple custom {@link android.view.View}.
 * */
// begin class MyView
public class MyView extends View {

    /* CONSTANTS */
    
    /* Integers */
    
    /* Strings */
        
    /* VARIABLES */

    /* Paints */

    private Paint mCirclePaint; // paint for drawing this custom view

    /* Primitives */

    private int mCircleColor; // ditto
    private int mLabelColor; // ditto

    /* Strings */

    private String mCircleText; // ditto

    /* CONSTRUCTOR */

    // default super constructors

    public MyView( Context context, AttributeSet attrs, int defStyleAttr ) {
        super( context, attrs, defStyleAttr );
    }

    /** Constructor that is called when inflating a view from XML. */
    // begin constructor for XML
    public MyView( Context context, AttributeSet attrs ) {

        // 0. super stuff
        // 1. instantiate the paint
        // 2. retrieve attributes set in the XML

        // 0. super stuff

        super( context, attrs );

        // 1. instantiate the paint

        mCirclePaint = new Paint();

        // 2. retrieve attributes set in the XML

        // obtainStyledAttributes
        //  - Returns a TypedArray holding the attribute values
        //    in the specified AttributeSet (in this case attrs)
        //    that are listed in array of desired attributes (in this case R.styleable.MyView).
        TypedArray a = context.getTheme().obtainStyledAttributes( attrs, R.styleable.MyView, 0, 0 );

        // begin try to initialize the member variables from names in the attrs.xml
        try {

            mCircleText = a.getString( R.styleable.MyView_circleLabel );
            mCircleColor = a.getInteger( R.styleable.MyView_circleColor, 0 ); // get the color, or 0
            mLabelColor = a.getInteger( R.styleable.MyView_labelColor, 0 );

        } // end try to initialize the member variables from names in the attrs.xml

        finally { a.recycle(); /* so we can never use a again */ }

    } // end constructor for XML

    public MyView( Context context ) {
        super( context );
    }
    
    /* METHODS */
    
    /* Getters and Setters */
    
    /* Overrides */

//    @Override
//    // begin onMeasure
//    protected void onMeasure( int widthMeasureSpec, int heightMeasureSpec ) {
//
//        super.onMeasure( widthMeasureSpec, heightMeasureSpec );
//
//    } // end onMeasure
//
//    @Override
//    // called during layout when the size of this view has changed.
//    // begin onSizeChanged
//    protected void onSizeChanged( int w, int h, int oldw, int oldh ) {
//        super.onSizeChanged( w, h, oldw, oldh );
//    } // end onSizeChanged

    @Override
    // does the drawing.
    // begin onDraw
    protected void onDraw( Canvas canvas ) {

//        super.onDraw( canvas );

        // 0. get half of width and height
        // 1. calculate the radius based on whichever of the lengths is smaller
        // 1a. put some padding on the radius
        // 2. draw the circle
        // 2a. we want a fill
        // 2b. use anti aliasing
        // 2c. use the color specified from XML
        // 2d. draw!
        // 3. draw the text
        // 3a. should have the label color
        // 3b. should be center aligned
        // 3c. some text size
        // 3d. draw!

        // 0. get half of width and height

        int viewWidthHalf = this.getMeasuredWidth() / 2;
        int viewHeightHalf = this.getMeasuredHeight() / 2;

        // 1. calculate the radius based on whichever of the lengths is smaller

        int radius = -1;

        if ( viewWidthHalf > viewHeightHalf ) { radius = viewHeightHalf; }
        else if ( viewHeightHalf > viewWidthHalf ) { radius = viewWidthHalf; }

        // 1a. put some padding on the radius

        radius -= getContext().getResources().getInteger( R.integer.radius_padding );

        // 2. draw the circle

        // 2a. we want a fill

        mCirclePaint.setStyle( Paint.Style.FILL );

        // 2b. use anti aliasing

        mCirclePaint.setAntiAlias( true );

        // 2c. use the color specified from XML

        mCirclePaint.setColor( mCircleColor );

        // 2d. draw!

        canvas.drawCircle( viewWidthHalf, viewHeightHalf, radius, mCirclePaint );

        // 3. draw the text

        // 3a. should have the label color

        mCirclePaint.setColor( mLabelColor );

        // 3b. should be center aligned

        mCirclePaint.setTextAlign( Paint.Align.CENTER );

        // 3c. some text size

        mCirclePaint.setTextSize( getContext().getResources().getDimension( R.dimen.text_size ) );

        // 3d. draw!

        canvas.drawText( mCircleText, viewWidthHalf, viewHeightHalf, mCirclePaint );

    } // end onDraw

    /* Other Methods */
    
    /* INNER CLASSES */

} // end class MyView