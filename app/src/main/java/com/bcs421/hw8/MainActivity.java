package com.bcs421.hw8;


import androidx.appcompat.app.AppCompatActivity;


import android.content.Context;
import android.content.Intent;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.AttributeSet;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputMethodManager;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MainActivity extends AppCompatActivity {

    EditText editText;
    ListView listView;
    ListAdapter listAdapter;
    Button btnCompute;
    // BarActivity barActivity = new BarActivity(this);


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        editText=findViewById(R.id.edittext);
        listView=findViewById(R.id.listView);
        btnCompute=findViewById(R.id.btnCompute);

        editText.setOnEditorActionListener(new EditText.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if (actionId == EditorInfo.IME_ACTION_DONE)
                {

                    try
                    {

                        if(editText.getText().toString().isEmpty())
                        {
                            listAdapter=null;
                            listView.setAdapter(null);
                        }
                        else
                        {
                            String[] arr =editText.getText().toString().split(",");
                            Intent i = new Intent(MainActivity.this,BarActivity.class);
                            i.putExtra("Grades", arr);
                            // startActivity(i);
                            listAdapter=new ListAdapter(MainActivity.this,arr);
                            listView.setAdapter(listAdapter);
                        }

                    }
                    catch (Exception e)
                    {
                        e.printStackTrace();
                    }
                    return true;
                }
                return false;
            }
        });

        btnCompute.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(listAdapter!=null)
                {

                    Integer [] list=listAdapter.getList();

                    Intent intent = new Intent(MainActivity.this,BarActivity.class);
                    intent.putExtra("Values", list);
                    // startActivity(intent);
                }
            }
        });

    }

    private void hideKeyBoard() {
        View view = this.getCurrentFocus();
        if (view != null) {
            InputMethodManager imm = (InputMethodManager)getSystemService(Context.INPUT_METHOD_SERVICE);
            imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
        }
    }


    class ListAdapter extends BaseAdapter{

        String arr[];
        Context context;
        LayoutInflater layoutInflater;
        Integer[] list;
        public  ListAdapter(Context context, String arr[])
        {
            this.arr=arr;
            this.list=new Integer[arr.length];
            this.context=context;
            layoutInflater=LayoutInflater.from(context);
        }
        @Override
        public int getCount() {
            return arr.length;
        }

        @Override
        public Object getItem(int i) {
            return arr[i];
        }

        @Override
        public long getItemId(int i) {
            return i;
        }
        public Integer[] getList()
        {
            return  this.list;
        }
        @Override
        public View getView(int i, View view, ViewGroup viewGroup)
        {


            View inflate = layoutInflater.inflate(R.layout.lyt_item, null);
            EditText editText=inflate.findViewById(R.id.edittext);
            editText.setHint("Enter the no of "+arr[i]+" Students");
            editText.addTextChangedListener(new TextWatcher() {
                @Override
                public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

                }

                @Override
                public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

                }

                @Override
                public void afterTextChanged(Editable editable) {

                    if(editText.getText().toString().isEmpty())
                    {
                        list[i]=0;
                    }
                    else
                    {
                        list[i]=Integer.parseInt(editText.getText().toString());
                       // Intent intent = new Intent(MainActivity.this,BarActivity.class);
                       // intent.putExtra("Values", list);
                    }

                }
            });
            return inflate;
        }
    }
}