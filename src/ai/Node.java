package ai; //the folder

//essentially, this class stores coordinates for PathFinder
public class Node {
	Node parent;    //the previous node
	public int col; //column = x
	public int row; //row    = y
	int gCost;      //'cost' variables are used to calculate path 
	int hCost;      //efficiancy
	int fCost;
	boolean solid;  //is it sold?
	boolean open;
	boolean checked;//has it been checked?
	
	public Node(int col, int row) {
		this.col = col;
		this.row = row;
		
	}
}
