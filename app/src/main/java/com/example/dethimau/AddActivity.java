package com.example.dethimau;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;

public class AddActivity extends AppCompatActivity {
    EditText editTextID,editNames;
    Button buttonAdd,buttonBack;
    String giotinh ;
    RadioButton rdNam,rdNu;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);
        editTextID=findViewById(R.id.editId);
        editNames=findViewById(R.id.editTen);
        buttonAdd=findViewById(R.id.buttonAdd);
        buttonBack=findViewById(R.id.buttonBack);
        rdNam=findViewById(R.id.radioButtonNam);
        rdNu=findViewById(R.id.radioButtonNu);

        buttonAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent =new Intent();
                Bundle bundle=new Bundle();
                bundle.putInt("Id",Integer.parseInt(editTextID.getText().toString()));

                bundle.putString("Name",editNames.getText().toString());
                if (rdNam.isChecked()){
                    giotinh="nam";
                }
                if (rdNu.isChecked()){
                    giotinh="nu";
                }
                bundle.putString("Phone",giotinh);

                intent.putExtras(bundle);
                setResult(200,intent);
                finish();
            }
        });
        buttonBack.setOnClickListener((v)->
        {
            Intent intent =new Intent(AddActivity.this,MainActivity.class);
            startActivity(intent);
            finish();
        });
    }

}