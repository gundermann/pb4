package com.example.vaadinprofilsample.guicomponents.gridbag;

public class Insets {

	private int left;
	private int top;
	private int right;
	private int bottom;

	// Kann man auch gut über Css regeln
	@SuppressWarnings(value = { "Muss bei Vaadin über Css geregelt werden. Wenn der Margin bei Componenten gesetzt werden kann, ist diese Funktionalität verfügbar." })
	public Insets(int left, int top, int right, int bottom) {
		this.left = left;
		this.top = top;
		this.right = right;
		this.bottom = bottom;
	}

	public int getLeft() {
		return left;
	}

	public void setLeft(int left) {
		this.left = left;
	}

	public int getTop() {
		return top;
	}

	public void setTop(int top) {
		this.top = top;
	}

	public int getRight() {
		return right;
	}

	public void setRight(int right) {
		this.right = right;
	}

	public int getBottom() {
		return bottom;
	}

	public void setBottom(int bottom) {
		this.bottom = bottom;
	}

}
