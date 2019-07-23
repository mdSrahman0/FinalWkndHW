package com.example.finalwkndhw;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

import androidx.appcompat.widget.AppCompatEditText;
import androidx.core.content.res.ResourcesCompat;

public class EditTextCustom extends AppCompatEditText {

    Drawable mClearBtnImage;

    public EditTextCustom(Context context) {
        super(context);
        init();
    }

    public EditTextCustom(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public EditTextCustom(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        mClearBtnImage = ResourcesCompat.getDrawable(getResources(),
                R.drawable.ic_clear_opaque_24dp, null);

        // If the clear (X) button is tapped, clear the text.
        // We want this edit text to be useful in both left-to-right (LTR) and right-to-left (RTL)
        // language layouts (english is LTR). For our example, the "X" button is on the right side of
        // the edit text. In order to detect if the user touched the "X" button, we need to check if the
        // touch happened after the start location of the button. In LTR, the start location
        // is slightly after the position of the "X", but right before the edge of the edit text
        setOnTouchListener(new OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {

                // retrieve the "X" button that's located at the "end" parameter of the
                // setCompoundDrawablesRelativeWithIntrinsicBounds() that's found in
                // showClearXButton and hideClearXButton. will execute only if it's not null
                if ((getCompoundDrawablesRelative()[2] != null)) {
                    float clearButtonStart; // Used for LTR languages
                    float clearButtonEnd;  // Used for RTL languages
                    boolean isClearButtonClicked = false;

                    // Detect the touch in RTL or LTR layout direction.
                    if (getLayoutDirection() == LAYOUT_DIRECTION_RTL) {
                        // If RTL, get the end of the button on the left side.
                        clearButtonEnd = mClearBtnImage
                                .getIntrinsicWidth() + getPaddingStart();
                        // If the touch occurred before the end of the button,
                        // set isClearButtonClicked to true.
                        if (event.getX() < clearButtonEnd) {
                            isClearButtonClicked = true;
                        }
                    } else {
                        // Layout is LTR.
                        // Get the start of the button on the right side.
                        clearButtonStart = (getWidth() - getPaddingEnd()
                                - mClearBtnImage.getIntrinsicWidth());
                        // If the touch occurred after the start of the button,
                        // set isClearButtonClicked to true.
                        if (event.getX() > clearButtonStart) {
                            isClearButtonClicked = true;
                        }
                    }

                    // Check for actions if the button is tapped. Show the black "X" on ACTION_DOWN,
                    // and remove the "X" on ACTION_UP
                    if (isClearButtonClicked) {
                        // check for ACTION_DOWN (always occurs before ACTION_UP
                        if (event.getAction() == MotionEvent.ACTION_DOWN) {
                            // switch to black version of clear button
                            mClearBtnImage = ResourcesCompat.getDrawable
                                    (getResources(),R.drawable.ic_clear_black_24dp, null);
                            showClearXButton();
                        }
                        // Check for ACTION_UP
                        if (event.getAction() == MotionEvent.ACTION_UP) {
                            // switch to opaque version of clear button.
                            mClearBtnImage = ResourcesCompat.getDrawable(getResources(),
                                    R.drawable.ic_clear_opaque_24dp, null);
                            // clear text and hide clear button
                            getText().clear();
                            hideClearXButton();
                            return true;
                        }
                        else {
                            return false;
                        }
                    }
                }
                return false;
            }
        });

        // if text changes, show or hide the X button
        // text watcher for watching an input text field
        addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                // Not using this
            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                showClearXButton(); // while input is happening, display the X
            }

            @Override
            public void afterTextChanged(Editable editable) {
                // Not using this
            }
        });
    }

    // While the user enters text, display the X button
    private void showClearXButton() {
        // set the "x" to the end of the text, by placing it in the end position
        setCompoundDrawablesRelativeWithIntrinsicBounds
                (null, null, mClearBtnImage, null);

    }

    // If there is no text in the edittext, hide the clear "x" button. This is done
    // by putting the end parameter to null
    private void hideClearXButton() {
        setCompoundDrawablesRelativeWithIntrinsicBounds
                (null, null, null, null);
    }

}
