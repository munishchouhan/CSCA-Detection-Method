package edu.utp.manu;
import java.util.*;  
public class BloomFilterTest
{
    public static void main(String[] args)
    {
        Scanner scan = new Scanner(System.in);
        System.out.println("Bloom Filter Test\n");   
 
        System.out.println("Enter set capacity and key size");
        BloomFilter bf = new BloomFilter(scan.nextInt() , scan.nextInt());
 
        char ch;
        /*  Perform bloom filter operations  */
        do    
        {
            System.out.println("\nBloomFilter Operations\n");
            System.out.println("1. insert ");
            System.out.println("2. contains");
            System.out.println("3. check empty");
            System.out.println("4. clear");
            System.out.println("5. size");
 
            int choice = scan.nextInt();            
            switch (choice)
            {
            case 1 : 
                System.out.println("Enter integer element to insert");
                bf.add( new Integer(scan.nextInt()) );                     
                break;                          
            case 2 : 
                System.out.println("Enter integer element to search");
                System.out.println("Search result : "+ bf.contains( new Integer(scan.nextInt()) ));
                break;                                          
            case 3 : 
                System.out.println("Empty status = "+ bf.isEmpty());
                break;
            case 4 : 
                System.out.println("\nBloom set Cleared");
                bf.makeEmpty();
                break;    
            case 5 : 
                System.out.println("\nSize = "+ bf.getSize() );
                break;            
            default : 
                System.out.println("Wrong Entry \n ");
                break;   
            }    
 
            System.out.println("\nDo you want to continue (Type y or n) \n");
            ch = scan.next().charAt(0);                        
        } while (ch == 'Y'|| ch == 'y');    
    }
}