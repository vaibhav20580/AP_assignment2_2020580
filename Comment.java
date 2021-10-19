package com.company;
import java.util.Scanner;
import java.io.*;
public class Comment {
    private String com;
    private String user;
    private String date;
    public Comment(String user, String date){
        System.out.println("Enter comment: ");
        Scanner sc = new Scanner(System.in);
        this.com = sc.nextLine();
        this.user = user;
        this.date = date;
    }
    public void view_comment(){
        System.out.println(com + " - " + user + "\n" + date);
    }
}
