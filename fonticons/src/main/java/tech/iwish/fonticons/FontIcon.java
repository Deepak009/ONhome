package tech.iwish.fonticons;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Typeface;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.util.AttributeSet;
import android.widget.TextView;

/**
 * Created by iwish on 5/29/2017.
 */

public class FontIcon extends TextView {
    public FontIcon(Context context) {
        super(context);
        setFont(null);
    }

    public FontIcon(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        setFont(attrs);
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    public FontIcon(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        setFont(attrs);
    }

    public FontIcon(Context context, AttributeSet attrs) {
        super(context, attrs);
        setFont(attrs);
    }

   public void setFont(AttributeSet attributeSet)
   {
    if(!isInEditMode()) {
        Typeface typeface = Typeface.createFromAsset(getContext().getAssets(), "fonts/fontawesome.ttf");
        setTypeface(typeface);
        if (attributeSet != null) {

            TypedArray a = getContext().obtainStyledAttributes(attributeSet, R.styleable.FontIcon);

            String fontName = a.getString(R.styleable.FontIcon_iconname);
            try {
                if (fontName != null) {

                    setText(getResources().getString(getResources().getIdentifier(fontName, "string", getContext().getPackageName())));
                }
            } catch (Exception e) {
                e.printStackTrace();
            }

            a.recycle();

        }

    }

   }
}
