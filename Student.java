package com.company;
import java.io.*;
public class Student {
    private String name;
    public int[] check = new int[100];
    public int[] grade = new int[100];
    public String[] grader = new String[100];
    public String[] filename = new String[100];
    public Student(String name) {
        this.name = name;
        for(int i=0; i<100; i++){
            this.check[i] = 0;
            this.grade[i] = -1;
        }
    }
    public String getname(){
        return this.name;
    }
}
