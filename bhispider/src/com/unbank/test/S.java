package com.unbank.test;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;

public class S {

	public static void main(String[] args) {
		String a="小明去吃饭，妈妈很高兴";
		String b="妈妈很高兴，d小明去吃饭了";
		System.out.println(RSAC(a,b));
		System.out.println(getSimilarityRatio(a,b));
	}
	/**
     * 两个字符串的相同率*/
    public static float RSAC(String str1,String str2){
    	List<String> list=new ArrayList<String>();
    	for (int k = 0; k < str1.length(); k++) {
			for (int j = 0; j < str2.length(); j++) {
				if (str1.charAt(k) == str2.charAt(j) && !isExist(list, str1.substring(k, k + 1))){
					list.add(str1.substring(k, k + 1));
					break;
				}
			}
		}
        for (String string : list) {
			System.out.println(string);
		}
		float size = (float) (list.size()*2) / (str1.length()+str2.length());
		DecimalFormat df = new DecimalFormat("0.00");// 格式化小数，不足的补0
		String ratiostr = df.format(size);// 返回的是String类型的
		float ratio = Float.parseFloat(ratiostr);
		return ratio;
    }
    /**
     * 是否存在相同*/
    private static boolean isExist(List<String> list, String dest) {
		for (String s : list) {
			if (dest.equals(s))
				return true;
		}
		return false;
	}
    
	private static int compare(String str, String target) {
        int d[][]; // 矩阵
        int n = str.length();
        int m = target.length();
        int i; // 遍历str的
        int j; // 遍历target的
        char ch1; // str的
        char ch2; // target的
        int temp; // 记录相同字符,在某个矩阵位置值的增量,不是0就是1
        
        if (n == 0) {
            return m;
        }
        
        if (m == 0) {
            return n;
        }
        
        d = new int[n + 1][m + 1];
        
        for (i = 0; i <= n; i++) { // 初始化第一列
            d[i][0] = i;
        }
        
        for (j = 0; j <= m; j++) { // 初始化第一行
            d[0][j] = j;
        }
        
        for (i = 1; i <= n; i++) { // 遍历str
            ch1 = str.charAt(i - 1);
            // 去匹配target
            for (j = 1; j <= m; j++) {
                ch2 = target.charAt(j - 1);
                if (ch1 == ch2) {
                    temp = 0;
                } else {
                    temp = 1;
                }
                
                // 左边+1,上边+1, 左上角+temp取最小
                d[i][j] = min(d[i - 1][j] + 1, d[i][j - 1] + 1, d[i - 1][j - 1] + temp);
            }
        }
        
        return d[n][m];
    }
    
    private static int min(int one, int two, int three) {
        return (one = one < two ? one : two) < three ? one : three;
    }
    
    /**
     * 获取两字符串的相似度
     * 
     * @param str
     * @param target
     * 
     * @return
     */
    
    public static float getSimilarityRatio(String str, String target) {
        return (float) compare(str, target) / Math.max(str.length(), target.length());
        
    }
}
