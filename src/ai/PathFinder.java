package ai;

//imported classes
import java.util.ArrayList; 
import entity.Entity;      //our classes
import main.GamePanel;


public class PathFinder {
	//the instance variables
	GamePanel gp;
	Node[][] node;                                       //a 2D array of nodes. It stores all the coordinates
	ArrayList<Node> openList = new ArrayList<>();
	public ArrayList<Node> pathList = new ArrayList<>();
	Node startNode, goalNode, currentNode;               //node values used to calculate the path
	boolean goalReached = false;                         //tells the entity when it has reached its goal
	int step = 0;
	
	public PathFinder(GamePanel gp) {
		this.gp= gp;
		instantiateNodes();
	}
	
	//creating the coordinate grid
	public void instantiateNodes() { 
		node = new Node[gp.maxWorldCol][gp.maxWorldRow]; //initialize the array
		int col = 0;                                     //and initialize x and y
		int row = 0;
		
		while(col < gp.maxWorldCol && row < gp.maxWorldRow) { //while our x and y are less than the maximum...
			node[col][row] = new Node(col,row);               //make a new node...
			col++;                                            //and move one x over
			
			if(col == gp.maxWorldCol) {                       //unless x equals the maximum x...
				col = 0;                                      //then set x to zero again...
				row++;                                        //and move one y down
			}
		}
	}
	
	public void resetNodes() {
		int col = 0;
		int row = 0;
		
		while(col < gp.maxWorldCol && row < gp.maxWorldRow) {
			//reset open, checked and solid state
			node[col][row].open = false;
			node[col][row].checked = false;
			node[col][row].solid = false;
			col++;
			if(col == gp.maxWorldCol) {
				col = 0; 
				row++;
			}
		}
		// reset other settings 
		openList.clear();
		pathList.clear();
		goalReached = false;
		step = 0;		
	}
	public void setNodes(int startCol, int startRow, int goalCol, int goalRow, Entity entity) {
		resetNodes();
		//set Start and Goal node
		startNode = node[startCol][startRow];
		currentNode = startNode;
		goalNode = node[goalCol][goalRow];
		openList.add(currentNode);
		
		int col = 0;
		int row = 0;
		
		while(col < gp.maxWorldCol && row < gp.maxWorldRow) {
			//set solid nodes 
			// check tiles
			
			int tileNum = gp.tileM.mapTileNum[gp.currentMap][col][row];
			if (gp.tileM.tile[tileNum] != null) {
			if(gp.tileM.tile[tileNum].collision) {
				node[col][row].solid = true;
			}
			}
			//check interactive Tiles
			for(int i = 0; i < gp.iTile[1].length; i++) {
				if( gp.iTile[gp.currentMap][i] != null && gp.iTile[gp.currentMap][i].destructible) {
					int itCol = gp.iTile[gp.currentMap][i].worldX/gp.tileSize;
					int itRow = gp.iTile[gp.currentMap][i].worldY/gp.tileSize;
					node[itCol][itRow].solid = true;
				}
			}
			
			// set cost
			
			getCost(node[col][row]);
			
			col++;
			
			if(col == gp.maxWorldCol) {
				col = 0; 
				row++;
			}
		}
		
	}
	
	public void getCost(Node node) {
		// get gCost distance from start to node.
				int xDistance = Math.abs(node.col - startNode.col);
				int yDistance = Math.abs(node.row - startNode.row);
				node.gCost = xDistance + yDistance;
				
				// hCost distance from goal to node 
				xDistance = Math.abs(node.col - goalNode.col);
				yDistance = Math.abs(node.row - goalNode.row);
				node.hCost = xDistance + yDistance;
				
				// fCost sum of gCost and hCost
				
				node.fCost = node.gCost + node.hCost;
				
//				// Display the cost on Node
//				
//				if(node != startNode  && node != goalNode) {
//					node.setText("<html>F:" + node.fCost + "<br>G:" +  node.gCost + "</html>");
//				}
	}
	
	public boolean search() {
		//System.out.println("1: " + step + " " +  goalReached);
		
		while(goalReached == false && step < 1500) {
			
			int col = currentNode.col;
			int row = currentNode.row;
			
			
			//checked current node
			currentNode.checked = true;
			openList.remove(currentNode);
			
			//Open the up Node
			if(row - 1 >= 0) {
			openNode(node[col][row - 1]);
			//System.out.println("You are here line 137");
			}
			// Open the left Node
			if(col - 1 >= 0) {
			openNode(node[col - 1][row]);
			}
			
			//Open the down Node
			if(row + 1 < gp.maxWorldRow) {
			openNode(node[col][row + 1]);
			}
			
			// Open the right Node
			if(col + 1 < gp.maxWorldCol) {
			openNode(node[col + 1][row]);
			//System.out.println("You are here line 152");
			}
		
			// Here is where the 14:54 ended continue 
			
			// for here later
			
		 /*
		  * 
		  * 
		  * 
		  * 
		  * 
		  */
			// Find Best to to travel to
			int bestNodeIndex = 0;
			int bestNodefCost = 999;
			
			for( int i = 0; i < openList.size();i++) {
				//Check if this node's F. cost is better
				if(openList.get(i).fCost < bestNodefCost) {
					bestNodeIndex = i;
					bestNodefCost = openList.get(i).fCost; 
				}
				// if fCost is equal to bestNodefCost use gCost
				else if(openList.get(i).fCost == bestNodefCost) {
					if(openList.get(i).gCost < openList.get(bestNodeIndex).gCost) {
						bestNodeIndex = i;
						
					}
				}
			}
			
			// If no node in the openList we end loop
			if(openList.size() == 0 ) {
				break;
			}
			//after the loop we get the best node for the next step
			currentNode = openList.get(bestNodeIndex);
			
			if(currentNode == goalNode) {
				goalReached = true;
				trackThePath();
				
			}
			step++;
		}
		return goalReached;
				
	}
	//***************************  Start Here at:14:54
	
	private void openNode(Node node) {
	
		if(node.open == false && node.checked == false  && node.solid == false) {
			node.open = true;
			node.parent = currentNode;
			openList.add(node);
		}
	}
	
	private void trackThePath() {
		// BackTrack and draw the best path
		Node current = goalNode;
		
		while(current != startNode) {
			
			pathList.add(0,current);
			current = current.parent;
			//System.out.println(pathList.get(0).col + "  " + pathList.get(0).row );
			//time 19:38
			
		}
		
	}
	
}
	

