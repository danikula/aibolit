package com.danikula.aibolit.test;

import android.app.Activity;
import android.app.Dialog;
import android.app.NotificationManager;
import android.content.Context;
import android.content.Intent;
import android.content.res.ColorStateList;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MenuItem.OnMenuItemClickListener;
import android.view.MotionEvent;
import android.view.View;
import android.view.animation.Animation;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.danikula.aibolit.Aibolit;
import com.danikula.aibolit.annotation.AibolitSettings;
import com.danikula.aibolit.annotation.Extra;
import com.danikula.aibolit.annotation.StringArrayAdapter;
import com.danikula.aibolit.annotation.OnCheckedChange;
import com.danikula.aibolit.annotation.OnClick;
import com.danikula.aibolit.annotation.OnFocusChange;
import com.danikula.aibolit.annotation.OnItemClick;
import com.danikula.aibolit.annotation.OnItemSelected;
import com.danikula.aibolit.annotation.OnKey;
import com.danikula.aibolit.annotation.OnLongClick;
import com.danikula.aibolit.annotation.OnRadioGroupCheckedChange;
import com.danikula.aibolit.annotation.OnTextChanged;
import com.danikula.aibolit.annotation.OnTouch;
import com.danikula.aibolit.annotation.Resource;
import com.danikula.aibolit.annotation.InjectService;
import com.danikula.aibolit.annotation.SystemService;
import com.danikula.aibolit.annotation.ViewById;
import com.danikula.aibolit.test.AibolitTestApplication.HttpManager;

public class TestInjectActivity extends Activity {
    // make fields public to simplify unit testing
    
    @Extra(name="name", required=false)
    private String name = "undefined";
    
    @InjectService
    private HttpManager httpManager;

    @ViewById(R.id.textView)
    public TextView textView;

    @ViewById(R.id.listView)
    public ListView listView;

    @SystemService(Context.LAYOUT_INFLATER_SERVICE)
    private LayoutInflater layoutInflater;
    
    @SystemService(Context.NOTIFICATION_SERVICE)
    private NotificationManager notificationManager;
    
    @Resource(R.anim.my_anim)
    private Animation animation;
    
    @Resource(R.color.button_text)
    private ColorStateList buttonText;

    @Resource(R.color.translucent_red)
    private int redColor;

    @Resource(android.R.drawable.btn_default)
    private Drawable drawable;

    @Resource(android.R.layout.simple_expandable_list_item_2)
    private View view;

    @Resource(R.array.numbers)
    private String[] numbers;
    
    @Resource(R.array.icons)
    private TypedArray icons;
    
    @Resource(R.array.integers)
    private int[] integers;
    
    @Resource(R.bool.screen_small)
    private boolean screenSmall;

    @Resource(R.dimen.font_size)
    private float fontSize;

    @Resource(R.integer.max_speed)
    private int maxSpeed;

    @StringArrayAdapter(textArrayResourceId = R.array.numbers, layoutId = android.R.layout.simple_list_item_1)
    private ArrayAdapter<CharSequence> adapter;
    
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Aibolit.setInjectedContentView(this, R.layout.test);
        
        listView.setAdapter(adapter);

