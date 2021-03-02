package com.esell.yxfsdksimple;

import android.app.Presentation;
import android.content.Context;
import android.os.Build;
import android.os.Bundle;
import android.view.Display;
import android.view.View;

import androidx.annotation.RequiresApi;

/**
 * 附屏
 */
@RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN_MR1)
public class PresentationImp extends Presentation {
    private View contentView;

    public PresentationImp(Context outerContext, Display display, View contentView) {
        super(outerContext, display);
        this.contentView = contentView;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(contentView);
    }
}
