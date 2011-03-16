package kevinpage.com;

import android.app.TabActivity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.TabHost;

//set up for Have/DontHave activities
public class TabDrinks extends TabActivity {
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.tabinventory);

        final TabHost tabHost = getTabHost();
        TabHost.TabSpec spec;
        Intent intent;

        intent = new Intent().setClass(this, CanMake.class);
        spec = tabHost.newTabSpec("complete").setIndicator("Complete", null)
                      .setContent(intent);
        tabHost.addTab(spec);

        intent = new Intent().setClass(this, NeedIngredients.class);
        spec = tabHost.newTabSpec("need").setIndicator("1 Missing Ingredient", null)
                      .setContent(intent);
        tabHost.addTab(spec);

        tabHost.setSaveEnabled(true);
        tabHost.setCurrentTab(0);
    }

    }