package com.line.dto;

public class CardDateStatisticsDto {

	private String date;
	
	private int num;
	
	private int pass;
	
	private int unpass;
	
	private float rate;
	
	public CardDateStatisticsDto(String date, int num, int pass) {
		super();
		this.date = date;
		this.num = num;
		this.pass = pass;
		this.unpass = num - pass;
		this.rate = (float) (pass * 100.0 / (num * 1.0));
	}

	@Override
	public String toString() {
		return "CardDateStatisticsDto [date=" + date + ", num=" + num
				+ ", pass=" + pass + ", unpass=" + unpass + ", rate=" + rate
				+ "]";
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public int getNum() {
		return num;
	}

	public void setNum(int num) {
		this.num = num;
	}

	public int getPass() {
		return pass;
	}

	public void setPass(int pass) {
		this.pass = pass;
	}

	public int getUnpass() {
		return unpass;
	}

	public void setUnpass(int unpass) {
		this.unpass = unpass;
	}

	public float getRate() {
		return rate;
	}

	public void setRate(float rate) {
		this.rate = rate;
	}
	
	
}
