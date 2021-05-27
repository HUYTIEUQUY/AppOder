package com.example.appoder;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link Tab_Oder_themban#newInstance} factory method to
 * create an instance of this fragment.
 */
public class Tab_Oder_themban extends Fragment implements View.OnClickListener{

    Button btntrolai, btnluu;
    ImageView imgthemmon, imgthemnuoc;
    EditText edtsoban;
    private  View mView;
    private DatabaseReference mdata;

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public Tab_Oder_themban() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment Tab_Home.
     */
    // TODO: Rename and change types and number of parameters
    public static Tab_Oder_themban newInstance(String param1, String param2) {
        Tab_Oder_themban fragment = new Tab_Oder_themban();
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
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
    }

    @Override
    public void onResume() {
        super.onResume();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        mView= inflater.inflate(R.layout.fragment_tab__order_themban, container, false);

        Bundle bundle = getArguments();
        if(bundle != null){
            edtsoban.setText(bundle.getString("SoBan"));
        }
            AnhXa();
            imgthemmon.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                   mdata= FirebaseDatabase.getInstance().getReference(edtsoban.getText().toString());

                }
            });
            btntrolai.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    startActivity(new Intent(getActivity(), MainActivity.class));
                }
            });
        return mView;
    }


private void AnhXa(){
    btntrolai = mView.findViewById(R.id.buttonTroLai);
    btnluu =mView.findViewById(R.id.buttonLuu);
    edtsoban = mView.findViewById(R.id.edtSoBan);
    imgthemmon = mView.findViewById(R.id.imageThemMon);
    imgthemnuoc = mView.findViewById(R.id.imageThemNuoc);
}

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initView(view);
    }

    private void initView(View view){
        imgthemmon=view.findViewById(R.id.imageThemMon);
        imgthemmon.setOnClickListener(this);

        imgthemnuoc=view.findViewById(R.id.imageThemNuoc);
        imgthemnuoc.setOnClickListener(this);


    }


    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.imageThemMon :
                Tab_Oder_themban_themmon a = new Tab_Oder_themban_themmon();
                Bundle bundle = new Bundle();
                bundle.putString("SoBan",edtsoban.getText().toString());
                a.setArguments(bundle);
                getFragmentManager().beginTransaction().replace(R.id.main,a).addToBackStack(null).commit();

                break;
            case R.id.imageThemNuoc:
                getFragmentManager().beginTransaction().replace(R.id.main,new Tab_Oder_themban_themnuoc()).addToBackStack(null).commit();
                break;
        }
    }
}