package main.java.edu.calpoly.cpe305.wenmin.ParkingApplication;

import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

public class MainTest {
  public static int[][] adj;
  public static int rows;
  public static int cols;
  public static int numVertices;
  public static boolean[] visited;
  public static int[] vertices;
  public static ArrayList<Geoloc> parkingLocs;

  /**
   * add the connection between two vertices.
   * @param from refer to the starting vertex
   * @param end refers to the ending vertex
   */
  public static void addEdge(int from, int end) {
    adj[from][end] = 1; 
    adj[end][from] = 1;
  }

  public static Geoloc locToPoint(int num, int col) {
    return new Geoloc(num % col, num / col);
  }

	public static void setAdj(int[] ver, int cols) {
		for (int i = 0; i < numVertices; i++) {
			if (ver[i] == 0) {
				if (i - cols > -1) {
					if (ver[i - cols] == 0) {
						addEdge(i, i - cols);
					}
				}

				if ((i + 1) % cols != 0) {
					if (ver[i + 1] == 0) {
						addEdge(i, i + 1);
					}
				}
			}
		}
	}

	/**
	 * Read in the file and set up the 2d array.
	 * 
	 * @param fileName
	 *            refer to the text file that contains parking info
	 * @throws FileNotFoundException
	 *             throw if the file is not found lol
	 */
	public static void readFile(String fileName) throws FileNotFoundException {
		File file = new File(fileName);
		System.out.println(fileName);
		Scanner scan = new Scanner(file);
		Scanner lineScan;
		String newLine;
		rows = scan.nextInt();
		System.out.println(rows + " ---- ");
		cols = scan.nextInt();
		numVertices = rows * cols;
		adj = new int[numVertices][numVertices];
		visited = new boolean[numVertices];
		vertices = new int[numVertices];
		System.out.println(rows + " ---- ");
		for (int i = 0; i < numVertices; i++) {
			vertices[i] = scan.nextInt();
			System.out.println(vertices[i] + " ---");
		}

		setAdj(vertices, cols);
	}

	public static void printAdj() {
		for (int i = 0; i < numVertices; i++) {
			for (int j = 0; j < numVertices; j++) {
				System.out.print(adj[i][j] + " ");
			}
			System.out.println();
		}
	}

	public static void main(String[] args) throws FileNotFoundException {
		readFile("test.txt");
		printAdj();
	}
}
