package com.glite.popeyes.font;

import android.content.Context;
import android.graphics.Typeface;

/**
 * @author Tran Huy Phuc
 * @since 7/12/16
 */
public final class FontManager {

    private FontManager() {}

    private static Typeface verdana;
    private static Typeface futuraStdBoldCondensed;

    /**
     * Create (if needed) and return verdana font
     *
     * @param context
     * @return The verdana font (typeface)
     */
    public static Typeface getVerdanaFont(Context context) {
        if (FontManager.verdana == null) {
            FontManager.verdana = Typeface.createFromAsset(context.getAssets(), "fonts/Verdana.ttf");
        }
        return FontManager.verdana;
    }

    /**
     * Create (if needed) and return FuturaStdBoldCondensed font
     *
     * @param context
     * @return The FuturaStdBoldCondensed font (typeface)
     */
    public static Typeface getFuturaStdBoldCondensedFont(Context context) {
        if (FontManager.futuraStdBoldCondensed == null) {
            FontManager.futuraStdBoldCondensed = Typeface.createFromAsset(context.getAssets(), "fonts/FuturaStdBoldCondensed.otf");
        }
        return FontManager.futuraStdBoldCondensed;
    }
}
