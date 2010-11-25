package jia.begins.exercises.basics.maria.raleva;

import java.util.Random;

/**
 * Created by IntelliJ IDEA.
 * User: maria
 * Date: Nov 24, 2010
 * Time: 1:08:11 PM
 * In this file: 1 abstract class Shape,
 * Circle and Rectangle extends Shape,
 * Class Square extends Rectangle
 * In Exercise7 the is array of 10 elements of Shape type
 * The elements inside are generated randomly(Circles, Rectangles, Squares)
 */

/**
 * abstract class Shape with
 * abstract method perimeter() and
 * abstract method area()
 */
abstract class Shape{
    abstract public double perimeter();
    abstract public double area();
}

/**
 * Class Circle extends Shape
 * Overrides methods perimeter(), area() and toString
 */
class Circle extends Shape{
     
     private double radius;

    Circle(double radius) {
        this.radius = radius;
    }

    public double getRadius() {
        return radius;
    }

    /**
     * Counts the perimeter of a circle with giver radius
     * @return  double perimeter
     */
    @Override
    public double perimeter(){
          return 2*Math.PI*radius;
    }

    /**
     * Counts area of a circle with given radius
     * @return  double area
     */
    @Override
    public double area(){
          return radius*radius*Math.PI;
    }

    /**
     * @return String - Shape kind and radius value
     */
    @Override
    public String toString(){
        return String.format("Shape Circle:\n Radius = %s\n", radius);
    }

}

/**
 * Class Shape extends Shape
 * Overrides methods perimeter(), area() and toString()
 */
class Rectangle extends Shape{
    private double width;
    private double height;

    public double getWidth() {
        return width;
    }
    public double getHeight() {
        return height;
    }

    Rectangle(double width, double height) {
        this.width = width;
        this.height = height;
    }

     /**
     * Counts the perimeter of a rectangle with giver width and height
     * @return  double perimeter
     */
    @Override
    public double perimeter() {
        return 2*width + 2*height;
    }

     /**
     * Counts the area of a rectangle with giver width and height
     * @return  double area
     */
    @Override
    public double area() {
        return width*height;
    }

    /**
     * @return  String - Shape kind and value of height and width
     */
    @Override
    public String toString() {
         return String.format("Shape Rectangle:\n Height = %s\n Width = %s\n", height, width);
    }
}
/**
 * Class Square extends Rectangle
 * Override method to String()
 */
class Square extends Rectangle{
    private double lengthOfSide;

    public double getLengthOfSide() {
        return lengthOfSide;
    }

    Square(double lengthOfSide) {
        super(lengthOfSide, lengthOfSide); //height==width
        this.lengthOfSide = lengthOfSide;
    }
     /**
     * @return  String - Shape kind and value of the length side
     */
    @Override
    public String toString(){
        return String.format("Shape Square:\n Length of the side = %s\n", lengthOfSide);
    }

}

/**
 * Fill an array of random elements(Circle, Rectangle and Square)
 * and prints there area and perimeter
 */
public class Exercise7 {
    public static void main(String[] args) {

        Random r = new Random();
        //array with 10 elements
        Shape[] arrayOfShapes = new Shape[10];
        int intNumber;
        for(int i = 0;i<10;++i){
            intNumber = r.nextInt(3); //random number(0..2)
            if(intNumber == 0){       //Circle element
                arrayOfShapes[i]= new Circle(r.nextDouble()*100);
            }else if(intNumber == 1){ //Rectangle element
                arrayOfShapes[i] = new Rectangle(r.nextDouble()*100,r.nextDouble()*100);
            }else {                   //Square element
                arrayOfShapes[i] = new Square(r.nextDouble()*100);
            }
        } //end for
        //Print area and perimeter for each element into the array
        for (Shape shape : arrayOfShapes) {
            System.out.println(shape.toString());
            System.out.println("Area: "+shape.area());
            System.out.println("Perimeter: "+shape.perimeter());
            System.out.println("----------------------------------------");
        }
    }//end main
    
}
