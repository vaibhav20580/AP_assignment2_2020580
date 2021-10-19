package com.company;
import java.util.Scanner;
import java.io.*;
public class Assignment implements Assessment{
    private String problem;
    private int marks;
    private int id;
    public Assignment(int id){
        this.id = id;
        System.out.println("Enter problem statement: ");
        Scanner sc = new Scanner(System.in);
        this.problem = sc.nextLine();
        System.out.println("Enter max marks: ");
        this.marks = sc.nextInt();
    }
    @Override
    public void view(){
        System.out.println("ID " + this.id + ": Assignment: " + this.problem + " Max Marks: " + marks);
    }
    @Override
    public int getid(){
        return this.id;
    }
    @Override
    public int getmarks(){
        return this.marks;
    }
    @Override
    public String getproblem(){
        return this.problem;
    }
    public void setproblem(String problem){
        this.problem=problem;
    }
    public void setMarks(int marks){
        this.marks = marks;
    }
}
