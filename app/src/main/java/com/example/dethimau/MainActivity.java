package com.example.dethimau;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.ContextMenu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ListView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class MainActivity extends AppCompatActivity {

    FloatingActionButton btnThem;
    EditText txtSearch;
    ListView lvDanhSach;
    NguyenVanAn_Adapter adapter;
    ArrayList<Contact_An> hoadons;
    public static NguyenVanAn_Sqlite db;
    int selectedID;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        AnhXa();
        registerForContextMenu(lvDanhSach);
        db = new NguyenVanAn_Sqlite(this, "Device.sqlite", null, 1);
        Contact_An hd = new Contact_An();
//        db.addHoaDon(new Contact_An(1,"Linh","90909"));
//        db.addHoaDon(new Contact_An(2,"Nam","90909"));
//        db.addHoaDon(new Contact_An(3,"Huy","90909"));
//        db.addHoaDon(new Contact_An(4,"Phong","90909"));
//        db.addHoaDon(new Contact_An(5,"Dũng","90909"));

        hoadons = db.getAll();
        adapter = new NguyenVanAn_Adapter(this, R.layout.dong_layout, hoadons);
        lvDanhSach.setAdapter(adapter);
        adapter.notifyDataSetChanged();

        lvDanhSach.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                selectedID=position;
                return false;
            }
        });
        btnThem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(MainActivity.this,AddActivity.class);
                startActivityForResult(intent,100);
            }
        });
        txtSearch.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

//                String value = txtSearch.getText().toString();
//                int key;
//                if(value.isEmpty()){
//                    key = 0;
//                    hoadons = db.getAll();
//                    adapter = new Adapter_3(MainActivity.this, R.layout.dong_hoa_don, hoadons);
//                    lvDanhSach.setAdapter(adapter);
//                }else{
//                    key = Integer.parseInt(txtSearch.getText().toString());
//                    hoadons = db.searchByPrice(key);
//                    adapter = new Adapter_3(MainActivity.this, R.layout.dong_hoa_don, hoadons);
//                    lvDanhSach.setAdapter(adapter);
//                }
//                Log.d("abc",value);
//                adapter.filter(key);

                String key = txtSearch.getText().toString();
                Log.d("key",key);
                adapter.filter(key);
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
    }
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        MenuInflater inflater = new MenuInflater(this);
        inflater.inflate(R.menu.menu,menu);
        super.onCreateContextMenu(menu, v, menuInfo);
    }

    @Override
    public boolean onContextItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){

            case R.id.edit:
                Intent intent=new Intent(MainActivity.this,EditActivity.class);
                Bundle bundle=new Bundle();
                bundle.putInt("Id",hoadons.get(selectedID).getId());
                bundle.putString("Name",hoadons.get(selectedID).getName());
                bundle.putString("Phone",hoadons.get(selectedID).getPhoneNum());
                intent.putExtras(bundle);
                startActivityForResult(intent,100);
                break;
            case R.id.delete:
                AlertDialog.Builder b = new AlertDialog.Builder(this);
                b.setTitle("Confirm");
                b.setMessage("Nguyen Hai Nam wants to delete?");
                b.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        db.deleteHoaDon(hoadons.get(selectedID).getId());
                        hoadons.remove(selectedID);
                        adapter.notifyDataSetChanged();
                        lvDanhSach.setAdapter(adapter);
                        dialog.cancel();
                    }
                });
                b.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        dialog.cancel();
                    }
                });
                AlertDialog al = b.create();
                al.show();
                break;
        }
        return super.onContextItemSelected(item);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        Bundle bundle=data.getExtras();
        int id=bundle.getInt("Id");
        String name=bundle.getString("Name");
        String phone=bundle.getString("Phone");
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode==100) {
            if(resultCode==200) {
                db.addHoaDon(new Contact_An(id, name, phone));
                hoadons.add(new Contact_An(id, name, phone));
            }
            if(resultCode==202)
            {
                db.editHoaDon(hoadons.get(selectedID).getId(),new Contact_An(id, name, phone));
                hoadons.set(selectedID, new Contact_An(id, name, phone));
            }

        }
        Collections.sort(hoadons, new Comparator<Contact_An>() {
            @Override
            public int compare(Contact_An o1, Contact_An o2) {
                return o1.getName().compareTo(o2.getName());
            }
        });
//        hoadons = db.getAll();
        adapter.notifyDataSetChanged();
        lvDanhSach.setAdapter(adapter);
    }

    private void AnhXa() {
        btnThem = findViewById(R.id.floatingActionButton);
        txtSearch = findViewById(R.id.editTextSearch);
        lvDanhSach = findViewById(R.id.listviewmain);

    }
}