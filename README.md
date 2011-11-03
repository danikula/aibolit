Aibolit
=======

Simple lightweight dependency injection implementation for Android




Scope
=====
Injecting view by id
Injecing application resource: array adapter, drawable, string, animation, boolean, integer, dimension, array, color 
Inflating layout
Injecting system services
Injecting custom application services




Usage
=====
Just put aibolit-*.jar in classpath




Example
=======
    public class AibolitChatActivity extends Activity {
    
        // annotate fields to be injected...
    
        &#064;InjectView(R.id.messageEditText)
        private EditText messageEditText;
    
        &#064;InjectView(R.id.historyListView)
        private ListView historyListView;
    
        &#064;InjectResource(R.string.symbols_count)
        private String symbolsCountPattern;
    
        &#064;InjectSystemService(Context.NOTIFICATION_SERVICE)
        private NotificationManager notificationManager;
    
        &#064;InjectService
        private HttpManager httpManager;
    
        &#064;InjectResource(R.layout.content)
        private View content;
    
        ...
    
        &#064;Override
        public void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
    
            setContentView(R.layout.chat_activity);
            // initialize annotated fields and methods
            Aibolit.doInjections(this);
        
            // or just Aibolit.setInjectedContentView(this);
        
            ...
        }
    
        // annotate event handlers... 
    
        &#064;InjectOnClickListener(R.id.sendButton)
        private void onSendButtonClick(View v) {
            // handle onClick event
        }
    
        &#064;InjectOnClickListener(R.id.clearHistoryButton)
        private void onClearHistoryButtonClick(View v) {
            // handle onClick event
        }
    
        &#064;InjectOnTextChangedListener(R.id.messageEditText)
        public void onMessageTextChanged(CharSequence s, int start, int before, int count) {
            // handle text changed event
        }
    
        ...
    
    }




Developed By
===========
Alexey Danilov - danikula@gmail.com



License
=======
    Copyright (C) 2011 Alexey Danilov
    
    Licensed under the Apache License, Version 2.0 (the "License");
    you may not use this file except in compliance with the License.
    You may obtain a copy of the License at
    
         http://www.apache.org/licenses/LICENSE-2.0
    
    Unless required by applicable law or agreed to in writing, software
    distributed under the License is distributed on an "AS IS" BASIS,
    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
    See the License for the specific language governing permissions and
    limitations under the License.
