package kevinpage.com;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Set;

import android.app.AlertDialog;

public class Drink implements Comparable<Drink>{

	public HashMap<String,String> ingredients = new HashMap<String,String>();
	public String name;
	public int rating;
	public String instructions;
	public boolean canMake;
	public boolean almostMake;
	public int ingCount;

	Drink(String name, int rating, ArrayList<String> ingredientNames, ArrayList<String> amount, String instructions){
		//checks to make sure each ingredient has a corresponding amount
		if (ingredientNames.size() != amount.size())
			return;
		//adds each ingredient/amount combo to this Drink's ingredient HashMap
		for(int i = 0; i < ingredientNames.size(); i++) {
			this.ingredients.put(ingredientNames.get(i), amount.get(i));
		}

		this.name = name;
		this.rating = rating;
		this.instructions = instructions;
	}

	Drink (){
	}

	public String getName() {
		return this.name;
	}
	//Checks if the user has the ingredients to make this drink
	public boolean canMake() {
		boolean canMake = true;
		Set<String> neededIngredients = this.ingredients.keySet();
		//for each ingredient in this Drink, checks to see if that ingredient is in the ownedIngredients TS
		for(String ingredient: neededIngredients) {
			if (!data.ownedIngredients.contains(ingredient)) {
				canMake = false;
				break;
			}
		}
		return canMake;
	}
	
	public boolean almostMake() {
		int count=0;
		almostMake=false;
		Set<String> neededIngredients = this.ingredients.keySet();
		for(String ingredient: neededIngredients) {
			if (data.ownedIngredients.contains(ingredient)) {
				count++;
			}
		}
			if((count+1) == this.ingredients.size()) {
				almostMake=true;
		}
		return almostMake;
	}

	//retrieves drink name
	public String toString(){
		return this.name;
	}

	//used for sorting drinks
	public int compareTo(Drink drink){
		return this.name.compareTo(drink.name);
	}

	//retrieves formatted ingredients and instructions
	public String getDisplayMessage(){
		String message = "Ingredients:\n";
		for(String ingredient : this.ingredients.keySet()){
			message += ingredient + " - " + this.ingredients.get(ingredient) + "\n";
		}
		message += "\n";
		message += "Instructions: \n";
		message += this.instructions;
		return message;
	}

	//retrieves formatted name and rating
	public String getDisplayTitle(){
		return this.name + " - Rating: " + this.rating;
	}

	public static void configureDialog(AlertDialog.Builder adb, Drink drink){
	 	adb.setTitle(drink.getDisplayTitle());
	 	adb.setMessage(drink.getDisplayMessage());
	 	adb.setPositiveButton("Ok", null);
	}
}