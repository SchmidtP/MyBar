package kevinpage.com;

import android.app.Activity;
import android.app.AlertDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.AdapterView.OnItemClickListener;

public class CanMake extends Activity {
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.drinks);

		final String[] drinkNames = new String[data.canMakeDrinks.size()];

		for (int i = 0; i < drinkNames.length; i++) {
			drinkNames[i] = ((data.canMakeDrinks.get(i)).getName());
		}
		
		ListView lv = (ListView) findViewById(R.id.makeable_drinks);
		lv.setAdapter(new ArrayAdapter<String>(this, R.layout.check,
				drinkNames));

		lv.setTextFilterEnabled(true);

		lv.setOnItemClickListener(new OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				if(position >= drinkNames.length && position <= drinkNames.length+2) {
				} else if(position<drinkNames.length) {
				AlertDialog.Builder adb = new AlertDialog.Builder(
						CanMake.this);
				Drink drink = data.canMakeDrinks.get(position);
				adb.setTitle(drink.getDisplayTitle());
				adb.setMessage(drink.getDisplayMessage());
				adb.setPositiveButton("Ok", null);
				adb.show();
				} else {
					AlertDialog.Builder adb = new AlertDialog.Builder(
							CanMake.this);
					Drink drink = data.almostMake.get(position-drinkNames.length-3);
					adb.setTitle(drink.getDisplayTitle());
					adb.setMessage(drink.getDisplayMessage());
					adb.setPositiveButton("Ok", null);
					adb.show();
				}
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
