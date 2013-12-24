package com.apress.gerber;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


/**
 * A simple {@link android.support.v4.app.Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link com.apress.gerber.EditFragment.OnEditFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link EditFragment#newInstance} factory method to
 * create an instance of this fragment.
 *
 */
public class EditFragment extends Fragment {


    private OnEditFragmentInteractionListener mListener;
    private int mPosition;


    public static EditFragment newInstance(int position) {
        EditFragment fragment = new EditFragment();
        Bundle args = new Bundle();
        args.putInt(MainActivity.REMINDER_ID, position);
        fragment.setArguments(args);
        return fragment;
    }
    public EditFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mPosition = getArguments().getInt(MainActivity.REMINDER_ID);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_edit, container, false);
    }



    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        //this casts the hosting activity to an interface which it implements (OnEditFragmentInteractionListener)
        //this allows us to call mListener.onEditFragmentInteraction() later
        try {
            mListener = (OnEditFragmentInteractionListener) activity;
        } catch (ClassCastException e) {
            throw new ClassCastException(activity.toString()
                    + " must implement OnEditFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        //release the reference to the activity onDetach. This is not required, but good practice anyway
        mListener = null;
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnEditFragmentInteractionListener {
        // TODO: Update argument type and name
        public void onEditFragmentInteraction();
    }

}
