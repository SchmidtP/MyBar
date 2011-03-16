package kevinpage.com;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.AdapterView.OnItemClickListener;

public class Have extends Activity {

    //Updates the view of the Have activity but re-filling the ingredient array with new values
	private void fillData(ListView lv, String[] array) {
		ArrayAdapter<String> help=
			new ArrayAdapter<String>(getApplicationContext(), R.layout.check, array);
		lv.setAdapter(help);
	}

	static ListView lvH;

    @Override
    public void onCreate(Bundle savedInstanceState) {
	  	super.onCreate(savedInstanceState);
        setContentView(R.layout.ingredients);
        //when activity is created, assigns items in ownedIngredients to the array al2.
        data.al2 = (String []) data.ownedIngredients.toArray (new String [data.ownedIngredients.size ()]);
		//sets ListView to that specified in ingredients.xml
		lvH = (ListView) findViewById(R.id.ingredient_list);
		//Updates the visible items for this activity
		fillData(lvH, data.al2);
	    lvH.setTextFilterEnabled(true);

		lvH.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
						Toast.makeText(getApplicationContext(), "Removed "+(((TextView) view).getText())+" from Inventory",
					          Toast.LENGTH_SHORT).show();
						//removes clicked item from ownedIngredients by searching for the TextView's text(a drink name) in the TreeSet
						data.ownedIngredients.remove((((TextView) view).getText()));
						//adds clicked item to the missingIngs list(So that it's later displayed in the DontHave activity)
						data.missingIngs.add((((TextView) view).getText()).toString());
						//Next two lines update al and al2 arrays according to newly removed ingredients
				        data.al = (String []) data.missingIngs.toArray (new String [data.missingIngs.size ()]);
				        data.al2 = (String []) data.ownedIngredients.toArray (new String [data.ownedIngredients.size ()]);
				        //updates views of Have/DontHave
				        fillData(lvH,data.al2);
				        fillData(DontHave.lvD,data.al);
			}
		});

		final Button clearButton = (Button) findViewById(R.id.clearORadd);
        clearButton.setOnClickListener(new View.OnClickListener() {
      	public void onClick(View v) {
			Toast.makeText(getApplicationContext(), "Removed All Items from Inventory",
			          Toast.LENGTH_SHORT).show();
				data.ownedIngredients.removeAll(data.ownedIngredients);
				data.missingIngs.addAll(data.totalIngredients);
		        data.al = (String []) data.missingIngs.toArray (new String [data.missingIngs.size ()]);
		        data.al2 = (String []) data.ownedIngredients.toArray (new String [data.ownedIngredients.size ()]);
		        //updates views of Have/DontHave
		        fillData(lvH,data.al2);
		        fillData(DontHave.lvD,data.al);
      	}
      	});
		
	    final Button mainButton = (Button) findViewById(R.id.main_menu);
        mainButton.setOnClickListener(new View.OnClickListener() {
      	public void onClick(View v) {
      		//clears the canMakeDrinks list for update
      		data.canMakeDrinks.clear();
      		data.almostMake.clear();
      		//checks all Drinks in library; if canMake, drink is added to canMakeDrinks
            for(Drink drink : data.allDrinks) {
            	if(drink.canMake()) {
            			data.canMakeDrinks.add(drink);
            	} else if(drink.almostMake()) {
            			data.almostMake.add(drink);
            		}
            	}
            finish();
      	}
      	});

    }
}