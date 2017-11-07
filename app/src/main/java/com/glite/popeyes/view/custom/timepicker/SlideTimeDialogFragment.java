package com.glite.popeyes.view.custom.timepicker;

import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.text.format.DateUtils;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.Button;

import com.glite.popeyes.R;
import com.glite.popeyes.view.custom.CustomViewPager;
import com.glite.popeyes.view.custom.datepicker.DateFragment;
import com.glite.popeyes.view.custom.datepicker.SlideDatePicker;

import java.util.Calendar;
import java.util.Date;

/**
 * Created by PC on 9/9/2016.
 */
public class SlideTimeDialogFragment extends DialogFragment implements TimeFragment.TimeChangedListener{

    /**
     * and {@link CustomViewPager}.</p>
     *
     * <p>The {@code CustomViewPager} contains the {@link DateFragment} and {@link TimeFragment}.</p>
     *
     * <p>This {@code DialogFragment} is managed by {@link SlideDatePicker}.</p>
     *
     * @author jjobes
     *
     */
        public static final String TAG_SLIDE_DATE_TIME_DIALOG_FRAGMENT = "tagSlideDateTimeDialogFragment";

        private static SlideTimeListener mListener;

        private Context mContext;
        private CustomViewPager mViewPager;
        private ViewPagerAdapter mViewPagerAdapter;
        private View mButtonHorizontalDivider;
        private View mButtonVerticalDivider;
        private Button mOkButton;
        private Button mCancelButton;
        private Date mInitialDate;
        private int mTheme;
        private int mIndicatorColor;
        private Date mMinDate;
        private Date mMaxDate;
        private boolean mIsClientSpecified24HourTime;
        private boolean mIs24HourTime;
        private Calendar mCalendar;
        private int mDateFlags =
                DateUtils.FORMAT_SHOW_WEEKDAY |
                        DateUtils.FORMAT_SHOW_DATE |
                        DateUtils.FORMAT_ABBREV_ALL;

        public SlideTimeDialogFragment()
        {
            // Required empty public constructor
        }

        /**
         * <p>Return a new instance of {@code SlideDateDialogFragment} with its bundle
         * filled with the incoming arguments.</p>
         *
         * <p>Called by {@link SlideDatePicker#show()}.</p>
         *
         * @param listener
         * @param initialDate
         * @param minDate
         * @param maxDate
         * @param isClientSpecified24HourTime
         * @param is24HourTime
         * @param theme
         * @param indicatorColor
         * @return
         */
        public static SlideTimeDialogFragment newInstance(SlideTimeListener listener,
                                                          Date initialDate, Date minDate, Date maxDate, boolean isClientSpecified24HourTime,
                                                          boolean is24HourTime, int theme, int indicatorColor)
        {
            mListener = listener;

            // Create a new instance of SlideDateDialogFragment
            SlideTimeDialogFragment dialogFragment = new SlideTimeDialogFragment();

            // Store the arguments and attach the bundle to the fragment
            Bundle bundle = new Bundle();
            bundle.putSerializable("initialDate", initialDate);
            bundle.putSerializable("minDate", minDate);
            bundle.putSerializable("maxDate", maxDate);
            bundle.putBoolean("isClientSpecified24HourTime", isClientSpecified24HourTime);
            bundle.putBoolean("is24HourTime", is24HourTime);
            bundle.putInt("theme", theme);
            bundle.putInt("indicatorColor", indicatorColor);
            dialogFragment.setArguments(bundle);

            // Return the fragment with its bundle
            return dialogFragment;
        }

        @Override
        public void onAttach(Activity activity)
        {
            super.onAttach(activity);

            mContext = activity;
        }

        @Override
        public void onCreate(Bundle savedInstanceState)
        {
            super.onCreate(savedInstanceState);

            setRetainInstance(true);

            unpackBundle();

            mCalendar = Calendar.getInstance();
            mCalendar.setTime(mInitialDate);

            switch (mTheme)
            {
                case SlideDatePicker.HOLO_DARK:
                    setStyle(DialogFragment.STYLE_NO_TITLE, android.R.style.Theme_Holo_Dialog_NoActionBar);
                    break;
                case SlideDatePicker.HOLO_LIGHT:
                    setStyle(DialogFragment.STYLE_NO_TITLE, android.R.style.Theme_Holo_Light_Dialog_NoActionBar);
                    break;
                default:  // if no theme was specified, default to holo light
                    setStyle(DialogFragment.STYLE_NO_TITLE, android.R.style.Theme_Holo_Light_Dialog_NoActionBar);
            }
        }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
        {
            View view = inflater.inflate(R.layout.slide_time_picker, container);

            setPositionOfDialog();
            setupViews(view);
            customizeViews();
            initViewPager();
            initButtons();

            return view;
        }

        @Override
        public void onDestroyView()
        {
            // Workaround for a bug in the compatibility library where calling
            // setRetainInstance(true) does not retain the instance across
            // orientation changes.
            if (getDialog() != null && getRetainInstance())
            {
                getDialog().setDismissMessage(null);
            }

            super.onDestroyView();
        }

