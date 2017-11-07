package com.glite.popeyes.view.custom.timepicker;

import java.util.Date;

/**
 * Created by PC on 9/9/2016.
 */
public abstract class SlideTimeListener {
    /**
     * Informs the client when the user presses "OK"
     * and selects a date and time.
     *
     * @param date  The {@code Date} object that contains the date
     *              and time that the user has selected.
     */
    public abstract void onTimeSet(Date date);

    /**
     * Informs the client when the user cancels the
     * dialog by pressing Cancel, touching outside
     * the dialog or pressing the Back button.
     * This override is optional.
     */
    public void onTimeCancel()
    {

    }
}