        Log.d("debug", "LayoutInflater: " + layoutInflater);
        Log.d("debug", "HttpManager: " + httpManager);
        Log.d("debug", "animation: " + animation);
        Log.d("debug", "buttonText: " + buttonText);
        Log.d("debug", "redColor: " + redColor);
        Log.d("debug", "drawable: " + drawable);
        Log.d("debug", "view: " + view);
        Log.d("debug", "numbers: " + numbers);
        Log.d("debug", "icons: " + icons);
        Log.d("debug", "integers: " + integers);
        Log.d("debug", "screenSmall: " + screenSmall);
        Log.d("debug", "fontSize: " + fontSize);
        Log.d("debug", "maxSpeed: " + maxSpeed);
        Log.d("debug", "name: " + name);
    }
    
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        menu.add(getString(R.string.menu_aibolit)).setOnMenuItemClickListener(new OnMenuItemClickListener() {
            
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                Log.d("debug", System.currentTimeMillis() + "");
                startActivity(new Intent(TestInjectActivity.this, AibolitChatActivity.class));
                return false;
            }
        });
        menu.add(getString(R.string.menu_test)).setOnMenuItemClickListener(new OnMenuItemClickListener() {
            
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                Log.d("debug", System.currentTimeMillis() + "");
                startActivity(new Intent(TestInjectActivity.this, ClassicChatActivity.class));
                return false;
            }
        });
        return super.onCreateOptionsMenu(menu);
    }

    @OnClick(R.id.button)
    private void onButtonClickListener(View view) {
        Log.d("debug", "onButtonClickListener!");
        httpManager.invokeRequest(new Object());
    }

    @OnLongClick(R.id.button)
    private boolean onButtonLongClickListener(View view) {
        Log.d("debug", "onButtonLongClickListener! " + view);
        new ConcreteSimpleDialog(this).show();
        return false;
    }
    
    @OnTouch(R.id.button)
    private boolean onButtonTouch(View v, MotionEvent event) {
        // handle touch event
        return false;
    }

    @OnItemClick(R.id.listView)
    private void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        Log.d("debug", String.format("onItemClick: %s, %s, %s, %s", parent, view, position, id));
    }
    
    @OnItemSelected(R.id.listView)
    private void onListViewItemSelected(AdapterView<?> parent, View view, int position, long id) {
    }

    @OnTouch(R.id.textView)
    private boolean onTextViewTouch(View v, MotionEvent event) {
        Log.d("debug", String.format("onTextViewTouch: %s, %s", v, event));
        return false;
    }

    @OnFocusChange(R.id.editText)
    private void onFocusChange(View v, boolean hasFocus) {
        Log.d("debug", String.format("onFocusChange: %s, %s", v, hasFocus));
    }

    @OnTextChanged(R.id.editText)
    private void onSearchTextChanged(CharSequence s, int start, int before, int count) {
        Log.d("debug", String.format("onTextChanged: %s, %s, %s, %s", s, start, before, count));
    }

    @OnCheckedChange(R.id.checkbox)
    private void onCheckedChanged(android.widget.CompoundButton arg0, boolean arg1) {
        Log.d("debug", String.format("onCheckedChanged: %s, %s", arg0, arg1));
    }
    
    @OnRadioGroupCheckedChange(R.id.radiogroup)
    private void onRadioGroupCheckedChanged(RadioGroup rg, int arg1) {
        Log.d("debug", String.format("onCheckedChanged: %s, %s", rg, arg1));
    }

    @OnKey(R.id.editText)
    private boolean onKey(View v, int keyCode, KeyEvent event) {
        Log.d("debug", String.format("onKey: %s, %s, %s", v, keyCode, event));
        return false;
    }
    
    private abstract static class AbstractSimpleDialog extends Dialog {
        
        @ViewById(android.R.id.text1)
        private TextView textView;

        public AbstractSimpleDialog(Context context) {
            super(context);
        }
        
        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            
            Aibolit.setInjectedContentView(this, android.R.layout.simple_expandable_list_item_2);
            Log.d("debug", "AbstractSimpleDialog.onCreate: textView " + textView);
        }
    }
    
    @AibolitSettings(injectSuperclasses = true)
    private static class ConcreteSimpleDialog extends AbstractSimpleDialog {
        
        @ViewById(android.R.id.text2)
        private TextView textView2;

        public ConcreteSimpleDialog(Context context) {
            super(context);
        }
        
        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            
            Aibolit.setInjectedContentView(this, android.R.layout.simple_expandable_list_item_2);
            Log.d("debug", "ConcreteSimpleDialog.onCreate: textView " + textView2);
        }
        
    }
}