package com.danikula.aibolit.test;

import java.util.Date;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import com.danikula.aibolit.test.support.Message;
import com.danikula.aibolit.test.support.MutableListAdapter;

public class ClassicChatActivity extends Activity {

    private LogAdapter adapter;

    private EditText messageEditText;

    private TextView symbolsCountTextVew;
    
    private String symbolsCountPattern;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        
        setContentView(R.layout.chat_activity);

        Log.d("debug", System.currentTimeMillis() + "");
        
        initComponents();
        initComponents();
        initComponents();
        initComponents();
        initComponents();
        
        Log.d("debug", System.currentTimeMillis() + "");
    }

    private void initComponents() {
        symbolsCountPattern = getString(R.string.symbols_count);

        ListView historyListView = (ListView) findViewById(R.id.historyListView);
        Button sendButton = (Button) findViewById(R.id.sendButton);
        Button clearHistoryButton = (Button) findViewById(R.id.clearHistoryButton);
        messageEditText = (EditText) findViewById(R.id.messageEditText);
        symbolsCountTextVew = (TextView) findViewById(R.id.symbolsCountTextVew);

        adapter = new LogAdapter(this, android.R.layout.simple_list_item_2);
        historyListView.setAdapter(adapter);

        sendButton.setOnClickListener(new OnSendButtonClickListener());
        clearHistoryButton.setOnClickListener(new OnClearHistoryButtonClickListener());
        messageEditText.addTextChangedListener(new OnMessageTextWatcher());
    }
    
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        menu.add(getString(R.string.menu_aibolit)).setIntent(new Intent(this, AibolitChatActivity.class));
        menu.add(getString(R.string.menu_test)).setIntent(new Intent(this, TestInjectActivity.class));
        return super.onCreateOptionsMenu(menu);
    }

    private final class OnMessageTextWatcher implements TextWatcher {

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {
            String symbolsCountText = String.format(symbolsCountPattern, s.length());
            symbolsCountTextVew.setText(symbolsCountText);
        }

        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {
        }

        @Override
        public void afterTextChanged(Editable s) {
        }
    }

    private final class OnSendButtonClickListener implements OnClickListener {

        @Override
        public void onClick(View v) {
            String text = messageEditText.getText().toString();
            adapter.add(new Message(new Date(), text));
            messageEditText.getEditableText().clear();
        }
    }
    
    private final class OnClearHistoryButtonClickListener implements OnClickListener {

        @Override
        public void onClick(View v) {
            adapter.clear();
        }
    }

    private class LogAdapter extends MutableListAdapter<Message> {

        public LogAdapter(Context context, int layoutId) {
            super(context, layoutId);
        }

        @Override
        protected void bind(Message message, View view, int position) {
            TextView messageTextView = (TextView) view.findViewById(android.R.id.text1);
            TextView timeTextView = (TextView) view.findViewById(android.R.id.text2);

            timeTextView.setText(message.getTime().toString());
            messageTextView.setText(message.getText());
        }
    }
}