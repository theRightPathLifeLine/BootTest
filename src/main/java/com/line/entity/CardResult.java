package com.line.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class CardResult {
	
	@Id
	private String imageName;
	
	@Column
	private Date date;
	
	@Column
	private int point;
	
	@Column
	private int error;
	
	@Column
	private int miss;
	
	@Column
	private int contrary;
	
	@Column
	private int more;
	
	@Column
	private int pass;

	public String getImageName() {
		return imageName;
	}

	public void setImageName(String imageName) {
		this.imageName = imageName;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
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

	public int getPass() {
		return pass;
	}

	public void setPass(int pass) {
		this.pass = pass;
	}

	public int getPoint() {
		return point;
	}

	public void setPoint(int point) {
		this.point = point;
	}
	
	

}
