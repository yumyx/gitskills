import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;



public class ParseParcel {



    public   ParseParcel()
    {
        makeBinTreeByMannual();

    }

    public void makeBinTreeByMannual()
    {
        /*
        Package Type	Length	Breadth	Height	Cost
        Small	        200mm	300mm	150mm	$5.00
        Medium	        300mm	400mm	200mm	$7.50
        Large	        400mm	600mm	250mm	$8.50
        */


        PackageType mSmall=new PackageType(25,200,300,150,5.0,"Small") ;
        PackageType mMedium=new PackageType(25,300,400,200,7.5,"Medium") ;
        PackageType mLarge=new PackageType(25,400,600,250,8.5,"Large") ;

        mTypesSort = new ArrayList<PackageType>(Arrays.asList(mSmall, mMedium, mLarge)); //sort by manumal
        root=mMedium;
        mMedium.mSmaller=mSmall;
        mMedium.mBiger=mLarge;

    }

    /*



    static public PackageType getSmall()
    {
        return mSmall;
    }

    static public PackageType getMedium()
    {
        return mMedium;
    }


    static public PackageType getLarge()
    {
        return mLarge;
    }




*/



    private List<PackageType>  mTypesSort;
    public   ParseParcel(List<PackageType> types )
    {
        makeBinTree( types );

    }


    public void print()
    {
        System.out.println("===================================================================");
        for(int i=0;i<mTypesSort.size();++i)
        {
            System.out.println(mTypesSort.get(i));
        }
        System.out.println("===================================================================\n\n");

    }

    public void makeBinTree(List<PackageType> typesSort )
    {

        Collections.sort(typesSort);

        mTypesSort=typesSort;

       int end= typesSort.size()-1;
       int start=0;


        PackageType  node=typesSort.get((end+start)/2);

        node.makeTree(typesSort,start,end);
        root=node;

    }


  private  PackageType root;




    public PackageType getType(double weight, int length, int breadth, int height )
    {


       return root.getType(weight,length, breadth, height);

    }


}
