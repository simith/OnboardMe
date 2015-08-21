package onboard.enterprise.design.activity;

import android.content.Context;
import android.content.Intent;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import onboard.enterprise.design.R;
import onboard.enterprise.design.manager.PageViewManager;
import onboard.enterprise.design.adapter.SlidePageAdapter;
import onboard.enterprise.design.model.Page;
import onboard.enterprise.design.fragments.OnBoardingPagerIndicator;
import onboard.enterprise.design.fragments.OnboardingPageFragment;
import onboard.enterprise.design.util.OnboardingSharedPref;

import static android.content.Context.MODE_PRIVATE;


public class OnboardingPagerActivity extends ActionBarActivity implements
        OnboardingPageFragment.OnFragmentInteractionListener,
        OnBoardingPagerIndicator.OnboardingPagerIndicatorInteractionListener{


    private PageViewManager mPagerMgr; //manages the slides/pages
    private ViewPager mViewPager; //slides
    private OnBoardingPagerIndicator onp; //the control at the bottom

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mPagerMgr = new PageViewManager();
        mPagerMgr.init();
        mPagerMgr.setTakeoffActivity(TakeoffActivity.class);

        Page p = new Page(0, "#354353", R.drawable.onboard);
        mPagerMgr.addPage(p);
        p = new Page(1, "#354ABC", R.drawable.two);
        mPagerMgr.addPage(p);
        p = new Page(2, "#23f353", R.drawable.onboard3);
        mPagerMgr.addPage(p);

        Log.e("onCreate", "ViewpagerActivity onCreate called ---------------------------------->");
        /*We are starting up or coming from an Activity destroyed state, add the fragment*/
        if (savedInstanceState == null) {
            Log.e("viewpager", "adding Fragment now to Activity:" );
            FragmentManager fm = getSupportFragmentManager();
            FragmentTransaction ft = fm.beginTransaction();
            /*add the fragment or recreate if it was destroyed*/
            onp = OnBoardingPagerIndicator.newInstance(mPagerMgr.numPages());
            ft.add(R.id.homeLayout, onp, "footer" );
            ft.commit();

        }

        setContentView(R.layout.onboarder_activity_);
        ActionBar actionBar = getSupportActionBar();
        actionBar.hide();

        mViewPager= (ViewPager) findViewById(R.id.pager);
        mViewPager.setAdapter(new SlidePageAdapter(getSupportFragmentManager(), mPagerMgr));
        mViewPager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {

            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                /*set the last page we were on*/
                mPagerMgr.setLastPage(position);

            }

            public void onPageSelected(int position) {
                Log.i("viewpager", "onPageSelected:" + position + " Last page:" + OnboardingPagerActivity.this.mPagerMgr.getLastPage());
                OnBoardingPagerIndicator lFooterFrag = (OnBoardingPagerIndicator) getSupportFragmentManager().findFragmentByTag("footer");
                View v = lFooterFrag.getView().findViewById(R.id.pageviewindicatorcircles);
                mPagerMgr.setCurrPage(position);
                saveSharedPrefState();

                int lLastPage = OnboardingPagerActivity.this.mPagerMgr.getLastPage();
                if (lLastPage == position) {
                    //this is an adjustment during scrolling back, hopefully the idea works - Simith
                    // do not really want to add the gesture listener
                    lLastPage = lLastPage + 1;
                }

                lFooterFrag.updateIndicatorCircles(lFooterFrag.getView(), position, lLastPage);
                lFooterFrag.updateIndicatorControlViews(lFooterFrag.getView(), position, lLastPage);
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });

        saveSharedPrefState();
    }

    private void saveSharedPrefState() {

        OnboardingSharedPref.saveState(getPreferences(Context.MODE_PRIVATE),mPagerMgr.getCurrPage());
    }

    @Override
    protected void onStop(){

        super.onStop();
        Log.w("STOPPED", "A C T I V I T Y Stopped.");
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_view_pager, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }



    @Override
    public void onSkip() {

       takeOff();

    }

    @Override
    public void onDone() {

       takeOff();


    }

    @Override
    public void onNext() {

        this.mViewPager.setCurrentItem(this.mPagerMgr.getCurrPage() + 1);
        saveSharedPrefState();

    }

    @Override
    public void onPrev() {

    }

    public void takeOff(){

        //start the Takeoff activity
        Class lTakeoffActivity = null;
        lTakeoffActivity = mPagerMgr.getTakeoffActivity();

        if(lTakeoffActivity == null)
            lTakeoffActivity= TakeoffActivity.class;

        Intent i = new Intent(this,lTakeoffActivity);
        i.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TOP );
        startActivity(i);
        finish();
    }
}
