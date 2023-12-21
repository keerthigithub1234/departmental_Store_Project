package com;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;

public class DepartmentalStoreImpl implements DepartmentalStore{

	Scanner sc= new Scanner(System.in);

	/**
	 * Product id is key -> <Interger>
	 * Product Object is the Value -> <Product>
	 */

	Map<Integer,Product> db=new LinkedHashMap<Integer,Product>();
	int Totalbill=0;

	@Override
	public void addProduct() {
		db.put(1, new Product("Chocolates",20,5));
		db.put(2, new Product("Biscuits",30,10));
		db.put(3, new Product("IceCreams",50,20));

	}

	@Override
	public void displayProduct() {
		Set<Integer> keys = db.keySet(); // 1 2 3 

		for(int key : keys)
		{
			Product p = db.get(key);  // getting product based on key
			System.out.println("enter " +key+" to order" +p.getName());
			System.out.println("Available quantity is" +p.getQuantity());
			System.out.println("cost:"+p.getCost());
			System.out.println("--------------------------");

		}

	}

	@Override
	public void buyProduct() {
		System.out.println("Enter Choice :");//choice ->key
		int choice  =  sc.nextInt();
		
		//getting Product based on choice (key)
		//if choice/key is not present, it returns null
		Product p = db.get(choice);
		
		if(p!=null)
		{
			System.out.println("Enter the Quantity :");
			int quantity = sc.nextInt();
			
			//checking if the quantity is available or not
			if(quantity  <= p.getQuantity())
			{
				//Calculating current product cost
				int productCost = quantity * p.getCost();
				
				//setting the new updated quantity
				p.setQuantity(p.getQuantity() - quantity);
				
				//adding current product cost to Totalbill
				Totalbill = Totalbill + productCost;
				
				System.out.println("Ordered "+quantity +" " + p.getName());
				System.out.println("Current Product cost is Rs."+productCost);
				System.out.println("Your Total bill as of now is Rs. " +Totalbill);
				
			}
			else
			{
				try
				{
					String message="Invalid Quantity , Please Enter the Valid Quantity(Product out of stock!!!!)";
					throw new InvalidChoiceException(message);
				}
				catch(Exception e)
				{
					System.out.println(e.getMessage());
				}
				
			}
		}
		else
		{
			try
			{
				String message="Invalid Choice ,Please Enter the Valid choice";
				throw new InvalidChoiceException(message);
			}
			catch(Exception e)
			{
				System.out.println(e.getMessage());
			}
			
		}


	}

	@Override
	public void checkout() {
		System.out.println("Your total bill is Rs. " +Totalbill);
		System.out.println("Thanks you for shopping at xyz Departmental Store");

	}

}
