/**
 * Graph.java
 /**
 * BST.java
 * @author Khang Nguyen
     * @author Surafel Abebe
     * @author Hazel
     * @author Rong Jin
     * @author Kang Su
     * @author Berenice Castro
     * CIS 22C Final Project
 */


import java.util.ArrayList;
import java.util.*;

public class Graph {
    private int vertices;
    private int edges; 
    private ArrayList<LinkedList<Integer> > adj;
    private ArrayList<Character> color;
    private ArrayList<Integer> distance;
    private ArrayList<Integer> parent;
    private ArrayList<Integer> discoverTime;
    private ArrayList<Integer> finishTime;
    private static int time = 0;
    private int size;
    
    
    /**Constructors and Destructors*/

  /**
   * initializes an empty graph, with n vertices
   * and 0 edges
   * @param n the number of vertices in the graph
   */
    public Graph(int n) {
    	this.vertices = n;
    	this.edges =0;
    	color = new ArrayList<Character>();
    	distance = new ArrayList<Integer>();
    	parent = new ArrayList<Integer>();
    	discoverTime = new ArrayList<Integer>();
    	finishTime = new ArrayList<Integer>();
    	adj = new ArrayList<LinkedList<Integer>>();
    	
    	for( int i =0; i <= n; i++) {
    		
    		this.color.add(i, 'W');
    		this.distance.add(i, -1);
    		this.parent.add(i, 0);
    		this.discoverTime.add(i, -1);
    		this.finishTime.add(i, -1);
    		adj.add(i, new LinkedList<Integer>()) ;
    		
    	}
    }
    
    public void increaseSize() {
    	vertices+=5;
    	for(int i =0; i< 5;i++) {
	    	color.add('W');
	    	distance.add(-1);
	    	parent.add(0);
	    	discoverTime.add(-1);
	    	finishTime.add(-1);
	    	adj.add(i,new LinkedList<Integer>());
    	}
    }
   
    
    /*** Accessors ***/
    
  /**
   * Returns the number of edges in the graph
   * @return the number of edges
   */
    public int getNumEdges() {
       return edges;//O(1)
    }
    
    /**
     * Returns the number of vertices in the graph
     * @return the number of vertices
     */
    public int getNumVertices() {
       return vertices;
    }
    
    /**
     * returns whether the graph is empty (no edges)
     * @return whether the graph is empty
     */
    public boolean isEmpty() {
       return edges==0;
    }

    /**
     * Returns the value of the distance[v]
     * @param v a vertex in the graph
     * @precondition 0 < v <= vertices
     * @return the distance of vertex v
     * @throws IndexOutOfBoundsException when 
     * v is out of bounds
     */
    public Integer getDistance(Integer v) throws IndexOutOfBoundsException{
    	
    	if( v <=0 || v >vertices) {
    		throw new IndexOutOfBoundsException("getDistance(): distance is out of bound");
    	}
       return distance.get(v);

    }
    
    /**
     * Returns the value of the parent[v]
     * @param v a vertex in the graph
     * @precondition 0 < v <= vertices
     * @return the parent of vertex v
     * @throws IndexOutOfBoundsException when 
     * v is out of bounds
     */
    public Integer getParent(Integer v) throws IndexOutOfBoundsException {
    	if( v <=0 ||  v >vertices) {
    		throw new IndexOutOfBoundsException("getParent(): v is out of bounds");
    	}
       return   parent.get(v);
    }
    
    /**
     * Returns the value of the color[v]
     * @param v a vertex in the graph
     * @precondition 0 < v <= vertices
     * @return the color of vertex v
     * @throws IndexOutOfBoundsException when 
     * v is out of bounds
     */
    public Character getColor(Integer v) throws IndexOutOfBoundsException {
         
    	if(v <=0 || v >vertices) {
    		throw new IndexOutOfBoundsException("getColor(): v is out of bounds");
    	}
    	
    	return color.get(v);
    }
    
    /**
     * Returns the value of the discoverTime[v]
     * @param v a vertex in the graph
     * @precondition 0 < v <= vertices
     * @return the discover time of vertex v
     * @throws IndexOutOfBoundsException when 
     * v is out of bounds
     */
    public Integer getDiscoverTime(Integer v) throws IndexOutOfBoundsException {
    	if(v <=0 || v> vertices ) {
    		throw new IndexOutOfBoundsException("getDiscoveryTime(): v is out of bounds");
    	}
       return discoverTime.get(v);//O(1) for all best,avg
    }
    
    /**
     * Returns the value of the finishTime[v]
     * @param v a vertex in the graph
     * @precondition 0 < v <= vertices
     * @return the finish time of vertex v
     * @throws IndexOutOfBoundsException when 
     * v is out of bounds
     */
    public Integer getFinishTime(Integer v) throws IndexOutOfBoundsException {
    	if( v <=0 || v> vertices ) {
    		throw new IndexOutOfBoundsException("getFinishTime(): v is out of bounds");
    	}    
    	return finishTime.get(v);//
    	
    }
    
