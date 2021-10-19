package com.company;
import java.util.Scanner;
import java.io.*;
public class Quiz implements Assessment{
    private String problem;
    private int id;
    private int marks = 1;
    public Quiz(int id){
        System.out.println("Enter quiz question: ");
        this.id = id;
        Scanner sc = new Scanner(System.in);
        this.problem = sc.nextLine();
    }
    @Override
    public void view(){
        System.out.println("ID " + this.id + ": Quiz: " + this.problem);
    }
    @Override
    public int getid(){
        return this.id;
    }
    @Override
    public String getproblem(){
        return this.problem;
    }
    @Override
    public int getmarks(){
        return marks;
    }
    public void setproblem(String problem){
        this.problem = problem;
    }
}
