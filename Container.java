public class Container implements Comparable<Container> {
    private String id;
    private String shortName;
    private String longName;
    public Container(String id,String shortName,String longName){
        this.id=id;
        this.shortName=shortName;
        this.longName=longName;
    }
    public Container(){

    }
    public String getId(){
        return this.id;
    }
    public String getShortName(){
        return this.shortName;
    }
    public String getLongName(){
        return this.longName;
    }
    public void setId(String id){
        this.id=id;
    }
    public void setShortName(String shortName){
        this.shortName=shortName;
    }
    public void setLongName(String longName){
        this.longName=longName;
    }
    public int compareTo(Container ex){
        if(this.shortName.charAt(9)>ex.getShortName().charAt(9)){
            return 1;
        }
        else if(this.shortName.charAt(9)<ex.getShortName().charAt(9)){
            return -1;
        }
        else{
            return 0;
        }
    }
    public String toString(){
        return "   <CONTAINER UUID=\"" +this.id+">\n"
        +"      <SHORT-NAME>"+this.shortName +"</SHORT-NAME>\n"
        +"      <LONG-NAME>"+ this.longName+"</LONG-NAME>\n"
        +"   </CONTAINER>\n"; 
    }
}
