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

import android.animation.ValueAnimator;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.RectF;
import android.text.TextPaint;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

import com.jlt.customview.R;

/**
 * A {@link android.view.View} that shows wind direction and speed.
 * */
// begin class WindDirectionSpeedView
public class WindDirectionAndSpeedView extends View {

    /* CONSTANTS */

    /* Integers */

    /** The default difference between the radii of the outer and inner circles in dp.  */
    private static final int DEFAULT_RADIUS_DIFFERENCE_DP = 16;

    /**
     * The space between the north indicator and the outer circle.
     * It should be equal to the space between the north indicator and the inner circle. */
    private static final int NORTH_INDICATOR_VERTICAL_PADDING_DP = 4;

    /* Strings */
        
    /* VARIABLES */

    /* Matrices */

    private Matrix mArrowPathRotationMatrix; // ditto

    /* Paints */

    private Paint mCirclePaint; // ditto
    private Paint mNorthIndicatorPaint; // ditto
    private Paint mArrowPaint; // ditto

    /* Paths */

    private Path mArrowPath; // ditto

    /* Primitives */

    private int mOuterCircleColor; // ditto
    private int mInnerCircleColor; // ditto
    private int mNorthIndicatorColor; //ditto
    private int mArrowColor; // ditto
    private int mArrowAnimationDuration; // ditto

    private float mArrowAngle; // ditto
    private float mArrowAngleToDraw; // ditto, used to draw the arrow during animation
    private float mRadiusDifference; // ditto
    private float mNorthIndicatorStrokeWidth; // ditto

    /* RectFs */

    private RectF mArrowBottomArcRectF; // rectangle to hold the arc used in the arrow

    /* Strings */

    private String mNorthIndicatorText; //ditto

    /* Text Paints */

    private TextPaint mTextPaint; // used to measure the text width

    /* Value Animators */

    private ValueAnimator mArrowValueAnimator; // to animate the arrow

    /* CONSTRUCTOR */

