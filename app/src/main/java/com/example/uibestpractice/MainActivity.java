package com.example.uibestpractice;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private List<Msg> msgList = new ArrayList<>();

    private EditText inputText;

    private Button send;

    private RecyclerView msgRecyclerView;

    private MsgAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initMsg();
        inputText = findViewById(R.id.input_text);
        send = findViewById(R.id.send);
        msgRecyclerView = findViewById(R.id.recycler_view);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        msgRecyclerView.setLayoutManager(layoutManager);
        adapter = new MsgAdapter(msgList);
        msgRecyclerView.setAdapter(adapter);
        send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String content = inputText.getText().toString();
                if (!"".equals(content)) {
                    Msg msg = new Msg(content, Msg.TYPE_SEND);
                    msgList.add(msg);
                    adapter.notifyItemInserted(msgList.size() - 1);
                    //当有新消息时，刷新RecyclerView中的显示
                    msgRecyclerView.scrollToPosition(msgList.size() - 1);
                    //将rsView定位到最后一行
                    inputText.setText("");//清空输入框中的内容
                }
            }
        });
    }

    private void initMsg() {
        Msg msg1 = new Msg("Hello guy", Msg.TYPE_SEND);
        msgList.add(msg1);
        Msg msg2 = new Msg("Hello,who is that?", Msg.TYPE_RECEIVED);
        msgList.add(msg2);
        Msg msg3 = new Msg("This is Tom.Nice to meet you", Msg.TYPE_SEND);
        msgList.add(msg3);
    }
}
