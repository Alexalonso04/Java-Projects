/*Changes
 * 42:31. Added ";"
 * Total Changes: 1.
 */

//Alejandro Alonso
//Section: 1149
//alexalonso04
//Project Number: 2
//Description: Program that takes user input and calculates different data depending on the shape selected by the user
import java.util.Scanner;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.lang.Math;

public class Project2 {
	public static void main (String [] args) {
		System.out.println("Shape Machine login");
		Scanner scan = new Scanner(System.in);
		
		String date = new SimpleDateFormat("dd-MM-yyyy").format(new Date());
		String day = date.substring(0,2);
		String month = date.substring(3,5);
		String year = date.substring(6,10);

		int inputDay;
		int inputMonth;
		int inputYear;
		
		String Day = null;
		String Month = null;
		String Year = null;
		
		int error = 0;
		int i = 0;
		
		String selection = null;
		
		//Login
		for (;error < 4;) 
		{									//Why does the second loop runs even though the conditions for the wrap loop are false?
			System.out.print("What is today's day? ");
			Day = scan.next();
			inputDay = Integer.parseInt(Day);
			
			System.out.print("What is today's month? ");
			Month = scan.next();
			inputMonth = Integer.parseInt(Month);
			
			System.out.print("What is today's year? ");
			Year = scan.next();
			inputYear = Integer.parseInt(Year);
						
			if ( (!Day.equals(day)) || (!Month.equals(month)) || (!Year.equals(year)) ) {
				error++;
				if (error == 3) {
					System.out.print("#ERROR 3rd invalid login attempt. Terminating program.");
					break;
				}
				if ( (Day.length() < 2 || Day.length() > 2 ) || (Month.length() > 2 || Month.length() < 2) || (Year.length() > 4 || Year.length() < 4) 
						&& (inputDay <= 0 || inputDay > 31)  || (inputMonth <= 0 || inputMonth > 12) || (inputYear <= 0)) {
					System.out.println("#ERROR Login attempt #" + error + " Invalid input. Please try again.");
					continue;
				} else {
					System.out.println("#ERROR Login attempt #" + error + " Invalid input. Please try again.");
					continue;
				}
			} else {
				System.out.println("Correct date. Welcome!");
			}
				
			//MENU
			for (i = 0;i == 0;) {
				System.out.println("---Welcome to the Shape Machine---");
				System.out.println("Available Options: ");
				System.out.println("Circles\n" + "Rectangles\n" + "Triangles\n" + "Exit\n");
				selection = scan.next();
				
				if (!selection.equals("Circles") && !selection.equals("Rectangles") && !selection.equals("Triangles")
					 && !selection.equals("Exit")){
					System.out.println("#ERROR Invalid option.Please try again.");
					continue;
				}
				//CIRCLES________________________________________________________________________________________________
				for (double radius; selection.equals("Circles");) {
					
					System.out.print(selection + " selected. Please enter the radius: ");
					radius = scan.nextDouble();
					
					//Invalid input
					while (radius <= (double) 0) {
						System.out.print("#ERROR Negative input. Please input the radius again: ");
						radius = scan.nextDouble();
					}
					
					//Calculations for valid input
					if (radius > (double) 0 ) {
						double C = Math.PI * (2*radius);				// C - circumference
						double Area = Math.PI * radius * radius;
						String circumference= Double.toString(C);
						String area = Double.toString(Area);
						String areaL = area.substring(0,area.indexOf(".")) + area.substring(area.indexOf(".")+1);
						String circumferenceL = circumference.substring(0,circumference.indexOf(".")) + circumference.substring(circumference.indexOf(".")+1);
						
						//OUTPUT
						System.out.println("The circumference is: " + circumference + "\n" + 
										   "The area is: " + Area);
						System.out.println("Total number of digits in the circumference is: " + circumferenceL.length());
						System.out.println("Total number of digits in the area is: " + areaL.length());
						break;
					}
				}
				
				//Rectangles_____________________________________________________________________________________________
				for (double sideA, sideB; selection.equals("Rectangles");) {
					
					System.out.print(selection + " selected. Please enter the 2 sides: ");
					sideA = scan.nextDouble();
					sideB = scan.nextDouble();
					
					//Invalid Input
					while (sideA <= (double) 0 || sideB<= (double) 0){										//While loop vs for loop?
						System.out.print("#ERROR Negative input. Please input the 2 sides again: ");
						sideA = scan.nextDouble();
						sideB = scan.nextDouble();
					}
					
					//Calculations for valid input
					if (sideA > (double) 0 && sideB > (double) 0) {
						double Area = sideA * sideB;
						double Perimeter = 2 * (sideA + sideB);
						String area = Double.toString(Area);
						String perimeter = Double.toString(Perimeter);
						String areaL = area.substring(0,area.indexOf(".")) + area.substring(area.indexOf(".")+1);
						String perimeterL = perimeter.substring(0,perimeter.indexOf(".")) + perimeter.substring(perimeter.indexOf(".")+1);
						
						//OUTPUT
						System.out.println("The area is: " + Area + "\n"+
											"The perimeter is: " + Perimeter);
						System.out.println("Total number of digits in the area is: " + areaL.length());
						System.out.println("Total number of digits in the perimeter is: " + perimeterL.length());
						break;
					}
				}
				
				//Triangles_____________________________________________________________________________________________
				for (double side1, side2, side3; selection.equals("Triangles");) {
					
					System.out.print(selection + " selected. Please enter the 3 sides: ");
					side1 = scan.nextDouble();
					side2 = scan.nextDouble();
					side3 = scan.nextDouble();
					
					
					//Invalid input
					if (side1 + side2 < side3 || side1 + side3 < side2 || side2 + side3 < side1){
						System.out.println("#ERROR Triangle is not valid. Returning to menu.");
						break;
					}
					while (side1 <= (double) 0 || side2 <= (double) 0 || side3 <= (double) 0) {
						System.out.print("#ERROR Negative input. Please input the 3 sides again: ");
						side1 = scan.nextDouble();
						side2 = scan.nextDouble();
						side3 = scan.nextDouble();
					}
					
					//Type of Triangle
					if (side1 + side2 > side3 && side1 + side3 > side2 && side2 + side3 > side1) {
						String triangleType = null;
						
						if (side1 == side2 && side2 == side3 && side3 == side1) {
							triangleType = "Equilateral";
						}
						else if ((side1 == side2 && side2 != side3) || (side3 == side2 && side2 != side1)) {
							triangleType = "Isosceles";
						}
						else {
							triangleType = "Scalene";
						}
						
					//Calculations for valid input
					double Perimeter = side1 + side2 + side3;
					double semiPerimeter = Perimeter / 2;
					double Area = Math.sqrt((semiPerimeter) * (semiPerimeter - side1) * (semiPerimeter - side2) 
								  * (semiPerimeter - side3) );		
					String perimeter = Double.toString(Perimeter);
					String area = Double.toString(Area);
					String areaL = area.substring(0,area.indexOf(".")) + area.substring(area.indexOf(".")+1);
					String perimeterL = perimeter.substring(0,perimeter.indexOf(".")) + perimeter.substring(perimeter.indexOf(".")+1);
					
						
					//OUPUT
					System.out.println("The triangle is: " + triangleType);
					System.out.println("The perimeter is: " + Perimeter + "\n" +
									   "The area is: " + Area);
					System.out.println("Total number of digits in the perimeter is: " + perimeterL.length());
					System.out.println("Total number of digits in the area is: " + areaL.length());	
					}
					break;
				}
				
				//Exit____________________________________________________________________________________________________
				if (selection.equals("Exit")) {
				System.out.println("Terminating the program. Have a nice day!");
				error =+4 ;
				i++;
				}
			}
		}
	}
}


