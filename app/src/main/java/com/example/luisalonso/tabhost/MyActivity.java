package com.example.luisalonso.tabhost;

import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TabHost;
import android.widget.TabWidget;
import android.widget.TextView;

public class MyActivity extends FragmentActivity{

    TabHost tabHost;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my);
        initComponents();
        setOnTabHost();

        TabWidget tw = tabHost.getTabWidget();
        tw.setOrientation(LinearLayout.VERTICAL);
        animateView();
    }

    public void initComponents(){
        tabHost = (TabHost)findViewById(R.id.tabHost);
        tabHost.setup();
    }

    public void setOnTabHost(){
        TabHost.TabSpec spec = tabHost.newTabSpec("Tab1");
        spec.setContent(R.id.tabFragmentWelcome);
//        spec.setIndicator("Welcome",getResources().getDrawable(R.drawable.ic_action_social_person));
        spec.setIndicator(createIndicatorView(tabHost,"Welcome",getResources().getDrawable(R.drawable.ic_action_social_person)));
        tabHost.addTab(spec);

        spec = tabHost.newTabSpec("Tab2");
        spec.setContent(R.id.tabFragmentMaintenance);
//        spec.setIndicator("Maintenance ...",getResources().getDrawable(R.drawable.ic_action_device_access_storage_1));
        spec.setIndicator(createIndicatorView(tabHost,"Maintenance ...",getResources().getDrawable(R.drawable.ic_action_device_access_storage_1)));
        tabHost.addTab(spec);

        spec = tabHost.newTabSpec("Tab3");
        spec.setContent(R.id.tabFragmentConsole);
//        spec.setIndicator("Console", getResources().getDrawable(R.drawable.ic_action_hardware_computer));
        spec.setIndicator(createIndicatorView(tabHost,"Console", getResources().getDrawable(R.drawable.ic_action_hardware_computer)));
        tabHost.addTab(spec);

    }
    private View createIndicatorView(TabHost tabHost, CharSequence label, Drawable icon) {

        LayoutInflater inflater = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        View tabIndicator = inflater.inflate(R.layout.tab_indicator,
                tabHost.getTabWidget(), // tab widget is the parent
                false); // no inflate params

        final TextView tv = (TextView) tabIndicator.findViewById(R.id.title);
        tv.setText(label);

        final ImageView iconView = (ImageView) tabIndicator.findViewById(R.id.icon);
        iconView.setImageDrawable(icon);

        return tabIndicator;
    }

    public void animateView(){
        for(int i=0; i<3; i++){
            tabHost.getTabWidget().getChildAt(i).setBackgroundColor(Color.parseColor("#000030"));
        }
        tabHost.setOnTabChangedListener(new AnimatedTabHostListener(tabHost));
        tabHost.setCurrentTab(1);
    }
}
