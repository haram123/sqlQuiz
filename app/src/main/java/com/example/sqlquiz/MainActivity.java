package com.example.sqlquiz;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {

    EditText etName, etNumber;
    EditText etIdDelete, etEditNumber, etEditName, etEditId;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();
        etIdDelete.setVisibility(View.GONE);
        etEditId.setVisibility(View.GONE);
        etEditName.setVisibility(View.GONE);
        etEditNumber.setVisibility(View.GONE);




    }
    public void init()
    {
        etName = findViewById(R.id.etName);
        etNumber = findViewById(R.id.etNumber);
        etIdDelete = findViewById(R.id.etIdDelete);
        etEditId = findViewById(R.id.etEditID);
        etEditName = findViewById(R.id.etEditName);
        etEditNumber = findViewById(R.id.etEditNumber);
    }
    public void clear()
    {
        etName.setText("");
        etNumber.setText("");
    }
    public void btnSubmit(View v)
    {
        String name = etName.getText().toString().trim();
        String number = etNumber.getText().toString().trim();
        EmployeeDB employeeDB = new EmployeeDB(this);
        employeeDB.open();
        employeeDB.addData(name,number);

        employeeDB.close();
        clear();



    }
    public void btnEditDone(View v)
    {

        etName.setVisibility(View.VISIBLE);
        etNumber.setVisibility(View.VISIBLE);

        etEditId.setVisibility(View.GONE);
        etEditName.setVisibility(View.GONE);
        etEditNumber.setVisibility(View.GONE);
    }
    public void btnDeleteDone(View v)
    {
        EmployeeDB employeeDB = new EmployeeDB(this);
        employeeDB.open();
        String id = etIdDelete.getText().toString();
        employeeDB.delete(id);
        employeeDB.close();
        etIdDelete.setVisibility(View.GONE);

    }
    public void btnShowData(View v)
    {
        startActivity(new Intent(MainActivity.this,ShowData.class));


    }
    public void btnEditData(View v)
    {
        EmployeeDB employeeDB = new EmployeeDB(this);
        employeeDB.open();
        etName.setVisibility(View.GONE);
        etNumber.setVisibility(View.GONE);

        etEditId.setVisibility(View.VISIBLE);
        etEditName.setVisibility(View.VISIBLE);
        etEditNumber.setVisibility(View.VISIBLE);
        String id = etEditId.getText().toString();
        String name = etEditName.getText().toString();
        String number = etEditNumber.getText().toString();
        long totalupdateRecords = employeeDB.update(id, name, number);
        clear();

        employeeDB.close();


    }
    public void btnDelete(View v)
    {


        etIdDelete.setVisibility(View.VISIBLE);

    }
}