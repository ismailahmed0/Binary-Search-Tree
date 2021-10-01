/*
Lazy Binary Search Tree Driver Program

A java program that reads an input and output file from command line arguments and
runs operations based on information from the input file through the LazyBinarySearchTree class which then
sends the output of said operations to the output file.

UML Diagram
****************************************
+P3Driver
****************************************
+main(args: String[]): static void
****************************************

Ismail Ahmed
UT Dallas - CS 3345.005
 */


import java.io.File;
import java.io.PrintWriter;
import java.util.*;


public class P3Driver
{
    public static void main(String[] args)
    {
        Scanner in;
        if (args.length!=2) { // not valid cmd line arg length
            System.out.print("Error Incorrect Arguments:" + Arrays.toString(args));
            System.exit(0);

        }
        try {
            // input/output files
            File input_file = new File(args[0]);
            in = new Scanner(input_file);
            File output_file = new File(args[1]);
            PrintWriter  out;
            out = new PrintWriter(output_file);

            LazyBinarySearchTree BST = new LazyBinarySearchTree(); // instantiate BST

            String operation = "";
            boolean result;

            while (in.hasNext()) { // while there is a next line in the file
                operation = in.next(); // get line

                int length = operation.length(); // length of line

                if (operation.contains("Insert:"))
                {
                    try {
                        int key = Integer.parseInt(operation.substring(7, (length))); // convert number to be inserted to int
                        result = BST.insert(key); // insert a node into the BST
                        out.println(result ? "True" :"False");

                    }
                    catch (IllegalArgumentException e) // key not in valid range
                    {
                        out.println("Error in insert: IllegalArgumentException raised");
                    }
                    catch (Exception e) {
                        out.println("ERROR");
                    }
                }
                else if (operation.contains("Delete:"))
                {
                    try {
                        int key = Integer.parseInt(operation.substring(7, (length)));
                        result = BST.delete(key); // "delete" a node in the BST
                        out.println(result ? "True" :"False");

                    }
                    catch (IllegalArgumentException e) // key not in valid range
                    {
                        out.println("Error in insert: IllegalArgumentException raised");
                    }
                    catch (Exception e) {
                        out.println("ERROR");
                    }
                }
                else if (operation.contains("Contains:"))
                {
                    try {
                        int key = Integer.parseInt(operation.substring(9, (length)));
                        result = BST.contains(key); // check if key in BST
                        out.println(result ? "True" :"False");

                    }
                    catch (IllegalArgumentException e) // key not in valid range
                    {
                        out.println("Error in insert: IllegalArgumentException raised");
                    }
                    catch (Exception e) {
                        out.println("ERROR");
                    }
                }
                else if (operation.equals("FindMin"))
                {
                    try {
                        out.println(BST.findMin()); // find min key value in BST
                    }
                    catch (Exception e) {
                        out.println("ERROR");
                    }
                }
                else if (operation.equals("FindMax"))
                {
                    try {
                        out.println(BST.findMax()); // find max key value in BST
                    }
                    catch (Exception e) {
                        out.println("ERROR");
                    }
                }
                else if (operation.equals("Size"))
                {
                    try {
                        out.println(BST.size()); // find number of all nodes in BST
                    }
                    catch (Exception e) {
                        out.println("ERROR");
                    }
                }
                else if (operation.equals("PrintTree"))
                {
                    try {
                        out.println(BST.toString()); // print keys in BST in pre-order
                    }
                    catch (Exception e) {
                        out.println("ERROR");
                    }
                }
                else if (operation.equals("Height"))
                {
                    try {
                        out.println(BST.height()); // find height of BST
                    }
                    catch (Exception e) {
                        out.println("ERROR");
                    }
                }
                else
                {
                    out.println("Error in Line: " + operation);
                }
            }

            in.close();
            out.close();
        }
        catch(Exception e){
            System.out.println("Exception: " + e.getMessage());
        }
    }
}
