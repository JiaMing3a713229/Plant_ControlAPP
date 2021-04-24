package com.example.plant_controlapp;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class Monitor_Fragment extends Fragment {

    private MonitorViewModel monitorViewModel;
    private String plant_temp;
    private String plant_hum;


    DatabaseReference dref;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        monitorViewModel =
                new ViewModelProvider(this).get(MonitorViewModel.class);

        View root = inflater.inflate(R.layout.fragment_plantmonitor, container, false);


        final TextView textView = root.findViewById(R.id.plant_temp_result);
        final TextView textView1 = root.findViewById(R.id.plant_hum_result2);





        dref = FirebaseDatabase.getInstance().getReference();
        dref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

                plant_temp=snapshot.child("plant_temp").getValue().toString();
                plant_hum=snapshot.child("plant_hum").getValue().toString();
                textView.setText(plant_temp);
                textView1.setText(plant_hum);



            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });



        return root;
        //return inflater.inflate(R.layout.fragment_badroom,container,false);
    }
}
