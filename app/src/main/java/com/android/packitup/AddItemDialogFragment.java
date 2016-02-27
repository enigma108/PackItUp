package com.android.packitup;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatDialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;


public class AddItemDialogFragment extends AppCompatDialogFragment {

    private static final String TAG = AddItemDialogFragment.class.getSimpleName();

    public interface OnItemAddListener {

        public void onItemAdd(String item);
    }

    OnItemAddListener mListener;

    public void setOnItemAddListener(OnItemAddListener listener) {
        mListener = listener;
    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {

        //build a custom dialog that accepts an item name
        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
        LayoutInflater inflater = (LayoutInflater) getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        final View view = inflater.inflate(R.layout.dialog_add_item, null);
        builder.setView(view)
                .setPositiveButton("Ok", new DialogInterface.OnClickListener() {

                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                        EditText et_item = (EditText) view.findViewById(R.id.et_add_text);
                        String item_text = et_item.getText().toString();

                        if (item_text.length() != 0) {

                            mListener.onItemAdd(item_text);
                        }
                    }
                })
                .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {

                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                        AddItemDialogFragment.this.getDialog().cancel();
                    }
                });
        return builder.create();
    }
}
