package com.jlgproject.view;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.TextView;

/**
 * @author 王锋 on 2017/8/25.
 */

public class AlwaysMarqueeTextView extends TextView {

    public AlwaysMarqueeTextView(Context context) {
        super(context);
    }

    public AlwaysMarqueeTextView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public AlwaysMarqueeTextView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }

    @Override
    public boolean isFocused() {
        return true;
    }
}
