import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ParseParcelTest {


    public static void main(String[] args) {
        ParseParcelTest test=new ParseParcelTest();

        test.doTestNormal();

        test.doTestHuge();






    }
      /*
 Package Type	Length	Breadth	Height	Cost
        Small	200mm	300mm	150mm	$5.00
        Medium	300mm	400mm	200mm	$7.50
        Large	400mm	600mm	250mm	$8.50

*/

      private ParseParcel parseParcel;

    public void doTestNormal()
    {
        //ParseParcel parseParcel= new ParseParcel();

        PackageType mSmall=new PackageType(25,200,300,150,5.0,"Small") ;
        PackageType mMedium=new PackageType(25,300,400,200,7.5,"Medium") ;
        PackageType mLarge=new PackageType(25,400,600,250,8.5,"Large") ;
        List<PackageType> typesSort = new ArrayList<PackageType>(Arrays.asList(mSmall, mMedium, mLarge));
        parseParcel= new ParseParcel(typesSort);
        parseParcel.print();



        String smallType=mSmall.getType();
        System.out.println("==========================="+smallType+"==========================");
       testGetType(smallType,25,200,300,150);
       testGetType(smallType,25,100,100,100);



       String mediumType=mMedium.getType();
        System.out.println("==========================="+mediumType+"==========================");
        testGetType(mediumType,25,200,301,150);
        testGetType(mediumType,25,299,399,199);
       testGetType(mediumType,25,300,400,200);


       String largeType=mLarge.getType();
       System.out.println("==========================="+largeType+"==========================");
       testGetType(largeType,25,300,400,201);
       testGetType(largeType,25,400,600,250);

       String overSize=new OverSizeWeight().getType();
        System.out.println("==========================="+overSize+"==========================");
       testGetType(overSize,25,401,600,250);
       testGetType(overSize,25,400,601,250);
       testGetType(overSize,25,400,600,251);
       testGetType(overSize,25,401,601,251);



       String overWeight=new OverSizeWeight().getType();
        System.out.println("==========================="+overWeight+"==========================");
       testGetType(overWeight,25.1,400,600,250);
        testGetType(overWeight,25.1,400,601,251);


       String errorInput= new ErrorInput().getType();
        System.out.println("==========================="+errorInput+"==========================");
        testGetType(errorInput,-1,400,600,250);

    }

    public void doTestHuge()
    {

        PackageType mLarge=new PackageType(25,400,600,250,8.5,"Large") ;
        PackageType mHuge=new PackageType(25,500,700,350,9.5,"Huge") ;

        PackageType tinny=new PackageType(25,100,200,100,4.0,"Tinny") ;
        PackageType mSmall=new PackageType(25,200,300,150,5.0,"Small") ;
        PackageType mMedium=new PackageType(25,300,400,200,7.5,"Medium") ;


        List<PackageType> typesSort = new ArrayList<PackageType>(Arrays.asList(tinny,mSmall, mMedium, mLarge,mHuge));
        parseParcel= new ParseParcel(typesSort);

        parseParcel.print();



        String tinnyType=tinny.getType();
        System.out.println("==========================="+tinnyType+"==========================");
        testGetType(tinnyType,25,100,200,100);


        String hugeType=mHuge.getType();
        System.out.println("==========================="+hugeType+"==========================");
        testGetType(hugeType,25,500,700,350);

        String overSize=new OverSizeWeight().getType();
        System.out.println("==========================="+overSize+"==========================");
        testGetType(overSize,25,501,600,250);

    }


    public void testGetType( String expectedType,double weight, int length, int breadth, int height )
    {
        System.out.println("Testing PackageType.GetType()"+" Weight:"+weight+"kg Length:"+length+"mm Breadth:"+breadth+"mm Height:"+height+"mm");

        PackageType type= parseParcel.getType( weight,  length,  breadth,  height );


        System.out.println(type.print());
       // String expectedType=ParseParcel.getSmall().getType();
        String returnType=type.getType();


        String result="fail";
        if(expectedType.equals(returnType))
            result="ok";
        System.out.println("Expected Type:["+expectedType+"]  Return Type :["+returnType+"]  Result:["+result+"]\n");
    }




}