        private void setPositionOfDialog(){
            getDialog().getWindow().setGravity(Gravity.CENTER_HORIZONTAL | Gravity.TOP);
            WindowManager.LayoutParams p = getDialog().getWindow().getAttributes();
            p.width = ViewGroup.LayoutParams.MATCH_PARENT;
            p.height = 183;
            p.softInputMode = WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_VISIBLE;
            p.x = 25;
            p.y = 2000;

            getDialog().getWindow().setAttributes(p);
        }

        private void unpackBundle()
        {
            Bundle args = getArguments();

            mInitialDate = (Date) args.getSerializable("initialDate");
            mMinDate = (Date) args.getSerializable("minDate");
            mMaxDate = (Date) args.getSerializable("maxDate");
            mIsClientSpecified24HourTime = args.getBoolean("isClientSpecified24HourTime");
            mIs24HourTime = args.getBoolean("is24HourTime");
            mTheme = args.getInt("theme");
            mIndicatorColor = args.getInt("indicatorColor");
        }

        private void setupViews(View v)
        {
            mViewPager = (CustomViewPager) v.findViewById(R.id.viewTimePager);
            mButtonHorizontalDivider = v.findViewById(R.id.buttonHorizontalDivider);
            mButtonVerticalDivider = v.findViewById(R.id.buttonVerticalDivider);
            mOkButton = (Button) v.findViewById(R.id.okButton);
            mCancelButton = (Button) v.findViewById(R.id.cancelButton);
        }

        private void customizeViews()
        {
            int lineColor = mTheme == SlideDatePicker.HOLO_DARK ?
                    getResources().getColor(R.color.gray_holo_dark) :
                    getResources().getColor(R.color.gray_holo_light);

            // Set the colors of the horizontal and vertical lines for the
            // bottom buttons depending on the theme.
            switch (mTheme)
            {
                case SlideDatePicker.HOLO_LIGHT:
                case SlideDatePicker.HOLO_DARK:
                    mButtonHorizontalDivider.setBackgroundColor(lineColor);
                    mButtonVerticalDivider.setBackgroundColor(lineColor);
                    break;

                default:  // if no theme was specified, default to holo light
                    mButtonHorizontalDivider.setBackgroundColor(getResources().getColor(R.color.gray_holo_light));
                    mButtonVerticalDivider.setBackgroundColor(getResources().getColor(R.color.gray_holo_light));
            }
        }

        private void initViewPager()
        {
            mViewPagerAdapter = new ViewPagerAdapter(getChildFragmentManager());
            mViewPager.setAdapter(mViewPagerAdapter);
        }

        private void initButtons()
        {
            mOkButton.setOnClickListener(new View.OnClickListener() {

                @Override
                public void onClick(View v)
                {
                    if (mListener == null)
                    {
                        throw new NullPointerException(
                                "Listener no longer exists for mOkButton");
                    }

                    mListener.onTimeSet(new Date(mCalendar.getTimeInMillis()));

                    dismiss();
                }
            });

            mCancelButton.setOnClickListener(new View.OnClickListener() {

                @Override
                public void onClick(View v)
                {
                    if (mListener == null)
                    {
                        throw new NullPointerException(
                                "Listener no longer exists for mCancelButton");
                    }

                    mListener.onTimeCancel();

                    dismiss();
                }
            });
        }

        /**
         * <p>The callback used by the TimePicker to update {@code mCalendar} as
         * the user changes the time. Each time this is called, we also update
         * the text on the time tab to reflect the time the user has currenly
         * selected.</p>
         *
         * <p>Implements the {@link TimeFragment.TimeChangedListener}
         * interface.</p>
         */
        @Override
        public void onTimeChanged(int hour, int minute)
        {
            mCalendar.set(Calendar.HOUR_OF_DAY, hour);
            mCalendar.set(Calendar.MINUTE, minute);
        }

    /**
     * <p>Called when the user clicks outside the dialog or presses the <b>Back</b>
     * button.</p>
     *
     * <p><b>Note:</b> Actual <b>Cancel</b> button clicks are handled by {@code mCancelButton}'s
     * event handler.</p>
     */
    @Override
    public void onCancel(DialogInterface dialog)
    {
        super.onCancel(dialog);

        if (mListener == null)
        {
            throw new NullPointerException(
                    "Listener no longer exists in onCancel()");
        }

        mListener.onTimeCancel();
    }

    private class ViewPagerAdapter extends FragmentPagerAdapter
    {
        public ViewPagerAdapter(FragmentManager fm)
        {
            super(fm);
        }

        @Override
        public Fragment getItem(int position)
        {
            switch (position)
            {
                case 0:
                    TimeFragment timeFragment = TimeFragment.newInstance(
                            mTheme,
                            mCalendar.get(Calendar.HOUR_OF_DAY),
                            mCalendar.get(Calendar.MINUTE),
                            mIsClientSpecified24HourTime,
                            mIs24HourTime);
                    timeFragment.setTargetFragment(SlideTimeDialogFragment.this, 200);
                    return timeFragment;
                default:
                    return null;
            }
        }

        @Override
        public int getCount()
        {
            return 1;
        }
    }

}
