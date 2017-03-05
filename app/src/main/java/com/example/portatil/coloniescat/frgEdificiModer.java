package com.example.portatil.coloniescat;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link frgEdificiModer.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link frgEdificiModer#newInstance} factory method to
 * create an instance of this fragment.
 */
public class frgEdificiModer extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private OnFragmentInteractionListener mListener;

    public frgEdificiModer() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment frgEdificiModer.
     */
    // TODO: Rename and change types and number of parameters
    public static frgEdificiModer newInstance(String param1, String param2) {
        frgEdificiModer fragment = new frgEdificiModer();
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
    //a mes de instanciar el necesari farem les crides per registrar la resposta del usuari e enviarles a la base de dades
    //per guardarles
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.fragment_frg_edifici_moder, container, false);
        final EditText respostaUsuari = (EditText) view.findViewById(R.id.editResposta);

        Button botoResposta = (Button) view.findViewById(R.id.resposta);

        botoResposta.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                try {
                    TalkerOH bd=new TalkerOH(getContext());
                    String contingutText;
                    contingutText = respostaUsuari.getText().toString();
                    bd.modificaResposta(7,contingutText);
                    Toast toast ;
                    toast =Toast.makeText(getContext(),"Resposta registrada si vols veure mes consulta el questionari en el menu", Toast.LENGTH_SHORT);
                    toast.show();
                }
                catch (Exception e) {
                    Toast toast ;
                    toast =Toast.makeText(getContext(),"Ups alguna cosa no anat com esperavam", Toast.LENGTH_SHORT);
                    toast.show();
                    return;
                }
            }
        });

        return  view;
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
