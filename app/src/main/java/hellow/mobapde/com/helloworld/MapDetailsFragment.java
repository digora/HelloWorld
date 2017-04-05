package hellow.mobapde.com.helloworld;

import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.MapsInitializer;
import com.google.android.gms.maps.OnMapReadyCallback;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link MapDetailsFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link MapDetailsFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class MapDetailsFragment extends Fragment implements OnMapReadyCallback{
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private OnFragmentInteractionListener mListener;

    Button btnGo;
    Button btnCancel;

    TextView tvAdventureViewedTitle;
    TextView tvAdventureViewedDetails;

    MapView mvDetailMap;
    GoogleMap googleMap;

    public MapDetailsFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment MapDetailsFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static MapDetailsFragment newInstance(String param1, String param2) {
        MapDetailsFragment fragment = new MapDetailsFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
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
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_map_details, container, false);

        createContentView(v);

        mvDetailMap = (MapView) v.findViewById(R.id.mv_detail_map);
        mvDetailMap.onCreate(savedInstanceState);

        if(mvDetailMap != null){
            if (ActivityCompat.checkSelfPermission(getActivity(),
                    android.Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED
                    && ActivityCompat.checkSelfPermission(getActivity(),
                    android.Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
                return v;
            }
            MapsInitializer.initialize(this.getActivity());
        }

        return v;
    }

    private void createContentView(View v){

        //Intent collectIntent = v.getIntent();



        btnGo = (Button) v.findViewById(R.id.btn_go);
        /*btnGo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                *//*Intent mapIntent = new Intent(getBaseContext(), MapsActivity.class);
                startActivity(mapIntent);*//*
                v.finish();
            }
        });*/

        btnCancel = (Button) v.findViewById(R.id.btn_cancel);
        /*btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });*/


        /* Hello */

        tvAdventureViewedTitle = (TextView) v.findViewById(R.id.tv_adventure_viewed_title);
        //tvAdventureViewedTitle.setText(collectIntent.getStringExtra("aName"));

        tvAdventureViewedDetails = (TextView) v.findViewById(R.id.tv_adventure_viewed_details);
        //tvAdventureViewedDetails.setText(collectIntent.getStringExtra("aDetails"));

        /*ivAdventureViewedPicture = (ImageView) findViewById(R.id.iv_adventure_viewed_picture);
        ivAdventureViewedPicture.setImageBitmap(BitmapFactory.decodeResource(getResources(), R.drawable.app_icon));*/

        /*Bitmap bmp = null;
        String filename = collectIntent.getStringExtra("aPicture");
        try{
            FileInputStream fis = openFileInput(filename);
            bmp = BitmapFactory.decodeStream(fis);
            fis.close();

            ivAdventureViewedPicture.setImageBitmap(bmp);
        }catch(Exception e){
            e.printStackTrace();
        }*/

    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        this.googleMap = googleMap;
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
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }
}
