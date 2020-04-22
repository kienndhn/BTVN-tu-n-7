package com.example.btvntun7;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.btvntun7.Model.EmailItemModel;
import com.example.btvntun7.adapter.EmailItemAdapter;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    ArrayList<EmailItemModel> items;
    EmailItemAdapter adapter;
    boolean f = false;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final EditText editText = findViewById(R.id.edit_text);
        Button button = findViewById(R.id.button);


        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(f == false) {
                    f = true;
                    ArrayList<EmailItemModel> farvoriteMail = new ArrayList<>();
                    for (EmailItemModel e : items) {
                        if (e.isFavorite())
                            farvoriteMail.add(e);
                        adapter.filterList(farvoriteMail);
                    }
                }
                else {
                    f = false;
                    adapter.filterList(items);
                }
            }
        });
        editText.addTextChangedListener(new TextWatcher(){

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                filter(s.toString());
            }
        });

        items = new ArrayList<>();
        for (int i = 0; i < 100; i++)
            if(i % 2 == 1)
                items.add(new EmailItemModel("Hello World " + i, "Subject subject " + i, "Content content " + i, "12:00 PM"));
            else
                items.add(new EmailItemModel("Hello Android " + i, "Android Android Android " + i, "Content content " + i, "12:00 PM"));

        RecyclerView recyclerView = findViewById(R.id.recycler_view);
        recyclerView.setHasFixedSize(true);

        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);

        adapter = new EmailItemAdapter(items);
        recyclerView.setAdapter(adapter);
    }

    void filter (String text){
        ArrayList<EmailItemModel> filterEmail = new ArrayList<>();

        for(int i = 0; i < items.size(); i++){
            if(items.get(i).getName().toLowerCase().contains(text.toLowerCase()) || items.get(i).getSubject().toLowerCase().contains(text.toLowerCase()) || items.get(i).getContent().toLowerCase().contains(text.toLowerCase())){
                filterEmail.add(items.get(i));
            }
        }
        adapter.filterList(filterEmail);
    }

}
