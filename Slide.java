package com.company;
import java.util.Scanner;
public class Slide {
    private String name;
    private int num;
    private String[] content = new String[100];
    private String inst;
    private String date;
    public Slide(String inst, String date){
        this.inst = inst;
        this.date = date;
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter topic of Slide:");
        this.name = sc.nextLine();
        System.out.println("Enter number of Slides:");
        this.num = sc.nextInt();
        sc.nextLine();
        for(int i=0; i<this.num; i++){
            System.out.println("Content of slide " + (i+1) + ":");
            this.content[i] = sc.nextLine();
        }
    }
    public void view_slide(){
        System.out.println("Title: " + this.name);
        for(int i=0; i<this.num; i++){
            System.out.println("Slide " + (i+1) + ": " + this.content[i]);
        }
        System.out.println("Number of Slides: " + this.num);
        System.out.println("Date of Upload: " + this.date);
        System.out.println("Uploaded by: " + this.inst);
    }
}
