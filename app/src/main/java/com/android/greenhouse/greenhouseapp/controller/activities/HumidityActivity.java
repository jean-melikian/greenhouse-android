package com.android.greenhouse.greenhouseapp.controller.activities;

import com.android.greenhouse.greenhouseapp.R;
import com.android.greenhouse.greenhouseapp.util.Constants;

public class HumidityActivity extends BaseActivity {

    @Override
    public int getContentViewId() {
        return R.layout.activity_humidity;
    }

    @Override
    public void onResume() {
        super.onResume();
        setActionBarTitleDynamic(getResources().getString(R.string.menu_humidity));
    }

    @Override
    public void initView() {

    }

    @Override
    public String getTitleBarTitle() {
        return getResources().getString(R.string.menu_humidity);
    }

    @Override
    public String getHexActionbarColor() {
        return Constants.ACTION_BAR_COLOR;
    }

    @Override
    public int getTitleActionBarColor() {
        return getResources().getColor(R.color.dark_gray_text);
    }
}
