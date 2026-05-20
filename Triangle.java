public class Triangle extends Shape
{
    private int id;
    private int width;
    private int type = 2;

    public String getSymbol() { return "T"; }

    protected String makeString() {
        return String.valueOf(width); // valueof 이거 String.valueof 하면 width 문자열로 변환.
    }

    protected void unpacking(String[] parts) {
        this.width = java.lang.Integer.parseInt(parts[2]);
    }

    public Triangle(int _id,int _width)
    {
        this.id = _id;
        this.width = _width;
    }

    public void draw()
    {
        for(int i = 0; i < width; i++)
        {
            for(int j=0; j<=i; j++) {
                System.out.print("*");
            }
            System.out.println();
        }
    }

    public int getId(){
        return this.id;
    }

    public void setId(int id){
        this.id = id;
    }

    public int getType(){
        return this.type;
    }

}
