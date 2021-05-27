package com.example.appoder;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.material.imageview.ShapeableImageView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.squareup.picasso.Picasso;

import java.io.IOException;
import java.util.HashMap;

import static android.app.Activity.RESULT_OK;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link Tab_User#newInstance} factory method to
 * create an instance of this fragment.
 */
public class Tab_User extends Fragment {

    private TextView  txtdangxuat;
    private EditText edtuser, edtemail,edtdiachi,edtsdt;
    private Button  btncapnhat;


    // Khai báo biến chứng thực
    private FirebaseAuth mAuth;

    private DatabaseReference refProfileAccount;

    private ShapeableImageView imageView;

    // Khai báo hằng số
    private final int PICK_IMAGE_REQUEST = 1;

    // Khai báo biền địa chỉ tập tin tên thiết bị
    private Uri pathFromDevice;
    // Tập tin đã lưu trên sever
    private Uri pathFromFirebase;

    private StorageReference refAvatar;

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public Tab_User() {
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
    public static Tab_User newInstance(String param1, String param2) {
        Tab_User fragment = new Tab_User();
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

        View view = inflater.inflate(R.layout.fragment_tab__user, container, false);
        // Inflate the layout for this fragment


        mAuth = FirebaseAuth.getInstance();
        // Tạo thư mục images
        refAvatar = FirebaseStorage.getInstance().getReference().child("images/" + mAuth.getCurrentUser().getUid());




        edtdiachi=view.findViewById(R.id.edtdiachi);
        edtsdt=view.findViewById(R.id.edtsdt);
        edtuser = view.findViewById(R.id.edtUsername);
        edtemail=view.findViewById(R.id.edtEmail);
        txtdangxuat=view.findViewById(R.id.txtDangXuat);
        btncapnhat = view.findViewById(R.id.btnCapNhat);
        imageView = view.findViewById(R.id.imageAvatar);


        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Chọn Ảnh
                chooseImage();
            }
        });
        txtdangxuat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mAuth.signOut();
                Intent intent = new Intent(getActivity(),com.example.appoder.SignIn.class);
                startActivity(intent);

            }
        });

        // Sự kiện khi click vào buttonSave
        btncapnhat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                HashMap<String, Object> hashMap = new HashMap<>();
                hashMap.put("user", edtuser.getText().toString());
                hashMap.put("diaChi", edtdiachi.getText().toString());
                hashMap.put("sdt", edtsdt.getText().toString());

                refProfileAccount.updateChildren(hashMap);

                uploadImage();
                startActivity(new Intent(getActivity(), MainActivity.class));

            }
        });





        return view;
    }

    private void loadProfileFirebase() {
        refProfileAccount = FirebaseDatabase.getInstance().getReference().child("account").child(mAuth.getCurrentUser().getUid());
        refProfileAccount.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                TaiKhoan account = snapshot.getValue(TaiKhoan.class);
                setDataToViews(account);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
        loadImage();
    }



    // Khi nhấn avatar, trong ProfileActivity sẽ gọi một intent mặc định do android cung cấp để chọn ảnh
    private void chooseImage() {
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(Intent.createChooser(intent, "Chọn ảnh"), PICK_IMAGE_REQUEST);
    }

    private void uploadImage() {
        // upload ảnh lên server
        refAvatar.putFile(pathFromDevice);
    }

    private void loadImage() {
        // Xử lý sự kiện tải ảnh sau khi thực hiện chức năng edit thông tin người dùng
        refAvatar.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
            @Override
            public void onSuccess(Uri uri) {
                pathFromFirebase = uri;
                Picasso.get().load(pathFromFirebase.toString()).into(imageView);
            }
        });
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == PICK_IMAGE_REQUEST && resultCode == RESULT_OK && data != null && data.getData() != null) {
            pathFromDevice = data.getData();
            try {
                Bitmap bitmap = MediaStore.Images.Media.getBitmap(getActivity().getContentResolver(), pathFromDevice);
                imageView.setImageBitmap(bitmap);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public void onStart() {
        super.onStart();
        FirebaseUser user = mAuth.getCurrentUser();
        if (user != null) {
            // LẤY DỮ LIỆU ĐƯA VÀO USERID
            loadProfileFirebase();
        }
    }

    private void setDataToViews(TaiKhoan account) {
        edtemail.setText(account.getEmail().toString());
        edtuser.setText(account.getUser().toString());
        edtdiachi.setText(account.getDiaChi().toString());
        edtsdt.setText(account.getSdt().toString());
    }


}