    // begin default constructor for XML
    public WindDirectionAndSpeedView( Context context, AttributeSet attrs ) {

        // 0. super stuff
        // 1. initialize things
        // 1a. paints
        // 1b. paths
        // 1c. rectangles
        // 1d. matrices
        // 1e. animators
        // 2. initialize member variables from the XML
        // 3. animate the arrow rotation

        // 0. super stuff

        super( context, attrs );

        // 1. initialize things

        // 1a. paints

        mCirclePaint = new Paint();

        mTextPaint = new TextPaint( mCirclePaint );

        mNorthIndicatorPaint = new Paint();

        mArrowPaint = new Paint();

        // 1b. paths

        mArrowPath = new Path();

        // 1c. rectangles

        mArrowBottomArcRectF = new RectF();

        // 1d. matrices

        mArrowPathRotationMatrix = new Matrix();

        // 1e. animators

        mArrowValueAnimator = null;

        // 2. initialize member variables from the XML

        TypedArray a = context.getTheme().obtainStyledAttributes(
                attrs, R.styleable.WindDirectionAndSpeedView, 0, 0
        );

        // begin trying to get things from XML
        try {

            mOuterCircleColor = a.getColor( R.styleable.WindDirectionAndSpeedView_outerCircleColor,
                    getResources().getColor( R.color.colorPrimary )
            );

            mInnerCircleColor = a.getColor( R.styleable.WindDirectionAndSpeedView_innerCircleColor,
                    getResources().getColor( android.R.color.white )
            );

            mRadiusDifference = a.getFloat( R.styleable.WindDirectionAndSpeedView_radiusDifference,
                    dpToPx( DEFAULT_RADIUS_DIFFERENCE_DP )
            );

            mNorthIndicatorText = a.getString( R.styleable.WindDirectionAndSpeedView_northIndicatorText );

            mNorthIndicatorColor = a.getColor( R.styleable.WindDirectionAndSpeedView_northIndicatorColor,
                    getResources().getColor( android.R.color.black )
            );

            mNorthIndicatorStrokeWidth = a.getFloat(
                    R.styleable.WindDirectionAndSpeedView_northIndicatorStrokeWidth, 2.0f
            );

            mArrowColor = a.getColor( R.styleable.WindDirectionAndSpeedView_arrowColor,
                    getResources().getColor( R.color.colorAccent )
            );

            mArrowAngle = a.getFloat( R.styleable.WindDirectionAndSpeedView_arrowAngle, 0f );

            mArrowAnimationDuration = a.getInt(
                    R.styleable.WindDirectionAndSpeedView_arrowAnimationDuration,
                    getResources().getInteger( android.R.integer.config_shortAnimTime ) * 5
                    // TODO: 9/18/16 I hope this 1500 ms value isn't gratuitous
            );

        } // end trying to get things from XML

        finally { a.recycle(); }

        // 3. animate the arrow rotation

        // from 0 degrees to the current angle
        animateArrowRotation( 0, mArrowAngle );

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
    public void setNorthIndicatorColor( int northIndicatorColor ) {

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

    // begin setter for the arrow angle
    public void setArrowAngle( float newArrowAngle ) {

        // 0. take care of any invalid initialization
        // 0a. if the new angle is less than zero then the member one should be zero
        // 0b. if the new angle is greater than 360 then the member one should be within 360
        // 0c. otherwise everything is okay, the member angle should have the value of the new angle
        // 1. animate the new angle
        // NB. no need to refresh the layout since the animation cares for that

        // 0. take care of any invalid initialization

        // 0a. if the new angle is less than zero then the member one should be zero

        if ( newArrowAngle < 0f ) { mArrowAngle = 0; }

        // 0b. if the new angle is greater than 360 then the member one should be within 360

        else if ( newArrowAngle > 360f ) { mArrowAngle = 360 - newArrowAngle % 360; }

        // 0c. otherwise everything is okay, the member angle should have the value of the new angle

        else { mArrowAngle = newArrowAngle; }

        // 1. animate the new angle
        
        // we'll animate from 0 degrees to the arrow angle's degrees 
        animateArrowRotation( 0, getArrowAngle() );
        
        // NB. no need to refresh the layout since the animation cares for that
        
    } // end setter for the arrow angle

    // getter for the arrow animation duration
    public int getArrowAnimationDuration() {
        return mArrowAnimationDuration;
    }

    // begin setter for the arrow animation duration
    public void setArrowAnimationDuration( int arrowAnimationDuration ) {

        this.mArrowAnimationDuration = arrowAnimationDuration;

        // refresh the layout
        invalidate();
        requestLayout();

    } // end setter for the arrow animation duration

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

    // getter for the north indicator stroke width
    public float getNorthIndicatorStrokeWidth() {
        return mNorthIndicatorStrokeWidth;
    }

    // setter for the north indicator stroke width
    public void setNorthIndicatorStrokeWidth( float northIndicatorStrokeWidth ) {

        this.mNorthIndicatorStrokeWidth = northIndicatorStrokeWidth;

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
        // 3. color the radius difference space
        // 4. write the north indicator
        // 4a. should be as tall as the radius difference and
        // the individual vertical indicator padding can allow
        // 4b. should be on the top of the circle
        // 4c. should be on the middle of the circle
        // 4d. should use the right color
        // 4e. should use the right boldness
        // 4f. should be drawn
        // 5. draw the arrow facing the north indicator
        // 5a. draw point A facing the indicator
        // 5b. draw point B to the left of A
        // 5c. draw point C to the right of A and B
        // 5d. join the points
        // 5e. color the arrow
        // 5last. draw the arrow
        // 6. rotate the arrow as needed
        // 7. put a space (of 16 dp) between speed text and compass
        // 8. draw speed text
        // 8a. should use the correct color
        // 8b. should use the correct boldness
        // 8c. should be in the correct position
        // 8c1. height should be the same as that of the inner circle
        // 8d. should be drawn
        // last. reset
        // lasta. the arrow path
        // lastb. the rotation matrix

        // 0. super stuff

        super.onDraw( canvas );

        // 1. draw the outer circle

        // 1a. radius should be half the smaller of the lengths

        int viewQuarterWidth = ( this.getMeasuredWidth() + getPaddingLeft() + getPaddingRight() ) / 4;
        int viewQuarterHeight = ( this.getMeasuredHeight() + getPaddingTop() + getPaddingBottom() ) / 4;

        int outerRadius = -1;

        if ( viewQuarterWidth < viewQuarterHeight ) { outerRadius = viewQuarterWidth; }
        else if ( viewQuarterHeight < viewQuarterWidth ) { outerRadius = viewQuarterHeight; }

        // 1b. use the outer circle color

        mCirclePaint.setColor( mOuterCircleColor );

        // 1c. draw

        canvas.drawCircle( viewQuarterWidth, viewQuarterHeight, outerRadius, mCirclePaint );

        // 2. draw the inner circle

        // 2a. radius should be less than the outer circle by the radius difference

        float innerRadius = outerRadius - mRadiusDifference;

        // 2b. use the inner circle color

        mCirclePaint.setColor( mInnerCircleColor );

        // 2c. draw

        canvas.drawCircle( viewQuarterWidth, viewQuarterHeight, innerRadius, mCirclePaint );

        // 3. color the radius difference space -> already done by filling the circles

        // 4. write the north indicator

        // 4a. should be as tall as the radius difference and
        // the individual vertical indicator padding can allow

        float textHeight = mRadiusDifference - ( dpToPx( NORTH_INDICATOR_VERTICAL_PADDING_DP ) * 2 );
        mNorthIndicatorPaint.setTextSize( textHeight );

        // 4b. should be on the top of the circle

        // thanks to http://www.programcreek.com/java-api-examples/index.php?source_dir=andbase-master/AndBase/src/com/ab/util/AbGraphicUtil.java
        // for the TextPaint idea
        float northIndicatorHeight = mTextPaint.getTextSize();

        float northIndicatorWidth = mTextPaint.measureText( mNorthIndicatorText );

        float northIndicatorY =
                ( viewQuarterHeight - outerRadius ) + // center it vertically on top of the outer circle
                mRadiusDifference / 2 + // put its baseline halfway the distance from the outer circle to the inner circle
                ( northIndicatorHeight / 2 ) // put its baseline below the halfway by half the text height
                ; // thus putting the indicator right in the middle of the circle

        // 4c. should be on the middle of the circle

        float northIndicatorX = viewQuarterWidth - northIndicatorWidth / 2.0f;

        // 4d. should use the right color

        mNorthIndicatorPaint.setColor( mNorthIndicatorColor );

        mNorthIndicatorPaint.setStyle( Paint.Style.STROKE );

        // 4e. should use the right boldness

        mNorthIndicatorPaint.setStrokeWidth( mNorthIndicatorStrokeWidth );

        // 4f. should be drawn

        canvas.drawText( mNorthIndicatorText, northIndicatorX, northIndicatorY, mNorthIndicatorPaint );

        // 5. draw the arrow facing the north indicator

        // 5a. draw point A facing the indicator

        float aX = getOriginX( outerRadius ) + mRadiusDifference + innerRadius,
              aY = getOriginY( outerRadius ) + mRadiusDifference + dpToPx( NORTH_INDICATOR_VERTICAL_PADDING_DP );

        // 5b. draw point B to the left of A

        float bX = getOriginX( outerRadius ) + mRadiusDifference + innerRadius - mRadiusDifference,
              bY = getOriginY( outerRadius ) + mRadiusDifference + innerRadius + northIndicatorHeight;

        // 5c. draw point C to the right of A and B

        float cX = bX + ( mRadiusDifference * 2 ), cY = bY;

        // 5d. join the points

        mArrowPath.moveTo( aX, aY );
        mArrowPath.lineTo( bX, bY );
        mArrowBottomArcRectF.left = bX;
        mArrowBottomArcRectF.top = bY - northIndicatorHeight;
        mArrowBottomArcRectF.right = cX;
        mArrowBottomArcRectF.bottom = cY + northIndicatorHeight;

        // 0 starts at right
        // -180 starts at left
        mArrowPath.arcTo( mArrowBottomArcRectF, -180f, 180f );

        mArrowPath.close();

        // 5e. color the arrow

        mArrowPaint.setColor( mArrowColor );
        mArrowPaint.setStyle( Paint.Style.FILL );

        // 5last. draw the arrow -> drawn when rotated at 6

        // 6. rotate the arrow as needed

        // http://stackoverflow.com/questions/6763231/draw-rotated-path-at-particular-point
        mArrowPathRotationMatrix.postRotate( mArrowAngleToDraw, getTotalWidth() / 4, getTotalHeight() / 4 );

        mArrowPath.transform( mArrowPathRotationMatrix );

        canvas.drawPath( mArrowPath, mArrowPaint );

        // last. reset

        // lasta. the arrow path

        mArrowPath.reset();

        // lastb. the rotation matrix

        // http://android-er.blogspot.co.ke/2014/06/rotate-path-with-matrix.html, MyShape.onDraw
        mArrowPathRotationMatrix.reset();

    } // end onDraw

    /* Other Methods */

    /**
     * Converts the passed in dp value to a pixel float value.
     *
     * @param dp    The integer dp value.
     *
     * @return      A float representing the pixel value of the passed in dp.
     * */
    // method dpToPx
    private float dpToPx( int dp ) {
        return ( dp * ( getResources().getDisplayMetrics().densityDpi / 160f ) );
    }

    /**
     * Gets the smaller value of the width and the height of this view.
     * */
    // begin method getSmallerLength
    private int getSmallerLength() {

        // 0. get total width
        // 1. get total height
        // 2. return the smaller

        // 0. get total width

        int totalWidth = this.getMeasuredWidth() + getPaddingLeft() + getPaddingRight();

        // 1. get total height

        int totalHeight = this.getMeasuredHeight() + getPaddingTop() + getPaddingBottom();

        // 2. return the smaller

        return ( totalWidth < totalHeight ) ? totalWidth : totalHeight;

    } // end method getSmallerLength

    /**
     * Gets the point we will use as the horizontal origin.
     *
     * @param outerCircleRadius The radius of the outer circle,
     *                          needed to find the origin when the width of the screen
     *                          is larger than screen height.
     *
     * @return The horizontal, or X, origin.
     * */
    // begin method getOriginX
    private float getOriginX( float outerCircleRadius ) {

        // 0. if the width is larger
        // 0a. origin is left padding + (total width)/4 - outer radius
        // 1. otherwise
        // 1a. origin is left padding

        // 0. if the width is larger
        // 0a. origin is left padding + (total width)/4 - outer radius

        if ( isWidthLarger() == true ) {
            return getPaddingLeft() + getTotalWidth() / 4 - outerCircleRadius;
        }

        // 1. otherwise
        // 1a. origin is left padding

        else { return getPaddingLeft(); }

    } // end method getOriginX

    /**
     * Gets the point we will use as the vertical origin.
     *
     * @param outerCircleRadius The radius of the outer circle,
     *                          needed to find the origin when the height of the screen
     *                          is larger than screen width.
     *
     * @return The vertical, or Y, origin - mostly the top padding.
     * */
    // method getOriginY
    private float getOriginY( float outerCircleRadius ) {

        // 0. if the height is larger
        // 0a. origin is top padding + (total height)/4 - outer radius
        // 1. otherwise
        // 1a. origin is top padding

        // 0. if the height is larger
        // 0a. origin is top padding + (total height)/4 - outer radius

        if ( isWidthLarger() == false /* so the height is the larger one */ ) {
            return getPaddingTop() + getTotalHeight() / 4 - outerCircleRadius;
        }

        // 1. otherwise
        // 1a. origin is top padding

        return getPaddingTop();
    }

    /**
     * Helper method to check which is larger, width or height
     * */
    // begin method isWidthLarger
    private boolean isWidthLarger() {

        // 0. get total width
        // 1. get total height
        // 2. return whether the width is larger

        // 0. get total width
        // 1. get total height
        // 2. return whether the width is larger

        return getTotalWidth() > getTotalHeight();

    } // end method isWidthLarger

    /** Helper method to get total width. */
    // method getTotalWidth
    private int getTotalWidth() {
        return this.getMeasuredWidth() + getPaddingLeft() + getPaddingRight();
    }

    /** Helper method to get total height. */
    // method getTotalHeight
    private int getTotalHeight() {
        return this.getMeasuredHeight() + getPaddingTop() + getPaddingBottom();
    }

    /** 
     * Helper method to animate the rotation of the arrow. 
     * 
     * @param previousAngle The angle to rotate from, in degrees.
     * @param targetAngle The angle to rotate to, in degrees.
     * */
    // begin method animateArrowRotation
    private void animateArrowRotation( final float previousAngle, float targetAngle ) {

        // 0. if there is an animation happening, stop it
        // 1. we are about to animate
        // 1a. animate from the angle at step 0 to the member angle
        // 1b. for the standard android short time
        // 1c. and on every animation update
        // 1c1. store the current value of the animation as the arrow angle we should draw
        // 1c2. invalidate, thus redraw
        // 2. animate!

        // 0. if there is an animation happening, stop it

        if ( mArrowValueAnimator != null ) { mArrowValueAnimator.cancel(); }

        // 1. we are about to animate

        mArrowValueAnimator =

                // 1a. animate from the angle at step 0 to the member angle

                ValueAnimator.ofFloat( previousAngle, targetAngle )

                // 1b. for the standard android short time

                .setDuration( getAnimationDurationToUse( previousAngle, targetAngle ) );

        // 1c. and on every animation update

        // begin addUpdateListener
        mArrowValueAnimator.addUpdateListener(

                // begin new ValueAnimator.AnimatorUpdateListener
                new ValueAnimator.AnimatorUpdateListener() {

                    @Override
                    // begin onAnimationUpdate
                    public void onAnimationUpdate( ValueAnimator valueAnimator ) {

                        // 1c1. store the current value of the animation
                        //      as the arrow angle we should draw

                        mArrowAngleToDraw = ( float ) valueAnimator.getAnimatedValue();

                        Log.e( "onAnimationUpdate: ",
                                "previousAngle = " + previousAngle +
                                        " mArrowAngleToDraw = " + mArrowAngleToDraw );
                        
                        // 1c2. invalidate, thus redraw

                        WindDirectionAndSpeedView.this.invalidate();

                    } // end onAnimationUpdate

                } // end new ValueAnimator.AnimatorUpdateListener

        ); // end addUpdateListener

        // 2. animate!

        mArrowValueAnimator.start();

    } // end method animateArrowRotation

    /**
     * Returns an animation duration that is dependent on
     * the amount of change between the animation start and end degrees.
     * Courtesy of https://www.intertech.com/Blog/android-custom-view-tutorial-part-4-animation/.
     *
     * @param animationStartDegree   The degree that the animation starts from
     * @param animationEndDegree    The degree that the animation ends at
     *
     * @return The time the animation should run
     * */
    // begin method getAnimationDurationToUse
    private long getAnimationDurationToUse( float animationStartDegree, float animationEndDegree ) {

        // 0. get the difference between the two degrees
        // 1. the time used should be proportional to how much difference there is between the degrees

        // 0. get the difference between the two degrees

        float degreeDifference = Math.abs( animationEndDegree - animationStartDegree );

        // 1. the time used should be proportional to how much difference there is between the degrees

        float maxDegrees = 360f;

        return ( long ) ( mArrowAnimationDuration * ( degreeDifference / maxDegrees ) );

    } // end method getAnimationDurationToUse

    /* INNER CLASSES */

} // end class WindDirectionSpeedView