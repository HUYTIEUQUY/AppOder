package com.example.appoder;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link Tab_Oder_themban_themmon#newInstance} factory method to
 * create an instance of this fragment.
 */
public class Tab_Oder_themban_themmon extends Fragment {
    private EditText  edtkhoiluong, edttenhaisan, edtcachchebien, edtghichu;
    private TextView txtsoban;
    private Button btnthemmon;
    private View mView;
   private  DatabaseReference mdata;

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public Tab_Oder_themban_themmon() {
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
    public static Tab_Oder_themban_themmon newInstance(String param1, String param2) {
        Tab_Oder_themban_themmon fragment = new Tab_Oder_themban_themmon();
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
         mView=inflater.inflate(R.layout.fragment_tab_oder_themban_themmon, container, false);

         AnhXa();
        mdata = FirebaseDatabase.getInstance().getReference();
        Bundle bundle = getArguments();
        if(bundle != null){
            txtsoban.setText(bundle.getString("SoBan"));
        }



        btnthemmon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                mdata.child("Bàn").child(txtsoban.getText().toString()).push().setValue(edtkhoiluong.getText().toString()+" "+edttenhaisan.getText().toString()+" "+edtcachchebien.getText().toString()+" ( "+edtghichu.getText().toString()+" )");
                startActivity(new Intent(getActivity(), MainActivity.class));
            }
        });

        return mView;
    }
    private void AnhXa(){
        edtkhoiluong=mView.findViewById(R.id.edtKhoiLuong);
        edttenhaisan=mView.findViewById(R.id.edtTenHaiSan);
        edtcachchebien=mView.findViewById(R.id.edtCachCheBien);
        edtghichu=mView.findViewById(R.id.edtGhChu);
        txtsoban=mView.findViewById(R.id.txtSoBan);
        btnthemmon= mView.findViewById(R.id.btnThemMon);
    }
}