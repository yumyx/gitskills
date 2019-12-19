import java.util.List;

public class PackageType implements Comparable<PackageType>
{
    final protected  double maxWeight;

    final protected  int maxLength;
    final protected  int maxBreadth;
    final protected  int maxHeight;
    final private  double mCost;
    final private  String  mType;

    public PackageType mSmaller=null;
    public PackageType mBiger=null;

    public void makeTree(List<PackageType> typesSort, int start, int end)
    {

        if(start>=end)
            return;



        int cur =(end+start)/2;
        int endLeft= cur-1;
        int startLeft=start;
        int left=(endLeft+startLeft)/2;
        if(left<cur && left>=start) {
            mSmaller = typesSort.get(left);
            mSmaller.makeTree(typesSort, startLeft, endLeft);
        }



        int endRight= end;
        int startRight=cur+1;
        int right=(endRight+startRight)/2;
        if(right>cur && right<=end) {
            mBiger = typesSort.get(right);
            mBiger.makeTree(typesSort, startRight, endRight);
        }





    }

    public PackageType(double weight,int length,int breadth,int height ,double cost,String type)
    {
        maxWeight=weight;
        maxLength=length;
        maxBreadth=breadth;
        maxHeight =height;
        mCost=cost;
        mType=type;


    }
    double getCost()
    {
        return mCost;
    }

    String getType()
    {
        return mType;
    }
    public String print()
    {
        return "This package "+"Type:"+mType+" Cost:"+mCost+"$";
    }
/*
    Package Type	Length	Breadth	Height	Cost
    Small	200mm	300mm	150mm	$5.00
    Medium	300mm	400mm	200mm	$7.50
    Large	400mm	600mm	250mm	$8.50

        */


    @Override
    public String toString()
    {
        return  "Type:"+mType+"\tWeight:"+maxWeight+"\tLength:"+maxLength+"\tBreadth:"+maxBreadth+"\tHeight:"+maxHeight+"\tCost:"+mCost;
    }
    public PackageType getType(double weight ,int length, int breadth, int height )
    {
        if(weight<0 || length<0  || breadth<0 || height<0)
            return new ErrorInput();

        PackageType type=null;

        if(maxLength>=length && maxBreadth>=breadth && maxHeight >=height && weight<=maxWeight)
        {
            type=this;
            if(mSmaller!=null) {
                PackageType small= mSmaller.getType(weight ,length, breadth, height);
                if(small!=null && small.mCost>=0)
                    type=small;
            }

        }
        else
        {

            if(mBiger!=null) {
                type = mBiger.getType(weight,length, breadth, height);
            }
            else //bigest
            {

                    type=new OverSizeWeight(this,weight,length, breadth, height);

            }

        }

        return type;
    }

    //compare by the cost
    @Override
    public int compareTo(PackageType type) {
        if(mCost==type.mCost)
            return 0;
        else if(mCost>type.mCost)
            return 1;

        return -1;

    }
}






class OverSizeWeight extends PackageType
{
    private  PackageType maxSize;
    public OverSizeWeight(PackageType type,double weight, int length, int breadth, int height )
    {
        super(weight, length, breadth, height,-1,"OverSizeWeight");

        maxSize=type;

    }

    public OverSizeWeight( )
    {
        super(-1, -1, -1, -1,-1,"OverSizeWeight");



    }
    public String print()
    {
        String ret="";
        if(maxSize==null)
            return ret;

        if(maxWeight>maxSize.maxWeight)
            ret="This pakage is overweight!! Weight:"+maxWeight+"Kg(Max:"+maxSize.maxWeight+")";

        int oversize=0;

        String lengthWarring="";
        if(maxLength>maxSize.maxLength) {
            ++oversize;
            lengthWarring = " Length:" + maxLength + "(Max " + maxSize.maxLength + ")";
        }

        String breadthWarring="";
        if(maxBreadth>maxSize.maxBreadth) {
            ++oversize;
            breadthWarring = " Breadth:" + maxBreadth + "(Max " + maxSize.maxBreadth + ")";
        }

        String heighthWarring="";
        if(maxHeight>maxSize.maxHeight) {
            ++oversize;
            heighthWarring = " Height:" + maxHeight + "(Max " + maxSize.maxHeight + ")";
        }

        String overSizeStr="";

        if(oversize>0)
            ret+= " This package is oversize!"+lengthWarring+breadthWarring+heighthWarring;;





        return ret;
    }
}



class ErrorInput extends PackageType
{
    public ErrorInput()
    {
        super(-1,-1,-1,-1,-1,"ErrorInput");

    }
    public String print()
    {
        return "Input data error!!!!";
    }
}

