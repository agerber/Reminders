package com.apress.gerber;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.Toast;


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

    private EditText mTitleEditText;
    private Button mSaveButton;
    private RelativeLayout mImagesRelativeLayout;

    private Reminder mExistingReminder;


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
        View rootView = inflater.inflate(R.layout.fragment_edit, container, false);

        mTitleEditText = (EditText) rootView.findViewById(R.id.edit_title);
        mSaveButton = (Button) rootView.findViewById(R.id.button_save);
        mImagesRelativeLayout = (RelativeLayout) rootView.findViewById(R.id.rel_layout_images);

        mExistingReminder = RemindersDepot.getInstance(getActivity()).getReminder(mPosition);
        mTitleEditText.setText(mExistingReminder.getTitle());
        mSaveButton.setText("Save");


        mSaveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //create a new reminder and check to see if it's in the depot
                Reminder reminderNew = new Reminder(mTitleEditText.getText().toString());
                boolean containsReminder =  RemindersDepot.getInstance(getActivity()).containsReminder(reminderNew);
                if (containsReminder || reminderNew.getTitle().equalsIgnoreCase("")){
                    Toast.makeText(getActivity(), "Reminders must be unique and not empty", Toast.LENGTH_SHORT).show();
                    return;
                }


                 //we already have a reference to the current reminder, so let's just mutate it
                 mExistingReminder.setTitle(mTitleEditText.getText().toString());


                //this will call onEditFragmentInteraction() inside the hosting activity (either DetailActivity or NewReminderActivity)
                if (mListener != null) {
                    mListener.onEditFragmentInteraction();
                }

            }
        });

        return rootView;
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
