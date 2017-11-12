package com.example.android.bcmliteapp;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.TabLayout;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.graphics.Palette;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

import com.example.android.bcmliteapp.adapters.PagerAdapter;
import com.example.android.bcmliteapp.fragments.PlanProcessesFragment;
import com.example.android.bcmliteapp.fragments.PlanStepsFragment;
import com.example.android.bcmliteapp.models.Plan;

public class PlanDetailActivity extends AppCompatActivity {

    private CollapsingToolbarLayout mCollapsingToolbarLayout;
    private Context context = this;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_plan_detail);

        Toolbar toolbar = (Toolbar) findViewById(R.id.tb_plan_detail);
        setSupportActionBar(toolbar);
        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
        setToolbarUpArrowAppearance(actionBar);

        mCollapsingToolbarLayout = (CollapsingToolbarLayout) findViewById(R.id.ctb_plan_detail);

        //Retrieve data from intent
        Intent intent = getIntent();

        // The detail Activity called via intent.  Inspect the intent for step details
        if (intent != null && intent.hasExtra("Plan")) {

            Plan plan = (Plan) intent.getSerializableExtra("Plan");

            mCollapsingToolbarLayout.setTitle(plan.getDepartmentName());

//            //Plan Name
//            ((TextView) findViewById(R.id.txt_plan_detail_name)).setText(plan.getName());
//
//            //Plan Department
//            ((TextView) findViewById(R.id.txt_plan_detail_department)).setText(plan.getDepartment());
//
//            //Plan Detail
//            ((TextView) findViewById(R.id.txt_plan_detail_description)).setText(plan.getDescription());
        }

        setDynamicToolbarColor();
        setToolbarTextAppearance();
        initViewPagerAndTabs();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        // Inflate the toolbar menu
        getMenuInflater().inflate(R.menu.menu_plan_detail_top, menu);

/*
        // Inflate and initialize the bottom menu
        ActionMenuView bottomBar = (ActionMenuView)findViewById(R.id.ab_plan_detail_bottom);

       Menu bottomMenu = bottomBar.getMenu();
        getMenuInflater().inflate(R.menu.menu_plan_detail_bottom, bottomMenu);
        for (int i = 0; i < bottomMenu.size(); i++) {
            bottomMenu.getItem(i).setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {
                @Override
                public boolean onMenuItemClick(MenuItem item) {
                    return onOptionsItemSelected(item);
                }
            });
        }*/

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()) {

            case R.id.menu_item_plan_search:
                //TODO: Action for plan search menu item
                return true;
            case R.id.menu_item_plan_settings:
                //TODO: Action for plan settings menu item
                return true;
/*            case R.id.menu_item_plan_view:
                //TODO: Action for plan view menu item
                return true;*/
        }

        return super.onOptionsItemSelected(item);
    }

    private void setDynamicToolbarColor() {

        Bitmap bitmap = BitmapFactory.decodeResource(getResources(),
                R.drawable.img_bcp);
        Palette.from(bitmap).generate(new Palette.PaletteAsyncListener() {

            @Override
            public void onGenerated(Palette palette) {
                mCollapsingToolbarLayout.setContentScrimColor(palette.getMutedColor(ContextCompat.getColor(context, R.color.colorPrimary)));
                mCollapsingToolbarLayout.setStatusBarScrimColor(palette.getMutedColor(ContextCompat.getColor(context, R.color.colorPrimaryDark)));
            }
        });
    }

    private void setToolbarTextAppearance() {
        mCollapsingToolbarLayout.setCollapsedTitleTextAppearance(R.style.appBarCollapsed);
        mCollapsingToolbarLayout.setExpandedTitleTextAppearance(R.style.appBarExpanded);
    }

    private void setToolbarUpArrowAppearance(ActionBar actionBar) {
        final Drawable upArrow = getResources().getDrawable(R.drawable.abc_ic_ab_back_material);
        upArrow.setColorFilter(ContextCompat.getColor(context, R.color.colorWhite), PorterDuff.Mode.SRC_ATOP);
        actionBar.setHomeAsUpIndicator(upArrow);
    }

    private void initViewPagerAndTabs() {
        ViewPager viewPager = (ViewPager) findViewById(R.id.view_pager_plan_resources);
        PagerAdapter pagerAdapter = new PagerAdapter(getSupportFragmentManager());

        pagerAdapter.addFragment(new PlanProcessesFragment(), getString(R.string.plan_processes));
        pagerAdapter.addFragment(new PlanStepsFragment(), getString(R.string.plan_steps));

        viewPager.setAdapter(pagerAdapter);

        TabLayout tabLayout = (TabLayout) findViewById(R.id.tabLayout);
        tabLayout.setupWithViewPager(viewPager);
    }

}
