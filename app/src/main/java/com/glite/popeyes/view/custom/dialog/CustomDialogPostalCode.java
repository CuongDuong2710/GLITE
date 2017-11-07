package com.glite.popeyes.view.custom.dialog;

import android.app.Dialog;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;

import com.glite.popeyes.R;
import com.glite.popeyes.view.custom.CustomButtonIcon;
import com.glite.popeyes.view.custom.VerdanaTextView;

/**
 * Created by QUOC CUONG on 06/09/2016.
 */
public class CustomDialogPostalCode extends DialogFragment {

    private static final String ARG_TITLE = "arg_title";
    private static final String ARG_MESSAGE = "arg_message";

    private String mTitle;
    private String mMessage;

    public CustomDialogPostalCode() {
    }

    public static CustomDialogPostalCode newInstance(final String title, final String message) {
        CustomDialogPostalCode customDialogPostalCode = new CustomDialogPostalCode();
        Bundle bundle = new Bundle();
        bundle.putString(ARG_TITLE, title);
        bundle.putString(ARG_MESSAGE, message);
        customDialogPostalCode.setArguments(bundle);
        return customDialogPostalCode;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mTitle = getArguments().getString(ARG_TITLE);
        mMessage = getArguments().getString(ARG_MESSAGE);
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        LayoutInflater inflater = getActivity().getLayoutInflater();
        View customView = inflater.inflate(R.layout.dialog_our_stores_are_closed, null);
        VerdanaTextView title = (VerdanaTextView) customView.findViewById(R.id.text_title);
        VerdanaTextView message = (VerdanaTextView) customView.findViewById(R.id.text_message);

        CustomButtonIcon button_ok = (CustomButtonIcon) customView.findViewById(R.id.btn_ok);

        title.setText(mTitle);
        message.setText(mMessage);

        button_ok.setOnClickListener(view -> dismiss());

        builder.setView(customView);

        return builder.create();
    }
}
