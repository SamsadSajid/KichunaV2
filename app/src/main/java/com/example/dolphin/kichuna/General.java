package com.example.dolphin.kichuna;


import android.app.Activity;
import android.view.View.OnClickListener;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import com.activeandroid.query.Select;

import java.io.IOException;

import static android.app.Activity.RESULT_OK;


/**
 * A simple {@link Fragment} subclass.
 */
public class General extends Fragment implements View.OnClickListener{

    EditText eName;
    EditText ePhone;
    EditText eAddress;
    EditText eInstitution;
    Button bEdit;
    Button bSave;
    Button bgallery;
    Button bcamera;
    private static final int PICK_IMAGE = 100;
    private static final int TAKE_IMAGE = 1;
    private Bitmap bImageBitmap;
    private String sCurrentPhotoPath;
    private ImageView imageview;
    User_Info user_info;

    public General() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment


        super.onCreate(savedInstanceState);
        getActivity().setContentView(R.layout.fragment_general);
        eName = (EditText) getActivity().findViewById(R.id.eName);
        ePhone = (EditText) getActivity().findViewById(R.id.ePhone);
        eAddress = (EditText) getActivity().findViewById(R.id.eAddress);
        eInstitution = (EditText) getActivity().findViewById(R.id.eInstitution);
        bEdit = (Button) getActivity().findViewById(R.id.bEdit);
        bSave = (Button) getActivity().findViewById(R.id.bSave);
        bgallery = (Button) getActivity().findViewById(R.id.iGallary);
        bcamera = (Button) getActivity().findViewById(R.id.iCamera);
        imageview = (ImageView) getActivity().findViewById(R.id.imageView);
        bSave.setVisibility(View.INVISIBLE);
        eName.setEnabled(false);
        ePhone.setEnabled(false);
        eAddress.setEnabled(false);
        eInstitution.setEnabled(false);

        try{
            user_info=new Select().from(User_Info.class).executeSingle();
        }
        catch (Exception e) {
            user_info = new User_Info();
        }

        if(user_info==null)user_info=new User_Info();
        else
        {
            eName.setText(user_info.getName());
            ePhone.setText(user_info.getPhone());
            eAddress.setText(user_info.getAddress());
            eInstitution.setText(user_info.getInstitution());
        }
        bEdit.setOnClickListener(this);
        bSave.setOnClickListener(this);

        bgallery.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openGallery();
            }
        });

        bcamera.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v) {
                openCamera();
            }
        });
        vanishKeyboard();
        return inflater.inflate(R.layout.fragment_general, container, false);
    }
    private void vanishKeyboard() {
        eName.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            public void onFocusChange(View v, boolean hasFocus) {
                if (!hasFocus) {
                    hideKeyboard(v);
                }
            }
        });

        ePhone.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            public void onFocusChange(View v, boolean hasFocus) {
                if (!hasFocus) {
                    hideKeyboard(v);
                }
            }
        });

        eAddress.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            public void onFocusChange(View v, boolean hasFocus) {
                if (!hasFocus) {
                    hideKeyboard(v);
                }
            }
        });

        eInstitution.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            public void onFocusChange(View v, boolean hasFocus) {
                if (!hasFocus) {
                    hideKeyboard(v);
                }
            }
        });
    }


    public void hideKeyboard(View view) {
        InputMethodManager inputMethodManager = (InputMethodManager) getActivity().getSystemService(Activity.INPUT_METHOD_SERVICE);
        inputMethodManager.hideSoftInputFromWindow(view.getWindowToken(), 0);
    }

    public void onClick(View view){
        if(view == bSave) {
            /*if(!eName.getText().toString().equals("")) {
                 user_info = new User_Info(eName.getText().toString(), ePhone.getText().toString(), eAddress.getText().toString(),
                        eInstitution.getText().toString(), eEmergency1.getText().toString(), eEmergency2.getText().toString(), sBlood.getSelectedItem().toString());
                user_info.save();
                EmergencyApp.getWritableDatabaseUserInfo().insertUserInfo(user_info, true);
            */
            if (!eName.getText().toString().equals("")){
                user_info.setName(eName.getText().toString());
                user_info.setPhone(ePhone.getText().toString());
                user_info.setAddress(eAddress.getText().toString());
                user_info.setInstitution(eInstitution.getText().toString());
                user_info.save();
                Msg.COUT(this.getActivity(), "Saved Successfully");
            }
            bEdit.setVisibility(View.VISIBLE);
            bSave.setVisibility(View.INVISIBLE);
            eName.setEnabled(false);
            ePhone.setEnabled(false);
            eAddress.setEnabled(false);
            eInstitution.setEnabled(false);
        }

        else if(view == bEdit){
            eName.setEnabled(true);
            ePhone.setEnabled(true);
            eAddress.setEnabled(true);
            eInstitution.setEnabled(true);

            bEdit.setVisibility(View.INVISIBLE);
            bSave.setVisibility(View.VISIBLE);

            /*user_info.setName(eName.getText().toString());
            user_info.setPhone(ePhone.getText().toString());
            user_info.setAddress(eAddress.getText().toString());
            user_info.setInstitution(eInstitution.getText().toString());
            user_info.setEmergency1(eEmergency1.getText().toString());
            user_info.setEmergency2(eEmergency2.getText().toString());
            user_info.setBloodgroup(sBlood.getSelectedItem().toString());
            user_info.save();*/
            //Msg.COUT(this,"Informations Inserted Successfully");
        }
    }

    private void openGallery() {
        Intent gallery =new Intent(Intent.ACTION_PICK,
                android.provider.MediaStore.Images.Media.INTERNAL_CONTENT_URI);
        startActivityForResult(gallery, PICK_IMAGE);
    }

    private void openCamera(){
        Intent cameraIntent = new Intent(android.provider.MediaStore.ACTION_IMAGE_CAPTURE);
        startActivityForResult(cameraIntent, TAKE_IMAGE);
    }



    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == PICK_IMAGE && resultCode == RESULT_OK) {
            Uri imageUri = data.getData();
            imageview.setImageURI(imageUri);
        }

        else if (requestCode == TAKE_IMAGE && resultCode == RESULT_OK) {
            Uri imageUri = data.getData();

            /*FileInputStream inputStream = null;
            Bitmap photo = (Bitmap) data.getExtras().get("data");
            imageview.setImageBitmap(photo);*/

            try {
                Bitmap bitmap = MediaStore.Images.Media.getBitmap(getActivity().getContentResolver(), imageUri);
                // Log.d(TAG, String.valueOf(bitmap));

                imageview = (ImageView) getActivity().findViewById(R.id.imageView);
                imageview.setImageBitmap(bitmap);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

}

