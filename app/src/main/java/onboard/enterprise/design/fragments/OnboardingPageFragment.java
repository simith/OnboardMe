package onboard.enterprise.design.fragments;

import android.app.Activity;
import android.os.Bundle;

import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import onboard.enterprise.design.R;
import onboard.enterprise.design.data.Page;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link OnboardingPageFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link OnboardingPageFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class OnboardingPageFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String PAGE_NUM = "PAGE_NUM";
    private static final String PAGE_COLOR = "PAGE_COLOR";
    private static  final String PAGE_IMG_RES_ID= "PAGE_IMG_RES_ID";

    private int imgResource = 0;
    // TODO: Rename and change types of parameters
    private String mPageNum;
    private String mPageColor;
    private int mImgResId;

    private OnFragmentInteractionListener mListener;


    // TODO: Rename and change types and number of parameters
    public static OnboardingPageFragment newInstance(Page pPage) {
        OnboardingPageFragment fragment = new OnboardingPageFragment();
        Bundle args = new Bundle();
        args.putString(PAGE_NUM, ""+pPage.getPageNum());
        args.putString(PAGE_COLOR, pPage.getBgColor());
        args.putInt(PAGE_IMG_RES_ID, pPage.getImgResId());
        fragment.setArguments(args);
        return fragment;
    }

    public OnboardingPageFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mPageNum = getArguments().getString(PAGE_NUM);
            mPageColor= getArguments().getString(PAGE_COLOR);
            mImgResId = getArguments().getInt(PAGE_IMG_RES_ID);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_onboarding_page, container, false);
        ImageView iv = (ImageView)v.findViewById(R.id.splashImg);
        iv.setImageResource(this.mImgResId);

         return v;
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        try {
            mListener = (OnFragmentInteractionListener) activity;
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


    public interface OnFragmentInteractionListener {

    }

}
