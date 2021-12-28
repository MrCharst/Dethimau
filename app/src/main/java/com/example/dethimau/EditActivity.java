package com.example.dethimau;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class EditActivity extends AppCompatActivity {
    EditText editTextID,editTextName,editTextPhone;
    Button buttonEdit,buttonCancel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit);

        editTextID=findViewById(R.id.editId);
        editTextName=findViewById(R.id.editTen);
        editTextPhone=findViewById(R.id.editSDT);
        buttonCancel=findViewById(R.id.buttonBack);
        buttonEdit=findViewById(R.id.buttonAdd);
        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();
        if(bundle!=null) {
            editTextID.setText(bundle.getInt("Id")+"");
            editTextName.setText(bundle.getString("Name"));
            editTextPhone.setText(bundle.getString("Phone"));
        }
        intent.putExtras(bundle);
        buttonEdit.setOnClickListener((v)->
        {
            Intent i=new Intent();
            Bundle b=new Bundle();
            b.putInt("Id",Integer.parseInt(editTextID.getText().toString()));
            b.putString("Name",editTextName.getText().toString());
            b.putString("Phone",editTextPhone.getText().toString());
            i.putExtras(b);
            setResult(202,i);
            finish();
        });
        buttonCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent =new Intent(EditActivity.this,MainActivity.class);
                startActivity(intent);
                finish();
            }
        });
    }
}
