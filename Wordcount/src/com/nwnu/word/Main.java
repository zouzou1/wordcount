package com.nwnu.word;

import java.io.BufferedReader;
import java.io.FileReader; 
import java.io.IOException;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Scanner; 
import java.util.TreeMap;
import java.io.File;
import java.io.FileWriter;
import java.util.Map.Entry;

public class Main { 
	
	static Scanner scanner = new Scanner(System.in);
	static Map<String,Integer> hashMap = new LinkedHashMap<String, Integer>(); 
    @SuppressWarnings("resource")
	public static void main(String[] args)throws IOException { 
    	
    	//读取要处理的文件
    	System.out.println("请输入文件名:");
    	String src = scanner.next();
		BufferedReader message = new BufferedReader(new FileReader(src));
        //计算词频
    	Map<String, Integer> treeMap = new TreeMap<String, Integer>();
        String value= message.readLine();
        while (value != null) {
           	String[] words = value.split("[^a-zA-Z]"); //正则表达式处理标点符号
           	for (int i = 0; i < words.length; i++) {   		
           		String key = words[i].toLowerCase(); //大写转换小写
              	if (key.length() > 0) {
              		if (!treeMap.containsKey(key)) {
               			treeMap.put(key, 1);
               		} 
               		else { 
              			int k = treeMap.get(key)+1;// 如果不是第一次出现，就把k值++
               			treeMap.put(key, k);
               		}
              	}
           	} 
           	value = message.readLine();
        }
        System.out.println("请选择您想执行的功能");
        System.out.println("1:显示查询的单词词频");
        System.out.println("2:输出词频最高的前N个单词");
        System.out.println("3:输出单词及词频到result.txt");
        System.out.println("0:退出");
        int w= scanner.nextInt();  
        while(w!=0){
        	switch(w){
        		case 1:
        				word(treeMap);
            	        break;
        		case 2:
                    	print(treeMap);
            	        break;
        		case 3:
                    	Write(treeMap);
            	        break;
                }
                System.out.println("请选择执行的功能：");
                w= scanner.nextInt();  
            }
        }    
    
    //显示词频
    public static void word(Map<String, Integer> map){
        
    	System.out.println("请输入需要查询的单词 :");
        scanner.nextLine();
        String string = scanner.nextLine();		
        String[] word= string.split("/");			
        float sum;
        for(int i = 0; i < word.length; i++) {
        	for(Map.Entry<String,Integer> w : map.entrySet()) { 
        		//int count=w.getValue();      			
        		if(word[i].equals(w.getKey()))
        		{  
        			System.out.println("单词"+w.getKey() + "出现" + w.getValue()+"次");
        			sum=(float)(w.getValue())/50; 
        			for(int j = 0;j < sum; j++){
        				System.out.print("#");
        			}
        			System.out.println();
        		}  
        	} 
        }
    }

    //输出前n个单词和呃，绘制柱状图
    public static void print(Map<String, Integer> map) {  
    	  
    	wordcount rs = new wordcount();
    	  hashMap = rs.sort(map,2);
    	  System.out.println("请输入查看降序排列的单词个数n：");
          int n = scanner.nextInt();
          for(Entry<String,Integer> w : hashMap.entrySet()) {  
              System.out.println("单词"+w.getKey() + "在文中出现" + w.getValue() + "次");  
              n--;
              for(int i = 0; i < w.getValue(); i++) {
            	  System.out.print("▋"+ "");
              }
              System.out.println();
              if(n <= 0)	
              	break;
          } 
          System.out.println();
        }  
      
    //排序写入result.txt
    public static void Write(Map<String, Integer> map)throws IOException {  
    	  wordcount rs = new wordcount();
    	  hashMap = rs.sort(map, 3);
          File file = new File("result.txt");
          FileWriter f = new FileWriter(file.getAbsoluteFile());
          for(Entry<String,Integer> w: hashMap.entrySet()) {
        	  f.write(w.getKey() + ""+ "共出现:" + w.getValue() + "次                                                       ");
          }
          f.close();
          System.out.println("已经保存到文档中，请到文件中检查！");
      }  
}
