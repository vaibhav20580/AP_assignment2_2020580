package com.company;
import java.util.Scanner;
import java.io.*;
public class Video {
    private String name;
    private String file;
    private String date;
    private String inst;
    public Video(String inst, String date, String name, String file){
        this.inst = inst;
        this.date = date;
        this.name = name;
        this.file = file;
    }
    public void view_video(){
        System.out.println("Title: " + this.name);
        System.out.println("Video File: " + this.file);
        System.out.println("Date of Upload: " + this.date);
        System.out.println("Uploaded by: " + this.inst);
    }
}
