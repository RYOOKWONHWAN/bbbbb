package com.mycompany.myapp;

import java.util.Scanner;

public class bookmain {
	public static void main(String[] args) {
		KakaoBookSearch kbs= new KakaoBookSearch();
//		SearchBook sb= new SearchBook();
		for(int i= 0; i < 4;i++) {
			System.out.println();
			System.out.println(kbs.GetTitleList().get(i));
			System.out.println(kbs.GetIsbnList().get(i)); 
			System.out.println(kbs.GetAuthroList().get(i));
			System.out.println(kbs.GetContentList().get(i));
			System.out.println(kbs.GetEA_ADD_CODE_List().get(i));
			
			System.out.println(kbs.GetThumbnailList().get(i));
			System.out.println(kbs.GetPublisherList().get(i));
		}
		Scanner sc= new Scanner(System.in);
		int a = sc.nextInt();
		
		SaveBook sb= new SaveBook(kbs.GetIsbnList().get(a),kbs.GetTitleList().get(a),kbs.GetAuthroList().get(a));
		
	}
}
