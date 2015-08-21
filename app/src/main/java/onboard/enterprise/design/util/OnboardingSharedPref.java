package onboard.enterprise.design.util;

import android.content.SharedPreferences;

/**
 * Created by snambiar on 20/08/15.
 */
public  class OnboardingSharedPref {


    static String stateKey="ONBOARDING_STATE";

    // A -1 means we have taken off, pages 0,1,2,3 etc.
    public static void saveState(SharedPreferences pSp,int pPageNum){

        SharedPreferences.Editor e = pSp.edit();
        e.putInt(stateKey,pPageNum);

    }

    public static int getState(SharedPreferences pSp){

       return  pSp.getInt(stateKey,0);

    }





}
