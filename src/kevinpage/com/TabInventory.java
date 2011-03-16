package kevinpage.com;

import android.app.TabActivity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.TabHost;

//set up for Have/DontHave activities
public class TabInventory extends TabActivity {
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.tabinventory);

        // The activity TabHost
        final TabHost tabHost = getTabHost();
        // Resusable TabSpec for each tab
        TabHost.TabSpec spec;
        // Reusable Intent for each tab
        Intent intent;

        //sets intent to DontHave activity
        intent = new Intent().setClass(this, DontHave.class);
        // Initialize a TabSpec for each tab and add it to the TabHost
        spec = tabHost.newTabSpec("dont").setIndicator("Add Items", null)
                      .setContent(intent);
        tabHost.addTab(spec);

        intent = new Intent().setClass(this, Have.class);
        spec = tabHost.newTabSpec("have").setIndicator("My Inventory", null)
                      .setContent(intent);
        tabHost.addTab(spec);

        tabHost.setSaveEnabled(true);
		//sets the current tab to MyInventory
        tabHost.setCurrentTab(1);
    }

    }