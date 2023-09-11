import java.io.BufferedReader;
import java.io.FileReader;

public class UABinarySearchTree {
	
	Node root;
	int size = 0;
	
	public void load(UABinarySearchTree t, String file) { //This method will run in O(n) runtime
		String line;
		
		try {
			
			BufferedReader br = new BufferedReader(new FileReader(file));
			
			while ( (line = br.readLine()) != null) {
				
				String[] tokens = line.split( "," );
				
				insert(t, new Node((Integer.parseInt(tokens[2])), tokens[0], tokens[1]));
				
			}
			
			br.close();
			
		}
		catch (Exception e) {
			System.out.println("Error: " + e);
		}
	}
	
	public void insert(UABinarySearchTree tree, Node stu) { //This method will run in O(lgn) runtime.
		Node temp = null;
		Node root = tree.root;
		
		while (root != null) {
			temp = root;
			if(stu.key < root.key) {
				root = root.left;
				size++;
			} else {
				root = root.right;
				size++;
			}
			
			stu.parent = temp;
		}
		
		if (temp == null) {
			tree.root = stu;
		} else if (stu.key < temp.key) {
			temp.left = stu;
		} else {
			temp.right = stu;
		}
	}
	
	public Node find(Node stu, int key) { //This method will run in O(lgn) runtime.
		if(stu == null || key == stu.key) {
			System.out.print("No matching values.");
			return stu;
		}
		
		if(key < stu.key) {
			return find(stu.left, key);
		} else {
			return find(stu.right, key);
		}
	}
	
	public Node min(Node stu) { //This method will run in O(lgn) runtime.
		while (stu.left != null) {
			stu = stu.left;
		}
		System.out.print("Minimum: " + stu.toString());
		return stu;
	}
	
	public Node max(Node stu) {
		while(stu.right != null) {
			stu = stu.right;
		}
		
		System.out.print("Maximum: " + stu.toString());
		return stu;
	}
	
	public Node successor(Node stu) { //This method will run in O(lgn) runtime.
		if(stu.right != null) {
			return min(stu.right);
		}
		
		Node temp = stu.parent;
		
		while(temp != null && stu == temp.right) {
			stu = temp;
			temp = temp.parent;
		}
		
		System.out.print("Successor: " + stu.toString());
		return temp;
 	}
	
	public Node predecessor(Node stu) { //This method will run in O(lgn) runtime.
		if(stu.left != null) {
			return max(stu.left);
		}
		
		Node temp = stu.parent;
		
		while(temp != null && stu == temp.left) {
			stu = temp;
			temp = temp.parent;
		}
		
		System.out.print("Predecessor: " + stu.toString());
		return temp;
 	}
	
	public void transplant(UABinarySearchTree tree, Node x, Node y) { //This method will run in O(lgn) runtime.
		if(x.parent == null) {
			tree.root = y;
		} else if (x == x.parent.left) {
			x.parent.left = y;
		} else {
			x.parent.right = y;
		}
		
		if(y != null) {
			y.parent = x.parent;
		}
	}
	
	public void delete(UABinarySearchTree tree, Node stu) { //This method will run in O(lgn) runtime.
		
		Node temp = new Node();
		
		if(stu.left == null) {
			transplant(tree, stu, stu.right);
		} else if(stu.right == null) {
			transplant(tree, stu, stu.left);
		} else {
			temp = min(stu.right);
			
			if(temp.parent != stu) {
				transplant(tree, temp, temp.right);
				temp.right = stu.right;
				temp.right.parent = temp;
			}
			
			transplant(tree, stu, temp);
			temp.left = stu.left;
			temp.left.parent = temp;
		}
	}
	
 	public int size() { //This method will run in O(1) runtime.
		return size;
	}
	
	public void print(Node stu) { //This method will run in O(lgn) runtime.
		if (stu != null) {
			print(stu.right);
			System.out.println(stu.toString());
		}
	}
	
	public static void main (String args[]) {
		String file = "records.txt";
		
		UABinarySearchTree bs = new UABinarySearchTree();
		
		bs.load(bs, file);
		bs.print(bs.root);
		//bs.min(bs.root);
		//bs.max(bs.root);
		//bs.successor(bs.root);
		//bs.predecessor(bs.root);
	}
	
	public class UABinarySearchTree2 extends UABinarySearchTree { 
		
		public void print(Node stu) { //This method will run in O(lgn) runtime.
			if (stu != null) {
				print(stu.left);
				System.out.println(stu.toString());
			}
		}
	}
	
	public class Node implements UANode {
		Node left;
		Node right;
		Node parent;
		String email;
		String name;
		int key;
		
		public Node() {
			
		}
		
		public Node(int key, String name, String email) {
			this.key = key;
			this.name = name;
			this.email = email;
			left = null;
			right = null;
			parent = null;
		}
		
		public Node getLeft() {
			return left;
		}

		public void setLeft(Node left) {
			this.left = left;
		}

		public Node getRight() {
			return right;
		}

		public void setRight(Node right) {
			this.right = right;
		}

		public Node getParent() {
			return parent;
		}

		public void setParent(Node parent) {
			this.parent = parent;
		}

		public int getKey() {
			return key;
		}

		public void setKey(int key) {
			this.key = key;
		}
		
		public String getEmail() {
			return email;
		}

		public void setEmail(String email) {
			this.email = email;
		}

		public String getName() {
			return name;
		}

		public void setName(String name) {
			this.name = name;
		}
		
		public String toString() {			
			return String.format("Student: %5d - %s %n", this.key, this.name + " - " + this.email);
		}
	}
}
