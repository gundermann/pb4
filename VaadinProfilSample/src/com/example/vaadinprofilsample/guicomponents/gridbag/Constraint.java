package com.example.vaadinprofilsample.guicomponents.gridbag;

public class Constraint {

	public static final int HORIZONTAL = 0;
	public static final int VERTICAL = 1;
	private int fill = 0;
	private Insets insets = new Insets(0, 0, 0, 0);
	private int gridx = 0;
	private int gridy = 0;
	private int gridHeigth = 0;
	private int gridWidth = 0;

	public int getFill() {
		return fill;
	}

	public void setFill(int fill) {
		this.fill = fill;
	}

	public Insets getInsets() {
		return insets;
	}

	public void setInsets(Insets insets) {
		this.insets = insets;
	}

	public int getGridx() {
		return gridx;
	}

	public void setGridx(int gridx) {
		this.gridx = gridx;
	}

	public int getGridy() {
		return gridy;
	}

	public void setGridy(int gridy) {
		this.gridy = gridy;
	}

	public int getGridHeigth() {
		return gridHeigth;
	}

	public void setGridHeigth(int gridHeigth) {
		this.gridHeigth = gridHeigth;
	}

	public int getGridWidth() {
		return gridWidth;
	}

	public void setGridWidth(int gridWidth) {
		this.gridWidth = gridWidth;
	}

}
