package com.example.kk.chat;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class chatroom extends AppCompatActivity {
Button button;

    EditText edit;ImageView image;
  RecyclerView recycler;ArrayList<chatdomain> cd=new ArrayList<>();
    String username,room;
    ChatRecycleAdapter chatRecycleAdapter;
    public DatabaseReference root;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chatroom);
        recycler = (RecyclerView) findViewById(R.id.recycle);
        image=findViewById(R.id.image);
       edit= (EditText) findViewById(R.id.editText);
        username=getIntent().getExtras().getString("username");
        room=getIntent().getExtras().getString("room");
        setTitle("room=="+room);
        root= FirebaseDatabase.getInstance().getReference().child(room);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Map<String,Object> map=new HashMap<String, Object>();
                String temp_key = root.push().getKey();
               root.updateChildren(map);
                DatabaseReference message_root=root.child(temp_key);
                Map<String,Object> map2=new HashMap<String,Object> ();
                map2.put("name",username);
                map2.put("msg",edit.getText().toString());
                message_root.updateChildren(map2) ;

            }
        });
        root.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
               conversation(dataSnapshot);
            }

            @Override
            public void onChildChanged(DataSnapshot dataSnapshot, String s) {conversation(dataSnapshot);
                conversation(dataSnapshot);
            }

            @Override
            public void onChildRemoved(DataSnapshot dataSnapshot) {
                conversation(dataSnapshot);
            }

            @Override
            public void onChildMoved(DataSnapshot dataSnapshot, String s) {
                conversation(dataSnapshot);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }


        });

    }
    String chatmsg,username1;
    private  void conversation(DataSnapshot dataSnapShot)
    {
        Iterator i=dataSnapShot.getChildren().iterator();
        while (i.hasNext())
        {     chatdomain c=new chatdomain();
             chatmsg = (String) ((DataSnapshot) i.next()).getValue();
            username1 = (String) ((DataSnapshot) i.next()).getValue();
          c.setChatmsg(chatmsg);
          c.setUsername(username1);
          cd.add(c);
          chatRecycleAdapter=new ChatRecycleAdapter(cd,getApplicationContext());

        }
    }
}
