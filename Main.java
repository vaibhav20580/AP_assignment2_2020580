package com.company;
import java.util.Scanner;
import java.io.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
public class Main {
    public static void inst_menu(){
        System.out.println("1. Add class material\n" +
                "2. Add assessments\n" +
                "3. View lecture materials\n" +
                "4. View assessments\n" +
                "5. Grade assessments\n" +
                "6. Close assessment\n" +
                "7. View comments\n" +
                "8. Add comments\n" +
                "9. Logout");
    }
    public static void std_menu(){
        System.out.println("1. View lecture materials\n" +
                "2. View assessments\n" +
                "3. Submit assessment\n" +
                "4. View grades\n" +
                "5. View comments\n" +
                "6. Add comments\n" +
                "7. Logout");

    }
    public static void add_class_material(Record r, int idx){
        System.out.println("1. Add Lecture Slide \n2. Add Lecture Video ");
        Scanner sc = new Scanner(System.in);
        int a = sc.nextInt();
        sc.nextLine();
        LocalDateTime obj = LocalDateTime.now();
        DateTimeFormatter myFormatObj = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");
        String date = obj.format(myFormatObj);
        if(a==1){
            r.slide_list[r.slide_cnt] = new Slide(r.inst_list[idx].getname(), date);
            r.slide_cnt++;
        }
        if(a==2){
            System.out.println("Enter topic of Video: ");
            String s1 = sc.nextLine();
            System.out.println("Enter filename of Video: ");
            String s2 = sc.nextLine();
            if(s2.substring(s2.length()-4,s2.length()).equals(".mp4")) {
                r.vid_list[r.vid_cnt] = new Video(r.inst_list[idx].getname(), date, s1, s2);
                r.vid_cnt++;
            }
            else{
                System.out.println("Invalid filename");
                return;
            }
        }
    }
    public static void add_assessments(Record r, int idx){
        System.out.println("1. Add Assignment \n2. Add Quiz ");
        Scanner sc = new Scanner(System.in);
        int a = sc.nextInt();
        if(a==1){
            r.assign_list[r.assign_cnt] = new Assignment(r.id);
            r.assign_cnt++;
            r.id++;
        }
        if(a==2){
            r.quiz_list[r.quiz_cnt] = new Quiz(r.id);
            r.quiz_cnt++;
            r.id++;
        }
    }
    public static void view_lecture_material(Record r){
        for(int i=0; i<r.slide_cnt; i++){
            r.slide_list[i].view_slide();
        }
        for(int i=0; i<r.vid_cnt; i++){
            r.vid_list[i].view_video();
        }
    }
    public static void view_assessments(Record r,int idx, int type){
        System.out.println("List of Open Assignments: ");
        int cnt = 0;
        for(int i=0; i<r.assign_cnt; i++){
            if(type == 0 && r.std_list[idx].check[r.assign_list[i].getid()]==0){
                r.assign_list[i].view();
                cnt++;
            }
            else if(type ==1){
                r.assign_list[i].view();
                cnt++;
            }
        }
        for(int i=0; i<r.quiz_cnt; i++){
            if(type == 0 && r.std_list[idx].check[r.quiz_list[i].getid()]==0){
                r.quiz_list[i].view();
                cnt++;
            }
            else if(type ==1){
                r.quiz_list[i].view();
                cnt++;
            }
        }
        if(cnt==0){
            System.out.println("No pending assessments");
        }
    }
    public static void submit_assessments(Record r, int idx){
        System.out.println("Pending Assessments: ");
        int cnt = 0;
        for(int i=0; i<r.assign_cnt; i++){
            if(r.std_list[idx].check[r.assign_list[i].getid()]==0){
                r.assign_list[i].view();
                cnt++;
            }
        }
        for(int i=0; i<r.quiz_cnt; i++){
            if(r.std_list[idx].check[r.quiz_list[i].getid()]==0){
                r.quiz_list[i].view();
                cnt++;
            }
        }
        if(cnt==0){
            System.out.println("No pending assessments");
            return;
        }
        System.out.println("Enter id of assessment: ");
        Scanner sc = new Scanner(System.in);
        int id = sc.nextInt();
        sc.nextLine();
        int f=0;
        for(int i=0; i< r.assign_cnt; i++){
            if(id == r.assign_list[i].getid()){
                f = 1;
            }
        }
        r.std_list[idx].check[id] = 1;
        if (f==1) {
            System.out.println("Enter filename of assessment: ");
            String str = sc.nextLine();
            if(!str.substring(str.length()-4,str.length()).equals(".zip")){
                System.out.println("Invalid filename");
                return;
            }
            r.std_list[idx].filename[id] = str;
        }
        else{
            String p="";
            for(int i=0; i<r.quiz_cnt; i++){
                if(id == r.quiz_list[i].getid()){
                    p = r.quiz_list[i].getproblem();
                }
            }
            System.out.println(p);
            String str = sc.nextLine();
            r.std_list[idx].filename[id] = str;
        }
    }
    public static void grade_assessments(Record r, int idx){
        view_assessments(r,idx,1);
        System.out.println("Enter ID of assessments:");
        Scanner sc = new Scanner(System.in);
        int id = sc.nextInt();
        System.out.println("Choose ID from these ungraded submissions: ");
        for(int i=0; i<r.std_cnt; i++){
            if(r.std_list[i].check[id]==1 && r.std_list[i].grade[id]==-1){
                System.out.println(i + ". " + r.std_list[i].getname());
            }
        }
        int sid = sc.nextInt();
        System.out.println("Submission: "+ r.std_list[sid].filename[id]);
        int m=1;
        for(int i=0; i< r.assign_cnt; i++){
            if(id == r.assign_list[i].getid()){
                m = r.assign_list[i].getmarks();
            }
        }
        System.out.println("Max marks: " + m +"\n Marks scored: ");
        int M = sc.nextInt();
        r.std_list[sid].grade[id] = M;
        r.std_list[sid].grader[id] = r.inst_list[idx].getname();
    }
    public static void view_grades(Record r, int idx){
        System.out.println("Graded Submissions:");
        for(int i=0; i<100; i++){
            if(r.std_list[idx].grade[i] != -1){
                System.out.println("Submission: " +r.std_list[idx].filename[i] +"\n Marks scored: " + r.std_list[idx].grade[i] + "\n Graded by: "+r.std_list[idx].grader[i]);
            }
        }
        System.out.println("Ungraded Submissions:");
        for(int i=0; i<100; i++){
            if(r.std_list[idx].grade[i] == -1 && r.std_list[idx].check[i]==1){
                System.out.println("Submission: "+ r.std_list[idx].filename[i]);
            }
        }
    }
    public static void close_assessments(Record r){
        view_assessments(r,0,1);
        System.out.println("Enter id of assignment to close: ");
        Scanner sc = new Scanner(System.in);
        int x = sc.nextInt();
        for(int i=0; i<r.assign_cnt; i++){
            if(r.assign_list[i].getid() == x){
                for(int j=i; j<r.assign_cnt-1; i++){
                    r.assign_list[j].setproblem(r.assign_list[j+1].getproblem());
                    r.assign_list[j].setMarks(r.assign_list[j+1].getmarks());
                }
                r.assign_cnt--;
            }
        }
        for(int i=0; i<r.quiz_cnt; i++){
            if(r.quiz_list[i].getid() == x){
                for(int j=i; j<r.quiz_cnt-1; i++){
                    r.quiz_list[j].setproblem(r.quiz_list[j+1].getproblem());
                }
                r.quiz_cnt--;
            }
        }
    }
    public static void view_comments(Record r){
        for(int i=0; i<r.com_cnt; i++){
            r.com_list[i].view_comment();
        }
    }
    public static void add_comments(Record r, int idx, int type){
        LocalDateTime obj = LocalDateTime.now();
        DateTimeFormatter myFormatObj = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");
        String date = obj.format(myFormatObj);
        if(type==1){
            r.com_list[r.com_cnt] = new Comment(r.inst_list[idx].getname(), date);
            r.com_cnt++;
        }
        else{
            r.com_list[r.com_cnt] = new Comment(r.std_list[idx].getname(), date);
            r.com_cnt++;
        }
    }
    public static void main(String[] args){
        Record r = new Record();
        r.inst_cnt = 2;
        r.std_cnt = 3;
        r.inst_list[0] = new Instructor("I0");
        r.inst_list[1] = new Instructor("I1");
        r.std_list[0] = new Student("S0");
        r.std_list[1] = new Student("S1");
        r.std_list[2] = new Student("S2");
        while(true){
            Scanner sc = new Scanner(System.in);
            System.out.println("Welcome to Backpack\n" +
                    "1. Enter as instructor\n" +
                    "2. Enter as student\n" +
                    "3. Exit");
            int inp = sc.nextInt();
            if(inp == 1){
                System.out.println("Instructors:");
                for(int i=0; i<r.inst_cnt; i++){
                    System.out.println(i + " - " + r.inst_list[i].getname());
                }
                System.out.println("Choose id:");
                int idx = sc.nextInt();
                while(true){
                    System.out.println("Welcome " + r.inst_list[idx].getname());
                    inst_menu();
                    int in = sc.nextInt();
                    if(in==1){
                        add_class_material(r,idx);
                    }
                    if(in==2){
                        add_assessments(r,idx);
                    }
                    if(in==3){
                        view_lecture_material(r);
                    }
                    if(in==4){
                        view_assessments(r,idx,1);
                    }
                    if(in==5){
                        grade_assessments(r,idx);
                    }
                    if(in==6){
                        close_assessments(r);
                    }
                    if(in==7){
                        view_comments(r);
                    }
                    if(in==8){
                        add_comments(r,idx,1);
                    }
                    if(in==9){
                        break;
                    }
                }
            }
            if(inp == 2){
                System.out.println("Students: ");
                for(int i=0; i<r.std_cnt; i++){
                    System.out.println(i + " - " + r.std_list[i].getname());
                }
                System.out.println("Choose id:");
                int idx = sc.nextInt();
                while(true){
                    System.out.println("Welcome " + r.std_list[idx].getname());
                    std_menu();
                    int in = sc.nextInt();
                    if(in==1){
                        view_lecture_material(r);
                    }
                    if(in==2){
                        view_assessments(r,idx,0);
                    }
                    if(in==3){
                        submit_assessments(r,idx);
                    }
                    if(in==4){
                        view_grades(r,idx);
                    }
                    if(in==5){
                        view_comments(r);
                    }
                    if(in==6){
                        add_comments(r,idx,0);
                    }
                    if(in==7){
                        break;
                    }
                }
            }
            if(inp == 3){
                break;
            }
        }
    }
}
