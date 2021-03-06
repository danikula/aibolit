# Aibolit
Simple lightweight dependency injection implementation for Android

![Aibolit logo](https://raw.githubusercontent.com/danikula/aibolit/master/logo.png)

## Scope
1. Injecting view by id
2. Injecting application resource: array adapter, drawable, string, animation, boolean, integer, dimension, array, color
3. Inflating layout
4. Injecting system services
5. Injecting custom application services

## Usage
Just add link to repository and dependency:
```
repositories {
    maven { url 'https://dl.bintray.com/alexeydanilov/maven' }
}
dependencies {
    compile 'com.danikula:aibolit:1.0'
}
```

## Example
``` java
public class AibolitChatActivity extends Activity {

    // annotate fields to be injected...

    @InjectView(R.id.messageEditText)
    private EditText messageEditText;

    @InjectView(R.id.historyListView)
    private ListView historyListView;

    @InjectResource(R.string.symbols_count)
    private String symbolsCountPattern;

    @InjectSystemService(Context.NOTIFICATION_SERVICE)
    private NotificationManager notificationManager;

    @InjectService
    private HttpManager httpManager;

    @InjectResource(R.layout.content)
    private View content;

    ...

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.chat_activity);
        // initialize annotated fields and methods
        Aibolit.doInjections(this);

        // or just Aibolit.setInjectedContentView(this);

        ...
    }

    // annotate event handlers...

    @InjectOnClickListener(R.id.sendButton)
    private void onSendButtonClick(View v) {
        // handle onClick event
    }

    @InjectOnClickListener(R.id.clearHistoryButton)
    private void onClearHistoryButtonClick(View v) {
        // handle onClick event
    }

    @InjectOnTextChangedListener(R.id.messageEditText)
    public void onMessageTextChanged(CharSequence s, int start, int before, int count) {
        // handle text changed event
    }

    ...

}
```

For more details see javadoc in code.

## Developed By
Alexey Danilov - danikula@gmail.com

Thanks [Aliaksei Latsinnik](http://be.net/toprojectman) for logo :)

If you decide to use Aibolit in your project, please, notify me about it :) I'd love to see how widely it is being used.


## License
    Copyright (C) 2011-2015 Alexey Danilov
    
    Licensed under the Apache License, Version 2.0 (the "License");
    you may not use this file except in compliance with the License.
    You may obtain a copy of the License at
    
         http://www.apache.org/licenses/LICENSE-2.0
    
    Unless required by applicable law or agreed to in writing, software
    distributed under the License is distributed on an "AS IS" BASIS,
    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
    See the License for the specific language governing permissions and
    limitations under the License.
