package kevinpage.com;


import android.app.Activity;
import android.app.AlertDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;


public class AllDrinks extends Activity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
   	 	setContentView(R.layout.drinks);

		//Creates empty array to store the names of all drinks
   	 	String[] drinkName = new String[data.allDrinks.size()];

   	 	//retrieves the names of all drinks in AllDrinks TreeSet and puts them in the string array
        for(int i=0; i<data.allDrinks.size();i++) {
        		drinkName[i]=((data.allDrinks.get(i)).getName());
        }
        //Assigns ListView lv to the ListView specified in drinks.xml
        ListView lv = (ListView) findViewById(R.id.makeable_drinks);
        //sets an ArrayAdapter to the drinkName array so all the items in the array are displayed as a list
	    lv.setAdapter(new ArrayAdapter<String>(this, R.layout.check, drinkName));
		lv.setTextFilterEnabled(true);

		lv.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
					//A new alert dialog is set up
				 	AlertDialog.Builder adb=new AlertDialog.Builder(AllDrinks.this);
				 	//retrieves the drink from the index that aligns with the position clicked
					Drink drink = data.allDrinks.get(position);
					//Sets the title of alert dialog to the drink name
				 	adb.setTitle(drink.getDisplayTitle());
				 	//uses method in Drink class to display ingredients and instructions
				 	adb.setMessage(drink.getDisplayMessage());
				 	adb.setPositiveButton("Ok", null);
				 	adb.show();
			}
		});

		final Button mainButton = (Button) findViewById(R.id.main_menu);
		mainButton.setOnClickListener(new View.OnClickListener() {
			public void onClick(View v) {
				finish();
			}
		});
	}
}