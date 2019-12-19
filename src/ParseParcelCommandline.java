import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class ParseParcelCommandline {



    public static void main(String[] args) {


        ParseParcel parseParcel = new ParseParcel();
        parseParcel.print();

        Scanner input = new Scanner(System.in);

        for(;;)
        {

            System.out.print("\n\nInput package weight(Kg):");
            double weight = input.nextDouble();


            System.out.print("Input package length(mm):");
            int length = input.nextInt();


            System.out.print("Input package breadth(mm):");
            int breadth = input.nextInt();


            System.out.print("Input package height(mm):");
            int height = input.nextInt();


            PackageType type = parseParcel.getType(weight, length, breadth, height);


            System.out.println(type.print() );

            System.out.print("Do you want to exit(y/n)");
            String yes = input.next();
            if(yes.indexOf("y")>=0)
                break;

        }

    }
}
