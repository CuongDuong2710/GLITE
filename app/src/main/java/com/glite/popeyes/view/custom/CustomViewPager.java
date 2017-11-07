package com.glite.popeyes.view.custom;

import android.content.Context;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewConfiguration;
import android.widget.DatePicker;
import android.widget.TimePicker;

import com.glite.popeyes.R;

/**
 * A custom {@link android.support.v4.view.ViewPager} implementation that corrects
 * the height of the ViewPager and also dispatches touch events to either the ViewPager
 * or the date or time picker
 *
 * @author jjobes
 *
 */
public class CustomViewPager extends ViewPager
{
    private DatePicker mDatePicker;
    private TimePicker mTimePicker;

    public CustomViewPager(Context context)
    {
        super(context);
    }

    public CustomViewPager(Context context, AttributeSet attrs)
    {
        super(context, attrs);
    }

    /**
     * Setting wrap_content on a ViewPager's layout_height in XML
     * doesn't seem to be recognized and the ViewPager will fill the
     * height of the screen regardless. We'll force the ViewPager to
     * have the same height as its immediate child.
     *
     * Thanks to alexrainman for the bugfix!
     */
    @Override
    public void onMeasure(int widthMeasureSpec, int heightMeasureSpec)
    {
        int height = 0;

        for (int i = 0; i < getChildCount(); i++)
        {
            View child = getChildAt(i);
            child.measure(widthMeasureSpec, MeasureSpec.makeMeasureSpec(0, MeasureSpec.UNSPECIFIED));
            int h = child.getMeasuredHeight();
            if (h > height)
                height = h;
        }

        heightMeasureSpec = MeasureSpec.makeMeasureSpec(height, MeasureSpec.EXACTLY);

        super.onMeasure(widthMeasureSpec, heightMeasureSpec);

        mDatePicker = (DatePicker) findViewById(R.id.datePicker);
        mTimePicker = (TimePicker) findViewById(R.id.timePicker);
    }

    /**
     * When the user swipes their finger horizontally, dispatch
     * those touch events to the ViewPager. When they swipe
     * vertically, dispatch those touch events to the date or
     * time picker (depending on which page we're currently on).
     *
     * @param event
     */
    @Override
    public boolean dispatchTouchEvent(MotionEvent event)
    {
        // dispatch the event to the DatePicker or TimePicker,
        if (mDatePicker != null)
            mDatePicker.dispatchTouchEvent(event);

        if (mTimePicker != null)
            mTimePicker.dispatchTouchEvent(event);

        return super.onTouchEvent(event);
    }
}
