package onboard.enterprise.design.activity;

import android.app.Activity;
import android.net.Uri;
import android.os.Bundle;

import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.ViewSwitcher;

import onboard.enterprise.design.R;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link OnBoardingPagerIndicator.OnboardingPagerIndicatorInteractionListener} interface
 * to handle interaction events.
 * Use the {@link OnBoardingPagerIndicator#newInstance} factory method to
 * create an instance of this fragment.
 */
public class OnBoardingPagerIndicator extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    private int mNumOfPages;

    private OnboardingPagerIndicatorInteractionListener mListener;





    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment OnBoardingPagerIndicator.
     */
    // TODO: Rename and change types and number of parameters
    public static OnBoardingPagerIndicator newInstance(int pNumOfPages) {
        OnBoardingPagerIndicator fragment = new OnBoardingPagerIndicator(pNumOfPages);
        Bundle args = new Bundle();
        args.putInt("pages",pNumOfPages);
        fragment.setArguments(args);
        return fragment;
    }

    public OnBoardingPagerIndicator(){

          this.mNumOfPages = 3;
    }


    public OnBoardingPagerIndicator(int pNumOfPages) {
        this.mNumOfPages = pNumOfPages;
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);

        if(null != outState){

            outState.putInt("pages",this.mNumOfPages);
        }
    }

    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        if (savedInstanceState != null) {
            this.mNumOfPages = savedInstanceState.getInt("pages");
            Log.e("Numpages","++++++++++++++"+"Fixing number of Pages problem :-) "+ this.mNumOfPages+"++++++++++++++");
        }
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {


        if (savedInstanceState != null) {
            this.mNumOfPages = savedInstanceState.getInt("pages");
            Log.e("onCreateView","++++++++++++++"+"Fixing number of Pages problem :-) "+ this.mNumOfPages+"++++++++++++++");
        }
        Log.e("OnboardingPageIndicatoronCreateView","create view called");
        // Inflate the layout for this fragment
        LinearLayout linearLayout = (LinearLayout)inflater.inflate(R.layout.fragment_on_boarding_pager_indicator, container, false);


        ImageView img = (ImageView) linearLayout.findViewById(R.id.nextButton);
        img.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                onButtonPressed(null);
            }


        });

        TextView skipTxtView = (TextView) linearLayout.findViewById(R.id.skipTxt);
        skipTxtView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                OnBoardingPagerIndicator.this.mListener.onSkip();
            }
        });

        TextView doneTxtView = (TextView) linearLayout.findViewById(R.id.doneTxt);
        doneTxtView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                OnBoardingPagerIndicator.this.mListener.onDone();
            }
        });

        Log.e("OnboardingPagerIndicator","We have ["+ mNumOfPages+"] set in this class");
        initPageIndicator(linearLayout);

        return linearLayout;
    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onNext();
        }
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        try {
            mListener = (OnboardingPagerIndicatorInteractionListener) activity;
        } catch (ClassCastException e) {
            throw new ClassCastException(activity.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    public void updatePage(int pCurrent) {

       /* LinearLayout l =(LinearLayout)getView().findViewById(R.id.pageviewindicatorcircles);
        ImageView v = new ImageView(this.getActivity() );
        v.setBackground(getResources().getDrawable(R.drawable.circle));
        l.addView(v);*/



    }

    private void initPageIndicator(LinearLayout pv){



        LinearLayout l =(LinearLayout)pv.findViewById(R.id.pageviewindicatorcircles);
        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(
                ViewGroup.LayoutParams.WRAP_CONTENT,
                ViewGroup.LayoutParams.WRAP_CONTENT,
                1f
        );
        Log.e("initPageIndicator","========================== { Entering initPageIndicator } ===========================");

        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        params.setMargins(5,5,5,5);


        for(int i=0; i < this.mNumOfPages;i++) {

            ImageView v = new ImageView(this.getActivity());

            v.setBackground(getResources().getDrawable(R.drawable.circle_without_fill));
            l.addView(v);
            v.setPadding(3,3,3,3);
            v.setLayoutParams(params );

        }

        Log.e("initPageIndicator","========================== { Before getting getChildCount } ===========================");
        int count = l.getChildCount();
        View v = null;

        v= l.getChildAt(0);
        v.setBackground(getResources().getDrawable(R.drawable.circle));

        Log.e("initPageIndicator", "========================== { Leaving initPageIndicator } ===========================");
    }


    public void
    updateIndicatorCircles(View pV,int pSet,int pUnset){

        LinearLayout l =(LinearLayout)pV.findViewById(R.id.pageviewindicatorcircles);
        View v = null;

        Log.i("updateIndicator", "Set" + pSet + " and unset:" + pUnset);

        v= l.getChildAt(pSet);
        v.setBackground(getResources().getDrawable(R.drawable.circle));

        v= l.getChildAt(pUnset);
        v.setBackground(getResources().getDrawable(R.drawable.circle_without_fill));



    }



    public void
    updateIndicatorControlViews(View pV,int pCurrPage,int pLastPage){

        //if we have moved back from the last page enable some views
        if( ( pCurrPage + 1 == pLastPage  ) && (this.mNumOfPages - 2 == pCurrPage)){

               ViewSwitcher vS =  (ViewSwitcher)pV.findViewById(R.id.doneViewSwitcher);
               vS.showNext();
               TextView skipText = (TextView) pV.findViewById(R.id.skipTxt);
               skipText.setVisibility(View.VISIBLE);
            }

        //we have reached the End of the Onboarding slides, hide the skip, show the DONE
        if(pCurrPage == this.mNumOfPages -1){

              ViewSwitcher vS =  (ViewSwitcher)pV.findViewById(R.id.doneViewSwitcher);
              vS.showPrevious();
              TextView skipText = (TextView) pV.findViewById(R.id.skipTxt);
              skipText.setVisibility(View.INVISIBLE);
        }
    }


    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p/>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnboardingPagerIndicatorInteractionListener {
        // TODO: Update argument type and name
        public void onNext();

        public void onPrev();

        public void onSkip();

        public void onDone();

    }
}