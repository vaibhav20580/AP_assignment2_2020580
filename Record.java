package com.company;
import java.io.*;
public class Record {
    Slide[] slide_list = new Slide[100];
    int slide_cnt;
    Instructor[] inst_list = new Instructor[100];
    int inst_cnt;
    Student[] std_list = new Student[100];
    int std_cnt;
    Video[] vid_list = new Video[100];
    int vid_cnt;
    Assignment[] assign_list = new Assignment[100];
    int assign_cnt;
    Quiz[] quiz_list = new Quiz[100];
    int quiz_cnt;
    int com_cnt;
    Comment[] com_list = new Comment[100];
    int id;
    public Record(){
        this.slide_cnt = 0;
        this.inst_cnt = 0;
        this.std_cnt = 0;
        this.vid_cnt = 0;
        this.assign_cnt = 0;
        this.quiz_cnt=0;
        this.id = 0;
        this.com_cnt=0;
    }
}