    /**
     * Returns the LinkedList stored at index v
     * @param v a vertex in the graph
     * @return the adjacency LinkedList a v
     * @precondition 0 < v <= vertices
     * @throws IndexOutOfBoundsException when 
     * v is out of bounds
     */
    public LinkedList<Integer> getAdjacencyList(Integer v) throws IndexOutOfBoundsException {
    	if( v <=0 ||  v >vertices) {
    		throw new IndexOutOfBoundsException("getAdjacencyList(): v is out of bound");
    	}
       return adj.get(v);//AVG O(1) 23344
    }
    public String getStringList(Integer v){
    	if( v <=0 ||  v >vertices) {
    		throw new IndexOutOfBoundsException("getAdjacencyList(): v is out of bound");
    	}
    	StringBuilder output = new StringBuilder();
    	
    	LinkedList<Integer> adjacent = getAdjacencyList(v);
    	
    	adjacent.positionIterator();
    	while(!adjacent.offEnd()) {// graph 1: 2354 adj(1) => 234
    		
    		output.append(adjacent.getIterator() + "\n");
    		
            adjacent.advanceIterator();
    	}
    	
    		
       return output.toString();
    	
    }
    
    /*** Manipulation Procedures ***/
    
    /**
     * Inserts vertex v into the adjacency list of vertex u
     * (i.e. inserts v into the list at index u)
     * @precondition, 0 < u, v <= vertices
     * @throws IndexOutOfBounds exception when 
     * u or v is out of bounds
     */
    public void addDirectedEdge(Integer u, Integer v) throws IndexOutOfBoundsException {
    	if( u <= 0 ||  v > vertices|| v <= 0 ||  u > vertices ) {
    		throw new IndexOutOfBoundsException("addDirectedEdge(): u or v are out of bounds");
    	}
    	adj.get(u).addLast(v);
    	edges++;//AVG(o(1)
    }
    
    /**
     * Inserts vertex v into the adjacency list of vertex u
     * (i.e. inserts v into the list at index u)
     * and inserts u into the adjacent vertex list of v
     * @precondition, 0 < u, v <= vertices
     * @throws IndexOutOfBoundsException when
     * u or v is out of bounds
     */
    public void addUndirectedEdge(Integer u, Integer v) throws IndexOutOfBoundsException {
    	if( u <= 0 || v > vertices ||  v <= 0 || u > vertices  ) {
    		throw new IndexOutOfBoundsException("addUndirectedEdge(): u or v are out of bounds");
    	}
    	
    	adj.get(u).addLast(v);
    	adj.get(v).addLast(u); //AVG(o(1)// for all
    	edges++;

    }
    
    /*** Additional Operations ***/
    
    /**
     * Creates a String representation of the Graph
     * Prints the adjacency list of each vertex in the graph,
     * vertex: <space separated list of adjacent vertices>
     */
    @Override public String toString() {
    	StringBuilder result = new StringBuilder();
    	
    	for(int i =1;i <=vertices;i++) {
    		result.append(i+ ": "+ adj.get(i).toString());
    	}
    	
       return result.toString();//O(N+M) FRO ALL
    }
    
    
    /**
     * Performs breath first search on this Graph give a source vertex
     * @param source the starting vertex
     * @precondition source is a vertex in the graph
     * @throws IndexOutOfBoundsException when the source vertex
     * is out of bounds of the graph
     */

    public void BFS(Integer source) throws IndexOutOfBoundsException {
    	if( source <1 || source >vertices) {
    		throw new IndexOutOfBoundsException("BFS(): the source vortex is out of bounds");
    	}
        
    	for(int i =1; i <=vertices;i++) {
    		this.color.set(i,'W');
    		this.distance.set(i, -1);
    		this.parent.set(i, 0);
    	}
    	int src = (int) source;
    	this.color.set(src,'G');
    	this.distance.set(src,0);
        
    
		this.adj.get(0).addLast(source);             
		   
		                                        
   
    	while(!adj.get(0).isEmpty()) {
    		Integer x = adj.get(0).getFirst();
    		this.adj.get(0).removeFirst(); //EMPTIES ARRAYLIST AT INDEX 0 BACK TO ITS ORIGINAL
    		
    		LinkedList<Integer> ad = getAdjacencyList(x); 
    		
    		
    		ad.positionIterator();
    		while(!ad.offEnd()) {
    			 
    			
    			int listin = (int) ad.getIterator();
    			if(color.get(listin) == 'W') {
    				color.set(listin, 'G');
    				distance.set(listin, distance.get(x)+1);
    				parent.set(listin, x);
    				adj.get(0).addLast(ad.getIterator());
    			}
    			
    			ad.advanceIterator();
    			
    		}
    		
    		color.set(x, 'B');
    	
    		
    	}
    	
    }
    
  //Call after calling the BFS which will set the distance for adjacency vertices
    public LinkedList<Integer> recommendFriends(User userId) {
    	LinkedList<Integer> recomend = new LinkedList<Integer>();
    	//int rank = 0;
    	//recommend this user object friends, it cant be the friends of a user
    	Integer id = (Integer) userId.getId();
    	if(this.getAdjacencyList(id).getLength()==0){//Handle it in main once we insert stuff into the graph 
    		//recommend by interest 
    	}
    
    	for(int i =1; i <=vertices;i++) {//1:   2 4 6 // the distance of MJ friends is 1, avoid suggestions
    	                                // 2:  9 6 7
    		if(this.getDistance(i) > 1) {
    			recomend.addLast(i);
    		}
    		
    	}
    	
    	   
    	return recomend;
    }
    
    public void removeFriend(Integer currentID, Integer friendID) {
    	adj.get(currentID).positionIterator();
    	
    	//System.out.println(adj.get(currentID).getFirst().toString());
    	while( !adj.get(currentID).offEnd() ) {
    		
    		if(adj.get(currentID).getIterator().equals(friendID)) {
    			adj.get(currentID).removeIterator();
    			break;
    		}
    		adj.get(currentID).advanceIterator();
    	}
    	
    	
    }
   
    
   
}