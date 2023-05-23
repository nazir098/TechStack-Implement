package com.example.demo.domain;

import org.slf4j.LoggerFactory;

import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Optional;

public class Test {
    private static  void fun(){
        String s="the rat the cow the man is running in the house";

        String arr[]=s.split("\\s+");
        HashSet<String> map=new HashSet<>();
        for(int i=0;i<arr.length;i++){

            if(!map.contains(arr[i])){
                System.out.print(arr[i] + " ");
            }
            map.add(arr[i]);
        }

    }
    private static void function(){
        String str="My life my rule";
        String fstr="";
        String[]rs=str.split(" ");
        for(int s=0;s<rs.length;s++){
if(!fstr.contains(rs[s])){

fstr=fstr+rs[s] + " "; }
        } System.out.println(fstr);
    }
    public static void main(String[] args) {

    }
}
