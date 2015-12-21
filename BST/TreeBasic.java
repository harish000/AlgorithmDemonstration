/*Implementation of Binary Search Tree*/

import java.io.*;
import java.util.Scanner;
 
class Nodes
{
    public int item;
    public Nodes lChild;
    public Nodes rChild;
    public void displayNode()
    {
        System.out.print("[");
        System.out.print(item);
        System.out.print("]");
    }
}
class StackNode
{
    public Nodes item;
    public StackNode next;
    public StackNode(Nodes val)
    {
        item = val;
    }
 
}
class LinkedListStack
{
    private StackNode first;
    public LinkedListStack()
    {
        first = null;
    }
    public boolean isEmpty()
    {
        return (first==null);
    }
    public void insert(Nodes key)//inserts at beginning of list
    {
        StackNode newLLNode = new StackNode(key);
        newLLNode.next = first;
        first = newLLNode;
    }
    public Nodes delete()//deletes at beginning of list
    {
        StackNode temp = first;
        first = first.next;
        return temp.item;
    }
}
class Stack
{
    private LinkedListStack listObj;
    public Stack()
    {
        listObj = new LinkedListStack();
    }
    public void push(Nodes num)
    {
        listObj.insert(num);
    }
    public Nodes pop()
    {
        return listObj.delete();
    }
    public boolean isEmpty()
    {
        return listObj.isEmpty();
    }
}
 
class Trees
{
    private Nodes root;
    public Trees()
    { root = null; }
    public Nodes find(int val)
    {
        Nodes current = root;
        while(current.item != val)
        {
            if(val < current.item)
                current = current.lChild;
            else
                current = current.rChild;
            if(current == null)
                return null;
        }
        return current;
    } 
    public void insert(int id)
    {
        Nodes newNode = new Nodes();
        newNode.item = id;
        if(root==null)
            root = newNode;
        else
        {
            Nodes current = root;
            Nodes parent;
            while(true)
            {
                parent = current;
                if(id < current.item)
                {
                    current = current.lChild;
                    if(current == null)
                    {
                        parent.lChild = newNode;
                        return;
                    }
                } 
                else
                {
                    current = current.rChild;
                    if(current == null) 
                    {
                        parent.rChild = newNode;
                        return;
                    }
                } 
            } 
        } 
    } 
    public boolean delete(int val) 
    {
        Nodes current = root;
        Nodes parent = root;
        boolean isLeftChild = true;
        while(current.item != val)
        {
            parent = current;
            if(val < current.item)
            {   
                isLeftChild = true;
                current = current.lChild;
            }
            else
            {
                isLeftChild = false;
                current = current.rChild;
            }
            if(current == null)
                return false;
 
        } 
        if(current.lChild==null && current.rChild==null)
        {
            if(current == root)
                root = null;
            else if(isLeftChild)
                parent.lChild = null;
            else
                parent.rChild = null;
        }
        else if(current.rChild==null)
        {
            if(current == root)
                root = current.lChild;
            else if(isLeftChild)
                parent.lChild = current.lChild;
            else
                parent.rChild = current.lChild;
        }
        else if(current.lChild==null)
        {
            if(current == root)
                root = current.rChild;
            else if(isLeftChild)
                parent.lChild = current.rChild;
            else
                parent.rChild = current.rChild;
        }
        else
        {
            Nodes successor = findSuccessor(current);
            if(current == root)
                root = successor;
            else if(isLeftChild)
                parent.lChild = successor;
            else
                parent.rChild = successor;
            successor.lChild = current.lChild;
        } 
        return true;
    } 
    private Nodes findSuccessor(Nodes delNode)
    {
        Nodes successorParent = delNode; 
        Nodes successor = delNode;
        Nodes current = delNode.rChild;
        while(current != null)
        {
            successorParent = successor;    
            successor = current;
            current = current.lChild;
        }
        if(successor != delNode.rChild)
        {
            successorParent.lChild = successor.rChild;
            successor.rChild = delNode.rChild;
        }
        return successor;
    }
    public void displayTree()
    {
        Stack globalStack = new Stack();
        globalStack.push(root); 
        int emptyLeaf = 32;
        boolean isRowEmpty = false;
        System.out.println("****......................................................****");
        while(isRowEmpty==false)
        {
 
            Stack localStack = new Stack();
            isRowEmpty = true;
            for(int j=0; j<emptyLeaf; j++)
                System.out.print(' ');
            while(globalStack.isEmpty()==false)
            {
                Nodes temp = globalStack.pop();
                if(temp != null)
                {
                    System.out.print(temp.item);
                    localStack.push(temp.lChild);
                    localStack.push(temp.rChild);
                    if(temp.lChild != null ||temp.rChild != null)
                        isRowEmpty = false;
                }
                else
                {
                    System.out.print("--");
                    localStack.push(null);
                    localStack.push(null);
                }
                for(int j=0; j<emptyLeaf*2-2; j++)
                    System.out.print(' ');
            }
            System.out.println();
            emptyLeaf /= 2;
            while(localStack.isEmpty()==false)
                globalStack.push( localStack.pop() );
        }
    System.out.println("****......................................................****");
    } 
} 
class TreeBasic
{
 
    public static void main(String[] args) throws IOException
    {
        int value;
        Trees theTree = new Trees();
        System.out.println("---Binary Search Tree---");
        System.out.println("1. Insert \n 2. Delete \n 3. Find \n 4. Display Tree \n 5. Exit \n");
        Scanner in = new Scanner(System.in);
        int choice = in.nextInt();
        
        while(choice != 5)
        {
        	if(choice==1)
        	{
        		System.out.println("Enter value to insert into tree");
    			int val = in.nextInt();
    			theTree.insert(val);
    			System.out.println("Displaying the Tree");
    			theTree.displayTree();
        	}
        	else if(choice==2)
        	{
        		System.out.println("Enter value to delete from tree");
    			int val = in.nextInt();
        		boolean ifDelete1 = theTree.delete(val);
                if(ifDelete1)
                    System.out.println("Deleted "+ val);
                else
                    System.out.println("Could not delete ");
                theTree.displayTree();        		
        	}
        	else if(choice==3)
        	{
        		System.out.println("Enter value to find in tree");
    			int val = in.nextInt();
        		Nodes found = theTree.find(val);
                if(found != null)
                {
                    System.out.print("Found: ");
                    found.displayNode();
                    System.out.println("\n");
                }
                else
                    System.out.println("Could not find the Nodes");
         
        	}
        	else if(choice==4)
        	{
        		theTree.displayTree();
        	}
        	
        	System.out.println("1. Insert \n 2. Delete \n 3. Find \n 4. Display Tree \n 5. Exit \n");
            choice = in.nextInt();
        }          
    } 
} 
