package kevinpage.com;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MyBar extends Activity {

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);

		//if the program hasn't yet generated the list of drinks it runs the following code:
		if (!data.generatedDrinks) {
			String line;
			//opens the resource file drinks.in
			InputStream is2 = getResources().openRawResource(R.raw.drinks);
			//new BufferedReader to retrieve text from drinks.in
			BufferedReader DrinkReader = new BufferedReader(
					new InputStreamReader(is2));
			ArrayList<String> drinkIngredients = new ArrayList<String>();
			ArrayList<String> amounts = new ArrayList<String>();
			try {
				// Adds all of the drinks in the file to allDrinks
				while ((line = DrinkReader.readLine()) != null) {
					//clears ingredients/amounts from previous drink
					drinkIngredients.clear();
					amounts.clear();
					//retrieves rating integer
					int r = Integer.parseInt(DrinkReader.readLine());
					while (true) {
						//while "0" is not read, reads line(which is an ingredient)
						String ing = DrinkReader.readLine();
						if (ing.equals("0"))
							break;
						//TreeSet makes sure there are no duplicates
						//adds ingredient to totalIngredients and missingIngs, explained later
						data.totalIngredients.add(ing);
						data.missingIngs.add(ing);
						//reads corresponding amount
						String a = DrinkReader.readLine();
						//adds ingredient/amount to THIS specific drink
						drinkIngredients.add(ing);
						amounts.add(a);
					}
					//reads instructions for this drink
					String instruct = DrinkReader.readLine();
					//creates new Drink based on the previously read values
					Drink d = new Drink(line, r, drinkIngredients, amounts,
							instruct);
					data.allDrinks.add(d);
				}
			} catch (IOException a) {
				// TODO Auto-generated catch block
				a.printStackTrace();
			}

			Collections.sort(data.allDrinks);
			data.generatedDrinks = true;
		}

		final Button canMakeButton = (Button) findViewById(R.id.canMake);
		canMakeButton.setOnClickListener(new View.OnClickListener() {

			public void onClick(View v) {
				//if user cannot make any drinks, returns message
				if (data.canMakeDrinks.isEmpty() && data.almostMake.isEmpty()) {
					AlertDialog.Builder adb = new AlertDialog.Builder(
							MyBar.this);
					adb.setTitle("Sorry!");
					adb
							.setMessage("You can't make any drinks yet!\nUpdate your inventory first.");
					adb.setPositiveButton("Ok", null);
					adb.show();
					//otherwise starts CanMakeDrinks activity
				} else {
					Intent myIntent = new Intent(v.getContext(),
							TabDrinks.class);
					MyBar.this.startActivity(myIntent);
				}
			}
		});


		final Button ingredientButton = (Button) findViewById(R.id.ingredients);
		ingredientButton.setOnClickListener(new View.OnClickListener() {
			//Starts TabInventory Activity
			public void onClick(View v) {
				Intent myIntent = new Intent(v.getContext(), TabInventory.class);
				MyBar.this.startActivity(myIntent);
			}
		});

		//Starts AllDrinks Activity; used to browse drink recipes
		final Button browseButton = (Button) findViewById(R.id.browse);
		browseButton.setOnClickListener(new View.OnClickListener() {

			public void onClick(View v) {
				Intent myIntent = new Intent(v.getContext(), AllDrinks.class);
				MyBar.this.startActivity(myIntent);
			}
		});

		//Displays a random drink recipe
		final Button randomAllButton = (Button) findViewById(R.id.random);
		randomAllButton.setOnClickListener(new View.OnClickListener() {
			public void onClick(View v) {
				int randomIndex = (int) (Math.random() * data.allDrinks.size());
				AlertDialog.Builder adb = new AlertDialog.Builder(MyBar.this);
				Drink drink = data.allDrinks.get(randomIndex);
				adb.setTitle(drink.getDisplayTitle());
				adb.setMessage(drink.getDisplayMessage());
				adb.setPositiveButton("Ok", null);
				adb.show();
			}
		});

	}
}