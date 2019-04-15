package tech.iwish.ONhome.helper;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Typeface;

import android.util.AttributeSet;

import tech.iwish.ONhome.R;
//import android.widget.TextView;

/**
 * Created by iwish on 5/29/2017.
 */

public class ProximaFonts extends android.support.v7.widget.AppCompatTextView {
    public ProximaFonts(Context context) {
        super(context);
        changeFont(null);
    }


    public ProximaFonts(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        changeFont(attrs);
    }

    public ProximaFonts(Context context, AttributeSet attrs) {
        super(context, attrs);
        changeFont(attrs);
    }


    public void changeFont(AttributeSet attributeSet) {
        Typeface typeface = Typeface.createFromAsset(getContext().getAssets(), "fonts/Proxima_Nova_Light.ttf");
        if (attributeSet != null) {

            TypedArray a = getContext().obtainStyledAttributes(attributeSet, R.styleable.ProximaFonts);
            String fontvalue = a.getString(R.styleable.FontIcon_iconname);
            try {
                if (fontvalue != null) {
                    switch (fontvalue) {
                        case "1":
                            typeface = Typeface.createFromAsset(getContext().getAssets(), "fonts/Proxima_Nova_Light.ttf");
                            break;
                        case "2":
                            typeface = Typeface.createFromAsset(getContext().getAssets(), "fonts/Proxima_Nova_Regular.ttf");
                            break;
                        case "3":
                            typeface = Typeface.createFromAsset(getContext().getAssets(), "fonts/Proxima_Nova_Bold.ttf");
                            break;
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
            setTypeface(typeface);

            a.recycle();


        }
    }

}
