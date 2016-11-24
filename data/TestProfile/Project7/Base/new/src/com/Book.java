package com;

public class Book{
	
	private int size;
	private int curPage;
	
	public Book(int size){
		this.size=size;
		this.curPage=-1;
	}
	
	public int getCurPage(){
		return this.curPage;
	}
	
	
	public void close(){
		this.curPage = -1;
	}
	
	public void open(){
		this.curPage=1;
	}
	
	public boolean isOpened(){
		return this.curPage!=-1;
	}
	
	public void read(){
		if(this.isOpened()){
			if(this.curPage<this.size){
				this.curPage++;
			}else{
				this.close();
			}
		}
	}
		
}