package sclab.doc.instantcvbanao;

import android.graphics.Bitmap;
import android.view.View;

public class screenshot {
    public static Bitmap takescreenshot(View v){
        v.setDrawingCacheEnabled(true);
        v.buildDrawingCache(true);
        Bitmap b=Bitmap.createBitmap(v.getDrawingCache());
        v.setDrawingCacheEnabled(false);
        return b;
    }
    public static Bitmap takescreenshotOfRootView(View v){
        return takescreenshot(v);
    }
}
