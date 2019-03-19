package com.line.dto;

public class CardPointStatisticsDto {

	private int point;
	
	private int num;
	
	private int pass;
	
	private int unpass;
	
	private int error;
	
	private int miss;
	
	private int contrary;
	
	private int more;
	
	private float rate;

	public CardPointStatisticsDto(int point, int num, int pass, int error,
			int miss, int contrary, int more) {
		super();
		this.point = point;
		this.num = num;
		this.pass = pass;
		this.error = error;
		this.miss = miss;
		this.contrary = contrary;
		this.more = more;
		this.unpass = num - pass;
		this.rate = (float) (unpass * 100.0 / (num * 1.0));
	}

	public int getPoint() {
		return point;
	}

	public void setPoint(int point) {
		this.point = point;
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

	public int getError() {
		return error;
	}

	public void setError(int error) {
		this.error = error;
	}

	public int getMiss() {
		return miss;
	}

	public void setMiss(int miss) {
		this.miss = miss;
	}

	public int getContrary() {
		return contrary;
	}

	public void setContrary(int contrary) {
		this.contrary = contrary;
	}

	public int getMore() {
		return more;
	}

	public void setMore(int more) {
		this.more = more;
	}

	public float getRate() {
		return rate;
	}

	public void setRate(float rate) {
		this.rate = rate;
	}

	@Override
	public String toString() {
		return "CardPointStatisticsDto [point=" + point + ", num=" + num
				+ ", pass=" + pass + ", unpass=" + unpass + ", error=" + error
				+ ", miss=" + miss + ", contrary=" + contrary + ", more="
				+ more + ", rate=" + rate + "]";
	}
	
}
