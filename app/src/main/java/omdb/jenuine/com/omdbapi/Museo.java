package omdb.jenuine.com.omdbapi;

import android.content.Context;
import android.graphics.Typeface;
import android.util.AttributeSet;
import android.widget.TextView;

/**
 * Created by jenu on 28/5/15.
 */
public class Museo extends TextView {

    /*
     * Caches typefaces based on their file path and name, so that they don't have to be created every time when they are referenced.
     */
    private static Typeface mTypeface;

    public Museo(final Context context) {
        this(context, null);
    }

    public Museo(final Context context, final AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public Museo(final Context context, final AttributeSet attrs, final int defStyle) {
        super(context, attrs, defStyle);

        if (mTypeface == null) {
            mTypeface = Typeface.createFromAsset(context.getAssets(), "museo_slab_50.ttf");
        }
        setTypeface(mTypeface);
    }

}